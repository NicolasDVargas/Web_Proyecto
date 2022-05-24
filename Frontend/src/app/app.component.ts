import { Component } from '@angular/core';
import { Administrador } from './models/admin.module';
import { Dulce} from './models/candy.model';
import { AdminsService } from './servicios/admins.service';
import { InventarioService } from './servicios/inventario.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Proyecto_DSanchez_NVargas';


  constructor(_adminService:AdminsService, _inventarioService: InventarioService){

    _adminService.agregar("Nicolas","12345");
    var dulce1 : Dulce = new Dulce()
    dulce1.nombre="Caja de Pocky";
    dulce1.tipo="Importados";
    dulce1.cantidad=50;
    dulce1.costo=10000;
    dulce1.imagen="../assets/images/products/GUEST_d0eb439c-3f20-4eb7-be83-471c10b67778.jpg";
    _inventarioService.agregar(dulce1,true);

    var dulce2 : Dulce = new Dulce();
    dulce2.nombre="Chocolatina jet";
    dulce2.tipo="Chocolate";
    dulce2.cantidad=50;
    dulce2.costo=500;
    dulce2.imagen="../assets/images/products/Chocolatina-Jet-x-12g-1000x798-1.jpg"
    _inventarioService.agregar(dulce2,true);

    var dulce3 : Dulce = new Dulce();
    dulce3.nombre="Bombombum";
    dulce3.tipo="Bombones";
    dulce3.cantidad=50;
    dulce3.costo=400;
    dulce3.imagen="../assets/images/products/Bombombun.jpg"
    _inventarioService.agregar(dulce3,true);

    var dulce4 : Dulce = new Dulce();
    dulce4.nombre="Hershey's Negra";
    dulce4.tipo="Importados";
    dulce4.cantidad=100;
    dulce4.costo=3000;
    dulce4.imagen="../assets/images/products/hersheysN.jpg"
    _inventarioService.agregar(dulce4,true);

    var dulce5 : Dulce = new Dulce();
    dulce5.nombre="Hershey's Blanca";
    dulce5.tipo="Importados";
    dulce5.cantidad=100;
    dulce5.costo=3000;
    dulce5.imagen="../assets/images/products/hersheysB.jpg"
    _inventarioService.agregar(dulce5,true);

    var dulce6 : Dulce = new Dulce();
    dulce6.nombre="Oreo x4";
    dulce6.tipo="Galleta";
    dulce6.cantidad=200;
    dulce6.costo=700;
    dulce6.imagen="../assets/images/products/oreo.jpg"
    _inventarioService.agregar(dulce6,true);
    localStorage.clear();
  }
  
}
