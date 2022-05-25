import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';
import { Dulce } from '../models/candy.model';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, UnsubscriptionError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class InventarioService {

  public Disponible:any [];
  public buscar: Dulce;

  ngOnInit() {
    
  }

  constructor(private http: HttpClient) {
    this.http.get("http://localhost:8080/Dulce").subscribe((respC: any) => {this.Disponible = respC})
   }


  public putDulce(actualizacion: Dulce){
    let _urlEdit = "http://localhost:8080/Dulce/editar?id=" + actualizacion.id;
    this.http.put(_urlEdit, actualizacion).subscribe(data => {console.log("ok")});
  }

  public agregar(articulo : Dulce){
    this.http.put("http://localhost:8080/Dulce/Ingresar", articulo).subscribe(data => {console.log("ok")});
    Swal.fire('Agregado', 'El dulce se a agregado correctamente!')
  }

  public obtener(){
    return this.Disponible;
  }

  public elimiar(deleteCandy: Dulce){
    let _urlDelete = "http://localhost:8080/Dulce/eliminar/id?id=" + deleteCandy.id;
    this.http.delete(_urlDelete).subscribe((Response) => {console.log("borradisimo")})
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
