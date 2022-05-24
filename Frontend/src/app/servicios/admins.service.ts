import { Injectable } from '@angular/core';
import { Administrador } from '../models/admin.module';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class AdminsService {

  public administradores: Administrador[] = [];
  public clientes: any[];
  public admin: Administrador;
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get('http://localhost:8080/Cliente').subscribe((resp: any) => {this.clientes = resp})
    for(let usu of this.clientes) {
      if(usu.admin == true) {
        this.administradores.push(usu)
      }
    }
  }

  public agregar(nombre: string, contra: string){
    this.admin=new Administrador();
    this.admin.crear(nombre,contra);
    this.administradores.push(this.admin);
    this.admin=new Administrador;
  }

  public obtener(){
    return this.administradores;
  }

  public limpiar(){
    this.admin=new Administrador;
  }
}
