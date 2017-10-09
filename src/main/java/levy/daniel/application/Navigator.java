package levy.daniel.application;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 * 
 * @author denis
 */
@ManagedBean
@SessionScoped
public class Navigator implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String P1 = "page1";
	private static final String P2 = "page2";
	private static final String P3 = "page3";
	private static final Map<Integer, String> MAP;

	static {
		MAP = new HashMap<>();
		MAP.put(1, P1);
		MAP.put(2, P2);
		MAP.put(3, P3);
	}
	private Integer nextPage;
	private Integer previousPage;
	private Integer currentPage = 1;

	
	 /**
	 * method CONSTRUCTEUR Navigator() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 */
	public Navigator() {
	}

	/**
	 * method getPages() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return : SelectItem[] :  .<br/>
	 */
	public SelectItem[] getPages() {
		return new SelectItem[] { new SelectItem(1, P1), new SelectItem(2, P2), new SelectItem(3, P3) };
	}

	/**
	 * method go() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return : String :  .<br/>
	 */
	public String go() {
		gotoPage(this.nextPage);
		return MAP.get(this.nextPage);
	}

	/**
	 * method page1() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return : String :  .<br/>
	 */
	public String page1() {
		return gotoPage(1);
	}

	/**
	 * method page2() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return : String :  .<br/>
	 */
	public String page2() {
		return gotoPage(1);
	}

	/**
	 * method page3() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return : String :  .<br/>
	 */
	public String page3() {
		return gotoPage(3);
	}

	/**
	 * method getCurrentPage() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return : Integer :  .<br/>
	 */
	public Integer getCurrentPage() {
		return this.currentPage;
	}

	/**
	 * method setCurrentPage() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pCurrentPage : void :  .<br/>
	 */
	public void setCurrentPage(Integer pCurrentPage) {
		this.currentPage = pCurrentPage;
	}

	/**
	 * method getNextPage() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return : Integer :  .<br/>
	 */
	public Integer getNextPage() {
		return this.nextPage;
	}

	/**
	 * method setNextPage() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pNextPage : void :  .<br/>
	 */
	public void setNextPage(final Integer pNextPage) {
		this.nextPage = pNextPage;
	}

	/**
	 * method getPreviousPage() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return : Integer :  .<br/>
	 */
	public Integer getPreviousPage() {
		return this.previousPage;
	}

	/**
	 * method gotoPage() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param page
	 * @return : String :  .<br/>
	 */
	private String gotoPage(final int page) {
		this.previousPage = this.currentPage;
		this.currentPage = page;
		return MAP.get(this.currentPage);
	}

	/**
	 * method getPreviousPageMessage() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return : String :  .<br/>
	 */
	public String getPreviousPageMessage() {
		String message = MessageFormat.format(getMessages("footer.previous_page_text"),
												getMessages(MAP.get(this.previousPage) + ".name"));
		return message;
	}

	/**
	 * method getMessages() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param key
	 * @return : String :  .<br/>
	 */
	private String getMessages(final String key) {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
		return bundle.getString(key);
	}
}
