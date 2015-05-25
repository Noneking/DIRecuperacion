/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Hibernate.Empleados;
import Hibernate.Fabricantes;
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
 * @author Mantenimiento
 */
public class OperacionesDBFabricantes {
    
    Session s;
    
    public void openSession()
    {
        s = NewHibernateUtil.getSessionFactory().openSession();
    }
    
    public void closeSession()
    {
        s.close();
    }
    
    public Fabricantes getFabricantesById(String denominacion)
    {
        openSession();
        Fabricantes f=(Fabricantes) s.get(Fabricantes.class, denominacion);
        closeSession();
        return f;
    }
    
    public ArrayList<Fabricantes> getFabricantesByQuestion(String question)
    {
        openSession();
        Query query=s.createQuery("from Fabricantes f where f.denominacion like :consulta OR f.localizacion like :consulta OR f.web like :consulta OR f.email like :consulta");
        ArrayList<Fabricantes> list = (ArrayList<Fabricantes>) query.setParameter("consulta", "%"+question+"%").list();
        closeSession();
        return list;
    }
    
    public ArrayList<Fabricantes> getFabricantes()
    {
        openSession();
        ArrayList<Fabricantes> list=(ArrayList<Fabricantes>) s.createQuery("from Fabricantes").list();
        closeSession();
        return list;
    }
    
    public void insertarDatosFabricante(String denominacion, String localizacion, String web, String email)
    {
        openSession();
        Transaction tx=s.beginTransaction();
        
        Fabricantes f=new Fabricantes();
        f.setDenominacion(denominacion);
        f.setLocalizacion(localizacion);
        f.setWeb(web);
        f.setEmail(email);
        s.save(f);
        
        tx.commit();
        closeSession();
    }
    
    public void modificarDatosFabricante(String denominacion,String localizacion, String web, String email)
    {
        openSession();
        Transaction tx=s.beginTransaction();
        
        Fabricantes f=(Fabricantes) s.get(Fabricantes.class, denominacion);
        f.setLocalizacion(localizacion);
        f.setWeb(web);
        f.setEmail(email);
        s.update(f);
        
        tx.commit();
        closeSession();
    }
    
    public void eliminarDatosFabricante(String denominacion)
    {
        openSession();
        Transaction tx=s.beginTransaction();
        
        Fabricantes f=(Fabricantes) s.get(Fabricantes.class, denominacion);
        s.delete(f);
        
        tx.commit();
        closeSession();
        JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO");
    }
    
    public DefaultTableModel printTable()
    {
        DefaultTableModel modeloFabricante=convertArrayListToObjectArrayFabricante(getFabricantes());
        return modeloFabricante;
    }
    
    public DefaultTableModel convertArrayListToObjectArrayFabricante(ArrayList<Fabricantes> arrayList)
    {
        Iterator it=arrayList.iterator();
        Object[] fila = new Object[4];
        DefaultTableModel m=new DefaultTableModel();
        Object[] nombreColumnasFabricante={"DENOMINACION","WEB","EMAIL","LOCALIZACION"};
        m.setColumnIdentifiers(nombreColumnasFabricante);
        while(it.hasNext())
        {
            Fabricantes f = (Fabricantes) it.next();
            fila[0]=f.getDenominacion();
            fila[1]=f.getWeb();
            fila[2]=f.getEmail();
            fila[3]=f.getLocalizacion();
            m.addRow(fila);
        }
        return m;
    }
    
    public DefaultTableModel printFabricantesByConsulta(String consulta)
    {
        DefaultTableModel FabricantesConsulta=new DefaultTableModel();
        FabricantesConsulta=convertArrayListToObjectArrayFabricante(getFabricantesByQuestion(consulta));
        return FabricantesConsulta;
    }
    
}
