/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Hibernate.Empleados;
import Hibernate.Productos;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Alumno
 */
public class OperacionesDB {
    
    String ruta="src/Files/jugueteria.db";
    
    OperacionesDBEmpleados opdbe=new OperacionesDBEmpleados();
    OperacionesDBFabricantes opdbfab=new OperacionesDBFabricantes();
    OperacionesDBFacturas opdbfact=new OperacionesDBFacturas();
    OperacionesDBProductos opdbp=new OperacionesDBProductos();
    OperacionesDBProductoFactura opdbpf=new OperacionesDBProductoFactura();
    
    public void generateXML()
    {
        OperacionesDBProductos p=new OperacionesDBProductos();
        
        try 
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            //ROOTELEMENT
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("products");
            doc.appendChild(rootElement);
            
            Iterator it=p.getProductos().iterator();
            while(it.hasNext())
            {
                Element element = doc.createElement("product");
                rootElement.appendChild(element);
                
                Productos product=(Productos) it.next();
                
                //PRODUCT ELEMENTS
                //STAFF ELEMENT
                Element codigo = doc.createElement("codigo");
                codigo.appendChild(doc.createTextNode(Integer.toString(product.getCodigo())));
                element.appendChild(codigo);
                
                Element nombre = doc.createElement("nombre");
                nombre.appendChild(doc.createTextNode(product.getNombre()));
                element.appendChild(nombre);
                
                Element categoria = doc.createElement("categoria");
                categoria.appendChild(doc.createTextNode(product.getCategoria()));
                element.appendChild(categoria);
                
                Element edad = doc.createElement("edad");
                edad.appendChild(doc.createTextNode(Integer.toString(product.getEdad())));
                element.appendChild(edad);
                
                Element procedencia = doc.createElement("procedencia");
                procedencia.appendChild(doc.createTextNode(product.getProcedencia()));
                element.appendChild(procedencia);
                
                Element precio = doc.createElement("precio");
                precio.appendChild(doc.createTextNode(Double.toString(product.getPrecio())));
                element.appendChild(precio);
                
                Element fabricante = doc.createElement("fabricante");
                fabricante.appendChild(doc.createTextNode(product.getFabricantes().getDenominacion()));
                element.appendChild(fabricante);
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/Files/products.xml"));
            
            transformer.transform(source, result);
            
            JOptionPane.showMessageDialog(null, "INVENTARIO CREADO");
        }
        catch (ParserConfigurationException ex)
        {
            JOptionPane.showMessageDialog(null, "HA HABIDO UN ERROR");
            Logger.getLogger(OperacionesDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            JOptionPane.showMessageDialog(null, "HA HABIDO UN ERROR");
            Logger.getLogger(OperacionesDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            JOptionPane.showMessageDialog(null, "HA HABIDO UN ERROR");
            Logger.getLogger(OperacionesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection SQLiteConection()
    {
        Connection conexion=null;
        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection(ruta);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OperacionesDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR CON SQLITE");
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR CON SQLITE");
        }
        return conexion;
    }
    
    public void fromMySQLtoSQLite()
    {
        try {
            Connection c=SQLiteConection();
            Statement st=c.createStatement();
            
            if(st.execute("select * from sqlite_master where type=table;")==false)
            {
                
            }
            
            Iterator it=null;
            
            ArrayList<Empleados> e=opdbe.getEmpleados();
            it=e.iterator();
            while (it.hasNext()) {
                Empleados empleado=(Empleados) it.next();
                
                String dni=empleado.getDni();
                String nombreYapellidos=empleado.getNombreYapellidos();
                String fechaNacimiento=empleado.getFechaNacimiento();
                int telefono=empleado.getTelefono();
                String email=empleado.getEmail();
                String puesto=empleado.getPuesto();
                
                
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
