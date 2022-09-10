package interfaz;

import java.sql.Date;
import java.util.ArrayList;
import logica.ControlClientes;
import logica.Cliente;
import logica.ControlStock;
import logica.Factura;
import logica.Producto;
import logica.Proveedor;
import utilidades.Consola;
import logica.ControlFacturas;
import logica.LineaFactura;

public class IuConsola {

    ControlClientes controlClientes = ControlClientes.getInstancia();
    ControlStock controlStock = ControlStock.getInstancia();
    ControlFacturas controlFacturas = ControlFacturas.getInstancia();

    /**
     * Ejecuta la consola
     */
    public void mostrarConsola() {
        boolean salir = false;
        do {

            int opcion = imprimirMenu();
            salir = procesarOpcion(opcion);

        } while (!salir);
    }

    /**
     * Imprime el menú y sus opciones a pantalla
     */
    private int imprimirMenu() {
        System.out.println("MENU");
        System.out.println("====");

        ArrayList<String> opciones = new ArrayList();
        opciones.add("Alta de Cliente");
        opciones.add("Alta de Producto");
        opciones.add("Alta de Factura");
        opciones.add("Cliente con el producto mas barato");
        opciones.add("Salir del menú");
        return Consola.menu(opciones);
    }

    /**
     * Captura la opción seleccionada por el usuario y ejecuta la acción
     * correspondiente
     */
    private boolean procesarOpcion(int opcion) {
        boolean salir = false;
        int numero;

        switch (opcion) {
            case 0:
                this.nuevoCliente();
                break;
            case 1:
                this.nuevoProducto();
                break;
            case 2:
                this.nuevaFactura();
                break;
            case 3:
                this.mostrarClientesProdMasBarato();
                break;
            case 4:
                salir = true;
                break;

        }
        return salir;
    }

    private void nuevoCliente() {

        System.out.println("ALTA DE CLIENTE");
        System.out.println("===============");

        Cliente unCliente = new Cliente();
        boolean ok = false;
        do {
            ok = unCliente.setCedula(Consola.leer("Cedula:"));
            if (!ok) {
                System.out.println("Cedula incorrecta");
            }
        } while (!ok);

        unCliente.setNombre(Consola.leer("Nombre:"));
        if (controlClientes.agregar(unCliente)) {
            mostrarClientes();
        } else {
            System.out.println("EL CLIENTE NO FUE INGRESADO");
        }

    }

    private void mostrarClientes() {
        System.out.println("=================");
        System.out.println("CLIENTES ACTUALES");
        System.out.println("=================");
        ArrayList<Cliente> clientes = controlClientes.getClientes();
        for (Cliente c : clientes) {
            System.out.println(c.getCedula() + " - " + c.getNombre());
        }
    }

    private void nuevoProducto() {
        System.out.println("ALTA DE PRODUCTO");
        System.out.println("===============");
        Producto unProducto = new Producto();
        boolean ok = false;
        do {
            ok = unProducto.setNombre(Consola.leer("Nombre: "));
            if (!ok) {
                System.out.println("Nombre no puede ser vacio.");
            }
        } while (!ok);

        unProducto.setUnidades(Consola.leerInt("Numero: "));

        ArrayList<Proveedor> proveedores = controlStock.getProveedores();
        int pos = Consola.menu(proveedores);
        Proveedor p = proveedores.get(pos);
        unProducto.setProveedor(p);

        unProducto.setPrecio(Consola.leerInt("Precio: "));
        if (controlStock.agregar(unProducto)) {

            mostrarProductos();
        } else {
            System.out.println("EL PRODUCTO NO FUE INGRESADO");
        }

    }

    private void mostrarProductos() {
        System.out.println("=================");
        System.out.println("PRODUCTOS ACTUALES");
        System.out.println("=================");
        ArrayList<Producto> productos = controlStock.getProductos();
        for (Producto p : productos) {
            System.out.println(p.toString());
        }
    }

    private void nuevaFactura() {
        System.out.println("ALTA DE FACTURA");
        System.out.println("===============");
        Factura unaFactura = new Factura();

        Cliente existeCliente = controlClientes.buscarClienteCedula(Consola.leer("Cedula: "));
        if (existeCliente == null) {
            System.out.println("El cliente no existe.");
        } else {
            unaFactura.setCliente(existeCliente);
            boolean ok = false;
            do {
                int codigo = Consola.leerInt("Digita el codigo: ");
                int cantUnidades = Consola.leerInt("Digita la cantidad de Unidades: ");
                Producto p = controlStock.buscarProdCod(codigo);
                if (p != null) {
                    if (unaFactura.agregarLinea(cantUnidades, p)) {
                        for (LineaFactura lf : unaFactura.getLineas()) {
                            System.out.println("CodProducto: " + lf.getProducto().getCodigo() + ", Nombre Producto: " + lf.getProducto().getNombre() + ", Cant Unidades:" + lf.getCantidad() + ", Subtotal: " + lf.Subtotal());
                        }
                    } else {
                        System.out.println("No fue posible ingresar la linea por falta de stock.");
                    }
                } else {
                    System.out.println("Dicho producto no existe o no hay cantidad en stock suficiente.");
                }
                System.out.println("Total de la Factura: " + unaFactura.totalFactura());
                String continuar = Consola.leer("Desea Continuar S/N:");
                if (continuar.equals("S") || continuar.equals("s")) {
                    ok = true;
                } else {
                    ok = false;
                }
            } while (ok);

            String almacenar = Consola.leer("Desea almacenar la factura S/N: ");

            if (almacenar.equals("S") || almacenar.equals("s")) {
                controlFacturas.agregar(unaFactura); //es una instancia de factura (unaFactura) no esta creada en el sistema
            }
        }

    }

    private void mostrarClientesProdMasBarato() {
        System.out.println("El producto más barato es:");
        Producto prod = controlStock.productoMasBarato();
        if (prod == null) {
            System.out.println("No hay ingresados productos");
        } else {
            System.out.println("CodProd: " + prod.getCodigo() + ", Nombre: " + prod.getNombre() + ", Precio: $" + prod.getPrecio() + ", Stock Actual: " + prod.getUnidades());
            ArrayList<Cliente> clientesCompraronProductoMasBarato = controlClientes.clientesCompraronProductoMasBarato();            
            if (clientesCompraronProductoMasBarato.isEmpty()) {
                System.out.println("No hay clientes con el producto mas barato");
            } else {                
                for (Cliente c : clientesCompraronProductoMasBarato) {
                    System.out.println("Cedula: " + c.getCedula() + ", Nombre: " + c.getNombre() + ", Fecha Ultima Compra: " + ControlFacturas.getInstancia().ultimaCompraPorUsuario(c,prod).getFecha());
                }
            }
        }

    }
}
