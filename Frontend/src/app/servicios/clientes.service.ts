import { Injectable } from '@angular/core';
import { cliente } from '../models/cliente.model';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  public vertedero: any = [];
  public actual: cliente;
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get('http://localhost:8080/Cliente').subscribe((resp: any) => {this.vertedero = resp})
    debugger;
  }

  public agregar(Usuario : cliente){
    this.vertedero.push(Usuario)
  }

  public obtener(){
    return this.vertedero;
  }


  public limpiar(){
    this.actual=new cliente();
  }
}
