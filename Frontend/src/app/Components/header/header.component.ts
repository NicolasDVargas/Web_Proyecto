import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminsService } from 'src/app/servicios/admins.service';
import { ClienteService } from 'src/app/servicios/clientes.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public clientes: any = [];

  constructor(public router: Router, public _adminService: AdminsService, public _clienteService:ClienteService, private http: HttpClient) { }

  ngOnInit() {
    this.http.get("http://localhost:8080/Cliente").subscribe((resp: any) => {this.clientes = resp})
  }

  home() {
    this.router.navigateByUrl('./home')
  }
  inventario() {
    this.router.navigateByUrl('./inventario')
  }
  cerrarSesion() {
    localStorage.clear();
    this._adminService.limpiar();
    this._clienteService.limpiar();
    this.router.navigateByUrl('');
  }

  revizarAdmin(): boolean {
    var nomUsuario = localStorage.getItem('user');
    if (nomUsuario != null) {
      for (let adm of this.clientes) {
        if (adm.nombre == nomUsuario && adm.admin == true) {
          return true;
        }
      }
    }
    return false;
  }

  carrito(){
    this.router.navigateByUrl("./carrito")
  }

  perfil(){
    this.router.navigateByUrl("./perfil")
  }


  revizar(): boolean {
    var nomUsuario = localStorage.getItem('user');
    if (nomUsuario != null) {
      for (let usu of this.clientes) {
        if (usu.nombre == nomUsuario && usu.admin == false) {
          return true;
        }
      }
    }
    return false;
  }

  Inicio(){
    localStorage.clear();
    this.router.navigateByUrl('./iniciar');
  }

}
