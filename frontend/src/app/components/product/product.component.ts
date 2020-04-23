import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/model/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  errorMessage: string = null;
  products: Product[] 

  constructor(private productService :ProductService) { }
  ngOnInit(): void {
  this.productService.getAll().subscribe(
      data => {
        console.log(data)
        this.products = data;
      },
      err => {
        this.errorMessage = err;
        console.log('errorrr ! ', err)
      }
    );

  }


}
