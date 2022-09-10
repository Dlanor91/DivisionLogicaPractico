
package logica;

import java.util.ArrayList;


public class ControlStock {
    
    private ArrayList<Producto> productos = new ArrayList();
    private ArrayList<Proveedor> proveedores = new ArrayList();

    private int proximoCodigo = 0;
    
    ControlStock() {
    }
  
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Proveedor> getProveedores() {
        return proveedores;
    }

    
    public Producto getProductoMenorPrecio(){
        Producto menor = productos.get(0);
        Producto p;
        for(int x=1;x<productos.size();x++){
            p = productos.get(x);
            if (p.getPrecio()<menor.getPrecio()){
                menor = p;
            }
        }            
        
        return menor;
        
      
    }
        
    public void agregar(Proveedor unProveedor){
        proveedores.add(unProveedor);
    }
    
    public  boolean agregar (Producto unProducto){
        
        boolean ok = false;
        if (unProducto.validar()){  
            
        unProducto.setCodigo(Codigo());
        productos.add(unProducto);
        unProducto.getProveedor().agregar(unProducto);
        ok = true;}
        
        return ok;
    }
    
    private int Codigo(){        
       proximoCodigo++;
       return proximoCodigo;
    }
    
    public Producto buscarProdCod(int cod){
        Producto prod = null;
        
           for(Producto pr:getProductos()){
               if(pr.getCodigo() == cod){
                  prod = pr;
                  return prod;
               }
           }
        return prod;
    }

    public Producto productoMasBarato() {
        Producto prod = null;
        if(getProductos().size()!=0) prod = productos.get(0) ;
        for(Producto p:getProductos()){
           if(p.getPrecio() <prod.getPrecio()){
               prod = p;
           }
        }
        return prod;
        
    }
    
}
