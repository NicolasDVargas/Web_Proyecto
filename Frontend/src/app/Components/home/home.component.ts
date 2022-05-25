import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { RouteConfigLoadEnd, Router } from '@angular/router';
import { Administrador } from 'src/app/models/admin.module';
import { Dulce } from 'src/app/models/candy.model';
import { cliente } from 'src/app/models/cliente.model';
import { AdminsService } from 'src/app/servicios/admins.service';
import { ClienteService } from 'src/app/servicios/clientes.service';
import jwt_decode from 'jwt-decode';

import { InventarioService } from 'src/app/servicios/inventario.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  public inventario: Dulce[] = [];
  public user:cliente


  constructor(public _InveService: InventarioService,_clienteService: ClienteService,_adminService: AdminsService, public route :Router, private http: HttpClient) {
  }

  async findUser() {
    let token: any = localStorage.getItem('user');
    if(token != null) {
      let decodedUser: any = jwt_decode(token);
      var _url = "http://localhost:8080/Cliente/Buscar/email?email=" + decodedUser.sub;
      this.user = await (await this.http.get(_url).toPromise()) as cliente
    }
  }

  public listProductTable(){
    let productos: Dulce[][] = []
    for(let i = 0; i <= this.inventario.length / 3; i++) {
      productos.push([])
      for(let j = i * 3; j < (i * 3) + 3 && j < this.inventario.length; j++) {
        productos[i].push(this.inventario[j])
      }
    }
    return productos
  }

  ngOnInit() {
    this.http.get("http://localhost:8080/Dulce").subscribe((resp: any) => {this.inventario = resp})
  }

  agregar(compra:Dulce){
    this._InveService.buscar=compra;
    this.route.navigateByUrl("./DetalleDulce")
  }
}
