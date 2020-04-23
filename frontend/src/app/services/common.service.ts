import { Injectable } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommonService <E>{

  constructor(public http : HttpClient) { }

  protected baseUrl: string;  
  protected busca: string;

  getAll(): Observable<E[]> {
    return this.http.get<E[]>(this.baseUrl);
  }

  getOne(id: number): Observable<E> {
    return this.http.get<E>(this.baseUrl + id);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + id);
  }

  post(entity: E): Observable<E> {
    return this.http.post<E>(this.baseUrl, entity);
  }

  put(id: number, entity: E) {
    return this.http.put<E>(this.baseUrl + id, entity);
  }

}
