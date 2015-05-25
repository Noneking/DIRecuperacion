/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Hibernate.Empleados;
import Hibernate.Fabricantes;
import Hibernate.Facturas;
import Hibernate.NewHibernateUtil;
import Hibernate.Productos;
import java.util.ArrayList;
import java.util.Iterator;
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
public class OperacionesDBFacturas {
    
    Session s;
    
    public void openSession()
    {
        s = NewHibernateUtil.getSessionFactory().openSession();
    }
    
    public void closeSession()
    {
        s.close();
    }
    
    public Facturas getFacturasById(int id)
    {
        openSession();
        Facturas f=(Facturas) s.get(Facturas.class, id);
        closeSession();
        return f;
    }
    
    public ArrayList<Facturas> getFacturasByQuestion(Object question)
    {
        openSession();
//        Query query=s.createQuery("from Facturas f where f.id like :consulta OR f.empleados like :consulta OR f.precioTotal like :consulta OR f.tipoPago like :consulta OR f.productos like :consulta");
        Query query=s.createQuery("from Facturas f where f.id like :consulta OR f.tipoPago like :consulta OR f.empleados like :consulta");
        ArrayList<Facturas> list = (ArrayList<Facturas>) query.setParameter("consulta", "%"+question+"%").list();
        closeSession();
        return list;
    }
    
    public ArrayList<Facturas> getFacturas()
    {
        openSession();
        ArrayList<Facturas>list =(ArrayList<Facturas>) s.createQuery("from Facturas").list();
        closeSession();
        return list;
    }
    
    public void insertarDatosFacturas(double precioTotal, String tipoPago, int productos, String empleado)
    {
        try {
            openSession();
            Transaction tx = s.beginTransaction();
            
            Facturas f=new Facturas();
            f.setPrecioTotal(precioTotal);
            f.setTipoPago(tipoPago);
            f.setProductos(productos);
            
            OperacionesDBEmpleados ope=new OperacionesDBEmpleados();
            Empleados e=ope.getEmpleadosById(empleado);
            f.setEmpleados(e);
            
            s.save(f);
            
            tx.commit();
            closeSession();
        } catch (org.hibernate.exception.ConstraintViolationException  sql){
            JOptionPane.showMessageDialog(null, "CLAVE PRIMARIA REPETIDA");
        }
    }
    
    public void modificarDatosFacturas(int id, double precioTotal, String tipoPago, int productos, String empleado)
    {
        try
        {
        openSession();
        Transaction tx = s.beginTransaction();
        
        Facturas f=(Facturas) s.get(Facturas.class, id);
        f.setPrecioTotal(precioTotal);
        f.setTipoPago(tipoPago);
        f.setProductos(productos);
        
        OperacionesDBEmpleados ope=new OperacionesDBEmpleados();
        Empleados e=ope.getEmpleadosById(empleado);
        e.setDni(empleado);
        
        s.update(e);
        tx.commit();
        s.close();
        }
        catch(ConstraintViolationException e)
        {
            JOptionPane.showMessageDialog(null, "CLAVE PRIMARIA REPETIDA");
        }
    }
    
    public void eliminarDatosProductos(int id)
    {
        openSession();
        Transaction tx = s.beginTransaction();
        
        Facturas f=(Facturas) s.get(Facturas.class, id);
        s.delete(f);
        
        tx.commit();
        closeSession();
        JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO");
    }
    
    public DefaultTableModel printTable()
    {
        DefaultTableModel modeloFactura=convertArrayListToObjectArrayFactura(getFacturas());
        return modeloFactura;
    }
    
    public DefaultTableModel convertArrayListToObjectArrayFactura(ArrayList<Facturas> arrayList)
    {
        Iterator it=arrayList.iterator();
        Object[] fila = new Object[5];
        DefaultTableModel m=new DefaultTableModel();
        Object[] nombreColumnasFacturas={"ID","PRECIO TOTAL","TIPO DE PAGO","CANTIDAD PRODUCTOS","EMPLEADO"};
        m.setColumnIdentifiers(nombreColumnasFacturas);
        while(it.hasNext())
        {
            Facturas f = (Facturas) it.next();
            fila[0]=f.getId();
            fila[1]=f.getPrecioTotal();
            fila[2]=f.getTipoPago();
            fila[3]=f.getProductos();
            fila[4]=f.getEmpleados().getDni();
            m.addRow(fila);
        }
        return m;
    }
    
    public DefaultTableModel printFacturasByConsulta(Object consulta)
    {
        DefaultTableModel FacturasConsulta=new DefaultTableModel();
        FacturasConsulta=convertArrayListToObjectArrayFactura(getFacturasByQuestion(consulta));
        return FacturasConsulta;
    }
    
}
