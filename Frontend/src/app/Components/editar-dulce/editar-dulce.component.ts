import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Dulce } from 'src/app/models/candy.model';
import { InventarioService } from 'src/app/servicios/inventario.service';

@Component({
  selector: 'app-editar-dulce',
  templateUrl: './editar-dulce.component.html',
  styleUrls: ['./editar-dulce.component.css']
})
export class EditarDulceComponent implements OnInit {

  public dulce:Dulce = new Dulce();
  constructor(public _inventService:InventarioService, public router:Router) { }

  ngOnInit(): void {
  }

  Editar(){
    if(this.dulce.id == null) {
      this.dulce.id=this._inventService.buscar.id;
    }
    if(this.dulce.nombre==null){
      this.dulce.nombre=this._inventService.buscar.nombre;
    }
    if(this.dulce.cantidad==null){
      this.dulce.cantidad=this._inventService.buscar.cantidad;
    }
    if(this.dulce.costo==null){
      this.dulce.costo=this._inventService.buscar.costo;
    }
    if(this.dulce.tipo==null){
      this.dulce.tipo=this._inventService.buscar.tipo;
    }
    if(this.dulce.vendido==null){
      this.dulce.vendido=this._inventService.buscar.vendido;
    }
    if(this.dulce.imagen==null){
      this.dulce.imagen=this._inventService.buscar.imagen;
    }
    this._inventService.editar(this.dulce);
    this._inventService.buscar=this.dulce;
    this._inventService.putDulce(this.dulce);
    this.router.navigateByUrl('./inventario')
  }

  cancelar(){
    this.router.navigateByUrl('./inventario');
  }
}
