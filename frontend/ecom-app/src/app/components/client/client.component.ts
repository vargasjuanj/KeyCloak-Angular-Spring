import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client';
import { ClientService } from 'src/app/services/Client.service';


@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {
  errorMessage: string = null;
  clients: Client[];

  constructor(private clientService: ClientService)  { }

  ngOnInit(): void {
    this.clientService.getAll().subscribe(
      data => {
        console.log(data)
        this.clients = data;
      },
      err => {
        this.errorMessage = err;
        console.log('errorrr ! ', err)
      }
    );

  }
}
