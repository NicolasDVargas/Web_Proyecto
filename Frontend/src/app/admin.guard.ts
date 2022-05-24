import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { Administrador } from './models/admin.module';
import { AdminsService } from './servicios/admins.service';


@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {
  public _adminService: AdminsService;
  public encontrado: boolean;

  canActivate() {
    var usuario = localStorage.getItem('user');
    var personas: Administrador []= this._adminService.administradores

    if (usuario == null) {
      alert("Debe registrarse para tener acceso a esta parte de la tienda")
      return false;
    } else {
      for (let usu of personas) {
        if (usu.nombre == usuario) {       
          return true;
        }
      }
    }
    alert("usted no tiene permiso de entrar a esto, de hecho no se como lo puede ver");
    return false;
  }
}