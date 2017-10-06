package fr.atatorus.tutoselenium.selenium;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import com.opera.core.systems.OperaDriver;
import com.thoughtworks.selenium.Selenium;

/**
 * class SeleniumTest :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
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
public class SeleniumTest {

    /**
     * PAGE_TO_LOAD_TIMEOUT : String :<br/>
     * .<br/>
     */
    private static final String PAGE_TO_LOAD_TIMEOUT = "100";
    /**
     * HTML_UNIT_DRIVER : int :<br/>
     * .<br/>
     */
    public static final int HTML_UNIT_DRIVER = 0;
    /**
     * FIREFOX_DRIVER : int :<br/>
     * .<br/>
     */
    public static final int FIREFOX_DRIVER = 1;
    /**
     * OPERA_DRIVER : int :<br/>
     * .<br/>
     */
    public static final int OPERA_DRIVER = 2;
    /**
     * CHROME_DRIVER : int :<br/>
     * .<br/>
     */
    public static final int CHROME_DRIVER = 3;

    /**
     * currentDriver : int :<br/>
     * .<br/>
     */
    private int currentDriver;

    /**
     * driver : WebDriver :<br/>
     * .<br/>
     */
    private WebDriver driver;
    /**
     * baseUrl : String :<br/>
     * .<br/>
     */
    private String baseUrl;
    /**
     * selenium : Selenium :<br/>
     * .<br/>
     */
    private Selenium selenium;
    /**
     * verificationErrors : StringBuffer :<br/>
     * .<br/>
     */
    private final StringBuffer verificationErrors = new StringBuffer();

    /**
     * method setUp() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception : void :  .<br/>
     */
    @Before
    public void setUp() throws Exception {
        Properties properties = System.getProperties();
        this.baseUrl = properties.getProperty("base.url", "http://127.0.0.1:8080/tutoselenium/");
    }

    /**
     * method firefoxTest() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception : void :  .<br/>
     */
    @Test
    public void firefoxTest() throws Exception {
    	this.driver = new FirefoxDriver();
    	this.currentDriver = FIREFOX_DRIVER;
    	this.selenium = new WebDriverBackedSelenium(this.driver, this.baseUrl);
        testSelenium();
    }

    /**
     * method htmlUnitTest() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception : void :  .<br/>
     */
    @Test
    public void htmlUnitTest() throws Exception {
    	this.driver = new HtmlUnitDriver(true);
    	this.currentDriver = HTML_UNIT_DRIVER;
    	this.selenium = new WebDriverBackedSelenium(this.driver, this.baseUrl);
        testSelenium();
    }

    /**
     * method operaTest() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception : void :  .<br/>
     */
    @Test
    public void operaTest() throws Exception {
    	this.driver = new OperaDriver();
    	this.currentDriver = OPERA_DRIVER;
    	this.selenium = new WebDriverBackedSelenium(this.driver, this.baseUrl);
        testSelenium();
    }

    /**
     * CHROME_DRIVER_PATH : String :<br/>
     * .<br/>
     */
    private static final String CHROME_DRIVER_PATH = "/usr/lib64/chromium/chromedriver";

    /**
     * method chromeTest() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception : void :  .<br/>
     */
    @Test
    public void chromeTest() throws Exception {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        this.driver = new ChromeDriver();
        this.currentDriver = CHROME_DRIVER;
        this.selenium = new WebDriverBackedSelenium(this.driver, this.baseUrl);
        testSelenium();
    }

    /**
     * method testSelenium() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception : void :  .<br/>
     */
    private void testSelenium() throws Exception {
        // Connection
    	this.driver.get(this.baseUrl + "faces/page1.xhtml");
    	this.selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);
        // Vérification
        checkHeader(Locale.FRENCH);
        checkPageUne(false, Locale.FRENCH);
        checkFooter(); // On vient de se connecter, on n'affiche donc pas le numéro de la page
                       // précédente

        // Avant d'aller page 2, on provoque une erreur
        this.driver.findElement(By.id("contentForm:pageText")).clear();
        this.driver.findElement(By.id("contentForm:pageText")).sendKeys("4");
        this.driver.findElement(By.id("contentForm:nextPage")).click();

        checkPageUne(true, Locale.FRENCH);

        // On va page 2
        this.driver.findElement(By.id("contentForm:pageText")).clear();
        this.driver.findElement(By.id("contentForm:pageText")).sendKeys("2");
        this.driver.findElement(By.id("contentForm:nextPage")).click();
        this.selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);
        // vérification
        checkHeader(Locale.FRENCH);
        checkPageDeux(Locale.FRENCH);
        checkFooter("page une", Locale.FRENCH); // On vient de la page 1 en français

        // On va page 3
        this.driver.findElement(By.id("contentForm:page3Button")).click();
        this.selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);
        // vérification
        checkHeader(Locale.FRENCH);
        checkPageTrois(Locale.FRENCH);
        checkFooter("page deux", Locale.FRENCH);

        // on retourne page 1
        new Select(this.driver.findElement(By.id("contentForm:pageList_input"))).selectByValue("1");
        this.driver.findElement(By.id("contentForm:nextPageButton")).click();
        this.selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);

        checkFooter("page trois", Locale.FRENCH);
        // On passe en anglais
        this.driver.findElement(By.id("headerForm:english_button")).click();
        this.selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);
        // Vérification
        checkHeader(Locale.ENGLISH);
        checkPageUne(false, Locale.ENGLISH);
        checkFooter("page three", Locale.ENGLISH);

        // On va page 2
        this.driver.findElement(By.id("contentForm:pageText")).clear();
        this.driver.findElement(By.id("contentForm:pageText")).sendKeys("2");
        this.driver.findElement(By.id("contentForm:nextPage")).click();
        this.selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);
        // vérification
        checkHeader(Locale.ENGLISH);
        checkPageDeux(Locale.ENGLISH);
        checkFooter("page one", Locale.ENGLISH);

        // On va page 3
        this.driver.findElement(By.id("contentForm:page3Button")).click();
        this.selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);
        // vérification
        checkHeader(Locale.ENGLISH);
        checkPageTrois(Locale.ENGLISH);
        checkFooter("page two", Locale.ENGLISH);

        // Retour vers la page 1
        new Select(this.driver.findElement(By.id("contentForm:pageList_input"))).selectByVisibleText("page1");
        this.driver.findElement(By.id("contentForm:nextPageButton")).click();
        this.selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);
        checkFooter("page three", Locale.ENGLISH);
    }

    /**
     * method checkPageTrois() :<br/>
     * .<br/>
     * <br/>
     *
     * @param locale : void :  .<br/>
     */
    private void checkPageTrois(Locale locale) {
        checkElement("contentForm", "pageTitle", locale == Locale.FRENCH ? "Page trois" : "Page three");
        checkPanelTitle("contentForm",
                        "panel",
                        "ui-panelgrid-header",
                        locale == Locale.FRENCH ? "Choix de la nouvelle page" : "Select the new page");

        checkElement("contentForm", "label", locale == Locale.FRENCH ? "Choisissez la nouvelle page :"
                                                                    : "Select the page :");
        checkElement("contentForm", "nextPageButton", locale == Locale.FRENCH ? "Page suivante" : "Next page");
        Select select = new Select(this.driver.findElement(By.id("contentForm:pageList_input")));
        List<WebElement> options = select.getOptions();
        assertThat(options.get(0).getText(), is("page1"));
        assertThat(options.get(1).getText(), is("page2"));
        assertThat(options.get(2).getText(), is("page3"));
    }

    /**
     * method checkFooter() :<br/>
     * .<br/>
     * <br/>
     * : void :  .<br/>
     */
    private void checkFooter() {
        assertThat(isElementPresent(By.id("previousPage")), is(false));
    }

    /**
     * method checkFooter() :<br/>
     * .<br/>
     * <br/>
     *
     * @param fromPage
     * @param locale : void :  .<br/>
     */
    private void checkFooter(String fromPage, Locale locale) {
        if (locale == Locale.FRENCH) {
            checkElement("previousPage", "Vous venez de la " + fromPage);
        } else {
            checkElement("previousPage", "You are coming from " + fromPage);
        }
    }

    /**
     * method checkElement() :<br/>
     * .<br/>
     * <br/>
     *
     * @param elementId
     * @param expected : void :  .<br/>
     */
    private void checkElement(String elementId, String expected) {
        assertThat(this.driver.findElement(By.id(elementId)).getText(), is(expected));
    }

    /**
     * method checkElement() :<br/>
     * .<br/>
     * <br/>
     *
     * @param parentId
     * @param elementId
     * @param expected : void :  .<br/>
     */
    private void checkElement(String parentId, String elementId, String expected) {
        checkElement(parentId + ":" + elementId, expected);
    }

    /**
     * method checkPageDeux() :<br/>
     * .<br/>
     * <br/>
     *
     * @param locale : void :  .<br/>
     */
    private void checkPageDeux(Locale locale) {
        checkElement("contentForm", "pageTitle", locale == Locale.FRENCH ? "Page deux" : "Page two");
        checkPanelTitle("contentForm",
                        "panel",
                        "ui-panel-title",
                        locale == Locale.FRENCH ? "Choix de la prochaine page" : "Select the new page");

        checkElement("contentForm", "page1Button", locale == Locale.FRENCH ? "Page une" : "Page one");
        checkElement("contentForm", "page2Button", locale == Locale.FRENCH ? "Page deux" : "Page two");
        checkElement("contentForm", "page3Button", locale == Locale.FRENCH ? "Page trois" : "Page three");
    }

    /**
     * method checkPageUne() :<br/>
     * .<br/>
     * <br/>
     *
     * @param errorMessage
     * @param locale : void :  .<br/>
     */
    private void checkPageUne(boolean errorMessage, Locale locale) {
        checkElement("contentForm", "pageTitle", locale == Locale.FRENCH ? "Page une" : "Page one");
        checkPanelTitle("contentForm",
                        "panel",
                        "ui-panel-title",
                        locale == Locale.FRENCH ? "Choix de la prochaine page" : "Select the new page");

        checkElement("contentForm", "label", locale == Locale.FRENCH ? "Numéro de la prochaine page"
                                                                    : "Number of next page :");
        checkElement("contentForm", "nextPage", locale == Locale.FRENCH ? "Page suivante" : "Next page");

        if (errorMessage) {
            checkElement("contentForm",
                         "pageError",
                         locale == Locale.FRENCH ? "Vous devez entrer une valeur entre un et trois."
                                                : "You must enter a number between one and three.");
            String color = this.driver.findElement(By.id("contentForm:pageError")).getCssValue("color");
            switch (this.currentDriver) {
                case CHROME_DRIVER:
                case FIREFOX_DRIVER:
                case OPERA_DRIVER:
                    assertThat(color, is("rgba(255, 0, 0, 1)"));
                    break;
                default:
                    assertThat(color, is("red"));
                    break;
            }
        } else {
            switch (this.currentDriver) {
                case FIREFOX_DRIVER:
                case OPERA_DRIVER:
                case CHROME_DRIVER:
                    assertThat(this.driver.findElement(By.id("contentForm:pageError")).isDisplayed(), is(false));
                    break;
                default:
                    checkElement("contentForm", "pageError", "");
            }
        }
    }

    /**
     * method checkPanelTitle() :<br/>
     * .<br/>
     * <br/>
     *
     * @param parentId
     * @param panelId
     * @param titleClass
     * @param expectedTitle : void :  .<br/>
     */
    private void checkPanelTitle(String parentId, String panelId, String titleClass, String expectedTitle) {
        WebElement panel = this.driver.findElement(By.id(parentId + ":" + panelId));
        WebElement panelTitle = panel.findElement(By.className(titleClass));
        assertThat(panelTitle.getText(), is(expectedTitle));
    }

    /**
     * method checkHeader() :<br/>
     * .<br/>
     * <br/>
     *
     * @param locale : void :  .<br/>
     */
    private void checkHeader(Locale locale) {
        WebElement title = this.driver.findElement(By.tagName("h1"));
        assertThat(title.getText(), is(locale == Locale.FRENCH ? "En tête" : "Header"));
        String drapeau = this.driver.findElement(By.id("headerForm:english_button")).getCssValue("background-image");
        assertThat(drapeau, is(buildUrl("drapeau_anglais.png")));
        drapeau = this.driver.findElement(By.id("headerForm:french_button")).getCssValue("background-image");
        assertThat(drapeau, is(buildUrl("drapeau_francais.png")));
    }

    /**
     * method buildUrl() :<br/>
     * .<br/>
     * <br/>
     *
     * @param resource
     * @return : String :  .<br/>
     */
    private String buildUrl(String resource) {
        switch (this.currentDriver) {
            case CHROME_DRIVER:
                return "url(" + this.baseUrl + "faces/javax.faces.resource/" + resource + "?ln=images)";
            case FIREFOX_DRIVER:
            case OPERA_DRIVER:
                return "url(\"" + this.baseUrl + "faces/javax.faces.resource/" + resource + "?ln=images\")";
            default:
                String[] split = this.baseUrl.split("/");
                String root = "";
                if (split.length == 4) {
                    root = "/" + split[3];
                }
                return "url(" + root + "/faces/javax.faces.resource/" + resource + "?ln=images)";
        }
    }

    /**
     * method tearDown() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception : void :  .<br/>
     */
    @After
    public void tearDown() throws Exception {
    	this.driver.quit();
        String verificationErrorString = this.verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    /**
     * method isElementPresent() :<br/>
     * .<br/>
     * <br/>
     *
     * @param by
     * @return : boolean :  .<br/>
     */
    private boolean isElementPresent(By by) {
        try {
        	this.driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
