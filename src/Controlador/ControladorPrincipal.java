/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.OperacionesDB;
import Vista.Program;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author PC26_AULA1
 */
public class ControladorPrincipal implements ActionListener, MouseListener, KeyListener, ChangeListener {
    
    //VistaPrincipal
    Program p;
    
    //Controladores
    ControladorProductos cp;
    ControladorEmpleados ce;
    ControladorFacturas cfac;
    ControladorFabricantes cfabr;
    ControladorProductoFactura cpf;
    
    //Modelo
    OperacionesDB opdb=new OperacionesDB();
    
    public ControladorPrincipal(Program p)
    {
        this.p=p;
        this.p.jTabbedPane.addChangeListener(this);
        this.p.jMenuGenerarInventario.addActionListener(this);
        this.p.jMenuGenerarInventario.setActionCommand("ACTIVAR_GENERAR_INVENTARIO");
        this.p.jMenuItemCopiaSeguridad.addActionListener(this);
        this.p.jMenuItemCopiaSeguridad.setActionCommand("ACTIVAR_COPIA_SEGURIDAD");
        
        cp=new ControladorProductos(p);
        ce=new ControladorEmpleados(p);
        cfac=new ControladorFacturas(p);
        cfabr=new ControladorFabricantes(p);
        cpf=new ControladorProductoFactura(p);
        
        cp.modificarModeloTabla();
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        int tabbedIndex=this.p.jTabbedPane.getSelectedIndex();
        switch(tabbedIndex)
        {
//            ce.modificarModeloTabla("electrodomestico");
            case 0:
                cp.modificarModeloTabla();
                reducirPaneles();
                setVisibleFalsePaneles();
                break;
            case 1:
                ce.modificarModeloTabla();
                reducirPaneles();
                setVisibleFalsePaneles();
                break;
            case 2:
                cfac.modificarModeloTabla();
                reducirPaneles();
                setVisibleFalsePaneles();
                break;
            case 3:
                cfabr.modificarModeloTabla();
                reducirPaneles();
                setVisibleFalsePaneles();
                break;
            case 4:
                cpf.modificarModeloTabla();
                reducirPaneles();
                setVisibleFalsePaneles();
                break;
            default:
                System.err.println("###-SWITCH-CASE-CONTROLADORPRINCIPAL-STATEDCHANGE(NO ENTRA DENTRO DE NINGÚN CASE)-###");
                break;
        }
    }
    
    public enum accionMVC
    {
        ACTIVAR_GENERAR_INVENTARIO,
        ACTIVAR_COPIA_SEGURIDAD
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(ControladorPrincipal.accionMVC.valueOf(e.getActionCommand()))
        {
            case ACTIVAR_GENERAR_INVENTARIO:
                opdb.generateXML();
                break;
            case ACTIVAR_COPIA_SEGURIDAD:
                
                break;
        }
    }
    
    public void panelSetSizeBy(JPanel panelPadre, JPanel panelHijo)
    {
        Dimension d=panelPadre.getSize();
        panelHijo.setSize(d);
    }
    
    public void reducirPaneles()
    {
        this.p.jPanelProductosInsertar.setSize(0, 0);
        this.p.jPanelProductosModificar.setSize(0, 0);
        this.p.jPanelProductosConsultar.setSize(0, 0);
        this.p.jPanelProductosEliminar.setSize(0, 0);
        this.p.jPanelEmpleadosInsertar.setSize(0, 0);
        this.p.jPanelEmpleadosModificar.setSize(0, 0);
        this.p.jPanelEmpleadosConsultar.setSize(0, 0);
        this.p.jPanelEmpleadosEliminar.setSize(0, 0);
        this.p.jPanelFacturasInsertar.setSize(0, 0);
        this.p.jPanelFacturasModificar.setSize(0, 0);
        this.p.jPanelFacturasConsultar.setSize(0, 0);
        this.p.jPanelFacturasEliminar.setSize(0, 0);
        this.p.jPanelFabricantesInsertar.setSize(0, 0);
        this.p.jPanelFabricantesModificar.setSize(0, 0);
        this.p.jPanelFabricantesConsultar.setSize(0, 0);
        this.p.jPanelFabricantesEliminar.setSize(0, 0);
        
        /**
         * INTRODUCIR REDUCCIÓN PARA LOS PANELES DE P&F(PRODUCTO Y FACTURA)
         */
        
    }
    
    public void setVisibleFalsePaneles()
    {
        this.p.jPanelProductosInsertar.setVisible(false);
        this.p.jPanelProductosModificar.setVisible(false);
        this.p.jPanelProductosConsultar.setVisible(false);
        this.p.jPanelProductosEliminar.setVisible(false);
        this.p.jPanelEmpleadosInsertar.setVisible(false);
        this.p.jPanelEmpleadosModificar.setVisible(false);
        this.p.jPanelEmpleadosConsultar.setVisible(false);
        this.p.jPanelEmpleadosEliminar.setVisible(false);
        this.p.jPanelFacturasInsertar.setVisible(false);
        this.p.jPanelFacturasModificar.setVisible(false);
        this.p.jPanelFacturasConsultar.setVisible(false);
        this.p.jPanelFacturasEliminar.setVisible(false);
        this.p.jPanelFabricantesInsertar.setVisible(false);
        this.p.jPanelFabricantesModificar.setVisible(false);
        this.p.jPanelFabricantesConsultar.setVisible(false);
        this.p.jPanelFabricantesEliminar.setVisible(false);
        
        /**
         * INTRODUCIR VISIBLE FALSE PARA LOS PANELES DE P&F(PRODUCTO Y FACTURA)
         */
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
