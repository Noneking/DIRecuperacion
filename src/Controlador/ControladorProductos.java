/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Hibernate.Fabricantes;
import Modelo.OperacionesDBFabricantes;
import Modelo.OperacionesDBProductos;
import Vista.Program;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 *
 * @author Noneking
 */
public class ControladorProductos implements ActionListener, MouseListener, KeyListener {
    
    Program p;
    
    OperacionesDBProductos op=new OperacionesDBProductos();
    
    public ControladorProductos(Program p)
    {
       this.p=p;
       
       //TABLA EMPLEADOS
        this.p.jTableProductos.addMouseListener(this);
       
       //OPCIONES PRODUCTOS
       this.p.jButtonOpcionesProductosInsertar.addActionListener(this);
       this.p.jButtonOpcionesProductosInsertar.setActionCommand("ACTIVAR_INSERCCION_PRODUCTO");
       this.p.jButtonOpcionesProductosModificar.addActionListener(this);
       this.p.jButtonOpcionesProductosModificar.setActionCommand("ACTIVAR_MODIFICACION_PRODUCTO");
       this.p.jButtonOpcionesProductosConsultar.addActionListener(this);
       this.p.jButtonOpcionesProductosConsultar.setActionCommand("ACTIVAR_CONSULTA_PRODUCTO");
       this.p.jButtonOpcionesProductosEliminar.addActionListener(this);
       this.p.jButtonOpcionesProductosEliminar.setActionCommand("ACTIVAR_ELIMINACION_PRODUCTO");
       
       //INSERTAR PRODUCTO
       this.p.jButtonProductosInsertarInsertar.addActionListener(this);
       this.p.jButtonProductosInsertarInsertar.setActionCommand("PRODUCTO_INSERTAR");
       
       //MODIFICAR PRODUCTO
       this.p.jButtonProductosModificarModificar.addActionListener(this);
       this.p.jButtonProductosModificarModificar.setActionCommand("PRODUCTO_MODIFICAR");
       
       //CONSULTAR PRODUCTO
       this.p.jTextFieldProductosConsultarConsulta.addKeyListener(this);
       this.p.jButtonProductosConsultarEliminar.addActionListener(this);
       this.p.jButtonProductosConsultarEliminar.setActionCommand("PRODUCTO_CONSULTAR_ELIMINAR");
       
       //ELIMINAR PRODUCTO
       this.p.jButtonProductosEliminarEliminar.addActionListener(this);
       this.p.jButtonProductosEliminarEliminar.setActionCommand("PRODUCTO_ELIMINAR");
       
    }
    
    public enum accionMVC
    {
        ACTIVAR_INSERCCION_PRODUCTO,
        ACTIVAR_MODIFICACION_PRODUCTO,
        ACTIVAR_CONSULTA_PRODUCTO,
        ACTIVAR_ELIMINACION_PRODUCTO,
        PRODUCTO_INSERTAR,
        PRODUCTO_MODIFICAR,
        PRODUCTO_CONSULTAR_ELIMINAR,
        PRODUCTO_ELIMINAR
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(ControladorProductos.accionMVC.valueOf(e.getActionCommand()))
        {
            case ACTIVAR_INSERCCION_PRODUCTO:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposProductos, this.p.jPanelProductosInsertar);
                this.p.jPanelProductosInsertar.setVisible(true);
                this.p.jPanelCamposProductos.add(this.p.jPanelProductosInsertar);
                this.p.jComboBoxProductosInsertarFabricante.removeAllItems();
                
                OperacionesDBFabricantes opFabr=new OperacionesDBFabricantes();
                Iterator it=opFabr.getFabricantes().iterator();
                while(it.hasNext())
                {
                    Fabricantes f=(Fabricantes) it.next();
                    this.p.jComboBoxProductosInsertarFabricante.addItem(f.getDenominacion());
                }
                break;
            case ACTIVAR_MODIFICACION_PRODUCTO:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposProductos, this.p.jPanelProductosModificar);
                this.p.jPanelProductosModificar.setVisible(true);
                this.p.jComboBoxProductosModificarFabricante.removeAllItems();
                this.p.jPanelCamposProductos.add(this.p.jPanelProductosModificar);
                break;
            case ACTIVAR_CONSULTA_PRODUCTO:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposProductos, this.p.jPanelProductosConsultar);
                this.p.jPanelProductosConsultar.setVisible(true);
                this.p.jPanelCamposProductos.add(this.p.jPanelProductosConsultar);
                break;
            case ACTIVAR_ELIMINACION_PRODUCTO:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposProductos, this.p.jPanelProductosEliminar);
                this.p.jPanelProductosEliminar.setVisible(true);
                this.p.jPanelCamposProductos.add(this.p.jPanelProductosEliminar);
                break;
            case PRODUCTO_INSERTAR:
                op.insertarDatosProducto(this.p.jTextFieldProductosInsertarNombre.getText(), this.p.jTextFieldProductosInsertarCategoria.getText(), this.p.jTextFieldProductosInsertarProcedencia.getText(), this.p.jComboBoxProductosInsertarFabricante.getSelectedItem().toString(),Integer.parseInt(this.p.jTextFieldProductosInsertarEdad.getText()), Integer.parseInt(this.p.jTextFieldProductosInsertarPrecio.getText()));
                modificarModeloTabla();
                deleteVisibleData();
                break;
            case PRODUCTO_MODIFICAR:
                op.modificarDatosProducto(Integer.parseInt(this.p.jTextFieldProductosModificarCodigo.getText()), this.p.jTextFieldProductosModificarNombre.getText(), this.p.jTextFieldProductosModificarCategoria.getText(), this.p.jTextFieldProductosModificarProcedencia.getText(), this.p.jComboBoxProductosModificarFabricante.getSelectedItem().toString(), Integer.parseInt(this.p.jTextFieldProductosModificarEdad.getText()), Double.parseDouble(this.p.jTextFieldProductosModificarPrecio.getText()));
                modificarModeloTabla();
                deleteVisibleData();
                break;
            case PRODUCTO_CONSULTAR_ELIMINAR:
                int index=this.p.jTableProductos.getSelectedRow();
                int codigo=Integer.parseInt(this.p.jTableProductos.getValueAt(index, 0).toString());
                op.eliminarDatosProductos(codigo);
                modificarModeloTabla();
                deleteVisibleData();
                break;
            case PRODUCTO_ELIMINAR:
                int fila=this.p.jTableProductos.getSelectedRow();
                int cod=Integer.parseInt((String) this.p.jTableProductos.getValueAt(fila, 0).toString());
                op.eliminarDatosProductos(cod);
                modificarModeloTabla();
                break;
            default:
                System.err.println("###-SWITCH-CASE-CONTROLADORPRODUCTOS-ACTIONPERFORMED(NO ENTRA DENTRO DE NINGÚN CASE)-###");
                break;
        }
    }
    
    public void modificarModeloTabla()
    {
        this.p.jTableProductos.setModel(op.printTable());
    }
    
    public void panelSetSizeBy(JPanel panelPadre, JPanel panelHijo)
    {
        Dimension d=panelPadre.getSize();
        panelHijo.setSize(d);
    }
    
    public void reducirPaneles()
    {
        this.p.jPanelProductosInsertar.setSize(0,0);
        this.p.jPanelProductosModificar.setSize(0,0);
        this.p.jPanelProductosConsultar.setSize(0,0);
        this.p.jPanelProductosEliminar.setSize(0,0);
    }
    
    public void setVisibleFalsePaneles()
    {
        this.p.jPanelProductosInsertar.setVisible(false);
        this.p.jPanelProductosModificar.setVisible(false);
        this.p.jPanelProductosConsultar.setVisible(false);
        this.p.jPanelProductosEliminar.setVisible(false);
    }
    
    public void deleteVisibleData()
    {
        //CAMPOS INSERCCION
        this.p.jTextFieldProductosInsertarCodigo.setText("");
        this.p.jTextFieldProductosInsertarNombre.setText("");
        this.p.jTextFieldProductosInsertarCategoria.setText("");
        this.p.jTextFieldProductosInsertarProcedencia.setText("");
        this.p.jComboBoxProductosInsertarFabricante.removeAllItems();
        this.p.jTextFieldProductosInsertarEdad.setText("");
        this.p.jTextFieldProductosInsertarPrecio.setText("");
        
        //CAMPOS MODIFICACION
        this.p.jTextFieldProductosModificarCodigo.setText("");
        this.p.jTextFieldProductosModificarNombre.setText("");
        this.p.jTextFieldProductosModificarCategoria.setText("");
        this.p.jTextFieldProductosModificarProcedencia.setText("");
        this.p.jComboBoxProductosModificarFabricante.removeAllItems();
        this.p.jTextFieldProductosModificarEdad.setText("");
        this.p.jTextFieldProductosModificarPrecio.setText("");
        
        //CAMPOS CONSULTA
        this.p.jTextFieldProductosConsultarConsulta.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.p.jComboBoxProductosModificarFabricante.removeAllItems();
        int index=this.p.jTableProductos.getSelectedRow();
        
        OperacionesDBFabricantes opFabr=new OperacionesDBFabricantes();
        Iterator it=opFabr.getFabricantes().iterator();
        while(it.hasNext())
        {
            Fabricantes f=(Fabricantes) it.next();
            this.p.jComboBoxProductosModificarFabricante.addItem(f.getDenominacion());
        }
        
        this.p.jTextFieldProductosModificarCodigo.setText(Integer.toString((int) this.p.jTableProductos.getValueAt(index, 0)));
        this.p.jTextFieldProductosModificarNombre.setText((String) this.p.jTableProductos.getValueAt(index, 1));
        this.p.jTextFieldProductosModificarCategoria.setText((String) this.p.jTableProductos.getValueAt(index, 2));
        this.p.jTextFieldProductosModificarProcedencia.setText((String) this.p.jTableProductos.getValueAt(index, 3));
        
        int selected=0;
        int size=this.p.jComboBoxProductosModificarFabricante.getItemCount();
        for(int i=0;i<size;i++)
        {
            if(this.p.jComboBoxProductosModificarFabricante.getItemAt(i).equals(this.p.jTableProductos.getValueAt(index, 4)))
            {
                selected=i;
                break;
            }
        }
        
        this.p.jComboBoxProductosModificarFabricante.setSelectedIndex(selected);
        this.p.jTextFieldProductosModificarEdad.setText(Integer.toString((int) this.p.jTableProductos.getValueAt(index, 5)));
        this.p.jTextFieldProductosModificarPrecio.setText(Double.toString((double) this.p.jTableProductos.getValueAt(index, 6)));
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        op.printProductosByConsulta(this.p.jTextFieldProductosConsultarConsulta.getText());
        this.p.jTableProductos.setModel(op.printProductosByConsulta(this.p.jTextFieldProductosConsultarConsulta.getText()));
    }
    
}
