package org.emp.gl.timer.service;

/**
 * Interface qui permet d'ajouter ou retirer des observateurs de temps.
 */
public interface TimeChangeProvider {

    void addTimeChangeListener(TimerChangeListener listener);

    void removeTimeChangeListener(TimerChangeListener listener);
}
