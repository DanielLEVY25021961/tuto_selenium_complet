package fr.atatorus.tutoselenium.selenium;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * class SeleniumWebDriver :<br/>
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
public class SeleniumWebDriver {

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
     * acceptNextAlert : boolean :<br/>
     * .<br/>
     */
//    private boolean acceptNextAlert = true;
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
    	this.driver = new FirefoxDriver();
    	this.baseUrl = "http://localhost:8080/tutoselenium";
    	this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
        // open | /tutoselenium/ |
    	this.driver.get(this.baseUrl);
        // type | id=contentForm:pageText | 2
    	this.driver.findElement(By.id("contentForm:pageText")).clear();
    	this.driver.findElement(By.id("contentForm:pageText")).sendKeys("2");
        // click | id=contentForm:nextPage |
    	this.driver.findElement(By.id("contentForm:nextPage")).click();
        // click | id=contentForm:page3Button |
    	this.driver.findElement(By.id("contentForm:page3Button")).click();
        // select | id=contentForm:pageList_input | value=1
        new Select(this.driver.findElement(By.id("contentForm:pageList_input"))).selectByValue("1");
        // click | id=contentForm:nextPageButton |
        this.driver.findElement(By.id("contentForm:nextPageButton")).click();
        // click | id=headerForm:english_button |
        this.driver.findElement(By.id("headerForm:english_button")).click();
        // type | id=contentForm:pageText | 2
        this.driver.findElement(By.id("contentForm:pageText")).clear();
        this.driver.findElement(By.id("contentForm:pageText")).sendKeys("2");
        // click | id=contentForm:nextPage |
        this.driver.findElement(By.id("contentForm:nextPage")).click();
        // click | id=contentForm:page3Button |
        this.driver.findElement(By.id("contentForm:page3Button")).click();
        // select | id=contentForm:pageList_input | label=page1
        new Select(this.driver.findElement(By.id("contentForm:pageList_input"))).selectByVisibleText("page1");
        // click | id=contentForm:nextPageButton |
        this.driver.findElement(By.id("contentForm:nextPageButton")).click();
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
//    private boolean isElementPresent(By by) {
//        try {
//        	this.driver.findElement(by);
//            return true;
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }

    /**
     * method closeAlertAndGetItsText() :<br/>
     * .<br/>
     * <br/>
     *
     * @return : String :  .<br/>
     */
//    private String closeAlertAndGetItsText() {
//        try {
//            Alert alert = this.driver.switchTo().alert();
//            if (this.acceptNextAlert) {
//                alert.accept();
//            } else {
//                alert.dismiss();
//            }
//            return alert.getText();
//        } finally {
//        	this.acceptNextAlert = true;
//        }
//    }
}
