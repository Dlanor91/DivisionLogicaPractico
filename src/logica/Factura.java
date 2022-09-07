/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author magda
 */
public class Factura {
    private Date fecha;
    private Cliente cliente;
    private ArrayList <LineaFactura> lineas = new ArrayList();

    
    public Factura(Cliente cliente) {
        this.cliente = cliente;
    }

    public Factura(Date fecha, Cliente cliente) {
        this.fecha = fecha;
        this.cliente = cliente;
    }

    public Factura() {
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
        
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public ArrayList<LineaFactura> getLineas() {
        return lineas;
    }

    public void agregar(int cantidad, Producto p){
        lineas.add(new LineaFactura(p, cantidad));
    }
    
    public boolean agregarLinea(int cantidad,Producto p){
            boolean encontrado = false;
            if(cantidad > p.getUnidades()){
                return false;
            }else{
                for(LineaFactura lf:getLineas()){
                    if(lf.getProducto().equals(p)){ 
                        encontrado = true;
                       if((lf.getCantidad()+cantidad)<=p.getUnidades()){
                           lf.setCantidad(cantidad + lf.getCantidad());
                       }else{
                           return false;
                       }
                    }
                }
                if(!encontrado){
                   lineas.add(new LineaFactura(p, cantidad));                    
                }
                return true;                
            }
    }
    
    public boolean tieneProducto(Producto unP){
        boolean ret = false;
        for(LineaFactura l: lineas){
            if(l.tieneProducto(unP)){
                ret = true;
            }
        }
        return ret;       
    }



    @Override
    public String toString() {
        return "Factura{" + "cliente=" + cliente + ", lineas=" + lineas + '}';
    }    

    public float totalFactura() {
        float total = 0;
        
        for(LineaFactura lf:getLineas()){
            total += lf.Subtotal();
        }
        
        return total;
    }

    public void rebajarStock() {
        for(LineaFactura lf: getLineas()){
            lf.rebajarStock();
        }
    }
    
}
