/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.ControladorProductos;
import Hibernate.Empleados;
import Hibernate.Fabricantes;
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
 * @author Noneking
 */
public class OperacionesDBProductos {
    
    Session s;
    
    public void openSession()
    {
        s = NewHibernateUtil.getSessionFactory().openSession();
    }
    
    public void closeSession()
    {
        s.close();
    }
    
    public Productos getProductosById(int codigo)
    {
        openSession();
        Productos p=(Productos) s.get(Productos.class, codigo);
        closeSession();
        return p;
    }
    
    public ArrayList<Productos> getProductosByQuestion(String question)
    {
        openSession();
//        Query query=s.createQuery("from Productos p where p.codigo like :consulta OR p.nombre like :consulta OR p.categoria like :consulta OR p.edad like :consulta OR p.procedencia like :consulta OR p.precio like :consulta");
        Query query=s.createQuery("from Productos p where p.nombre like :consulta OR p.categoria like :consulta OR p.procedencia like :consulta");
        ArrayList<Productos> list = (ArrayList<Productos>) query.setParameter("consulta", "%"+question+"%").list();
        closeSession();
        return list;
    }
    
    public ArrayList<Productos> getProductos()
    {
        openSession();
        ArrayList<Productos>list =(ArrayList<Productos>) s.createQuery("from Productos").list();
        closeSession();
        return list;
    }
    
    public void insertarDatosProducto(String nombre, String categoria, String procedencia, String fabricante, int edad, double precio)
    {
        try {
            openSession();
            Transaction tx = s.beginTransaction();
            
            Productos p=new Productos();
            p.setNombre(nombre);
            p.setCategoria(categoria);
            p.setProcedencia(procedencia);
            
            OperacionesDBFabricantes opFabr=new OperacionesDBFabricantes();
            Fabricantes f=opFabr.getFabricantesById(fabricante);
            
            p.setFabricantes(f);
            p.setEdad(edad);
            p.setPrecio(precio);
            s.save(p);
            
            tx.commit();
            closeSession();
        } catch (org.hibernate.exception.ConstraintViolationException  sql){
            JOptionPane.showMessageDialog(null, "YA EXISTE OTRO PRODUCTO CON ESE NOMBRE");
        }
    }
    
    public void modificarDatosProducto(int codigo, String nombre, String categoria, String procedencia, String fabricante, int edad, double precio)
    {
        try
        {
        openSession();
        Transaction tx = s.beginTransaction();
        
        Productos p=(Productos) s.get(Productos.class, codigo);
        p.setNombre(nombre);
        p.setCategoria(categoria);
        p.setProcedencia(procedencia);
        
        OperacionesDBFabricantes opFabr=new OperacionesDBFabricantes();
        Fabricantes f=opFabr.getFabricantesById(fabricante);
        p.setFabricantes(f);
        
        p.setEdad(edad);
        p.setPrecio(precio);
        s.update(p);
        
        tx.commit();
        s.close();
        }
        catch(ConstraintViolationException e)
        {
            JOptionPane.showMessageDialog(null, "YA EXISTE OTRO PRODUCTO CON ESE NOMBRE");
        }
    }
    
    public void eliminarDatosProductos(int codigo)
    {
        openSession();
        Transaction tx = s.beginTransaction();
        
        Productos p=(Productos) s.get(Productos.class, codigo);
        s.delete(p);
        
        tx.commit();
        closeSession();
        JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO");
    }
    
    public DefaultTableModel printTable()
    {
        DefaultTableModel modeloProducto=convertArrayListToObjectArrayProducto(getProductos());
        return modeloProducto;
    }
    
    public DefaultTableModel convertArrayListToObjectArrayProducto(ArrayList<Productos> arrayList)
    {
        Iterator it=arrayList.iterator();
        Object[] fila = new Object[7];
        DefaultTableModel m=new DefaultTableModel();
        Object[] nombreColumnasProductos={"CODIGO","NOMBRE","CATEGORÍA","PROCEDENCIA","FABRICANTE","EDAD","PRECIO"};
        m.setColumnIdentifiers(nombreColumnasProductos);
        while(it.hasNext())
        {
            Productos p = (Productos) it.next();
            fila[0]=p.getCodigo();
            fila[1]=p.getNombre();
            fila[2]=p.getCategoria();
            fila[3]=p.getProcedencia();
            fila[4]=p.getFabricantes().getDenominacion();
            fila[5]=p.getEdad();
            fila[6]=p.getPrecio();
            m.addRow(fila);
        }
        return m;
    }
    
    public DefaultTableModel printProductosByConsulta(String consulta)
    {
        DefaultTableModel ProductosConsulta=new DefaultTableModel();
        ProductosConsulta=convertArrayListToObjectArrayProducto(getProductosByQuestion(consulta));
        return ProductosConsulta;
    }
    
}
