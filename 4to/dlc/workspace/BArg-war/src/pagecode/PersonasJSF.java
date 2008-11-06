/**
 * 
 */
package pagecode;

import utn.frc.dlc.barg.db.SqlManager;
import javax.faces.component.html.HtmlOutputText;

import utn.frc.dlc.javalib.base.Persona;
import utn.frc.dlc.javalib.db.SqlManager;

import com.ibm.faces.component.UIColumnEx;
import com.ibm.faces.component.html.HtmlDataTableEx;
import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.component.html.HtmlForm;

/**
 * @author scarafia
 *
 */
public class PersonasJSF extends PageCodeBase {
	SqlManager sql = null;

	protected HtmlScriptCollector scriptCollector1;
	protected HtmlDataTableEx tblPersonas;
	protected UIColumnEx clNombre;
	protected HtmlOutputText hdNombre;
	protected HtmlOutputText hdDocumento;
	protected UIColumnEx clDocumento;
	protected HtmlOutputText txtDocumento;

	protected HtmlForm form1;

	protected HtmlOutputText txtNombre;
	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}

	protected HtmlDataTableEx getTblPersonas() {
		if (tblPersonas == null) {
			tblPersonas = (HtmlDataTableEx) findComponentInRoot("tblPersonas");
		}
		return tblPersonas;
	}

	protected UIColumnEx getClNombre() {
		if (clNombre == null) {
			clNombre = (UIColumnEx) findComponentInRoot("clNombre");
		}
		return clNombre;
	}

	protected HtmlOutputText getHdNombre() {
		if (hdNombre == null) {
			hdNombre = (HtmlOutputText) findComponentInRoot("hdNombre");
		}
		return hdNombre;
	}

	protected HtmlOutputText getHdDocumento() {
		if (hdDocumento == null) {
			hdDocumento = (HtmlOutputText) findComponentInRoot("hdDocumento");
		}
		return hdDocumento;
	}

	protected UIColumnEx getClDocumento() {
		if (clDocumento == null) {
			clDocumento = (UIColumnEx) findComponentInRoot("clDocumento");
		}
		return clDocumento;
	}

	public PersonasJSF() {
		super();
		this.sql = (SqlManager) this.getSessionScope().get("sql");
	}
	
	protected HtmlOutputText getTxtDocumento() {
		if (txtDocumento == null) {
			txtDocumento = (HtmlOutputText) findComponentInRoot("txtDocumento");
		}
		return txtDocumento;
	}

	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}

	protected HtmlOutputText getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = (HtmlOutputText) findComponentInRoot("txtNombre");
		}
		return txtNombre;
	}
}