/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.OperacionesDBEmpleados;
import Vista.Program;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
public class ControladorEmpleados implements ActionListener, MouseListener, KeyListener, FocusListener {
    
    Program p;
    
    OperacionesDBEmpleados ope=new OperacionesDBEmpleados();
    
    public ControladorEmpleados(Program p)
    {
        this.p=p;
        
        //TABLA EMPLEADOS
        this.p.jTableEmpleados.addMouseListener(this);
        
        //OPCIONES EMPLEADOS
        this.p.jButtonOpcionesEmpleadosInsertar.addActionListener(this);
        this.p.jButtonOpcionesEmpleadosInsertar.setActionCommand("ACTIVAR_INSERCCION_EMPLEADOS");
        this.p.jButtonOpcionesEmpleadosModificar.addActionListener(this);
        this.p.jButtonOpcionesEmpleadosModificar.setActionCommand("ACTIVAR_MODIFICACION_EMPLEADOS");
        this.p.jButtonOpcionesEmpleadosConsultar.addActionListener(this);
        this.p.jButtonOpcionesEmpleadosConsultar.setActionCommand("ACTIVAR_CONSULTA_EMPLEADOS");
        this.p.jButtonOpcionesEmpleadosEliminar.addActionListener(this);
        this.p.jButtonOpcionesEmpleadosEliminar.setActionCommand("ACTIVAR_ELIMINACION_EMPLEADOS");
        
        //INSERTAR EMPLEADO
        this.p.jButtonEmpleadosInsertarInsertar.addActionListener(this);
        this.p.jButtonEmpleadosInsertarInsertar.setActionCommand("EMPLEADO_INSERTAR");
        
        //MODIFICAR EMPLEADO
        this.p.jButtonEmpleadosModificarModificar.addActionListener(this);
        this.p.jButtonEmpleadosModificarModificar.setActionCommand("EMPLEADO_MODIFICAR");
        
        //CONSULTAR EMPLEADO
        this.p.jTextFieldEmpleadosConsultarConsulta.addKeyListener(this);
        this.p.jButtonEmpleadosConsultarEliminar.addActionListener(this);
        this.p.jButtonEmpleadosConsultarEliminar.setActionCommand("EMPLEADO_CONSULTAR_ELIMINAR");
        
        //ELIMINAR EMPLEADO
        this.p.jButtonEmpleadosEliminarEliminar.addActionListener(this);
        this.p.jButtonEmpleadosEliminarEliminar.setActionCommand("EMPLEADO_ELIMINAR");
        
        //SELECCION
        this.p.jTextFieldEmpleadosInsertarFechaNacimientoDia.addFocusListener(this);
        this.p.jTextFieldEmpleadosInsertarFechaNacimientoMes.addFocusListener(this);
        this.p.jTextFieldEmpleadosInsertarFechaNacimientoAnio.addFocusListener(this);
        this.p.jTextFieldEmpleadosModificarFechaNacimientoDia.addFocusListener(this);
        this.p.jTextFieldEmpleadosModificarFechaNacimientoMes.addFocusListener(this);
        this.p.jTextFieldEmpleadosModificarFechaNacimientoAnio.addFocusListener(this);
        
    }
    
    public enum accionMVC
    {
        ACTIVAR_INSERCCION_EMPLEADOS,
        ACTIVAR_MODIFICACION_EMPLEADOS,
        ACTIVAR_CONSULTA_EMPLEADOS,
        ACTIVAR_ELIMINACION_EMPLEADOS,
        EMPLEADO_INSERTAR,
        EMPLEADO_MODIFICAR,
        EMPLEADO_CONSULTAR_ELIMINAR,
        EMPLEADO_ELIMINAR
    }

    @Override
    public void actionPerformed(ActionEvent e){
        switch(ControladorEmpleados.accionMVC.valueOf(e.getActionCommand()))
        {
            case ACTIVAR_INSERCCION_EMPLEADOS:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposEmpleados, this.p.jPanelEmpleadosInsertar);
                this.p.jPanelEmpleadosInsertar.setVisible(true);
                this.p.jPanelCamposEmpleados.add(this.p.jPanelEmpleadosInsertar);
                this.p.jTextFieldEmpleadosInsertarFechaNacimientoDia.setText("DD");
                this.p.jTextFieldEmpleadosInsertarFechaNacimientoMes.setText("MM");
                this.p.jTextFieldEmpleadosInsertarFechaNacimientoAnio.setText("AAAA");
                break;
            case ACTIVAR_MODIFICACION_EMPLEADOS:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposEmpleados, this.p.jPanelEmpleadosModificar);
                this.p.jPanelEmpleadosModificar.setVisible(true);
                this.p.jPanelCamposEmpleados.add(this.p.jPanelEmpleadosModificar);
                this.p.jTextFieldEmpleadosModificarFechaNacimientoDia.setText("DD");
                this.p.jTextFieldEmpleadosModificarFechaNacimientoMes.setText("MM");
                this.p.jTextFieldEmpleadosModificarFechaNacimientoAnio.setText("AAAA");
                break;
            case ACTIVAR_CONSULTA_EMPLEADOS:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposEmpleados, this.p.jPanelEmpleadosConsultar);
                this.p.jPanelEmpleadosConsultar.setVisible(true);
                this.p.jPanelCamposEmpleados.add(this.p.jPanelEmpleadosConsultar);
                break;
            case ACTIVAR_ELIMINACION_EMPLEADOS:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposEmpleados, this.p.jPanelEmpleadosEliminar);
                this.p.jPanelEmpleadosEliminar.setVisible(true);
                this.p.jPanelCamposEmpleados.add(this.p.jPanelEmpleadosEliminar);
                break;
            case EMPLEADO_INSERTAR:
                ope.insertarDatosEmpleado(this.p.jTextFieldEmpleadosInsertarDni.getText(), this.p.jTextFieldEmpleadosInsertarNombre.getText()+"-"+this.p.jTextFieldEmpleadosInsertarApellidos.getText(), this.p.jTextFieldEmpleadosInsertarFechaNacimientoDia.getText()+"/"+this.p.jTextFieldEmpleadosInsertarFechaNacimientoMes.getText()+"/"+this.p.jTextFieldEmpleadosInsertarFechaNacimientoAnio.getText(), Integer.parseInt(this.p.jTextFieldEmpleadosInsertarTelefono.getText()), this.p.jTextFieldEmpleadosInsertarEmail.getText(), this.p.jTextFieldEmpleadosInsertarPuesto.getText());
                modificarModeloTabla();
                deleteVisibleData();
                this.p.jTextFieldEmpleadosInsertarFechaNacimientoDia.setText("DD");
                this.p.jTextFieldEmpleadosInsertarFechaNacimientoMes.setText("MM");
                this.p.jTextFieldEmpleadosInsertarFechaNacimientoAnio.setText("AAAA");
                break;
            case EMPLEADO_MODIFICAR:
                ope.modificarDatosEmpleado(this.p.jTextFieldEmpleadosModificarDni.getText(), this.p.jTextFieldEmpleadosModificarNombre.getText()+"-"+this.p.jTextFieldEmpleadosModificarApellidos.getText(), this.p.jTextFieldEmpleadosModificarFechaNacimientoDia.getText()+"/"+this.p.jTextFieldEmpleadosModificarFechaNacimientoMes.getText()+"/"+this.p.jTextFieldEmpleadosModificarFechaNacimientoAnio.getText(), Integer.parseInt(this.p.jTextFieldEmpleadosModificarTelefono.getText()), this.p.jTextFieldEmpleadosModificarEmail.getText(), this.p.jTextFieldEmpleadosModificarPuesto.getText());
                modificarModeloTabla();
                deleteVisibleData();
                this.p.jTextFieldEmpleadosModificarFechaNacimientoDia.setText("DD");
                this.p.jTextFieldEmpleadosModificarFechaNacimientoMes.setText("MM");
                this.p.jTextFieldEmpleadosModificarFechaNacimientoAnio.setText("AAAA");
                break;
            case EMPLEADO_CONSULTAR_ELIMINAR:
                try
                {
                    int index=this.p.jTableEmpleados.getSelectedRow();
                    ope.eliminarDatosEmpleados((String) this.p.jTableEmpleados.getValueAt(index, 0));
                    modificarModeloTabla();
                    deleteVisibleData();
                }
                catch(ArrayIndexOutOfBoundsException a)
                {
                    JOptionPane.showMessageDialog(null, "SELECCIONE UN EMPLEADO");
                }
                break;
            case EMPLEADO_ELIMINAR:
                try
                {
                int index=this.p.jTableEmpleados.getSelectedRow();
                ope.eliminarDatosEmpleados((String) this.p.jTableEmpleados.getValueAt(index, 0));
                modificarModeloTabla();
                }
                catch(ArrayIndexOutOfBoundsException a)
                {
                    JOptionPane.showMessageDialog(null, "SELECCIONE UN EMPLEADO");
                }
                break;
            default:
                System.err.println("###-SWITCH-CASE-CONTROLADOREMPLEADOS-ACTIONPERFORMED(NO ENTRA DENTRO DE NINGÚN CASE)-###");
                break;
        }
    }
    
    public void modificarModeloTabla()
    {
        this.p.jTableEmpleados.setModel(ope.printTable());
    }
    
    public void panelSetSizeBy(JPanel panelPadre, JPanel panelHijo)
    {
        Dimension d=panelPadre.getSize();
        panelHijo.setSize(d);
    }
    
    public void reducirPaneles()
    {
        this.p.jPanelEmpleadosInsertar.setSize(0, 0);
        this.p.jPanelEmpleadosModificar.setSize(0, 0);
        this.p.jPanelEmpleadosConsultar.setSize(0, 0);
        this.p.jPanelEmpleadosEliminar.setSize(0, 0);
    }
    
    public void setVisibleFalsePaneles()
    {
        this.p.jPanelEmpleadosInsertar.setVisible(false);
        this.p.jPanelEmpleadosModificar.setVisible(false);
        this.p.jPanelEmpleadosConsultar.setVisible(false);
        this.p.jPanelEmpleadosEliminar.setVisible(false);
    }
    
    public void deleteVisibleData()
    {
        //CAMPO INSERCCION
        this.p.jTextFieldEmpleadosInsertarDni.setText("");
        this.p.jTextFieldEmpleadosInsertarNombre.setText("");
        this.p.jTextFieldEmpleadosInsertarApellidos.setText("");
        this.p.jTextFieldEmpleadosInsertarFechaNacimientoDia.setText("");
        this.p.jTextFieldEmpleadosInsertarFechaNacimientoMes.setText("");
        this.p.jTextFieldEmpleadosInsertarFechaNacimientoAnio.setText("");
        this.p.jTextFieldEmpleadosInsertarTelefono.setText("");
        this.p.jTextFieldEmpleadosInsertarEmail.setText("");
        this.p.jTextFieldEmpleadosInsertarPuesto.setText("");
        
        //CAMPO MODIFICACION
        this.p.jTextFieldEmpleadosModificarDni.setText("");
        this.p.jTextFieldEmpleadosModificarNombre.setText("");
        this.p.jTextFieldEmpleadosModificarApellidos.setText("");
        this.p.jTextFieldEmpleadosModificarFechaNacimientoDia.setText("");
        this.p.jTextFieldEmpleadosModificarFechaNacimientoMes.setText("");
        this.p.jTextFieldEmpleadosModificarFechaNacimientoAnio.setText("");
        this.p.jTextFieldEmpleadosModificarTelefono.setText("");
        this.p.jTextFieldEmpleadosModificarEmail.setText("");
        this.p.jTextFieldEmpleadosModificarPuesto.setText("");
        
        //CAMPOS CONSULTA
        this.p.jTextFieldEmpleadosConsultarConsulta.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila=this.p.jTableEmpleados.getSelectedRow();
        this.p.jTextFieldEmpleadosModificarDni.setText(this.p.jTableEmpleados.getValueAt(fila, 0).toString());
        String[] nombreYapellidos=this.p.jTableEmpleados.getValueAt(fila, 1).toString().split("-");
        this.p.jTextFieldEmpleadosModificarNombre.setText(nombreYapellidos[0]);
        this.p.jTextFieldEmpleadosModificarApellidos.setText(nombreYapellidos[1]);
        String[] fechaNacimiento=this.p.jTableEmpleados.getValueAt(fila, 2).toString().split("/");
        this.p.jTextFieldEmpleadosModificarFechaNacimientoDia.setText(fechaNacimiento[0]);
        this.p.jTextFieldEmpleadosModificarFechaNacimientoMes.setText(fechaNacimiento[1]);
        this.p.jTextFieldEmpleadosModificarFechaNacimientoAnio.setText(fechaNacimiento[2]);
        this.p.jTextFieldEmpleadosModificarTelefono.setText(this.p.jTableEmpleados.getValueAt(fila, 3).toString());
        this.p.jTextFieldEmpleadosModificarEmail.setText(this.p.jTableEmpleados.getValueAt(fila, 4).toString());
        this.p.jTextFieldEmpleadosModificarPuesto.setText(this.p.jTableEmpleados.getValueAt(fila, 5).toString());
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
    public void focusGained(FocusEvent e) {
        Object o = e.getSource(); 
        if(o instanceof javax.swing.JTextField)
        {
            javax.swing.JTextField txt = (javax.swing.JTextField) o; 
            txt.selectAll();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        Object o = e.getSource(); 
        if(o instanceof javax.swing.JTextField)
        { 
            javax.swing.JTextField txt = (javax.swing.JTextField) o; 
            txt.setSelectionStart(0); 
            txt.setSelectionEnd(txt.getText().length());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        ope.printEmpleadosByConsulta(this.p.jTextFieldEmpleadosConsultarConsulta.getText());
        this.p.jTableEmpleados.setModel(ope.printEmpleadosByConsulta(this.p.jTextFieldEmpleadosConsultarConsulta.getText()));
    }
    
}
