import { Injectable } from '@angular/core';
import { CommonService } from './common.service';
import { Client } from '../model/client';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class ClientService extends CommonService<Client>{


  constructor(http: HttpClient) {
    super(http);
    this.baseUrl = "http://localhost:8081/api/v1/ecom/clients/";

  }
  

 
}
