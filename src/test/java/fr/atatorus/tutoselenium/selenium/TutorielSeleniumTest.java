package fr.atatorus.tutoselenium.selenium;

import static fr.atatorus.tutoselenium.selenium.WebDriverFactory.Type.CHROME;
import static fr.atatorus.tutoselenium.selenium.WebDriverFactory.Type.FIREFOX;
import static fr.atatorus.tutoselenium.selenium.WebDriverFactory.Type.HTML_UNIT;
import static fr.atatorus.tutoselenium.selenium.WebDriverFactory.Type.OPERA;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.annotation.Page;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;

import com.thoughtworks.selenium.Selenium;

import fr.atatorus.tutoselenium.pages.BasePage;
import fr.atatorus.tutoselenium.pages.BrowserDelegate;
import fr.atatorus.tutoselenium.pages.ChromeDelegate;
import fr.atatorus.tutoselenium.pages.FirefoxDelegate;
import fr.atatorus.tutoselenium.pages.HtmlUnitDelegate;
import fr.atatorus.tutoselenium.pages.OperaDelegate;
import fr.atatorus.tutoselenium.pages.Page1;
import fr.atatorus.tutoselenium.pages.Page2;
import fr.atatorus.tutoselenium.pages.Page3;

/**
 * class TutorielSeleniumTest :<br/>
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
@RunWith(Parameterized.class)
public class TutorielSeleniumTest extends FluentTest {

    /**
     * PAGE_TO_LOAD_TIMEOUT : String :<br/>
     * .<br/>
     */
    private static final String PAGE_TO_LOAD_TIMEOUT = "250";

    /**
     * method data() :<br/>
     * .<br/>
     * <br/>
     *
     * @return Object[][]
     * @throws IOException : Collection<Object[]> :  .<br/>
     */
    @Parameters(name = "{0}")
    public static Collection<Object[]> data() throws IOException {
        return Arrays.asList(new Object[][] { { HTML_UNIT, new HtmlUnitDelegate() },
                                             { FIREFOX, new FirefoxDelegate() },
                                             { OPERA, new OperaDelegate() },
                                             { CHROME, new ChromeDelegate() } });
    }

    /**
     * driver : WebDriver :<br/>
     * .<br/>
     */
    private final WebDriver driver;
    /**
     * delegate : BrowserDelegate :<br/>
     * .<br/>
     */
    private final BrowserDelegate delegate;
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
     * method CONSTRUCTEUR TutorielSeleniumTest() :<br/>
     * .<br/>
     * <br/>
     *
     * @param driverType
     * @param pDelegate
     * @throws InterruptedException
     */
    public TutorielSeleniumTest(WebDriverFactory.Type driverType, BrowserDelegate pDelegate) throws InterruptedException {
        super();
        this.driver = WebDriverFactory.getDriver(driverType);
        this.delegate = pDelegate;
        createPage(Page1.class);
    }

    /**
     * page1 : Page1 :<br/>
     * .<br/>
     */
    @Page
    protected Page1 page1;
    /**
     * page2 : Page2 :<br/>
     * .<br/>
     */
    @Page
    protected Page2 page2;
    /**
     * page3 : Page3 :<br/>
     * .<br/>
     */
    @Page
    protected Page3 page3;

    /**
     * {@inheritDoc}
     */
    @Override
    public WebDriver getDefaultDriver() {
        return this.driver;
    }

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
        this.selenium = new WebDriverBackedSelenium(this.driver, this.baseUrl);
        BasePage.resetLocale();
        this.page1.setDelegate(this.delegate);
        this.page2.setDelegate(this.delegate);
        this.page3.setDelegate(this.delegate);
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
    }

    /**
     * method page1to1WithErrorFrench() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception : void :  .<br/>
     */
    @Test
    public void page1to1WithErrorFrench() throws Exception {
        connect();

        this.page1.isAt();
        this.page1.checkPage(false);

        this.page1.setNextPage(4);
        this.page1.buttonClick();

        this.page1.isAt();
        this.page1.checkPage(true);
    }

    /**
     * method page1to2French() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception : void :  .<br/>
     */
    @Test
    public void page1to2French() throws Exception {
        connect();

        this.page1.isAt();
        this.page1.checkPage(false);

        this.page1.setNextPage(2);
        this.page1.buttonClick();

        this.page2.isAt();
        this.page2.checkPage(1);
    }

    /**
     * method page2to3French() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception : void :  .<br/>
     */
    @Test
    public void page2to3French() throws Exception {
        connectAndGo(2);

        this.page2.isAt();
        this.page2.checkPage(1);

        this.page2.clicPage3Button();

        this.page3.isAt();
        this.page3.checkPage(2);
    }

    /**
     * method page3to1French() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception : void :  .<br/>
     */
    @Test
    public void page3to1French() throws Exception {
        connectAndGo(3);

        this.page3.isAt();
        this.page3.checkPage(1);

        this.page3.selectNextPage("1");
        this.page3.clickOnNextPageButton();

        this.page1.isAt();
        this.page1.checkPage(false, 3);
    }

    /**
     * method page1to1WithErrorEnglish() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception : void :  .<br/>
     */
    @Test
    public void page1to1WithErrorEnglish() throws Exception {
        connect();

        this.page1.isAt();
        this.page1.clickOnEnglishFlags();
        this.page1.checkPage(false);

        this.page1.setNextPage(4);
        this.page1.buttonClick();

        this.page1.isAt();
        this.page1.checkPage(true);
    }

    /**
     * method page1to2English() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception : void :  .<br/>
     */
    @Test
    public void page1to2English() throws Exception {
        connect();

        this.page1.isAt();
        this.page1.clickOnEnglishFlags();
        this.page1.checkPage(false);

        this.page1.setNextPage(2);
        this.page1.buttonClick();

        this.page2.isAt();
        this.page2.checkPage(1);
    }

    /**
     * method page2to3English() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception : void :  .<br/>
     */
    @Test
    public void page2to3English() throws Exception {
        connectAndGo(2);

        this.page2.isAt();
        this.page2.clickOnEnglishFlags();
        this.page2.checkPage(1);

        this.page2.clicPage3Button();
        this.page3.isAt();
        this.page3.checkPage(2);
    }

    /**
     * method page3to1English() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception : void :  .<br/>
     */
    @Test
    public void page3to1English() throws Exception {
        connectAndGo(3);

        this.page3.isAt();
        this.page3.clickOnEnglishFlags();
        this.page3.checkPage(1);

        this.page3.selectNextPage("1");
        this.page3.clickOnNextPageButton();

        this.page1.isAt();
        this.page1.checkPage(false, 3);
    }

    /**
     * method connect() :<br/>
     * .<br/>
     * <br/>
     * : void :  .<br/>
     */
    private void connect() {
        goTo(this.baseUrl + "faces/page1.xhtml");
        this.selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);
    }

    /**
     * method connectAndGo() :<br/>
     * .<br/>
     * <br/>
     *
     * @param page : void :  .<br/>
     */
    private void connectAndGo(int page) {
        connect();
        if (page != 1) {
        	this.page1.gotoPage(page);
        }
    }

    /**
     * method seleniumTest() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception : void :  .<br/>
     */
    @Test
    public void seleniumTest() throws Exception {
        // Connection
    	this.driver.get(this.baseUrl + "faces/page1.xhtml");

        // Vérification
    	this.page1.isAt();
    	this.page1.checkPage(false);

        // Avant d'aller page 2, on provoque une erreur
    	this.page1.setNextPage(4);
    	this.page1.buttonClick();
    	this.page1.checkPage(true);

        // On va page 2
    	this.page1.setNextPage(2);
    	this.page1.buttonClick();

    	this.page2.checkPage(1);
    	this.page2.checkPage(1);

        // On va page 3
    	this.page2.clicPage3Button();

    	this.page3.isAt();
    	this.page3.checkPage(2);

        // on retourne page 1
    	this.page3.selectNextPage("1");
    	this.page3.clickOnNextPageButton();

    	this.page1.isAt();
    	this.page1.checkPage(false, 3);
        // On passe en anglais
    	this.page1.clickOnEnglishFlags();

    	this.page1.isAt();
    	this.page1.checkPage(false, 3);

        // On va page 2
    	this.page1.setNextPage(2);
    	this.page1.buttonClick();

    	this.page2.isAt();
    	this.page2.checkPage(1);

        // On va page 3
    	this.page2.clicPage3Button();

    	this.page3.isAt();
    	this.page3.checkPage(2);

        // Retour vers la page 1
    	this.page3.selectNextPage("1");
    	this.page3.clickOnNextPageButton();

    	this.page1.isAt();
    	this.page1.checkPage(false, 3);
    }

}
