import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { cliente } from 'src/app/models/cliente.model';
import { ClienteService } from 'src/app/servicios/clientes.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {

  public usu:cliente;

  constructor(public router:Router,public _clienteService: ClienteService) { 
    debugger;
    this.usu = _clienteService.actual;
  }

  ngOnInit(): void {
  }

  verFacturas(){
    this.router.navigateByUrl("./verFacts")
  }
  
}
