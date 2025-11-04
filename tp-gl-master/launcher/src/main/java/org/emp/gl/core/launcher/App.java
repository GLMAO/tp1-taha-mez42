package org.emp.gl.core.launcher;

import org.emp.gl.clients.CompteARebours;
import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.HorlogeGraphique;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;

import javax.swing.SwingUtilities;
import java.util.Random;

/**
 * Lanceur principal du projet (TP Horloge + Compte à Rebours + Interface Graphique)
 */
public class App {

    public static void main(String[] args) {

        // === Création d’un seul service de temps partagé ===
        TimerService timerService = new DummyTimeServiceImpl();

        // === Lancement des horloges texte dans la console ===
        testHorloges(timerService);

        // === Lancement des comptes à rebours ===
        testComptesARebours(timerService);

        // === Lancement de l’interface graphique Swing ===
        SwingUtilities.invokeLater(() -> new HorlogeGraphique(timerService));

        // === Démarrage du service de temps ===
        timerService.start();

        System.out.println("\n✅ Système de temps démarré : Horloges + Comptes à rebours + GUI\n");
    }

    /**
     * Création de plusieurs horloges textuelles dans la console
     */
    private static void testHorloges(TimerService timerService) {
        new Horloge(timerService, "H1");
        new Horloge(timerService, "H2");
        new Horloge(timerService, "H3");
        System.out.println("=== Horloges H1, H2 et H3 démarrées ===");
    }

    /**
     * Création de plusieurs comptes à rebours aléatoires
     */
    private static void testComptesARebours(TimerService timerService) {
        Random random = new Random();

        // Un compte fixe de 5 secondes
        new CompteARebours(timerService, 5);

        // 10 comptes à rebours aléatoires entre 10 et 20 secondes
        for (int i = 0; i < 10; i++) {
            int valeur = 10 + random.nextInt(11); // 10 à 20 inclus
            new CompteARebours(timerService, valeur);
        }

        System.out.println("=== 10 comptes à rebours aléatoires démarrés ===");
    }

    // (Facultatif) Nettoyer la console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
