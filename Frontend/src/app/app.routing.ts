import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { AdminGuard } from "./admin.guard";
import { AgregarDulceComponent } from "./components/agregar-dulce/agregar-dulce.component";
import { CarritoComponent } from "./Components/carrito/carrito.component";
import { DetalleDulceComponent } from "./components/detalle-dulce/detalle-dulce.component";
import { EditarDulceComponent } from "./Components/editar-dulce/editar-dulce.component";
import { HomeComponent } from "./components/home/home.component";
import { IniciarSesionComponent } from "./components/iniciar-sesion/iniciar-sesion.component";
import { InicioComponent } from "./components/inicio/inicio.component";
import { InventarioComponent } from "./components/inventario/inventario.component";
import { LaFacturaComponent } from "./Components/la-factura/la-factura.component";
import { PerfilComponent } from "./Components/perfil/perfil.component";
import { VerFacturasComponent } from "./Components/ver-facturas/ver-facturas.component";




const routes = [
    {
        path: '',component: InicioComponent
    },
    {path: './home',component: HomeComponent},
    {path: './iniciar',component: IniciarSesionComponent},
    {path:'./inventario',component: InventarioComponent,},
    {path:'./nuevoDulce',component: AgregarDulceComponent,},
    {path:'./DetalleDulce',component: DetalleDulceComponent,},
    {path:'./EditarDulce',component:EditarDulceComponent,},
    {path:'./carrito',component:CarritoComponent,},
    {path:'./perfil',component:PerfilComponent,},
    {path:'./verFacts',component:VerFacturasComponent,},
    {path:'./laFactura',component:LaFacturaComponent,},
    {path: './**',component: HomeComponent}
];

@NgModule(
    {
        imports: [
            RouterModule.forRoot(routes)
        ],
        exports: [
            RouterModule
        ]
    })
export class AppRoutingModule {}   