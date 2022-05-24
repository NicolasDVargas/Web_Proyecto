import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

export class Administrador{

  nombre:string;
  contrasenna:string;

  crear (nombre:string,contra:string){
    this.nombre=nombre;
    this.contrasenna=contra;
  }

  constructor (){ }
}
