import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Administrador } from 'src/app/models/admin.module';
import { AdminsService } from 'src/app/servicios/admins.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  constructor(private router:Router, public _adminsService:AdminsService) { }
  public admin: Administrador =new Administrador();

  ngOnInit(): void {}

  iniciar(){
    this.router.navigateByUrl('./iniciar');
  }
}
