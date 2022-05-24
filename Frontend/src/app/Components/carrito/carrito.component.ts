import { Component, OnInit } from '@angular/core';
import { DefaultValueAccessor } from '@angular/forms';
import { Router } from '@angular/router';
import { Dulce } from 'src/app/models/candy.model';
import { elemento } from 'src/app/models/cliente.model';
import { ClienteService } from 'src/app/servicios/clientes.service';
import { InventarioService } from 'src/app/servicios/inventario.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css']
})
export class CarritoComponent implements OnInit {

  public carrito:elemento[];
  public dulce:elemento = new elemento();
  public total:number;

  constructor(public _clienteService: ClienteService, public route: Router, public _inventService:InventarioService) { 
    this.carrito=_clienteService.actual.carrito;
    this.total=this.calcularTotal();
  }

  ngOnInit(): void {
  }

  eliminar(dulce:elemento){
    Swal.fire({
      title: 'Esta seguro que deseas borrar este dulce de tu carrito?',
      text: 'Piensatelo 2 veces ;)',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Borrar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.value) {
        this._clienteService.actual.elimiar(dulce.id);
        this.total=this.calcularTotal();
        Swal.fire(
          'Borrado!',
          'Se ha elimiado correctamente el dulce de tu carrito.',
          'success'
        )
      }
    })
  }

  comprar(){
    if(this.calcularTotal() == 0) {
      Swal.fire(
        'Oops...',
        'Tu carrito esta vacio :(, Llenalo de dulces y vuelve a intentarlo!',
        'error'
      )
    }
    else {
      this.moverInvent();
      this._clienteService.actual.facturar(this.total);
      Swal.fire(
      'Listo!',
      'Disfruta de tus dulces :D.',
      'success'
    )
    this.route.navigateByUrl("./home")
    }
  }

  moverInvent(){
    for(let dulce of this._clienteService.actual.carrito){
      for(let dulceI of this._inventService.Disponible){
        if(dulce.cosa.nombre==dulceI.nombre){
          dulceI.cantidad--;
          dulceI.vendido++;
        }
      }
    }
  }

  calcularTotal(): number {
    var cont = 0;
    for(let dulce of this._clienteService.actual.carrito){
      cont=cont+dulce.cosa.costo;
    }
    return cont;
  }

  home(){
    this.route.navigateByUrl("./home")
  }
}



