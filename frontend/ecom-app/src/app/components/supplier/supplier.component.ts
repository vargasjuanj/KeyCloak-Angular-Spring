import { Component, OnInit } from '@angular/core';
import { SupplierService } from 'src/app/services/supplier.service';
import { Supplier } from 'src/app/model/supplier';



@Component({
  selector: 'app-supplier',
  templateUrl: './supplier.component.html',
  styleUrls: ['./supplier.component.scss']
})
export class SupplierComponent implements OnInit {
  errorMessage: string = null;
  suppliers: Supplier[];

  constructor(private supplierService: SupplierService)  { }

  ngOnInit(): void {
    this.supplierService.getAll().subscribe(
      data => {
        console.log(data)
        this.suppliers = data;
      },
      err => {
        this.errorMessage = err;
        console.log('errorrr ! ', err)
      }
    );

  }

}
