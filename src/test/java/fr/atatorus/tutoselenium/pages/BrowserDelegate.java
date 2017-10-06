package fr.atatorus.tutoselenium.pages;

import org.fluentlenium.core.domain.FluentWebElement;

/**
 * class BrowserDelegate :<br/>
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
public interface BrowserDelegate {

    /**
     * method buildUrl() :<br/>
     * .<br/>
     * <br/>
     *
     * @param baseUrl
     * @param resource
     * @return : String :  .<br/>
     */
    String buildUrl(String baseUrl, String resource);

    /**
     * method getErrorColor() :<br/>
     * .<br/>
     * <br/>
     *
     * @return : String :  .<br/>
     */
    String getErrorColor();

    /**
     * method isDisplayed() :<br/>
     * .<br/>
     * <br/>
     *
     * @param element
     * @return : boolean :  .<br/>
     */
    boolean isDisplayed(FluentWebElement element);

}
