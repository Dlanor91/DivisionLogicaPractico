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
public class LineaFactura {
    
    private Producto producto;
    private int cantidad;

    public LineaFactura() {
    }
    
    

    public LineaFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public boolean tieneProducto(Producto unP){
        return this.getProducto() == unP;
    }

    @Override
    public String toString() {
        return "LineaFactura{" + "producto=" + producto + ", cantidad=" + cantidad + '}';
    }  
        
    public float Subtotal(){
        return this.producto.getPrecio() * this.cantidad;
    }

    public void rebajarStock() {    
        this.producto.setUnidades(getProducto().getUnidades()-cantidad);
    }
}
