import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';
import { Dulce } from '../models/candy.model';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, UnsubscriptionError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class InventarioService {

  public Disponible: any = [];
  public buscar: Dulce;

  ngOnInit() {
    
  }

  constructor(private http: HttpClient) {
    let dulces: Dulce[] = [];
    this.http.get("http://localhost:8080/Dulce").subscribe((resp: any) => {this.Disponible = resp})
    debugger;
   }

  public putDulce(actualizacion: Dulce){
    let url = "http://localhost:8080/Dulce/editar?id=" + actualizacion.id;
    this.http.put(url, actualizacion).subscribe(data => {console.log("ok")});
  }

  public agregar(articulo : Dulce,primeraVez:boolean){
    this.Disponible.push(articulo)
    if(!primeraVez){
      Swal.fire('Agregado', 'El dulce se a agregado correctamente!')
    }
  }

  public obtener(){
    return this.Disponible;
  }

  public elimiar(){
    
  }

  public editar(actualizacion:Dulce){
    debugger;
    for (let dulce of this.Disponible) {
      if(dulce.id == actualizacion.id) {
        this.Disponible.splice(actualizacion.id - 1, 1, actualizacion);
      }
    }
  }

}
