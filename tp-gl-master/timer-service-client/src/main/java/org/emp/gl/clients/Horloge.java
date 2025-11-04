package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import java.beans.PropertyChangeEvent;
import java.time.LocalTime;

public class Horloge implements TimerChangeListener {

    private final TimerService timerService;
    private final String nom;

    public Horloge(TimerService timerService, String nom) {
        this.timerService = timerService;
        this.nom = nom;
        timerService.addTimeChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Horloge [" + nom + "] : " +
                evt.getPropertyName() + " -> " + evt.getNewValue() +
                " à " + LocalTime.now());
    }

    public void stop() {
        timerService.removeTimeChangeListener(this);
    }
}
