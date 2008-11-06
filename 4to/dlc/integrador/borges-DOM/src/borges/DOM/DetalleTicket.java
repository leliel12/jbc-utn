/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package borges.DOM;

/**
 *
 * @author juan
 */
public class DetalleTicket extends GenericBorgesDOM {
    
    private Ticket ticket;
    private Libro libro;
    private Servicio servicio;
    private int cantidad;

    public DetalleTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public DetalleTicket(Ticket ticket, Libro libro, int cantidad) {
        this(ticket);
        this.libro = libro;
        this.cantidad = cantidad;
    }

    public DetalleTicket(Ticket ticket, Servicio servicio, int cantidad) {
        this(ticket);
        this.servicio = servicio;
        this.cantidad = cantidad;
    }

    public DetalleTicket() {
    }

    
    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.servicio=null;
        this.libro = libro;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.libro=null;
        this.servicio = servicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
