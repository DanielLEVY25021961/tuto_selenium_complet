package fr.atatorus.tutoselenium.pages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

/**
 * class Page1 :<br/>
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
public class Page1 extends BasePage {

    // //////////////////////////////////////////////
    // BODY

    @FindBy(id = "contentForm:pageTitle")
    private FluentWebElement pageTitle;
    @FindBy(className = "ui-panel-title")
    private FluentWebElement panelTitle;
    @FindBy(id = "contentForm:label")
    private FluentWebElement label;
    @FindBy(id = "contentForm:pageText")
    private FluentWebElement textField;
    @FindBy(id = "contentForm:pageError")
    private FluentWebElement errorMessage;
    @FindBy(id = "contentForm:nextPage")
    private FluentWebElement button;

    /**
     * method setNextPage() :<br/>
     * .<br/>
     * <br/>
     *
     * @param page : void :  .<br/>
     */
    public void setNextPage(final int page) {
    	this.textField.clear();
    	this.textField.text("" + page);
    }

    /**
     * method buttonClick() :<br/>
     * .<br/>
     * <br/>
     * : void :  .<br/>
     */
    public void buttonClick() {
    	this.button.click();
        waitPageToLoad();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isAt() {
        assertThat(this.pageTitle.getText(), is(locale == Locale.FRENCH ? "Page une" : "Page one"));
    }

    /**
     * method checkPage() :<br/>
     * .<br/>
     * <br/>
     *
     * @param errorExpected
     * @param previousPage : void :  .<br/>
     */
    public void checkPage(boolean errorExpected, int previousPage) {
        checkHeader();
        checkBody(errorExpected);
        assertThat(hasPreviousPage(), is(true));
        checkFooter(previousPage);
    }

    /**
     * method checkPage() :<br/>
     * .<br/>
     * <br/>
     *
     * @param errorExpected : void :  .<br/>
     */
    public void checkPage(boolean errorExpected) {
        checkHeader();
        checkBody(errorExpected);
        assertThat(hasPreviousPage(), is(false));
    }

    /**
     * method gotoPage() :<br/>
     * .<br/>
     * <br/>
     *
     * @param page : void :  .<br/>
     */
    public void gotoPage(int page) {
        setNextPage(page);
        buttonClick();
    }

    /**
     * method checkBody() :<br/>
     * .<br/>
     * <br/>
     *
     * @param errorExpected : void :  .<br/>
     */
    private void checkBody(boolean errorExpected) {
        assertThat(this.panelTitle.getText(), is(locale == Locale.FRENCH ? "Choix de la prochaine page"
                                                                   : "Select the new page"));
        assertThat(this.label.getText(), is(locale == Locale.FRENCH ? "Numéro de la prochaine page"
                                                              : "Number of next page :"));
        assertThat(this.button.getText(), is(locale == Locale.FRENCH ? "Page suivante" : "Next page"));
        String errorText = this.errorMessage.getText();
        assertThat(this.delegate.isDisplayed(this.errorMessage), is(errorExpected));
        if (errorExpected) {
            assertThat(errorText,
                       is(locale == Locale.FRENCH ? "Vous devez entrer une valeur entre un et trois."
                                                 : "You must enter a value between one and three"));
            assertThat(this.errorMessage.getElement().getCssValue("color"), is(this.delegate.getErrorColor()));
        }
    }

}
