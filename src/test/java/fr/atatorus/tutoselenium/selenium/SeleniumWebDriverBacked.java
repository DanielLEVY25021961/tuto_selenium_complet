package fr.atatorus.tutoselenium.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

/**
 * class SeleniumWebDriverBacked :<br/>
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
@Ignore
public class SeleniumWebDriverBacked {

    /**
     * selenium : Selenium :<br/>
     * .<br/>
     */
    private Selenium selenium;

    /**
     * method setUp() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception : void :  .<br/>
     */
    @Before
    public void setUp() throws Exception {
        WebDriver driver = new FirefoxDriver();
        String baseUrl = "http://localhost:8080/tutoselenium";
        this.selenium = new WebDriverBackedSelenium(driver, baseUrl);
    }

    /**
     * method testSelenium() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception : void :  .<br/>
     */
    @Test
    public void testSelenium() throws Exception {
    	this.selenium.open("/tutoselenium/");
    	this.selenium.type("id=contentForm:pageText", "2");
    	this.selenium.click("id=contentForm:nextPage");
    	this.selenium.waitForPageToLoad("30000");
    	this.selenium.click("id=contentForm:page3Button");
    	this.selenium.waitForPageToLoad("30000");
    	this.selenium.select("id=contentForm:pageList_input", "value=1");
    	this.selenium.click("id=contentForm:nextPageButton");
    	this.selenium.waitForPageToLoad("30000");
    	this.selenium.click("id=headerForm:english_button");
    	this.selenium.waitForPageToLoad("30000");
    	this.selenium.type("id=contentForm:pageText", "2");
    	this.selenium.click("id=contentForm:nextPage");
    	this.selenium.waitForPageToLoad("30000");
    	this.selenium.click("id=contentForm:page3Button");
    	this.selenium.waitForPageToLoad("30000");
    	this.selenium.select("id=contentForm:pageList_input", "label=page1");
    	this.selenium.click("id=contentForm:nextPageButton");
    	this.selenium.waitForPageToLoad("30000");
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
    	this.selenium.stop();
    }
}
