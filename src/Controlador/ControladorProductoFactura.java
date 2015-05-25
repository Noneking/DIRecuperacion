/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Hibernate.Facturas;
import Hibernate.Productos;
import Hibernate.Productosfacturas;
import Modelo.OperacionesDBFacturas;
import Modelo.OperacionesDBProductoFactura;
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
public class ControladorProductoFactura implements ActionListener, MouseListener, KeyListener {
    
    Program p;
    
    OperacionesDBProductoFactura oppf=new OperacionesDBProductoFactura();
    
    public ControladorProductoFactura(Program p)
    {
        this.p=p;
        
        //TABLA PRODUCTOS/FACTURAS
        this.p.jTableProductosYFacturas.addMouseListener(this);
        
        //OPCIONES PRODUCTOS&FACTURA
        this.p.jButtonOpcionesProductosYFacturasInsertar.addActionListener(this);
        this.p.jButtonOpcionesProductosYFacturasInsertar.setActionCommand("ACTIVAR_INSERCCION_PRODUCTOSFACTURAS");
        this.p.jButtonOpcionesProductosYFacturasModificar.addActionListener(this);
        this.p.jButtonOpcionesProductosYFacturasModificar.setActionCommand("ACTIVAR_MODIFICACION_PRODUCTOSFACTURAS");
        this.p.jButtonOpcionesProductosYFacturasConsultar.addActionListener(this);
        this.p.jButtonOpcionesProductosYFacturasConsultar.setActionCommand("ACTIVAR_CONSULTA_PRODUCTOSFACTURAS");
        this.p.jButtonOpcionesProductosYFacturasEliminar.addActionListener(this);
        this.p.jButtonOpcionesProductosYFacturasEliminar.setActionCommand("ACTIVAR_ELIMINACION_PRODUCTOSFACTURAS");
    
        //INSERTAR PRODUCTO/FACTURA
        this.p.jButtonProductoYFacturaInsertarInsertar.addActionListener(this);
        this.p.jButtonProductoYFacturaInsertarInsertar.setActionCommand("PRODUCTO_FACTURA_INSERTAR");
        
        //MODIFICAR PRODUCTO/FACTURA
        this.p.jButtonProductoYFacturaModificarModificar.addActionListener(this);
        this.p.jButtonProductoYFacturaModificarModificar.setActionCommand("PRODUCTO_FACTURA_MODIFICAR");
        
        //CONSULTAR PRODUCTO/FACTURA
        this.p.jTextFieldProductoYFacturaConsultarConsulta.addKeyListener(this);
        this.p.jButtonProductoYFacturaConsultarEliminar.addActionListener(this);
        this.p.jButtonProductoYFacturaConsultarEliminar.setActionCommand("PRODUCTO_FACTURA_CONSULTAR_ELIMINAR");
        
        //ELIMINAR PRODUCTO/FACTURA
        this.p.jButtonProductoYFacturaEliminarEliminar.addActionListener(this);
        this.p.jButtonProductoYFacturaEliminarEliminar.setActionCommand("PRODUCTO_FACTURA_ELIMINAR");
        
    }
    
    public enum accionMVC
    {
        ACTIVAR_INSERCCION_PRODUCTOSFACTURAS,
        ACTIVAR_MODIFICACION_PRODUCTOSFACTURAS,
        ACTIVAR_CONSULTA_PRODUCTOSFACTURAS,
        ACTIVAR_ELIMINACION_PRODUCTOSFACTURAS,
        PRODUCTO_FACTURA_INSERTAR,
        PRODUCTO_FACTURA_MODIFICAR,
        PRODUCTO_FACTURA_CONSULTAR_ELIMINAR,
        PRODUCTO_FACTURA_ELIMINAR
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(ControladorProductoFactura.accionMVC.valueOf(e.getActionCommand()))
        {
            case ACTIVAR_INSERCCION_PRODUCTOSFACTURAS:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposProductosYFacturas, this.p.jPanelProductoYFacturaInsertar);
                this.p.jPanelProductoYFacturaInsertar.setVisible(true);
                this.p.jPanelCamposProductosYFacturas.add(this.p.jPanelProductoYFacturaInsertar);
                
                OperacionesDBProductos opp=new OperacionesDBProductos();
                OperacionesDBFacturas opf=new OperacionesDBFacturas();
                Iterator it=opf.getFacturas().iterator();
                while(it.hasNext())
                {
                    Facturas f=(Facturas) it.next();
                    this.p.jComboBoxProductoYFacturaInsertarIdFactura.addItem(f.getId());
                }
                it=opp.getProductos().iterator();
                while(it.hasNext())
                {
                    Productos p=(Productos) it.next();
                    this.p.jComboBoxProductoYFacturaInsertarCodigoProducto.addItem(p.getCodigo());
                }
                break;
            case ACTIVAR_MODIFICACION_PRODUCTOSFACTURAS:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposProductosYFacturas, this.p.jPanelProductoYFacturaModificar);
                this.p.jPanelProductoYFacturaModificar.setVisible(true);
                this.p.jPanelCamposProductosYFacturas.add(this.p.jPanelProductoYFacturaModificar);
                break;
            case ACTIVAR_CONSULTA_PRODUCTOSFACTURAS:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposProductosYFacturas, this.p.jPanelProductoYFacturaConsultar);
                this.p.jPanelProductoYFacturaConsultar.setVisible(true);
                this.p.jPanelCamposProductosYFacturas.add(this.p.jPanelProductoYFacturaConsultar);
                break;
            case ACTIVAR_ELIMINACION_PRODUCTOSFACTURAS:
                deleteVisibleData();
                reducirPaneles();
                setVisibleFalsePaneles();
                panelSetSizeBy(this.p.jPanelCamposProductosYFacturas, this.p.jPanelProductoYFacturaEliminar);
                this.p.jPanelProductoYFacturaEliminar.setVisible(true);
                this.p.jPanelCamposProductosYFacturas.add(this.p.jPanelProductoYFacturaEliminar);
                break;
            case PRODUCTO_FACTURA_INSERTAR:
                it=oppf.getProductosFacturas().iterator();
                boolean repetido=false;
                while(it.hasNext())
                {
                    Productosfacturas pf=(Productosfacturas) it.next();
                    if(pf.getProductos().getCodigo().equals(this.p.jComboBoxProductoYFacturaInsertarCodigoProducto.getSelectedItem().toString()))
                    {
                        if(pf.getFacturas().getId().equals(Integer.parseInt(this.p.jComboBoxProductoYFacturaInsertarIdFactura.getSelectedItem().toString())));
                        {
                            repetido=true;
                        }
                    }
                }
                if(repetido==false)
                {
                    oppf.insertarDatosProductosFacturas(Integer.parseInt(this.p.jComboBoxProductoYFacturaInsertarCodigoProducto.getSelectedItem().toString()), Integer.parseInt(this.p.jComboBoxProductoYFacturaInsertarIdFactura.getSelectedItem().toString()));
                }
//                oppf.insertarDatosProductosFacturas(Integer.parseInt(this.p.jComboBoxProductoYFacturaInsertarCodigoProducto.getSelectedItem().toString()), Integer.parseInt(this.p.jComboBoxProductoYFacturaInsertarIdFactura.getSelectedItem().toString()));
                modificarModeloTabla();
                deleteVisibleData();
                OperacionesDBFacturas opfact=new OperacionesDBFacturas();
                it=opfact.getFacturas().iterator();
                while(it.hasNext())
                {
                    Facturas f=(Facturas) it.next();
                    this.p.jComboBoxProductoYFacturaInsertarIdFactura.addItem(f.getId());
                }
                OperacionesDBProductos opdbp=new OperacionesDBProductos();
                it=opdbp.getProductos().iterator();
                while(it.hasNext())
                {
                    Productos p=(Productos) it.next();
                    this.p.jComboBoxProductoYFacturaInsertarCodigoProducto.addItem(p.getCodigo());
                }
                break;
            case PRODUCTO_FACTURA_MODIFICAR:
                int fila=this.p.jTableProductosYFacturas.getSelectedRow();
                oppf.modificarDatosProductosFacturas(Integer.parseInt(this.p.jTableProductosYFacturas.getValueAt(fila, 0).toString()), Integer.parseInt(this.p.jTableProductosYFacturas.getValueAt(fila, 1).toString()), Integer.parseInt(this.p.jComboBoxProductoYFacturaModificarCodigoProducto.getSelectedItem().toString()), Integer.parseInt(this.p.jComboBoxProductoYFacturaModificarIdFactura.getSelectedItem().toString()));
                modificarModeloTabla();
                break;
            case PRODUCTO_FACTURA_CONSULTAR_ELIMINAR:
                
                break;
            case PRODUCTO_FACTURA_ELIMINAR:
                fila=this.p.jTableProductosYFacturas.getSelectedRow();
                oppf.eliminarDatosProductosFacturas((int) this.p.jTableProductosYFacturas.getValueAt(fila, 0), (int) this.p.jTableProductosYFacturas.getValueAt(fila, 1));
                modificarModeloTabla();
                break;
            default:
                System.err.println("###-SWITCH-CASE-CONTROLADORPRODUCTOFACTURA-ACTIONPERFORMED(NO ENTRA DENTRO DE NINGÚN CASE)-###");
                break;
        }
    }
    
    public void modificarModeloTabla()
    {
        this.p.jTableProductosYFacturas.setModel(oppf.printTable());
    }
    
    public void panelSetSizeBy(JPanel panelPadre, JPanel panelHijo)
    {
        Dimension d=panelPadre.getSize();
        panelHijo.setSize(d);
    }
    
    public void reducirPaneles()
    {
        this.p.jPanelProductoYFacturaInsertar.setSize(0,0);
        this.p.jPanelProductoYFacturaModificar.setSize(0,0);
        this.p.jPanelProductoYFacturaConsultar.setSize(0,0);
        this.p.jPanelProductoYFacturaEliminar.setSize(0,0);
    }
    
    public void setVisibleFalsePaneles()
    {
        this.p.jPanelProductoYFacturaInsertar.setVisible(false);
        this.p.jPanelProductoYFacturaModificar.setVisible(false);
        this.p.jPanelProductoYFacturaConsultar.setVisible(false);
        this.p.jPanelProductoYFacturaEliminar.setVisible(false);
    }
    
    public void deleteVisibleData()
    {
        //CAMPOS INSERCCION
        this.p.jComboBoxProductoYFacturaInsertarCodigoProducto.removeAllItems();
        this.p.jComboBoxProductoYFacturaInsertarIdFactura.removeAllItems();
        
        //CAMPOS MODIFICACION
        this.p.jComboBoxProductoYFacturaModificarCodigoProducto.removeAllItems();
        this.p.jComboBoxProductoYFacturaModificarIdFactura.removeAllItems();
        
        //CAMPOS CONSULTA
        this.p.jTextFieldProductoYFacturaConsultarConsulta.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.p.jComboBoxProductoYFacturaModificarCodigoProducto.removeAllItems();
        this.p.jComboBoxProductoYFacturaModificarIdFactura.removeAllItems();
        int fila=this.p.jTableProductosYFacturas.getSelectedRow();
        OperacionesDBProductos opp=new OperacionesDBProductos();
        OperacionesDBFacturas opf=new OperacionesDBFacturas();
        Iterator it=opp.getProductos().iterator();
        while(it.hasNext())
        {
            Productos product=(Productos) it.next();
            this.p.jComboBoxProductoYFacturaModificarCodigoProducto.addItem(product.getCodigo());
        }
        it=opf.getFacturas().iterator();
        while(it.hasNext())
        {
            Facturas fact=(Facturas) it.next();
            this.p.jComboBoxProductoYFacturaModificarIdFactura.addItem(fact.getId());
        }
        int codProducto=(int)this.p.jTableProductosYFacturas.getValueAt(fila, 0);
        int idFactura=(int)this.p.jTableProductosYFacturas.getValueAt(fila, 1);
        
        for(int i=0;i<this.p.jComboBoxProductoYFacturaModificarCodigoProducto.getItemCount();i++)
        {
            if(this.p.jComboBoxProductoYFacturaModificarCodigoProducto.getItemAt(i).equals(codProducto))
            {
                this.p.jComboBoxProductoYFacturaModificarCodigoProducto.setSelectedIndex(i);
            }
        }
        for(int i=0;i<this.p.jComboBoxProductoYFacturaModificarIdFactura.getItemCount();i++)
        {
            if(this.p.jComboBoxProductoYFacturaModificarIdFactura.getItemAt(i).equals(idFactura))
            {
                this.p.jComboBoxProductoYFacturaModificarIdFactura.setSelectedIndex(i);
            }
        }
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
        
    }
    
}
