import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { cliente } from 'src/app/models/cliente.model';
import { Facturas } from 'src/app/models/facturas.module';
import { ClienteService } from 'src/app/servicios/clientes.service';
import jwt_decode from 'jwt-decode';
import { LaFacturaComponent } from '../la-factura/la-factura.component';

@Component({
  selector: 'app-ver-facturas',
  templateUrl: './ver-facturas.component.html',
  styleUrls: ['./ver-facturas.component.css'],
})
export class VerFacturasComponent implements OnInit {
  public user:cliente
  public facturas:any
  constructor(public router:Router,public _clienteService: ClienteService, private http: HttpClient) {
  }

  ngOnInit(): void {
    this.getFacturas()
  }

  async getFacturas() {
    let token: any = localStorage.getItem('user');
    let decodedUser: any = jwt_decode(token);
    var _urlUser = "http://localhost:8080/Cliente/Buscar/email?email=" + decodedUser.sub;
    this.user = await (await this.http.get(_urlUser).toPromise()) as cliente
    var _urlFacturas = "http://localhost:8080/Compra/Buscar/duenno?id=" + this.user.id;
    this.http.get(_urlFacturas).toPromise().then((facturas) => {
      this.facturas = facturas
      this.calcularTotal()
    })
  }


  perfil(){
    this.router.navigateByUrl("./perfil")
  }

  elegir(factura:Facturas){
    localStorage.setItem('id',factura.id.toString());
    this.router.navigateByUrl("./laFactura");
  }

  calcularTotal(): void{
    var cont = 0;
    for (let factura of this.facturas) {
      for(let dulce of factura.pedido){
        cont += dulce?.costo;
        factura.total = cont
      }
      cont = 0;
    }
  }
}