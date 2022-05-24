import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { cliente } from 'src/app/models/cliente.model';
import { Facturas } from 'src/app/models/facturas.module';
import { ClienteService } from 'src/app/servicios/clientes.service';
import { LaFacturaComponent } from '../la-factura/la-factura.component';

@Component({
  selector: 'app-ver-facturas',
  templateUrl: './ver-facturas.component.html',
  styleUrls: ['./ver-facturas.component.css'],
})
export class VerFacturasComponent implements OnInit {
  public factura : Facturas ;
  public Usuario : cliente = new cliente;
  constructor(public router:Router,public _clienteService: ClienteService) {
    this.Usuario=_clienteService.actual;
    this.factura=_clienteService.actual.factura;
  }

  ngOnInit(): void {
  }

  perfil(){
    this.router.navigateByUrl("./perfil")
  }

  elegir(factura:Facturas){
    localStorage.setItem('id',factura.id.toString());
    this.router.navigateByUrl("./laFactura");
  }
}