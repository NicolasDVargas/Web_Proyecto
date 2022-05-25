import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminsService } from 'src/app/servicios/admins.service';
import { ClienteService } from 'src/app/servicios/clientes.service';
import { HttpClient } from '@angular/common/http';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public clientes: any = [];

  constructor(public router: Router, public _adminService: AdminsService, public _clienteService:ClienteService, private http: HttpClient) { }

  ngOnInit() {
    
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
    let token: any = localStorage.getItem('user');
    if (token != null) {
      let decodedUser: any = jwt_decode(token);
      if (decodedUser.rol == "ROLE_ADMIN") {
        return true;
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
    let token: any = localStorage.getItem('user');
    if (token != null) {
      let decodedUser: any = jwt_decode(token);
      if (decodedUser.rol == "ROLE_USER") {
        return true;
      }
    }
    return false;
  }

  Inicio(){
    localStorage.clear();
    this.router.navigateByUrl('./iniciar');
  }

}
