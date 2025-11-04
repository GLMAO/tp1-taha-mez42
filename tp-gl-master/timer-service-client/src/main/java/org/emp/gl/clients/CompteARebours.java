package org.emp.gl.clients;

import java.beans.PropertyChangeEvent;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class CompteARebours implements TimerChangeListener {

    private int compteur;
    private final TimerService timerService;

    public CompteARebours(TimerService timerService, int valeurInitiale) {
        this.timerService = timerService;
        this.compteur = valeurInitiale;
        timerService.addTimeChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // On ne réagit qu'au changement de seconde
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            if (compteur > 0) {
                System.out.println("⏳ CompteARebours [" + this.hashCode() + "] : " + compteur);
                compteur--;
            } else {
                System.out.println("⏰ CompteARebours [" + this.hashCode() + "] terminé !");
                timerService.removeTimeChangeListener(this);
            }
        }
    }
}
