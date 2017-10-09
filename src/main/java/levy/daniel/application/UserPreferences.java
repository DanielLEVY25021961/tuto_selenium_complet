package levy.daniel.application;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 
 * @author denis
 */
@ManagedBean(name = "prefs")
@SessionScoped
public class UserPreferences implements Serializable {

	/**
	 * serialVersionUID : long :<br/>
	 * .<br/>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * locale : String :<br/>
	 * .<br/>
	 */
	private String locale = Locale.FRENCH.toString();

	/**
	 * method getLocale() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return : String :  .<br/>
	 */
	public String getLocale() {
		return this.locale;
	}

	/**
	 * method setLocale() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pLocale : void :  .<br/>
	 */
	public void setLocale(final String pLocale) {
		this.locale = pLocale;
	}

	/**
	 * method setFrenchLocale() :<br/>
	 * .<br/>
	 * <br/>
	 * : void :  .<br/>
	 */
	public void setFrenchLocale() {
		this.locale = Locale.FRENCH.toString();
	}

	
	/**
	 * method setEnglishLocale() :<br/>
	 * .<br/>
	 * <br/>
	 * : void :  .<br/>
	 */
	public void setEnglishLocale() {
		this.locale = Locale.ENGLISH.toString();
	}

}
