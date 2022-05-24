import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Administrador } from 'src/app/models/admin.module';
import { Dulce } from 'src/app/models/candy.model';
import { cliente } from 'src/app/models/cliente.model';
import { AdminsService } from 'src/app/servicios/admins.service';
import { ClienteService } from 'src/app/servicios/clientes.service';
import { InventarioService } from 'src/app/servicios/inventario.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-detalle-dulce',
  templateUrl: './detalle-dulce.component.html',
  styleUrls: ['./detalle-dulce.component.css']
})
export class DetalleDulceComponent implements OnInit {

  public dulce: Dulce = new Dulce;
  public Cliente: cliente;
  public admin: Administrador;
  public cant: number;

  constructor(public _inventService: InventarioService, public route: Router, public _clienteService: ClienteService, public _adminsService: AdminsService) {
    this.dulce = _inventService.buscar;
    this.Cliente = _clienteService.actual;
    this.admin = _adminsService.admin;
  }

  ngOnInit(): void {
    
  }

  volverHome() {
    this.route.navigateByUrl("./home");
  }

  agregarCarrito() {
    var nomUsuario = localStorage.getItem('user');
    if (nomUsuario != null) {
      this._clienteService.actual.agregar(this._inventService.buscar);     
      this._clienteService.actual.agregarFactura(this._inventService.buscar);
      
      Swal.fire('Agregado!','Se ha agregado a tu carrito.','success')

    } else {
      Swal.fire('Debes estar logeado para poder agregar cosas a tu carrito');
    }

  }

  editar() {
    this.route.navigateByUrl("./EditarDulce");
  }


  eliminar() {
    Swal.fire({
      title: 'Esta seguro que desea borrar este elemento de la base de datos?',
      text: 'Una vez eliminado no se podra recuperar la información!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Borrar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.value) {
        this._inventService.elimiar();
        Swal.fire(
          'Borrado!',
          'Se ha elimiado correctamente el dulce de la base de datos.',
          'success'
        )
        this.route.navigateByUrl("./inventario");
      }
    })
  }
}
