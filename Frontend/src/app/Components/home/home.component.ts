import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { RouteConfigLoadEnd, Router } from '@angular/router';
import { Administrador } from 'src/app/models/admin.module';
import { Dulce } from 'src/app/models/candy.model';
import { cliente } from 'src/app/models/cliente.model';
import { AdminsService } from 'src/app/servicios/admins.service';
import { ClienteService } from 'src/app/servicios/clientes.service';

import { InventarioService } from 'src/app/servicios/inventario.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  public clientes: any = [];
  public inventario: Dulce[] = [];
  public cliente:cliente;
  public admin:Administrador;
  public encontrado: boolean;


  constructor(public _InveService: InventarioService,_clienteService: ClienteService,_adminService: AdminsService, public route :Router, private http: HttpClient) {
    
    var nomUsuario = localStorage.getItem('user');
    for(let cli of this.clientes){
      if(cli.nombre==nomUsuario && cli.admin == false){
        this.cliente = new cliente();
        this.cliente=cli;
        this.encontrado=true;
      }
    }

    if(!this.encontrado){
      for(let adm of this.clientes){
        if(adm.nombre==nomUsuario && adm.admin == true){
          this.admin = new Administrador();
          this.admin=adm;
          this.encontrado=true;
        }
      }
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
    this.http.get('http://localhost:8080/Cliente').subscribe(resp => {this.clientes = resp;})
  }

  agregar(compra:Dulce){
    this._InveService.buscar=compra;
    this.route.navigateByUrl("./DetalleDulce")
  }
}
