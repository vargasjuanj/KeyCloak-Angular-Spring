import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../model/product';
import { CommonService } from './common.service';


@Injectable({
  providedIn: 'root'
})
export class ProductService extends CommonService<Product>{

  constructor(http: HttpClient) {
    super(http);
    this.baseUrl = "http://localhost:8081/api/v1/ecom/products/";

  }
}
