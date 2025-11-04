package org.emp.gl.timer.service;

import java.beans.PropertyChangeListener;

/**
 * Interface représentant un observateur du service Timer.
 * Elle écoute les changements de temps via des événements PropertyChangeEvent.
 *
 * @author tina
 */
public interface TimerChangeListener extends PropertyChangeListener {

    String DIXEME_DE_SECONDE_PROP = "dixième";
    String SECONDE_PROP = "seconde";
    String MINUTE_PROP = "minute";
    String HEURE_PROP = "heure";

    //  Pas besoin de redéclarer propertyChange()
}
