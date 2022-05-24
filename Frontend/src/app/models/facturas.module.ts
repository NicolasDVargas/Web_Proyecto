import { Dulce } from "./candy.model";
import { elemento } from "./cliente.model";

export class Facturas {

    id:number;
    persona: String;
    pedido:elemento[]=[];
    total:number;


    constructor (nombre:String,num:number){
        this.persona=nombre;
        this.id= num;
    }

    agregar(dulce:elemento){
        this.pedido.push(dulce);
    }

    setTotal(goukei:number){
        this.total=goukei;
    }
}