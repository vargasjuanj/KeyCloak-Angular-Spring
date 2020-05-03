import { Component, OnInit, Host, Input, ViewChild, ElementRef } from '@angular/core';
import { ProductComponent } from '../product/product.component';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Product } from 'src/app/model/product';
import { ProductService} from 'src/app/services/product.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  constructor(private productService: ProductService, @Host() private tabla: ProductComponent, private formBuilder: FormBuilder) { }

  @Input() set productActual(valor) {
    this.onBuild();
    if (valor) {
      this.productOrignal = valor;
      this.formProduct.patchValue({
        id: valor.id,
        name: valor.name,
       price: valor.price

      });
      if (valor.id !== 0) {
        this.edit = true;
      } else {
        this.edit = false;
      }
    }
  }

  @ViewChild('btnClose', {static: true}) btnClose: ElementRef;

  public formProduct: FormGroup;
  public productOrignal: any;
  public edit = false;
  public isError = false;

  ngOnInit() {
    this.onBuild();
  }

  onBuild() {
    this.formProduct = this.formBuilder.group({
      id: new FormControl(0),
      name: new FormControl('', Validators.required),
      price: new FormControl('', Validators.required)

    });
  }

  onSave(formProduct: FormGroup): void {
    if (formProduct.invalid) {
      this.isError = true;
    } else {
      if (formProduct.value.id === 0) {
        // Agregar
        console.log("agregar")
        this.add(formProduct.value);
      } else {
        console.log("update")
        this.update(formProduct.value);
      }
      this.btnClose.nativeElement.click();
    }
  }

  add(product: Product) {
    this.productService.post(product).subscribe(
      res => {
        this.tabla.products.push(res);
      },
      err => {
        alert('Ocurrió un error al agregar la product');
      }
    );
  }

  update(product: Product) {
    this.productService.put(product.id, product).subscribe(
      res => {
        alert('Product fue actualizada con éxito');
        const cambio = this.tabla.products.filter( item => item.id !== product.id);
        this.tabla.products = cambio;
        this.tabla.products.unshift(product);
      },
      err => {
        alert('Ocurrió un error al actualizar product');
      }
    );
  }

  onClose() {
    this.productActual = {
      id: 0,
name:'',
price:0
    };
    this.isError = false;
  }

  onCloseAlert() {
    this.isError = false;
  }

}
