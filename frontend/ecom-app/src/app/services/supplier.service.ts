import { Injectable } from '@angular/core';
import { CommonService } from './common.service';
import { Supplier } from '../model/supplier';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class SupplierService extends CommonService<Supplier>{


  constructor(http: HttpClient) {
    super(http);
    this.baseUrl = "http://localhost:8082/api/v1/ecom/suppliers/";

  }


 
}
