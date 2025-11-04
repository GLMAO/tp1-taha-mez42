package org.emp.gl.core.launcher;

import org.emp.gl.clients.CompteARebours;

import org.emp.gl.clients.Horloge;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;

import java.util.Random;

public class App {
    public static void main(String[] args) {
        DummyTimeServiceImpl timerService = new DummyTimeServiceImpl();

        // Créer plusieurs horloges
        new Horloge(timerService, "H1");
        new Horloge(timerService, "H2");

        // Compte à rebours fixe
        new CompteARebours(timerService, 5);

        // 10 comptes à rebours aléatoires
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            new CompteARebours(timerService, 10 + rand.nextInt(11)); // 10 à 20
        }

        // Démarrer le timer
        timerService.start();
    }
}
