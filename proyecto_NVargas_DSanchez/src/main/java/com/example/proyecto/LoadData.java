package com.example.proyecto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import com.example.proyecto.Repository.ClienteRepository;
import com.example.proyecto.Repository.CompraRepository;
import com.example.proyecto.Repository.DulceRepository;
import com.example.proyecto.model.Cliente;
import com.example.proyecto.model.Compra;
import com.example.proyecto.model.Dulce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadData {

    @Bean
    CommandLineRunner pobladorBD (DulceRepository repoD, CompraRepository repoC, ClienteRepository repoCli){
        return args ->{
            System.out.println("poblando la BD\n");
          
            Cliente a = new Cliente("Pablo","101112","jpablo@gmail.com",false);
            Cliente b = new Cliente("Diego","131415","dfsanchez@gmail.com",false);
            Cliente c = new Cliente("Nicolas","12345","nicroleto@gmail.com",true);
            Cliente d = new Cliente("Camilo","6789","juank1@gmail.com",true);
            Cliente e = new Cliente("Juana","78910","juanitasf2@gmail.com",false);
            Cliente f = new Cliente("Miguel","55555","migueldl@gmail.com",true);
            Cliente g = new Cliente("Sofia","54321","sofisofi@gmail.com",true);
            Cliente h = new Cliente("Paul","8080","paulsp8@gmail.com",false);
            Cliente i = new Cliente("Laura","7410","lauperez2@gmail.com",false);
            Cliente j = new Cliente("Natalia","8426","natadiaz1@gmail.com",false);
            repoCli.save(a);
            repoCli.save(b);
            repoCli.save(c);
            repoCli.save(d);
            repoCli.save(e);
            repoCli.save(f);
            repoCli.save(g);
            repoCli.save(h);
            repoCli.save(i);
            repoCli.save(j);
            

            Dulce ga = new Dulce("gomita trululu","Gomas",45L,12000L);
            Dulce ca = new Dulce("Chocolatina","Chocolate",45L,11000L);
            Dulce fa = new Dulce("Ferrero","Importados",4L,18000L);
            Dulce pa = new Dulce("Poocky","Importados",16L,10000L);
            Dulce oa = new Dulce("Oreo","Galletas",22L,4000L);
            Dulce bo = new Dulce("Bombombun","Bombon",40L,3000L);
            Dulce ta = new Dulce("Trident","Chicle",35L,2000L);
            Dulce ha = new Dulce("Hersheys","Chocolate",55L,9000L);
            Dulce ja = new Dulce("Jet","Chocolate",80L,2500L);
            Dulce ba = new Dulce("Boobaloo","Chicle",60L,2000L);
            repoD.save(ga);
            repoD.save(ca);
            repoD.save(fa);
            repoD.save(pa);
            repoD.save(oa);
            repoD.save(bo);
            repoD.save(ta);
            repoD.save(ha);
            repoD.save(ja);
            repoD.save(ba);

            List<Dulce> lista1 = new ArrayList<Dulce>();
            lista1.add(ga);
            lista1.add(fa);
            lista1.add(ca);
            lista1.add(ga);

            List<Dulce> lista2 = new ArrayList<Dulce>();
            lista2.add(ga);
            lista2.add(pa);
            lista2.add(ba);
            lista2.add(ja);
            lista2.add(ha);

            List<Dulce> lista3 = new ArrayList<Dulce>();
            lista3.add(ca);
            lista3.add(ga);
            lista3.add(fa);
            lista3.add(bo);

            List<Dulce> lista4 = new ArrayList<Dulce>();
            lista4.add(ha);
            lista4.add(ca);
            lista4.add(fa);
            lista4.add(ba);
            lista4.add(ja);

            List<Dulce> lista5 = new ArrayList<Dulce>();
            lista5.add(bo);
            lista5.add(oa);
            lista5.add(ta);
            lista5.add(ja);
            lista5.add(ha);

            Compra com1 = new Compra(a,lista1);
            Compra com2 = new Compra(e,lista2);
            Compra com3 = new Compra(b,lista3);
            Compra com4 = new Compra(j,lista4);
            Compra com5 = new Compra(h,lista5);

            repoC.save(com1);//a=cliente list1=lista de dulces
            repoC.save(com2);
            repoC.save(com3);
            repoC.save(com4);
            repoC.save(com5);

            List<Compra> factura1 = new ArrayList<>();
            factura1.add(com1);

            List<Compra> factura2 = new ArrayList<>();
            factura2.add(com2);

            List<Compra> factura3 = new ArrayList<>();
            factura3.add(com3);

            List<Compra> factura4 = new ArrayList<>();
            factura4.add(com4);

            List<Compra> factura5 = new ArrayList<>();
            factura5.add(com5);

            a.setFacturas(factura1);
            e.setFacturas(factura2);
            b.setFacturas(factura3);
            j.setFacturas(factura4);
            h.setFacturas(factura5);

            List<Dulce> listav1 = new ArrayList<Dulce>();
            listav1.add(ga);
            listav1.add(ca);
            listav1.add(fa);
            listav1.add(ga);
            Compra compra1 = new Compra(a, listav1);

            List<Dulce> listav2 = new ArrayList<Dulce>();
            listav2.add(ga);
            listav2.add(pa);
            listav2.add(ba);
            listav2.add(ja);
            listav2.add(ha);
            Compra compra2 = new Compra(e, listav2);

            List<Dulce> listav3 = new ArrayList<Dulce>();
            listav3.add(ca);
            listav3.add(ga);
            listav3.add(fa);
            listav3.add(bo);
            Compra compra3 = new Compra(b, listav3);

            List<Dulce> listav4 = new ArrayList<Dulce>();
            listav4.add(ha);
            listav4.add(ca);
            listav4.add(fa);
            listav4.add(ba);
            listav4.add(ja);
            Compra compra4 = new Compra(j, listav4);

            List<Dulce> listav5 = new ArrayList<Dulce>();
            listav5.add(bo);
            listav5.add(oa);
            listav5.add(ta);
            listav5.add(ja);
            listav5.add(ha);
            Compra compra5 = new Compra(h, listav5);
            

            a.setCarrito(compra1);
            repoC.save(compra1);
            repoCli.save(a);

            e.setCarrito(compra2);
            repoC.save(compra2);
            repoCli.save(e);

            b.setCarrito(compra3);
            repoC.save(compra3);
            repoCli.save(b);

            j.setCarrito(compra4);
            repoC.save(compra4);
            repoCli.save(j);

            h.setCarrito(compra5);
            repoC.save(compra5);
            repoCli.save(h);
        };
    }

    @Bean
    CommandLineRunner testDulces ( DulceRepository repoD){
        return args -> {
            System.out.println("Dulce repository\n");

            Dulce d = repoD.getById(1L);
        };
    }

    @Bean
    CommandLineRunner testCompra ( CompraRepository repoC){
        return args -> {
            System.out.println("Compra repository\n");

            List<Compra> c = repoC.getByIdPropietario(1L);
            if(c.size() > 0) {
                Compra c1=c.get(0);
            }
        };
    }

    @Bean
    CommandLineRunner testCliente ( ClienteRepository repoCli){
        return args -> {

            Optional<Cliente> r = repoCli.findById(1L);
            if(r.isPresent()){
                System.out.println(r.get().getNombre());
            }
        };
    }
}
