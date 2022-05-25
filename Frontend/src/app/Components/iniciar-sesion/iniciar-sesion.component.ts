import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Administrador } from 'src/app/models/admin.module';
import { cliente } from 'src/app/models/cliente.model';
import { AdminsService } from 'src/app/servicios/admins.service';
import { HttpClient } from '@angular/common/http';
import { ClienteService } from 'src/app/servicios/clientes.service';
import Swal from 'sweetalert2';
import jwt_decode from 'jwt-decode';


@Component({
  selector: 'app-iniciar-sesion',
  templateUrl: './iniciar-sesion.component.html',
  styleUrls: ['./iniciar-sesion.component.css']

})

export class IniciarSesionComponent implements OnInit {


  private _url = "http://localhost:8080/";
  public clientes: any = [];
  public Cliente: cliente = new cliente();
  public Admin: Administrador = new Administrador();
  public encontrado: boolean = false;

  constructor(public _clienteService: ClienteService, public router: Router, public _adminsService: AdminsService, private http: HttpClient) { }

  ngOnInit() {
  }

  async loginUser(dataUser: any) {
    try {
      let loginR: any = await this.http.post(this._url + "login", dataUser, {responseType: 'text'}).toPromise()
      let user: any = jwt_decode(loginR);
      localStorage.setItem('user', loginR);
      this._clienteService.actual = user;
      Swal.fire('Bienvenido!', 'Has iniciado sesión correctamente.', 'success')
      this.router.navigateByUrl('./home');
    } catch (e) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Parece que ese usuario o contraseños son incorrectos',
      })
    }
  }

  buscar(correo: string, contra: string) {
    if (correo == "" || correo == null) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Olvidaste llenar el nombre',
      })
    } else {
      if (contra == "") {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'olvidaste llenar la contraseña',
        })
      } else {
        
        var dataUser = {"email":correo, "contrasenna":contra};
        this.loginUser(dataUser);
      }
    }


  }

  cancelar() {
    this.router.navigateByUrl('');
    localStorage.clear();
  }

  registrar(Cliente: cliente) {
    var yaExiste: boolean = false;
    if (Cliente.nombre == null || Cliente.nombre == "") {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'No se ha ingresado nombre!',
      })
    } else {
      if (Cliente.contrasenna == null || Cliente.contrasenna == "") {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'No se ha ingresado una contraseña valida!',
        })
      } else {
        if (Cliente.email == null || Cliente.contrasenna == "") {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'No se ha ingresado un email valida!',
          })
        } else {
          for (let cli of this.clientes) {
            if (cli.nombre == Cliente.nombre) {
              Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Ese ya existe!',
              })
              yaExiste = true;

            }
          }
          for (let adm of this.clientes) {
            if (adm.nombre == Cliente.nombre && adm.contrasenna == Cliente.contrasenna) {
              Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Ese ya existe!',
              })
              yaExiste = true;
            } else {
              for (let adm of this._adminsService.administradores) {
                if (adm.nombre == Cliente.nombre && adm.contrasenna == Cliente.contrasenna) {
                  Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Ese ya existe!',
                  })
                  yaExiste = true;
                }
              }
            }
          }
          if (!yaExiste) {
            this.Cliente.generarFactura();
            this._clienteService.agregar(this.Cliente);
            localStorage.setItem('user', this.Cliente.nombre);
            Swal.fire('Bienvenido ' + Cliente.nombre, 'El registro a sido exitoso', 'success')
            this._clienteService.actual = Cliente;
            this.Cliente = new cliente();
            this.router.navigateByUrl('./home');
          }
        }
      }
    }
  }
}
