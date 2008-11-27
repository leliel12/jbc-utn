

/** 
 * Una interface que proporciona un nombre y descripcion del objeto de la clase que lo implemente.
 * @version Diciembre de 2005
 */
public interface Describible extends Comparable
{
	/** Acceso a una descripcion del objeto.
     * Esta descripcion varia de acuerdo al tipo y caracteristicas del objeto
     * @return String que es la descripcion del objeto
     */
	public String getDescripcion();
	
	/** Acceso al nombre del objeto
     * @return String que es el nombre del objeto
     */
	public String getNombre();
}