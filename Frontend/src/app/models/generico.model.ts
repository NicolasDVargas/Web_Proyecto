export class Pila<T>{
    private datos: T[] = [];

    push(dato:T) {
        this.datos.push(dato);
    }


    toString(){
        console.log(JSON.stringify(this.datos))
    }
}