import { DefaultValueAccessor } from "@angular/forms";
import { Dulce } from "./candy.model";
import { Facturas } from "./facturas.module";

export class elemento {
  id:number;
  cosa:Dulce;

  constructor (id?:number,thing?:Dulce){
    if(id){
      this.id=id;
    }
    if(thing){
      this.cosa=thing;
    }
  }
}

export class subCliente {
  nombre:string;
  contrasenna:string;
  email:string

  constructor (nombre?:string,contrasenna?:string,email?:string){
    if(nombre){
      this.nombre=nombre;
    }
    if(contrasenna){
      this.contrasenna=contrasenna;
    }
    if(email){
      this.email=email;
    }
  }
}

export class cliente {

  id:number;
  nombre:string;
  contrasenna:string;
  email:string;
  numFact: number = 0;
  numElemt: number = 0;
  carrito: elemento[] = [];
  Facturas: Facturas[] =[];
  factura:Facturas;
  dulce:elemento;
  admin:boolean;

  constructor (){
    this.generarFactura();
  }

  public agregar(articulo : Dulce){
    this.numElemt++;
    this.dulce = new elemento(this.numElemt,articulo);
    this.carrito.push(this.dulce)
  }

  public obtener(){
    return this.carrito;
  }

  public generarFactura(){
    this.factura = new Facturas(this.nombre,this.numFact);
    this.numFact++;
  }

  public agregarFactura(dulce:Dulce){
    var cosa:elemento=new elemento(this.numElemt,dulce);
    this.factura.agregar(cosa);
  }

  public elimiar(id:number){
    this.carrito.forEach((elemt,index)=>{
      if(elemt.id==id) this.carrito.splice(index,1);
    });
    this.factura.pedido.forEach((elemt,index)=>{
      if(elemt.id==id) this.factura.pedido.splice(index,1);
    });
  }

  public facturar(total:number){
    this.factura.setTotal(total);
    this.Facturas.push(this.factura);
    this.generarFactura();
    this.carrito=[];
  }
}

