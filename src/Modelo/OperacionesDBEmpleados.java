/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Hibernate.Empleados;
import Hibernate.NewHibernateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Alumno
 */
public class OperacionesDBEmpleados {
    
    Session s;
    
    public void openSession()
    {
        s = NewHibernateUtil.getSessionFactory().openSession();
    }
    
    public void closeSession()
    {
        s.close();
    }
    
    public Empleados getEmpleadosById(String dni)
    {
        openSession();
        Empleados e=(Empleados) s.get(Empleados.class, dni);
        closeSession();
        return e;
    }
    
    public ArrayList<Empleados> getEmpleadosByQuestion(String question)
    {
        openSession();
        Query query=s.createQuery("from Empleados e where e.dni like :consulta OR e.nombreYapellidos like :consulta OR e.fechaNacimiento like :consulta OR e.telefono like :consulta OR e.email like :consulta OR e.puesto like :consulta");
        ArrayList<Empleados> list = (ArrayList<Empleados>) query.setParameter("consulta", "%"+question+"%").list();
        closeSession();
        return list;
    }
    
    public ArrayList<Empleados> getEmpleados()
    {
        openSession();
        ArrayList<Empleados>list =(ArrayList<Empleados>) s.createQuery("from Empleados").list();
        closeSession();
        return list;
    }
    
    public void insertarDatosEmpleado(String dni, String nombreYapellidos, String fechaNacimiento, int telefono, String email, String puesto)
    {
        try {
            openSession();
            Transaction tx = s.beginTransaction();
            
            Empleados e=new Empleados();
            e.setDni(dni);
            e.setNombreYapellidos(nombreYapellidos);
            e.setFechaNacimiento(fechaNacimiento);
            e.setTelefono(telefono);
            e.setEmail(email);
            e.setPuesto(puesto);
            s.save(e);
            
            tx.commit();
            closeSession();
        } catch (org.hibernate.exception.ConstraintViolationException  sql){
            JOptionPane.showMessageDialog(null, "CLAVE PRIMARIA REPETIDA");
        }
    }
    
    public void modificarDatosEmpleado(String dni,String nombreYapellidos, String fechaNacimiento, int telefono, String email, String puesto)
    {
        openSession();
        Transaction tx = s.beginTransaction();
        
        Empleados e=(Empleados) s.get(Empleados.class, dni);
        e.setNombreYapellidos(nombreYapellidos);
        e.setFechaNacimiento(fechaNacimiento);
        e.setTelefono(telefono);
        e.setEmail(email);
        e.setPuesto(puesto);
        s.update(e);
        
        tx.commit();
        s.close();
    }
    
    public void eliminarDatosEmpleados(String dni)
    {
        openSession();
        Transaction tx = s.beginTransaction();
        
        Empleados e=(Empleados) s.get(Empleados.class, dni);
        s.delete(e);
        
        tx.commit();
        closeSession();
        JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO");
    }
    
    public DefaultTableModel printTable()
    {
        DefaultTableModel modeloEmpleado=convertArrayListToObjectArrayEmpleado(getEmpleados());
        return modeloEmpleado;
    }
    
    public DefaultTableModel convertArrayListToObjectArrayEmpleado(ArrayList<Empleados> arrayList)
    {
        Iterator it=arrayList.iterator();
        Object[] fila = new Object[6];
        DefaultTableModel m=new DefaultTableModel();
        Object[] nombreColumnasEmpleado={"DNI","NOMBRE Y APELLIDOS","FECHA NACIMIENTO","TELEFONO","EMAIL","PUESTO"};
        m.setColumnIdentifiers(nombreColumnasEmpleado);
        while(it.hasNext())
        {
            Empleados e = (Empleados) it.next();
            fila[0]=e.getDni();
            fila[1]=e.getNombreYapellidos();
            fila[2]=e.getFechaNacimiento();
            fila[3]=e.getTelefono();
            fila[4]=e.getEmail();
            fila[5]=e.getPuesto();
            m.addRow(fila);
        }
        return m;
    }
    
    public DefaultTableModel printEmpleadosByConsulta(String consulta)
    {
        DefaultTableModel EmpleadosConsulta=new DefaultTableModel();
        EmpleadosConsulta=convertArrayListToObjectArrayEmpleado(getEmpleadosByQuestion(consulta));
        return EmpleadosConsulta;
    }
    
}
