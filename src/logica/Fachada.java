/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;

/**
 *
 * @author rl850
 */
public class Fachada {
    private ControlClientes cClientes = new ControlClientes();
    private ControlFacturas cFacturas = new ControlFacturas();
    private ControlStock cStock = new ControlStock();
    
    //Singleton
    private static Fachada instancia= new Fachada();

    public static Fachada getInstancia() {
        return instancia;
    }

    private Fachada() {
    }

    public ArrayList<Producto> getProductos() {
        return cStock.getProductos();
    }

    public ArrayList<Proveedor> getProveedores() {
        return cStock.getProveedores();
    }

    public Producto getProductoMenorPrecio() {
        return cStock.getProductoMenorPrecio();
    }

    public void agregar(Proveedor unProveedor) {
        cStock.agregar(unProveedor);
    }

    public boolean agregar(Producto unProducto) {
        return cStock.agregar(unProducto);
    }

    public Producto buscarProdCod(int cod) {
        return cStock.buscarProdCod(cod);
    }

    public Producto productoMasBarato() {
        return cStock.productoMasBarato();
    }

    public ArrayList<Cliente> getClientes() {
        return cClientes.getClientes();
    }

    public ArrayList clientesNoCompraronProductoMenorPrecio() {
        return cClientes.clientesNoCompraronProductoMenorPrecio();
    }

    public boolean existeCliente(Cliente c) {
        return cClientes.existeCliente(c);
    }

    public boolean agregar(Cliente c) {
        return cClientes.agregar(c);
    }

    public Cliente buscarClienteCedula(String cedula) {
        return cClientes.buscarClienteCedula(cedula);
    }

    public ArrayList clientesCompraronProductoMasBarato() {
        return cClientes.clientesCompraronProductoMasBarato();
    }

    public ArrayList<Factura> getFacturas() {
        return cFacturas.getFacturas();
    }

    public void agregar(Factura unaFactura) {
        cFacturas.agregar(unaFactura);
    }

    public boolean clienteComproProducto(Cliente c, Producto p) {
        return cFacturas.clienteComproProducto(c, p);
    }

    public Factura ultimaCompraPorUsuario(Cliente c, Producto prod) {
        return cFacturas.ultimaCompraPorUsuario(c, prod);
    }
    
    
}
