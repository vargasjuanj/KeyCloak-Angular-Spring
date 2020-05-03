import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/model/product';
import { ProductService } from 'src/app/services/product.service';
import { KeycloakSecurityService } from 'src/app/services/keycloak-security.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  errorMessage: string = null;
  products: Product[] 
  public productActual: Product = {
    id:0,
    name:'',
    price:0
  };
  product:Product={
    id:0,
    name:"nuevo",
    price:100
  }
  constructor(private keycloakService:KeycloakSecurityService  ,private productService :ProductService) { }
  ngOnInit(): void {
      this.getAll()
  }
getAll(){
  
  this.productService.getAll().subscribe(
   
    data => {
      console.log("getAll()  - only rol admin")
        console.log(data)
        this.products = data;
      },
      err => {
        this.errorMessage = err;
        console.log('errorrr ! ', err)
      }
    );
}


save(){
  this.productService.post(this.product).subscribe(
    data => {
      console.log(data)
    },
    err => {

     
      console.log('errorrr ! ', err)
    }
  );
}
delete(product:Product){
  const opcion = confirm('¿Desea eliminar este registro?');
  if (opcion === true) {
    this.productService.delete(product.id).subscribe(
      res => {
        alert('El registro fue eliminado con éxito');
        const indexProduct = this.products.indexOf(product);
        this.products.splice(indexProduct, 1);
      }
    );
  }
}
onPreUpdate(product:Product){
  this.productActual = product;
}
  isAdmin() {
    return this.keycloakService.keycloak.hasRealmRole('admin')
  }

}
