/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package borges.DOM;

import java.util.ArrayList;

/**
 *
 * @author juan
 */
public class Ticket extends GenericBorgesDOM {
    
    private ArrayList<DetalleTicket> detalles;
    private String fecha;
    private float totalPrecio;
    private FormaPago formaPago;
    private Usuario usuario;

    public Ticket(DetalleTicket[] detalles, String fecha, float totalPrecio, FormaPago formaPago, Usuario usuario) {
        this.addDetalles(detalles);
        this.fecha = fecha;
        this.totalPrecio = totalPrecio;
        this.formaPago = formaPago;
        this.usuario = usuario;
    }

    public Ticket(String fecha, float totalPrecio, FormaPago formaPago, Usuario usuario) {
        this.fecha = fecha;
        this.totalPrecio = totalPrecio;
        this.formaPago = formaPago;
        this.usuario = usuario;
    }

    public Ticket() {
    }
    
    public DetalleTicket[] getDetalles() {
        return (DetalleTicket[]) detalles.toArray();
    }
    
    public void addDetalles(DetalleTicket[] detalles){
        for (int i = 0; i < detalles.length; i++) {
            DetalleTicket detalleTicket = detalles[i];
            this.addDetalle(detalleTicket);
        }
    }

    public void addDetalle(DetalleTicket detalle) {
        detalle.setTicket(this); //soy YO tu ticket detalle puto!
        this.detalles.add(detalle);
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(float totalPrecio) {
        this.totalPrecio = totalPrecio;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
