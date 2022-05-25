import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { cliente } from 'src/app/models/cliente.model';
import { ClienteService } from 'src/app/servicios/clientes.service';
import jwt_decode from 'jwt-decode';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {

  public usu:cliente;

  constructor(public router:Router,public _clienteService: ClienteService, private http: HttpClient) { 
    
  }

  async findUser() {
    let token: any = localStorage.getItem('user');
    let decodedUser: any = jwt_decode(token);
    var _url = "http://localhost:8080/Cliente/Buscar/email?email=" + decodedUser.sub;
    this.usu = await (await this.http.get(_url).toPromise()) as cliente
  }

  ngOnInit(): void {
    this.findUser();
  }

  verFacturas(){
    this.router.navigateByUrl("./verFacts")
  }
  
}
