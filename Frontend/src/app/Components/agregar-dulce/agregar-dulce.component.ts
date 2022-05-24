import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Dulce } from 'src/app/models/candy.model';
import { InventarioService } from 'src/app/servicios/inventario.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-agregar-dulce',
  templateUrl: './agregar-dulce.component.html',
  styleUrls: ['./agregar-dulce.component.css']
})
export class AgregarDulceComponent implements OnInit {

  public dulce: Dulce = new Dulce();

  constructor(public _inventService: InventarioService, public router: Router) { }

  ngOnInit(): void {
  }



  registrar() {
    if (this.dulce.nombre == null) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'No se ingreso el nombre',
      })
    } else {
      if (this.dulce.cantidad == null) {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'No se ingreso la cantidad!',
        })
      } else {
        if (this.dulce.costo == null) {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'No se ingreso el costo!',
          })
        } else {
          if (this.dulce.tipo == null) {
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'No se ingreso la categoria!',
            })
          } else {
            this._inventService.agregar(this.dulce, false);
            this.dulce = new Dulce;
            this.router.navigateByUrl('./inventario');

          }
        }
      }
    }
  }
  cancelar() {
    this.router.navigateByUrl('./inventario');
  }

}
