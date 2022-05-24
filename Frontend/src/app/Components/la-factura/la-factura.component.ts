import { Component, Directive, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Facturas } from 'src/app/models/facturas.module';
import { ClienteService } from 'src/app/servicios/clientes.service';

@Component({
  selector: 'app-la-factura',
  templateUrl: './la-factura.component.html',
  styleUrls: ['./la-factura.component.css'],
  
})
export class LaFacturaComponent implements OnInit {

  public factura:Facturas ;
  public id:number;
  constructor(public rout:Router,public _clienteService: ClienteService) {
    debugger;
    var algo=localStorage.getItem('id')!;
    this.id=parseInt(algo);
    for(let facturaI of _clienteService.actual.Facturas){
      if(facturaI.id==this.id){
        this.factura=facturaI;
      }
    }
   }

  ngOnInit(): void {
  }

  recibirFactura(facturaR:Facturas){
    this.factura=facturaR;
    alert("zapato");
  }

  volver(){
    this.rout.navigateByUrl("./verFacts")
  }
}
