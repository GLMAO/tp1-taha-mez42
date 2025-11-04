package org.emp.gl.time.service.impl;

import java.beans.PropertyChangeSupport;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class DummyTimeServiceImpl implements TimerService {

    // --- Gestion du temps ---
    private int dixiemeDeSeconde;
    private int secondes;
    private int minutes;
    private int heures;

    // --- Gestion des observateurs ---
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // --- Timer Java ---
    private Timer timer;

    public DummyTimeServiceImpl() {
        setTimeValues();
    }

    // --- Lecture de l’heure système ---
    private void setTimeValues() {
        LocalTime now = LocalTime.now();
        setHeures(now.getHour());
        setMinutes(now.getMinute());
        setSecondes(now.getSecond());
        setDixiemeDeSeconde(now.getNano() / 100_000_000);
    }

    // --- Méthodes de notification des changements ---
    public void setDixiemeDeSeconde(int newVal) {
        if (this.dixiemeDeSeconde != newVal) {
            int old = this.dixiemeDeSeconde;
            this.dixiemeDeSeconde = newVal;
            support.firePropertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP, old, newVal);
        }
    }

    public void setSecondes(int newVal) {
        if (this.secondes != newVal) {
            int old = this.secondes;
            this.secondes = newVal;
            support.firePropertyChange(TimerChangeListener.SECONDE_PROP, old, newVal);
        }
    }

    public void setMinutes(int newVal) {
        if (this.minutes != newVal) {
            int old = this.minutes;
            this.minutes = newVal;
            support.firePropertyChange(TimerChangeListener.MINUTE_PROP, old, newVal);
        }
    }

    public void setHeures(int newVal) {
        if (this.heures != newVal) {
            int old = this.heures;
            this.heures = newVal;
            support.firePropertyChange(TimerChangeListener.HEURE_PROP, old, newVal);
        }
    }

    // --- Gestion des observateurs ---
    @Override
    public void addTimeChangeListener(TimerChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    // --- Méthode appelée à chaque "tic" ---
    private void timeChanged() {
        setTimeValues();
    }

    // --- Démarrer le timer ---
    @Override
    public void start() {
        if (timer != null) {
            stop();
        }
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timeChanged();
            }
        };
        // Notification toutes les 1000 ms = chaque seconde
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    // --- Arrêter le timer ---
    @Override
    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    // --- Getters du temps ---
    @Override
    public int getDixiemeDeSeconde() {
        return dixiemeDeSeconde;
    }

    @Override
    public int getSecondes() {
        return secondes;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getHeures() {
        return heures;
    }
}
