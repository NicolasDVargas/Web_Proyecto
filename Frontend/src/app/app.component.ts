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
  }
  
}
