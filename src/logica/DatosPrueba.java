/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author magda
 */
public class DatosPrueba {

    public static void cargar() {

        Fachada logica = Fachada.getInstancia();

        Proveedor pA = new Proveedor("Proveedor A");
        Proveedor pB = new Proveedor("Proveedor B");
        Proveedor pC = new Proveedor("Proveedor C");

        logica.agregar(pA);
        logica.agregar(pB);
        logica.agregar(pC);

        Producto caramelo = new Producto("Caramelo", 2, 3000, pA);
        Producto camisa = new Producto("Camisa", 1300, 1000, pB);
        Producto computadora = new Producto("Computadora", 20000, 40, pC);

        logica.agregar(caramelo);
        logica.agregar(camisa);
        logica.agregar(computadora);

        Cliente juan = new Cliente("12345678", "Juan");
        Cliente ana = new Cliente("13456789", "Ana");
        Cliente mario = new Cliente("21234567", "Mario");

        logica.agregar(juan);
        logica.agregar(ana);
        logica.agregar(mario);

        Factura f1 = new Factura(juan);
        f1.agregar(30, caramelo);
        f1.agregar(2, camisa);
        f1.agregar(1, computadora);

        logica.agregar(f1);

        Factura f2 = new Factura(ana);
        f2.agregar(400, caramelo);
        f2.agregar(20, camisa);
        f2.agregar(10, computadora);

        logica.agregar(f2);

        Factura f3 = new Factura(mario);

        f3.agregar(1, camisa);
        f3.agregar(1, computadora);

        logica.agregar(f3);
    }

}
