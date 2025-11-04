package org.emp.gl.timer.service;

/**
 * Interface du service Timer, responsable de fournir le temps courant
 * et de notifier les observateurs (via TimeChangeProvider).
 */
public interface TimerService extends TimeChangeProvider {

    /** Renvoie les minutes actuelles. */
    int getMinutes();

    /** Renvoie les heures actuelles. */
    int getHeures();

    /** Renvoie les secondes actuelles. */
    int getSecondes();

    /** Renvoie les dixièmes de secondes actuels. */
    int getDixiemeDeSeconde();

    /** Démarre le service de minuterie. */
    void start();

    /** Arrête le service de minuterie. */
    void stop();
}
