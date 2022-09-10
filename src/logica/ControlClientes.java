/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;

/**
 *
 * @author magda
 */
public class ControlClientes {

    private static ControlClientes instancia;

    private ArrayList<Cliente> clientes = new ArrayList();

    public static ControlClientes getInstancia() {

        if (instancia == null) {
            instancia = new ControlClientes();
        }
        return instancia;
    }

    private ControlClientes() {

    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList clientesNoCompraronProductoMenorPrecio() {
        Producto menor = ControlStock.getInstancia().getProductoMenorPrecio();
        ArrayList<Cliente> retorno = new ArrayList<Cliente>();

        for (Cliente c : clientes) {
            if (!ControlFacturas.getInstancia().clienteComproProducto(c, menor)) {
                retorno.add(c);
            }

        }
        return retorno;

    }

    public boolean existeCliente(Cliente c) {
        /*boolean existe = false;
           int pos=0;
           ArrayList<Cliente> lista = this.getClientes();
           while (pos<lista.size()&&!existe){
               Cliente c = lista.get(pos);
               if (c.getCedula().equals(unaCedula)){
                   existe = true;
               }
               pos++;
           }
           return existe;*/
        return clientes.contains(c);
    }

    public boolean agregar(Cliente c) {
        boolean ok = false;
        if (c.validar() && !this.existeCliente(c)) {
            clientes.add(c);
            ok = true;
        }

        return ok;
    }

    public Cliente buscarClienteCedula(String cedula) {
        Cliente c = null;

        for (Cliente uncliente : getClientes()) {
            if (uncliente.getCedula().equals(cedula)) {
                c = uncliente;
                return c;
            }
        }

        return c;

    }

    public ArrayList clientesCompraronProductoMasBarato() {
        Producto masBarato = ControlStock.getInstancia().productoMasBarato();
        ArrayList<Cliente> clientesQueCompraron = new ArrayList<Cliente>();

        if (masBarato != null) {
            for (Cliente c : getClientes()) {
                if (ControlFacturas.getInstancia().clienteComproProducto(c, masBarato)) {
                    clientesQueCompraron.add(c);
                }
            }
        }
        return clientesQueCompraron;
    }

}
