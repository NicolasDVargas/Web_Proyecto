import { Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Dulce } from 'src/app/models/candy.model';
import { InventarioService } from 'src/app/servicios/inventario.service';
import { HttpClient } from '@angular/common/http';
import { EventEmitter } from '@angular/core';


@Component({
  selector: 'app-inventario',
  templateUrl: './inventario.component.html',
  styleUrls: ['./inventario.component.css']
})
export class InventarioComponent implements OnInit {

  public inventario: Dulce[] = [];
  public nombreEleg:string;
  public total:number=0;

  ngOnInit() {
    this.http.get("http://localhost:8080/Dulce").subscribe((resp: any) => {this.inventario = resp})
  }

  constructor(public _inventarioService: InventarioService, public route:Router, private http: HttpClient) { 
    this.agregarTotal();
  }

  detalles(dulce:Dulce){
    this._inventarioService.buscar=dulce;
    this.route.navigateByUrl("./DetalleDulce");
  }
  agregarNuevo(){
    this.route.navigateByUrl("./nuevoDulce");
  }

  agregarTotal(){
    for(let dulce of this.inventario){
      this.total=this.total+(dulce.vendido*dulce.costo);
    }
  }

  home(){
    this.route.navigateByUrl('./home');
  }

}
