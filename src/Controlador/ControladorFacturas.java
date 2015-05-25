/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Hibernate.Empleados;
import Hibernate.Fabricantes;
import Modelo.OperacionesDBEmpleados;
import Modelo.OperacionesDBFabricantes;
import Modelo.OperacionesDBFacturas;
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
public class ControladorFacturas implements ActionListener, MouseListener, KeyListener {
    
    Program p;
    
    OperacionesDBFacturas opFact=new OperacionesDBFacturas();
    
    public ControladorFacturas(Program p)
    {
        this.p=p;
        
        //TABLA FACTURAS
        this.p.jTableFacturas.addMouseListener(this);
        
        //OPCIONES FACTURAS
        this.p.jButtonOpcionesFacturasInsertar.addActionListener(this);
        this.p.jButtonOpcionesFacturasInsertar.setActionCommand("ACTIVAR_INSERCCION_FACTURAS");
        this.p.jButtonOpcionesFacturasModificar.addActionListener(this);
        this.p.jButtonOpcionesFacturasModificar.setActionCommand("ACTIVAR_MODIFICACION_FACTURAS");
        this.p.jButtonOpcionesFacturasConsultar.addActionListener(this);
        this.p.jButtonOpcionesFacturasConsultar.setActionCommand("ACTIVAR_CONSULTA_FACTURAS");
        this.p.jButtonOpcionesFacturasEliminar.addActionListener(this);
        this.p.jButtonOpcionesFacturasEliminar.setActionCommand("ACTIVAR_ELIMINACION_FACTURAS");
        
        //INSERTAR FACTURA
        this.p.jButtonFacturasInsertarInsertar.addActionListener(this);
        this.p.jButtonFacturasInsertarInsertar.setActionCommand("FACTURA_INSERTAR");
        
        //MODIFICAR FACTURA
        this.p.jButtonFacturasModificarModificar.addActionListener(this);
        this.p.jButtonFacturasModificarModificar.setActionCommand("FACTURA_MODIFICAR");
        
        //CONSULTAR FACTURA
        this.p.jTextFieldFacturasConsultarConsulta.addKeyListener(this);
        this.p.jButtonFacturasConsultarEliminar.addActionListener(this);
        this.p.jButtonFacturasConsultarEliminar.setActionCommand("FACTURA_CONSULTAR_ELIMINAR");
        
        //ELIMINAR FACTURA
        this.p.jButtonFacturasEliminarEliminar.addActionListener(this);
        this.p.jButtonFacturasEliminarEliminar.setActionCommand("FACTURA_ELIMINAR");
        
    }
    
    public enum accionMVC
    {
        ACTIVAR_INSERCCION_FACTURAS,
        ACTIVAR_MODIFICACION_FACTURAS,
        ACTIVAR_CONSULTA_FACTURAS,
        ACTIVAR_ELIMINACION_FACTURAS,
        FACTURA_INSERTAR,
        FACTURA_MODIFICAR,
        FACTURA_CONSULTAR_ELIMINAR,
        FACTURA_ELIMINAR
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(ControladorFacturas.accionMVC.valueOf(e.getActionCommand()))
        {
            case ACTIVAR_INSERCCION_FACTURAS:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposFacturas, this.p.jPanelFacturasInsertar);
                this.p.jPanelFacturasInsertar.setVisible(true);
                this.p.jPanelCamposFacturas.add(this.p.jPanelFacturasInsertar);
                
                OperacionesDBEmpleados ope=new OperacionesDBEmpleados();
                Iterator it=ope.getEmpleados().iterator();
                while(it.hasNext())
                {
                    Empleados em=(Empleados) it.next();
                    this.p.jComboBoxFacturasInsertarEmpleado.addItem(em.getDni());
                }
                break;
            case ACTIVAR_MODIFICACION_FACTURAS:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposFacturas, this.p.jPanelFacturasModificar);
                this.p.jPanelFacturasModificar.setVisible(true);
                this.p.jPanelCamposFacturas.add(this.p.jPanelFacturasModificar);
                break;
            case ACTIVAR_CONSULTA_FACTURAS:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposFacturas, this.p.jPanelFacturasConsultar);
                this.p.jPanelFacturasConsultar.setVisible(true);
                this.p.jPanelCamposFacturas.add(this.p.jPanelFacturasConsultar);
                break;
            case ACTIVAR_ELIMINACION_FACTURAS:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposFacturas, this.p.jPanelFacturasEliminar);
                this.p.jPanelFacturasEliminar.setVisible(true);
                this.p.jPanelCamposFacturas.add(this.p.jPanelFacturasEliminar);
                break;
            case FACTURA_INSERTAR:
                opFact.insertarDatosFacturas(Double.parseDouble(this.p.jTextFieldFacturasInsertarPrecio.getText()), this.p.jComboBoxFacturasInsertarTipoPago.getSelectedItem().toString(), Integer.parseInt(this.p.jTextFieldFacturasInsertarProductos.getText()), this.p.jComboBoxFacturasInsertarEmpleado.getSelectedItem().toString());
                modificarModeloTabla();
                modificarModeloTabla();
                deleteVisibleData();
                ope=new OperacionesDBEmpleados();
                it=ope.getEmpleados().iterator();
                while(it.hasNext())
                {
                    Empleados em=(Empleados) it.next();
                    this.p.jComboBoxFacturasInsertarEmpleado.addItem(em.getDni());
                }
                break;
            case FACTURA_MODIFICAR:
                opFact.modificarDatosFacturas(Integer.parseInt(this.p.jTextFieldFacturasModificarProductos.getText()), Double.parseDouble(this.p.jTextFieldFacturasInsertarPrecio.getText()), this.p.jComboBoxFacturasInsertarTipoPago.getSelectedItem().toString(), Integer.parseInt(this.p.jTextFieldFacturasInsertarProductos.getText()), this.p.jComboBoxFacturasInsertarEmpleado.getSelectedItem().toString());
                modificarModeloTabla();
                deleteVisibleData();
                break;
            case FACTURA_CONSULTAR_ELIMINAR:
                int index=this.p.jTableFacturas.getSelectedRow();
                int id=Integer.parseInt(this.p.jTableFacturas.getValueAt(index, 0).toString());
                opFact.eliminarDatosProductos(id);
                modificarModeloTabla();
                deleteVisibleData();
                break;
            case FACTURA_ELIMINAR:
                int fila=this.p.jTableFacturas.getSelectedRow();
                int identificador=Integer.parseInt(this.p.jTableFacturas.getValueAt(fila, 0).toString());
                opFact.eliminarDatosProductos(identificador);
                modificarModeloTabla();
                deleteVisibleData();
                break;
            default:
                System.err.println("###-SWITCH-CASE-CONTROLADORFACTURAS-ACTIONPERFORMED(NO ENTRA DENTRO DE NINGÚN CASE)-###");
                break;
        }
    }
    
    public void modificarModeloTabla()
    {
        this.p.jTableFacturas.setModel(opFact.printTable());
    }
    
    public void panelSetSizeBy(JPanel panelPadre, JPanel panelHijo)
    {
        Dimension d=panelPadre.getSize();
        panelHijo.setSize(d);
    }
    
    public void reducirPaneles()
    {
        this.p.jPanelFacturasInsertar.setSize(0,0);
        this.p.jPanelFacturasModificar.setSize(0,0);
        this.p.jPanelFacturasConsultar.setSize(0,0);
        this.p.jPanelFacturasEliminar.setSize(0,0);
    }
    
    public void setVisibleFalsePaneles()
    {
        this.p.jPanelFacturasInsertar.setVisible(false);
        this.p.jPanelFacturasModificar.setVisible(false);
        this.p.jPanelFacturasConsultar.setVisible(false);
        this.p.jPanelFacturasEliminar.setVisible(false);
    }
    
    public void deleteVisibleData()
    {
        //CAMPOS INSERCCION
        this.p.jTextFieldFacturasInsertarProductos.setText("");
        this.p.jComboBoxFacturasInsertarTipoPago.removeAllItems();
        this.p.jComboBoxFacturasInsertarTipoPago.addItem("EFECTIVO");
        this.p.jComboBoxFacturasInsertarTipoPago.addItem("TARJETA");
        this.p.jTextFieldFacturasInsertarPrecio.setText("");
        this.p.jComboBoxFacturasInsertarEmpleado.removeAllItems();
        
        //CAMPOS MODIFICACION
        this.p.jTextFieldFacturasModificarProductos.setText("");
        this.p.jComboBoxFacturasModificarTipoPago.removeAllItems();
        this.p.jComboBoxFacturasModificarTipoPago.addItem("EFECTIVO");
        this.p.jComboBoxFacturasModificarTipoPago.addItem("TARJETA");
        this.p.jTextFieldFacturasModificarPrecio.setText("");
        this.p.jComboBoxFacturasModificarEmpleado.removeAllItems();
        
        //CAMPOS CONSULTA
        this.p.jTextFieldFacturasConsultarConsulta.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.p.jComboBoxFacturasModificarEmpleado.removeAllItems();
        int index=this.p.jTableFacturas.getSelectedRow();
        
        OperacionesDBEmpleados ope=new OperacionesDBEmpleados();
        Iterator it=ope.getEmpleados().iterator();
        while(it.hasNext())
        {
            Empleados empleados=(Empleados) it.next();
            this.p.jComboBoxFacturasModificarEmpleado.addItem(empleados.getDni());
        }
        
        this.p.jTextFieldFacturasModificarId.setText(Integer.toString((int) this.p.jTableFacturas.getValueAt(index, 0)));
        this.p.jTextFieldFacturasModificarProductos.setText(Integer.toString((int) this.p.jTableFacturas.getValueAt(index, 3)));
        
        int selectedTipoPago=0;
        int sizeTipoPago=this.p.jComboBoxFacturasModificarTipoPago.getItemCount();
        for(int i=0;i<sizeTipoPago;i++)
        {
            if(this.p.jComboBoxFacturasModificarTipoPago.getItemAt(i).equals(this.p.jTableFacturas.getValueAt(index, 2)))
            {
                selectedTipoPago=i;
                break;
            }
        }
        this.p.jComboBoxFacturasModificarTipoPago.setSelectedIndex(selectedTipoPago);
        
        this.p.jTextFieldFacturasModificarPrecio.setText(Double.toString((double) this.p.jTableFacturas.getValueAt(index, 1)));
        
        int selected=0;
        int size=this.p.jComboBoxFacturasModificarEmpleado.getItemCount();
        for(int i=0;i<size;i++)
        {
            if(this.p.jComboBoxFacturasModificarEmpleado.getItemAt(i).equals(this.p.jTableFacturas.getValueAt(index, 4)))
            {
                selected=i;
                break;
            }
        }
        
        this.p.jComboBoxProductosModificarFabricante.setSelectedIndex(selected);
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
        opFact.printFacturasByConsulta((Object) this.p.jTextFieldFacturasConsultarConsulta.getText());
        this.p.jTableFacturas.setModel(opFact.printFacturasByConsulta(this.p.jTextFieldFacturasConsultarConsulta.getText()));
    }
    
}
