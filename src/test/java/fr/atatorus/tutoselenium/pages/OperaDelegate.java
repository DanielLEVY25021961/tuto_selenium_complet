package fr.atatorus.tutoselenium.pages;

import org.fluentlenium.core.domain.FluentWebElement;

/**
 * class OperaDelegate :<br/>
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
public class OperaDelegate implements BrowserDelegate {

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildUrl(String baseUrl, String resource) {
        return "url(\"" + baseUrl + "faces/javax.faces.resource/" + resource + "?ln=images\")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getErrorColor() {
        return "rgba(255, 0, 0, 1)";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDisplayed(FluentWebElement element) {
        return element.isDisplayed();
    }

}
