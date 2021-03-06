package Hibernate;
// Generated 12-may-2015 11:43:36 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Facturas generated by hbm2java
 */
public class Facturas  implements java.io.Serializable {


     private Integer id;
     private Empleados empleados;
     private Double precioTotal;
     private String tipoPago;
     private Integer productos;
     private Set productosfacturases = new HashSet(0);

    public Facturas() {
    }

    public Facturas(Empleados empleados, Double precioTotal, String tipoPago, Integer productos, Set productosfacturases) {
       this.empleados = empleados;
       this.precioTotal = precioTotal;
       this.tipoPago = tipoPago;
       this.productos = productos;
       this.productosfacturases = productosfacturases;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Empleados getEmpleados() {
        return this.empleados;
    }
    
    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }
    public Double getPrecioTotal() {
        return this.precioTotal;
    }
    
    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }
    public String getTipoPago() {
        return this.tipoPago;
    }
    
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
    public Integer getProductos() {
        return this.productos;
    }
    
    public void setProductos(Integer productos) {
        this.productos = productos;
    }
    public Set getProductosfacturases() {
        return this.productosfacturases;
    }
    
    public void setProductosfacturases(Set productosfacturases) {
        this.productosfacturases = productosfacturases;
    }




}


