/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package borges.DOM;

/**
 *
 * @author juan
 */
public class Usuario extends GenericBorgesDOM{
    
    private String login;
    private String apellido;
    private String nombre;
    private String password;
    private String mail;
    private String direccion;
    private String telefono;
    private Rol rol;
    private Localidad localidad;

    public Usuario(String login, String apellido, String nombre, String password, String mail, String direccion, String telefono, Rol rol, Localidad localidad) {
        this.login = login;
        this.apellido = apellido;
        this.nombre = nombre;
        this.password = password;
        this.mail = mail;
        this.direccion = direccion;
        this.telefono = telefono;
        this.rol = rol;
        this.localidad = localidad;
    }

    public Usuario() {
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }
    
}
