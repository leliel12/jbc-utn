import java.util.*;
import java.io.*;

/**
 * Clase que proporciona manejo de date de alta para las instancias de las clases que hereden de esta
 * @version Febrero 2006
 */
public class Dateable implements Comparable,Serializable
{
	/** Date de alta de la instancia*/
	protected Date dateAlta;
	
	public Dateable()
	{
		dateAlta=new Date();
	}
	
    /**
    * Acceso al date de alta
    * @return date de alta
    */ 
	public Date getDateAlta()
	{
		return dateAlta;
	}

	/**
    * Modifica el date de alta
    * @param dateAlta Date que representa el date de alta
    */ 	
	public void setDateAlta(Date dateAlta)
	{
		this.dateAlta=dateAlta;
	}      
	
   /**
    * Metodo que indica si el date de alta es anterior al pasado por parametro
    * @param dateHasta Date para filtrar (limite de date superior)
    * @return boolean que indica si el date de alta es anterior al date de parametro
    */	
	public boolean esDateAltaHasta(Date dateHasta)
	{
		if (dateHasta==null) return false;
		return dateAlta.before(dateHasta);
	}

   /**
    * Metodo que indica si el date de alta es posterior al pasado por parametro
    * @param dateHasta Date para filtrar (limite de date superior)
    * @return boolean que indica si el date de alta es posterior al date de parametro
    */	
	public boolean esDateAltaDesde(Date dateDesde)
	{
		if (dateDesde==null) return false;
		return dateAlta.after(dateDesde);
	}
	
   /**
    * Metodo que indica si el date de alta esta dentro de los limites de date pasados por parametro
    * @param dateDesde Date para filtrar (limite de date inferior)
    * @param dateHasta Date para filtrar (limite de date superior)
    * @return boolean que indica si el date de alta esta dentro de los dates de parametro
    */
	public boolean esDateAltaDesdeHasta(Date dateDesde,Date dateHasta)
	{
		if (dateDesde==null||dateHasta==null) return false;
		return dateAlta.after(dateDesde)&&dateAlta.before(dateHasta);
	}		
	
	/**
	 * Retorna el codigo del dateable
	 * @return codigo del dateable
	 */
	public int hashCode()
	{
		return getDateAlta().hashCode();
	}
	
   /**
    * Redefine el metodo equals() heredado de Object
    * @param o un Object que sera el dateable a comparar
    * @return boolean que indica si el dateable es igual al pasado por parametro
    */	
	public boolean equals(Object o)
	{
		if (o==null||!(o instanceof Dateable)) return false;
		Dateable t=(Dateable)o;
		return this.hashCode()==t.hashCode();
	}
	
  	/**
    * Define el metodo compareTo de la interface Comparable
    * @param o un Object que sera el dateable a comparar
    * @return int que indica si el codigo del dateable es igual, menor o mayor que el codigo del dateable pasado por parametro
    */
	public int compareTo(Object o)
	{
		if (o==null||!(o instanceof Dateable)) return -1;
		Dateable t=(Dateable)o;
		return this.getDateAlta().hashCode()-t.getDateAlta().hashCode();
	}	
}