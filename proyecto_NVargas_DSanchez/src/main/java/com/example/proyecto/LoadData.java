package com.example.proyecto;

import java.util.ArrayList;
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
          
            Cliente a = new Cliente("Pablo","101112","ewfwfwfewfsegvs",false);
            Cliente b = new Cliente("Diegfo","131415","yhjtykjtyktykj",false);
            repoCli.save(new Cliente("Nicolas","12345","sndlkfnsldknl",true));
            repoCli.save(new Cliente("Camilo","6789","ewfwfwfwsf",true));
            repoCli.save(a);
            repoCli.save(b);
            

            Dulce g = new Dulce("gomita trululu","Gomas",45L,12000L);
            Dulce c = new Dulce("Chocolatina","Chocolate",45L,11000L);
            Dulce f = new Dulce("Ferrero","Importados",4L,18000L);
            Dulce p = new Dulce("Poocky","Importados",16L,10000L);
            repoD.save(g);
            repoD.save(c);
            repoD.save(f);
            repoD.save(p);

            List<Dulce> lista1 = new ArrayList<Dulce>();
            lista1.add(g);
            lista1.add(g);
            lista1.add(c);
            lista1.add(g);

            List<Dulce> lista2 = new ArrayList<Dulce>();
            lista2.add(g);
            lista2.add(p);

            List<Dulce> lista3 = new ArrayList<Dulce>();
            lista3.add(c);
            lista3.add(g);
            lista3.add(f);

            repoC.save(new Compra(a,lista1));//a=cliente list1=lista de dulces
            repoC.save(new Compra(a,lista2));
            repoC.save(new Compra(b,lista3));

            List<Dulce> listav1 = new ArrayList<Dulce>();
            listav1.add(g);
            listav1.add(c);
            listav1.add(f);
            Compra compra1 = new Compra(a, listav1);
            

            a.setCarrito(compra1);
            repoC.save(compra1);
            repoCli.save(a);
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

            List<Compra> c = repoC.getByIdPropietario(3L);
            Compra c1=c.get(0);
        };
    }

    @Bean
    CommandLineRunner testCliente ( ClienteRepository repoCli){
        return args -> {

            Optional<Cliente> r = repoCli.findById(3L);
            if(r.isPresent()){
                System.out.println(r.get().getNombre());
            }
        };
    }
}
