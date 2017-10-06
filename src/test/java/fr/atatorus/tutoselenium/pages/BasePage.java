package fr.atatorus.tutoselenium.pages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;
import java.util.Properties;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.support.FindBy;

import com.thoughtworks.selenium.Selenium;

/**
 * class BasePage :<br/>
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
public abstract class BasePage extends FluentPage {

	private static final String PAGE_TO_LOAD_TIMEOUT = "250";


	/**
	 * locale : Locale :<br/>
	 * .<br/>
	 */
	protected static Locale locale = Locale.FRENCH;



	/**
	 * method resetLocale() :<br/>
	 * .<br/>
	 * <br/>
	 * : void : .<br/>
	 */
	public static void resetLocale() {

		locale = Locale.FRENCH;
	}


	/**
	 * PAGES_FR : String[] :<br/>
	 * .<br/>
	 */
	private static final String[] PAGES_FR = { "", "page une", "page deux", "page trois" };


	/**
	 * PAGES_EN : String[] :<br/>
	 * .<br/>
	 */
	private static final String[] PAGES_EN = { "", "page one", "page two", "page three" };


	/**
	 * delegate : BrowserDelegate :<br/>
	 * .<br/>
	 */
	protected BrowserDelegate delegate;



	/**
	 * method setDelegate() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pDelegate
	 *            : void : .<br/>
	 */
	public void setDelegate(final BrowserDelegate pDelegate) {

		this.delegate = pDelegate;
	}


	// //////////////////////////////////////////////
	// HEADER

	@FindBy(tagName = "h1")
	private FluentWebElement headerTitle;


	@FindBy(id = "headerForm:english_button")
	private FluentWebElement englishFlag;


	@FindBy(id = "headerForm:french_button")
	private FluentWebElement frenchFlag;


	// //////////////////////////////////////////////
	// FOOTER

	@FindBy(id = "previousPage")
	private FluentWebElement footerText;


	private Selenium selenium;



	// ////////////////////////////////////

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getBaseUrl() {

		Properties properties = System.getProperties();
		return properties.getProperty("base.url", "http://127.0.0.1:8080/tutoselenium/");
	}



	/**
	 * method clickOnFrenchFlags() :<br/>
	 * .<br/>
	 * <br/>
	 * : void : .<br/>
	 */
	public void clickOnFrenchFlags() {

		locale = Locale.FRENCH;
		this.frenchFlag.click();
		waitPageToLoad();
	}



	/**
	 * method clickOnEnglishFlags() :<br/>
	 * .<br/>
	 * <br/>
	 * : void : .<br/>
	 */
	public void clickOnEnglishFlags() {

		locale = Locale.ENGLISH;
		this.englishFlag.click();
		waitPageToLoad();
	}



	/**
	 * method checkHeader() :<br/>
	 * .<br/>
	 * <br/>
	 * : void : .<br/>
	 */
	protected void checkHeader() {

		assertThat(this.headerTitle.getText(), is(locale == Locale.FRENCH ? "En tête" : "Header"));
		String url = this.delegate.buildUrl(getBaseUrl(), "drapeau_anglais.png");
		assertThat(this.englishFlag.getElement().getCssValue("background-image"), is(url));
		url = this.delegate.buildUrl(getBaseUrl(), "drapeau_francais.png");
		assertThat(this.frenchFlag.getElement().getCssValue("background-image"), is(url));
	}



	/**
	 * method checkFooter() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param previousPage
	 *            : void : .<br/>
	 */
	protected void checkFooter(final int previousPage) {

		String page = "";
		if (locale == Locale.FRENCH) {
			page = PAGES_FR[previousPage];
		}
		else {
			page = PAGES_EN[previousPage];
		}
		assertThat(this.footerText.getText(),
				is(locale == Locale.FRENCH ? "Vous venez de la " + page : "You are coming from " + page));
	}



	/**
	 * method hasPreviousPage() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return : boolean : .<br/>
	 */
	protected boolean hasPreviousPage() {

		FluentWebElement previousPage = getPreviousPageElement();
		if (previousPage == null) {
			return false;
		}
		return previousPage.isDisplayed();
	}



	/**
	 * method getPreviousPageElement() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return : FluentWebElement : .<br/>
	 */
	private FluentWebElement getPreviousPageElement() {

		FluentList<FluentWebElement> elements = find("#previousPage");
		if (!elements.isEmpty()) {
			return elements.get(0);
		}
		return null;
	}



	/**
	 * method waitPageToLoad() :<br/>
	 * .<br/>
	 * <br/>
	 * : void : .<br/>
	 */
	protected void waitPageToLoad() {

		if (this.selenium == null) {
			this.selenium = new WebDriverBackedSelenium(getDriver(), getBaseUrl());
		}
		this.selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);
	}

}
