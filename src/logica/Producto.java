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
public class Producto {
    private String nombre;
    private int precio;
    private int unidades;
    private Proveedor proveedor;
    private int codigo;

    public Producto() {
    }
    
    

    public Producto(String nombre, int precio, int stock, Proveedor proveedor) {
        this.nombre = nombre;
        this.precio = precio;
        this.unidades = stock;
        this.proveedor = proveedor;
        
        //proveedor.agregar(this);
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }


    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public String getNombre() {
        return nombre;
    }

    public boolean setNombre(String unNombre) {
        if(validarNombre(unNombre)){
           nombre = unNombre;
           return true;
       }return false;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombre= " + nombre + ", precio= " + precio + ", unidades= " + unidades + ", proveedor= " + proveedor + ", codigo= " + codigo + '}';
    }
   
    public void setCodigo(int cod) {
       codigo = cod;
    }

    public boolean validarNombre(String unNombre) {
        boolean ok = false;
        
        if(!unNombre.equals("")) 
        {
            ok =true;
        }        
        
        return ok;
    }

    public boolean verificarDatos(int unasUnidades,float unPrecio) {
        boolean ok = false;
        
        if(unasUnidades >0 && unPrecio>0) ok =true;
        
        return ok;
    }

    public boolean validar() {
        return validarNombre(nombre) && verificarDatos(unidades,precio) && proveedor !=null ;
    }
        
}
