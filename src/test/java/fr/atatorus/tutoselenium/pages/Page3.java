package fr.atatorus.tutoselenium.pages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * class Page3 :<br/>
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
public class Page3 extends BasePage {

    // //////////////////////////////////////////////
    // BODY

    @FindBy(id = "contentForm:pageTitle")
    private FluentWebElement pageTitle;
    @FindBy(className = "ui-panelgrid-header")
    private FluentWebElement panelTitle;
    @FindBy(id = "contentForm:label")
    private FluentWebElement label;
    @FindBy(id = "contentForm:nextPageButton")
    private FluentWebElement button;
    @FindBy(id = "contentForm:pageList_input")
    private FluentWebElement list;

    /**
     * method selectNextPage() :<br/>
     * .<br/>
     * <br/>
     *
     * @param page : void :  .<br/>
     */
    public void selectNextPage(final String page) {
        getList().selectByValue(page);
    }

    /**
     * method clickOnNextPageButton() :<br/>
     * .<br/>
     * <br/>
     * : void :  .<br/>
     */
    public void clickOnNextPageButton() {
    	this.button.click();
        waitPageToLoad();
    }

    /**
     * method getOption() :<br/>
     * .<br/>
     * <br/>
     *
     * @param index
     * @return : WebElement :  .<br/>
     */
    private WebElement getOption(int index) {
        return getList().getOptions().get(index);
    }

    /**
     * method getList() :<br/>
     * .<br/>
     * <br/>
     *
     * @return : Select :  .<br/>
     */
    private Select getList() {
        Select select = new Select(this.list.getElement());
        return select;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isAt() {
        assertThat(this.pageTitle.getText(), is(locale == Locale.FRENCH ? "Page trois" : "Page three"));
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
        assertThat(this.panelTitle.getText(), is(locale == Locale.FRENCH ? "Choix de la nouvelle page"
                                                                   : "Select the new page"));
        assertThat(this.label.getText(), is(locale == Locale.FRENCH ? "Choisissez la nouvelle page :"
                                                              : "Select the page :"));
        assertThat(this.button.getText(), is(locale == Locale.FRENCH ? "Page suivante" : "Next page"));
        assertThat(getOption(0).getText(), is("page1"));
        assertThat(getOption(1).getText(), is("page2"));
        assertThat(getOption(2).getText(), is("page3"));
    }

}
