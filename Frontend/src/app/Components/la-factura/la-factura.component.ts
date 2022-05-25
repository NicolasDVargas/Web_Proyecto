import { HttpClient } from '@angular/common/http';
import { Component, Directive, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { cliente } from 'src/app/models/cliente.model';
import { Facturas } from 'src/app/models/facturas.module';
import { ClienteService } from 'src/app/servicios/clientes.service';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-la-factura',
  templateUrl: './la-factura.component.html',
  styleUrls: ['./la-factura.component.css'],
  
})
export class LaFacturaComponent implements OnInit {
  public factura:any;
  public id:number;
  constructor(public rout:Router,public _clienteService: ClienteService, private http: HttpClient) {
   }

  ngOnInit(): void {
    this.buscarFactura()
  }

  async buscarFactura() {
    var idFactura = localStorage.getItem('id')!;
    this.id = parseInt(idFactura);
    let _urlFactura = "http://localhost:8080/Compra/Buscar/id?id=" + this.id;
    let facturaR:any = await this.http.get(_urlFactura).toPromise()
    this.recibirFactura(facturaR)
  }

  recibirFactura(facturaR:Facturas){
    this.factura = facturaR;
    this.calcularTotal()
  }

  volver(){
    this.rout.navigateByUrl("./verFacts")
  }

  calcularTotal(): void{
    var cont = 0;
    for(let dulce of this.factura.pedido){
      cont += dulce?.costo;
    }
    this.factura.total = cont
  }
}
