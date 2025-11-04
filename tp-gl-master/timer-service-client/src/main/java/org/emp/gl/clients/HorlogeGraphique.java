package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.time.LocalTime;

public class HorlogeGraphique extends JFrame implements TimerChangeListener {

    private final JLabel heuresLabel;
    private final JLabel minutesLabel;
    private final JLabel secondesLabel;
    private final TimerService timerService;

    public HorlogeGraphique(TimerService timerService) {
        this.timerService = timerService;
        timerService.addTimeChangeListener(this);

        // === Configuration de la fenêtre ===
        setTitle("Horloge - Observer Pattern");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // === Panneau principal avec fond dégradé ===
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(70, 130, 180), 0, getHeight(), new Color(123, 104, 238));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BorderLayout());

        // === Titre ===
        JLabel titre = new JLabel("Horloge du Service Timer", SwingConstants.CENTER);
        titre.setFont(new Font("SansSerif", Font.BOLD, 22));
        titre.setForeground(Color.WHITE);
        panel.add(titre, BorderLayout.NORTH);

        // === Conteneur pour les chiffres ===
        JPanel timePanel = new JPanel();
        timePanel.setOpaque(false);
        timePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 40));

        heuresLabel = createTimeLabel();
        minutesLabel = createTimeLabel();
        secondesLabel = createTimeLabel();

        JLabel deuxPoints1 = createColonLabel();
        JLabel deuxPoints2 = createColonLabel();

        timePanel.add(heuresLabel);
        timePanel.add(deuxPoints1);
        timePanel.add(minutesLabel);
        timePanel.add(deuxPoints2);
        timePanel.add(secondesLabel);

        panel.add(timePanel, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    // === Méthode utilitaire pour créer un label de chiffre ===
    private JLabel createTimeLabel() {
        JLabel label = new JLabel("00", SwingConstants.CENTER);
        label.setFont(new Font("Consolas", Font.BOLD, 60));
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 120), 2));
        label.setPreferredSize(new Dimension(100, 100));
        return label;
    }

    // === Méthode utilitaire pour les deux-points (:) ===
    private JLabel createColonLabel() {
        JLabel label = new JLabel(":", SwingConstants.CENTER);
        label.setFont(new Font("Consolas", Font.BOLD, 60));
        label.setForeground(Color.WHITE);
        return label;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(TimerChangeListener.SECONDE_PROP)) {
            LocalTime now = LocalTime.now();
            SwingUtilities.invokeLater(() -> {
                heuresLabel.setText(String.format("%02d", now.getHour()));
                minutesLabel.setText(String.format("%02d", now.getMinute()));
                secondesLabel.setText(String.format("%02d", now.getSecond()));
            });
        }
    }
}
