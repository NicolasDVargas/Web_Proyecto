import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DefaultValueAccessor } from '@angular/forms';
import { Router } from '@angular/router';
import { Dulce } from 'src/app/models/candy.model';
import { cliente, elemento } from 'src/app/models/cliente.model';
import { ClienteService } from 'src/app/servicios/clientes.service';
import { InventarioService } from 'src/app/servicios/inventario.service';
import Swal from 'sweetalert2';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css']
})
export class CarritoComponent implements OnInit {

  public user:cliente;
  public carrito:any;
  public dulce:elemento = new elemento();
  public total:number;

  constructor(public _clienteService: ClienteService, public route: Router, public _inventService:InventarioService, private http: HttpClient) { 
  }

  async findCarrito() {
    let token: any = localStorage.getItem('user');
    let decodedUser: any = jwt_decode(token);
    var _urlUser = "http://localhost:8080/Cliente/Buscar/email?email=" + decodedUser.sub;
    this.user = await (await this.http.get(_urlUser).toPromise()) as cliente
    var _urlCarrito = "http://localhost:8080/Cliente/Buscar/Carrito?id=" + this.user.id;
    this.http.get(_urlCarrito).toPromise().then((carrito) => {
      this.carrito = carrito
      this.total = this.calcularTotal();
    })
  }

  ngOnInit(): void {
    this.findCarrito();
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
        this.user.elimiar(dulce.id);
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
      let _urlBuy = "http://localhost:8080/Compra/Ingresar?id=" + this.user.id;
      this.http.put(_urlBuy, this.carrito).subscribe(data => {console.log("ok")});
      Swal.fire(
      'Listo!',
      'Disfruta de tus dulces :D.',
      'success'
    )
    this.route.navigateByUrl("./home")
    }
  }

  moverInvent(){
    for(let dulce of this.carrito?.pedido){
      for(let dulceI of this._inventService.Disponible) {
        if(dulce.id == dulceI.id){
          dulceI.cantidad--;
          dulceI.vendido++;
        }
      }
    }
  }

  calcularTotal(): number {
    var cont = 0;
    console.log(this.carrito)
    for(let dulce of this.carrito?.pedido){
      cont += dulce?.costo;
    }
    return cont;
  }

  home(){
    this.route.navigateByUrl("./home")
  }
}



