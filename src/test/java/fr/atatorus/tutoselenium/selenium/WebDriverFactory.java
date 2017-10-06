package fr.atatorus.tutoselenium.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.opera.core.systems.OperaDriver;

/**
 * class WebDriverFactory :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 8 févr. 2017
 *
 */
public final class WebDriverFactory {

	/**
	 * method CONSTRUCTEUR WebDriverFactory() :<br/>
	 * .<br/>
	 * <br/>
	 */
	private WebDriverFactory() {
		super();
	}


	/**
	 * class Type :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * - Exemple d'utilisation :<br/>
	 * <br/>
	 * 
	 * - Mots-clé :<br/>
	 * <br/>
	 *
	 * - Dépendances :<br/>
	 * <br/>
	 *
	 *
	 * @author daniel.levy Lévy
	 * @version 1.0
	 * @since 8 févr. 2017
	 *
	 */
	public enum Type {
		/**
		 * FIREFOX : Type :<br/>
		 * .<br/>
		 */
		FIREFOX,
		/**
		 * CHROME : Type :<br/>
		 * .<br/>
		 */
		CHROME,
		/**
		 * OPERA : Type :<br/>
		 * .<br/>
		 */
		OPERA,
		/**
		 * HTML_UNIT : Type :<br/>
		 * .<br/>
		 */
		HTML_UNIT,
		/**
		 * IE : Type :<br/>
		 * .<br/>
		 */
		IE
	}


	/**
	 * CHROME_DRIVER_PATH : String :<br/>
	 * .<br/>
	 */
	private static final String CHROME_DRIVER_PATH = "/usr/lib64/chromium/chromedriver";

	static {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
	}



	/**
	 * method getDriver() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pDriverType
	 * @return : WebDriver : .<br/>
	 */
	public static WebDriver getDriver(Type pDriverType) {

		switch (pDriverType) {
			case FIREFOX:
				return new FirefoxDriver();
			case CHROME:
				return new ChromeDriver();
			case OPERA:
				return new OperaDriver();
			case IE:
				throw new IllegalArgumentException();
				// $CASES-OMITTED$
			default:
				return new HtmlUnitDriver(true);
		}
	}
}
