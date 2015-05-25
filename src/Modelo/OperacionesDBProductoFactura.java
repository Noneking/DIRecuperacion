/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Hibernate.Fabricantes;
import Hibernate.Facturas;
import Hibernate.NewHibernateUtil;
import Hibernate.Productos;
import Hibernate.Productosfacturas;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Mantenimiento
 */
public class OperacionesDBProductoFactura {
    
    Session s;
    
    public void openSession()
    {
        s = NewHibernateUtil.getSessionFactory().openSession();
    }
    
    public void closeSession()
    {
//        s.close();
    }
    
    public int getProductosFacturasById(int codProducto, int idFactura)
    {
        openSession();
        int codigoProductoFactura = 0;
        Productosfacturas pf;
        Iterator it=getProductosFacturas().iterator();
        while(it.hasNext())
        {
            pf=(Productosfacturas) it.next();
            if(pf.getProductos().getCodigo()==codProducto && pf.getFacturas().getId()==idFactura)
            {
                codigoProductoFactura=pf.getCodigoTabla();
            }
        }
//        Productosfacturas pFact = null;
//        if(codigoProductoFactura!=0)
//        {
//            pFact=(Productosfacturas) s.get(Productosfacturas.class, codigoProductoFactura);
//        }
        closeSession();
        return codigoProductoFactura;
    }
    
    public ArrayList<Productosfacturas> getProductosFacturasByQuestion(String question)
    {
        openSession();
        Query query=s.createQuery("from Productosfacturas pf where pf.facturas like :consulta OR pf.productos like :consulta");
        ArrayList<Productosfacturas> list = (ArrayList<Productosfacturas>) query.setParameter("consulta", "%"+question+"%").list();
        closeSession();
        return list;
    }
    
    public ArrayList<Productosfacturas> getProductosFacturas()
    {
        openSession();
//        ArrayList<Object> array=new ArrayList<>();
//        ArrayList<Facturas>listFacturas =(ArrayList<Facturas>) s.createQuery("from Facturas").list();
//        array.add(listFacturas);
//        ArrayList<Productos>listProductos =(ArrayList<Productos>) s.createQuery("from Productos").list();
//        array.add(listProductos);
        
        ArrayList<Productosfacturas>list =(ArrayList<Productosfacturas>) s.createQuery("from Productosfacturas").list();
        closeSession();
        return list;
    }
    
    Set product=new HashSet();
    
    public void insertarDatosProductosFacturas(int codProducto, int idFactura)
    {
        try
        {
        openSession();
        Transaction tx=s.beginTransaction();
        
        Facturas f=(Facturas) s.get(Facturas.class, idFactura);
        Productos p=(Productos) s.get(Productos.class, codProducto);
        
        Productosfacturas pf=new Productosfacturas();
        pf.setProductos(p);
        pf.setFacturas(f);
//        Set product=new HashSet();
//        product.add(p);
//        f.setProductosfacturases(product);
        s.save(pf);
//        product.clear();
        
        tx.commit();
        s.clear();
        closeSession();
        }
        catch(ConstraintViolationException e)
        {
            JOptionPane.showMessageDialog(null, "CLAVE PRIMARIA REPETIDA");
        }
    }
    
    public void modificarDatosProductosFacturas(int codProductoAntiguo, int idFacturaAntiguo, int codProductoNuevo, int idFacturaNuevo)
    {
        openSession();
//        Transaction tx=s.beginTransaction();
        
        int productoFacturaId=getProductosFacturasById(codProductoAntiguo, idFacturaAntiguo);
        Productosfacturas pf=(Productosfacturas) s.get(Productosfacturas.class, productoFacturaId);
        
        Productos p=(Productos) s.get(Productos.class, codProductoNuevo);
        Facturas f=(Facturas) s.get(Facturas.class, idFacturaNuevo);
        
        pf.setProductos(p);
        pf.setFacturas(f);
        Transaction tx=s.beginTransaction();
        s.update(pf);
        
        tx.commit();
        closeSession();
    }
    
    public void eliminarDatosProductosFacturas(int codProducto,int idFactura)
    {
        openSession();
//        Transaction tx=s.beginTransaction();
        
        int codProductoFactura=getProductosFacturasById(codProducto, idFactura);
        Productosfacturas pf=(Productosfacturas) s.get(Productosfacturas.class, codProductoFactura);
        Transaction tx=s.beginTransaction();
        s.delete(pf);
        
        tx.commit();
        closeSession();
        JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO");
    }
    
    public DefaultTableModel printTable()
    {
        DefaultTableModel modeloProductosFacturas=convertArrayListToObjectArrayProductosFacturas(getProductosFacturas());
        return modeloProductosFacturas;
    }
    
    public DefaultTableModel convertArrayListToObjectArrayProductosFacturas(ArrayList<Productosfacturas> arrayList)
    {
        Iterator it=arrayList.iterator();
        Object[] fila = new Object[2];
        DefaultTableModel m=new DefaultTableModel();
        Object[] nombreColumnasProductosFacturas={"CODIGO PRODUCTO","ID FACTURA"};
        m.setColumnIdentifiers(nombreColumnasProductosFacturas);
        while(it.hasNext())
        {
            Productosfacturas pf=(Productosfacturas) it.next();
            fila[0]=pf.getProductos().getCodigo();
            fila[1]=pf.getFacturas().getId();
            m.addRow(fila);
//            Facturas f = (Facturas) it.next();
//            Iterator its=f.getProductosfacturases().iterator();
//            while (its.hasNext()) {
//                Productos p = (Productos) its.next();
//                fila[0]=p.getCodigo();
//                fila[1]=f.getId();
//                m.addRow(fila);
//            }
        }
        return m;
    }
    
}
