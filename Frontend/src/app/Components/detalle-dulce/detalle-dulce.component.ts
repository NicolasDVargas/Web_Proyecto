import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Administrador } from 'src/app/models/admin.module';
import { Dulce } from 'src/app/models/candy.model';
import { cliente, elemento } from 'src/app/models/cliente.model';
import { AdminsService } from 'src/app/servicios/admins.service';
import { ClienteService } from 'src/app/servicios/clientes.service';
import { InventarioService } from 'src/app/servicios/inventario.service';
import Swal from 'sweetalert2';
import jwt_decode from 'jwt-decode';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-detalle-dulce',
  templateUrl: './detalle-dulce.component.html',
  styleUrls: ['./detalle-dulce.component.css']
})
export class DetalleDulceComponent implements OnInit {

  public user: cliente
  public carrito: any
  public dulce: Dulce = new Dulce;
  public cant: number;

  constructor(public _inventService: InventarioService, public route: Router, public _clienteService: ClienteService, public _adminsService: AdminsService, private http: HttpClient) {
    this.dulce = _inventService.buscar;
  }

  ngOnInit(): void {
    this.findCarrito();
  }

  volverHome() {
    this.route.navigateByUrl("./home");
  }

  async findCarrito() {
    let token: any = localStorage.getItem('user');
    let decodedUser: any = jwt_decode(token);
    var _urlUser = "http://localhost:8080/Cliente/Buscar/email?email=" + decodedUser.sub;
    this.user = await (await this.http.get(_urlUser).toPromise()) as cliente
    var _urlCarrito = "http://localhost:8080/Cliente/Buscar/Carrito?id=" + this.user.id;
    this.http.get(_urlCarrito).toPromise().then((carrito) => {
      this.carrito = carrito
      this.calcularTotal();
    })
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

  agregarCarrito() {
    if (this.user != null) {

      let newCarrito: Dulce[] = [];

      for (let elemt of this.carrito.pedido) {
        newCarrito.push(elemt);    
      }

      newCarrito.push(this.dulce)

      var _urlCarritoCambio = "http://localhost:8080/Cliente/Editar/Carrito?id=" + this.user.id
      let funciono = this.http.put(_urlCarritoCambio, newCarrito).subscribe(data => { console.log("ok") })

      if (funciono) {
        Swal.fire('Agregado!', 'Se ha agregado a tu carrito.', 'success')
        this.findCarrito()

      } else {
        Swal.fire(
          'No agregado!',
          'No sa ha agregado correctamente el dulce a tu carrito.',
          'warning')
      }
     

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
        this._inventService.elimiar(this._inventService.buscar);
        Swal.fire(
          'Borrado!',
          'Se ha elimiado correctamente el dulce de la base de datos.',
          'success'
        )
        this.route.navigateByUrl("./inventario");
      }
    })
  }

  calcularTotal(): void {
    var cont = 0;
    console.log(this.carrito)
    for (let dulce of this.carrito?.pedido) {
      cont += dulce?.costo;
    }
    this.carrito.total = cont
  }
}
