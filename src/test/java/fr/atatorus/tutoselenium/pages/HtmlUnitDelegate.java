package fr.atatorus.tutoselenium.pages;

import org.fluentlenium.core.domain.FluentWebElement;

/**
 * class HtmlUnitDelegate :<br/>
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
public class HtmlUnitDelegate implements BrowserDelegate {

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildUrl(String baseUrl, String resource) {
        String[] split = baseUrl.split("/");
        String root = "";
        if (split.length == 4) {
            root = "/" + split[3];
        }
        return "url(" + root + "/faces/javax.faces.resource/" + resource + "?ln=images)";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getErrorColor() {
        return "red";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDisplayed(FluentWebElement element) {
        return !element.getText().equals("");
    }

}
