package fr.atatorus.tutoselenium.pages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

/**
 * class Page2 :<br/>
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
public class Page2 extends BasePage {

    // //////////////////////////////////////////////
    // BODY

    @FindBy(id = "contentForm:pageTitle")
    private FluentWebElement pageTitle;
    @FindBy(className = "ui-panel-title")
    private FluentWebElement panelTitle;
    @FindBy(id = "contentForm:page1Button")
    private FluentWebElement page1Button;
    @FindBy(id = "contentForm:page2Button")
    private FluentWebElement page2Button;
    @FindBy(id = "contentForm:page3Button")
    private FluentWebElement page3Button;

    /**
     * method clicPage1Button() :<br/>
     * .<br/>
     * <br/>
     * : void :  .<br/>
     */
    public void clicPage1Button() {
    	this.page1Button.click();
        waitPageToLoad();
    }

    /**
     * method clicPage2Button() :<br/>
     * .<br/>
     * <br/>
     * : void :  .<br/>
     */
    public void clicPage2Button() {
    	this.page2Button.click();
        waitPageToLoad();
    }

    /**
     * method clicPage3Button() :<br/>
     * .<br/>
     * <br/>
     * : void :  .<br/>
     */
    public void clicPage3Button() {
    	this.page3Button.click();
        waitPageToLoad();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isAt() {
        assertThat(this.pageTitle.getText(), is(locale == Locale.FRENCH ? "Page deux" : "Page two"));
    }

    /**
     * method checkPage() :<br/>
     * .<br/>
     * <br/>
     *
     * @param previousPage : void :  .<br/>
     */
    public void checkPage(final int previousPage) {
        checkHeader();
        checkBody();
        checkFooter(previousPage);
    }

    /**
     * method checkPage() :<br/>
     * .<br/>
     * <br/>
     * : void :  .<br/>
     */
    public void checkPage() {
        checkHeader();
        checkBody();
        assertThat(hasPreviousPage(), is(false));
    }

    /**
     * method checkBody() :<br/>
     * .<br/>
     * <br/>
     * : void :  .<br/>
     */
    private void checkBody() {
        assertThat(this.panelTitle.getText(), is(locale == Locale.FRENCH ? "Choix de la prochaine page"
                                                                   : "Select the new page"));
        assertThat(this.page1Button.getText(), is(locale == Locale.FRENCH ? "Page une" : "Page one"));
        assertThat(this.page2Button.getText(), is(locale == Locale.FRENCH ? "Page deux" : "Page two"));
        assertThat(this.page3Button.getText(), is(locale == Locale.FRENCH ? "Page trois" : "Page three"));
    }

}
