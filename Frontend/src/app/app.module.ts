import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routing';
import { FormsModule } from '@angular/forms';
import { InicioComponent } from './components/inicio/inicio.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { IniciarSesionComponent } from './components/iniciar-sesion/iniciar-sesion.component';
import { InventarioComponent } from './components/inventario/inventario.component';
import { AgregarDulceComponent } from './components/agregar-dulce/agregar-dulce.component';
import { DetalleDulceComponent } from './components/detalle-dulce/detalle-dulce.component';
import { EditarDulceComponent } from './Components/editar-dulce/editar-dulce.component';
import { CarritoComponent } from './Components/carrito/carrito.component';
import { PerfilComponent } from './Components/perfil/perfil.component';
import { VerFacturasComponent } from './Components/ver-facturas/ver-facturas.component';
import { LaFacturaComponent } from './Components/la-factura/la-factura.component';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    InicioComponent,
    IniciarSesionComponent,
    InventarioComponent,
    AgregarDulceComponent,
    DetalleDulceComponent,
    EditarDulceComponent,
    CarritoComponent,
    PerfilComponent,
    VerFacturasComponent,
    LaFacturaComponent,

  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
