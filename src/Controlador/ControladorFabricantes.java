/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.OperacionesDBFabricantes;
import Vista.Program;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Noneking
 */
public class ControladorFabricantes implements ActionListener, MouseListener, KeyListener {
    
    Program p;
    
    OperacionesDBFabricantes opf=new OperacionesDBFabricantes();
    
    public ControladorFabricantes(Program p)
    {
        this.p=p;
        
        //TABLA EMPLEADOS
        this.p.jTableFabricantes.addMouseListener(this);
        
        //OPCIONES FABRICANTES
        this.p.jButtonOpcionesFabricantesInsertar.addActionListener(this);
        this.p.jButtonOpcionesFabricantesInsertar.setActionCommand("ACTIVAR_INSERCCION_FABRICANTES");
        this.p.jButtonOpcionesFabricantesModificar.addActionListener(this);
        this.p.jButtonOpcionesFabricantesModificar.setActionCommand("ACTIVAR_MODIFICACION_FABRICANTES");
        this.p.jButtonOpcionesFabricantesConsultar.addActionListener(this);
        this.p.jButtonOpcionesFabricantesConsultar.setActionCommand("ACTIVAR_CONSULTA_FABRICANTES");
        this.p.jButtonOpcionesFabricantesEliminar.addActionListener(this);
        this.p.jButtonOpcionesFabricantesEliminar.setActionCommand("ACTIVAR_ELIMINACION_FABRICANTES");
        
        //INSERTAR FABRICANTE
        this.p.jButtonFabricantesInsertarInsertar.addActionListener(this);
        this.p.jButtonFabricantesInsertarInsertar.setActionCommand("FABRICANTE_INSERTAR");
        
        //MODIFICAR FABRICANTE
        this.p.jButtonFabricantesModificarModificar.addActionListener(this);
        this.p.jButtonFabricantesModificarModificar.setActionCommand("FABRICANTE_MODIFICAR");
        
        //CONSULTAR FABRICANTE
        this.p.jTextFieldFabricantesConsultarConsulta.addKeyListener(this);
        this.p.jButtonFabricantesConsultarEliminar.addActionListener(this);
        this.p.jButtonFabricantesConsultarEliminar.setActionCommand("FABRICANTE_CONSULTAR_ELIMINAR");
        
        //ELIMINAR FABRICANTE
        this.p.jButtonFabricantesEliminarEliminar.addActionListener(this);
        this.p.jButtonFabricantesEliminarEliminar.setActionCommand("FABRICANTE_ELIMINAR");
        
        //SELECCION
        
    }
    
    public enum accionMVC
    {
        ACTIVAR_INSERCCION_FABRICANTES,
        ACTIVAR_MODIFICACION_FABRICANTES,
        ACTIVAR_CONSULTA_FABRICANTES,
        ACTIVAR_ELIMINACION_FABRICANTES,
        FABRICANTE_INSERTAR,
        FABRICANTE_MODIFICAR,
        FABRICANTE_CONSULTAR_ELIMINAR,
        FABRICANTE_ELIMINAR
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(ControladorFabricantes.accionMVC.valueOf(e.getActionCommand()))
        {
            case ACTIVAR_INSERCCION_FABRICANTES:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposFabricantes, this.p.jPanelFabricantesInsertar);
                this.p.jPanelFabricantesInsertar.setVisible(true);
                this.p.jPanelCamposFabricantes.add(this.p.jPanelFabricantesInsertar);
                break;
            case ACTIVAR_MODIFICACION_FABRICANTES:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposFabricantes, this.p.jPanelFabricantesModificar);
                this.p.jPanelFabricantesModificar.setVisible(true);
                this.p.jPanelCamposFabricantes.add(this.p.jPanelFabricantesModificar);
                break;
            case ACTIVAR_CONSULTA_FABRICANTES:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposFabricantes, this.p.jPanelFabricantesConsultar);
                this.p.jPanelFabricantesConsultar.setVisible(true);
                this.p.jPanelCamposFabricantes.add(this.p.jPanelFabricantesConsultar);
                break;
            case ACTIVAR_ELIMINACION_FABRICANTES:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposFabricantes, this.p.jPanelFabricantesEliminar);
                this.p.jPanelFabricantesEliminar.setVisible(true);
                this.p.jPanelCamposFabricantes.add(this.p.jPanelFabricantesEliminar);
                break;
            case FABRICANTE_INSERTAR:
                opf.insertarDatosFabricante(this.p.jTextFieldFabricantesInsertarDenominacion.getText(), this.p.jTextFieldFabricantesInsertarLocalizacion.getText(), this.p.jTextFieldFabricantesInsertarWeb.getText(), this.p.jTextFieldFabricantesInsertarEmail.getText());
                modificarModeloTabla();
                deleteVisibleData();
                break;
            case FABRICANTE_MODIFICAR:
                opf.modificarDatosFabricante(this.p.jTextFieldFabricantesModificarDenominacion.getText(), this.p.jTextFieldFabricantesModificarLocalizacion.getText(), this.p.jTextFieldFabricantesModificarWeb.getText(), this.p.jTextFieldFabricantesModificarEmail.getText());
                modificarModeloTabla();
                deleteVisibleData();
                break;
            case FABRICANTE_CONSULTAR_ELIMINAR:
                try
                {
                int index=this.p.jTableFabricantes.getSelectedRow();
                opf.eliminarDatosFabricante((String) this.p.jTableFabricantes.getValueAt(index, 0));
                modificarModeloTabla();
                deleteVisibleData();
                }catch(ArrayIndexOutOfBoundsException a)
                {
                    JOptionPane.showMessageDialog(null, "SELECCIONE UN FABRICANTE");
                }
                break;
            case FABRICANTE_ELIMINAR:
                try
                {
                int indexFabricante=this.p.jTableFabricantes.getSelectedRow();
                opf.eliminarDatosFabricante((String) this.p.jTableFabricantes.getValueAt(indexFabricante, 0));
                modificarModeloTabla();
                }
                catch(ArrayIndexOutOfBoundsException a)
                {
                    JOptionPane.showMessageDialog(null, "SELECCIONE UN FABRICANTE");
                }
                break;
            default:
                System.err.println("###-SWITCH-CASE-CONTROLADORFABRICANTES-ACTIONPERFORMED(NO ENTRA DENTRO DE NINGÚN CASE)-###");
                break;
        }
    }
    
    public void modificarModeloTabla()
    {
        this.p.jTableFabricantes.setModel(opf.printTable());
    }
    
    public void panelSetSizeBy(JPanel panelPadre, JPanel panelHijo)
    {
        Dimension d=panelPadre.getSize();
        panelHijo.setSize(d);
    }
    
    public void reducirPaneles()
    {
        this.p.jPanelFabricantesInsertar.setSize(0,0);
        this.p.jPanelFabricantesModificar.setSize(0,0);
        this.p.jPanelFabricantesConsultar.setSize(0,0);
        this.p.jPanelFabricantesEliminar.setSize(0,0);
    }
    
    public void setVisibleFalsePaneles()
    {
        this.p.jPanelFabricantesInsertar.setVisible(false);
        this.p.jPanelFabricantesModificar.setVisible(false);
        this.p.jPanelFabricantesConsultar.setVisible(false);
        this.p.jPanelFabricantesEliminar.setVisible(false);
    }
    
    public void deleteVisibleData()
    {
        //CAMPOS INSERCCION
        this.p.jTextFieldProductosInsertarNombre.setText("");
        this.p.jTextFieldFabricantesInsertarDenominacion.setText("");
        this.p.jTextFieldFabricantesInsertarWeb.setText("");
        this.p.jTextFieldFabricantesInsertarEmail.setText("");
        this.p.jTextFieldFabricantesInsertarLocalizacion.setText("");
        
        //CAMPOS MODIFICACION
        this.p.jTextFieldProductosModificarNombre.setText("");
        this.p.jTextFieldFabricantesModificarDenominacion.setText("");
        this.p.jTextFieldFabricantesModificarWeb.setText("");
        this.p.jTextFieldFabricantesModificarEmail.setText("");
        this.p.jTextFieldFabricantesModificarLocalizacion.setText("");
        
        //CAMPOS CONSULTA
        this.p.jTextFieldFabricantesConsultarConsulta.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila=this.p.jTableFabricantes.getSelectedRow();
        this.p.jTextFieldFabricantesModificarDenominacion.setText((String) this.p.jTableFabricantes.getValueAt(fila, 0));
        this.p.jTextFieldFabricantesModificarLocalizacion.setText((String) this.p.jTableFabricantes.getValueAt(fila, 1));
        this.p.jTextFieldFabricantesModificarWeb.setText((String) this.p.jTableFabricantes.getValueAt(fila, 2));
        this.p.jTextFieldFabricantesModificarEmail.setText((String) this.p.jTableFabricantes.getValueAt(fila, 3));
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
        opf.printFabricantesByConsulta(this.p.jTextFieldFabricantesConsultarConsulta.getText());
        this.p.jTableFabricantes.setModel(opf.printFabricantesByConsulta(this.p.jTextFieldFabricantesConsultarConsulta.getText()));
    }
    
}
