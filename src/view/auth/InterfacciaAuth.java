package src.view.auth;

import javax.swing.*;
import java.awt.*;


public class InterfacciaAuth extends JFrame {
    private JPanel cardPanel = new JPanel(new CardLayout());
    private Registrazione registrazione = new Registrazione();
    private Login loginPanel = new Login();
    private JPanel buttonPanel = new JPanel();
    private JButton primaryButton = new JButton();
    private JButton switchButton = new JButton();

    public InterfacciaAuth() {
        super("Registrazione/Login");
        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // CardPanel
        cardPanel.add(registrazione, "Registrazione");
        cardPanel.add(loginPanel, "Login");

        // Configurazione bottoni
        confBottonePrincipale();
        confSwitch();

        // Pannello dei bottoni
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JPanel primaryButtonPanel = new JPanel();
        primaryButtonPanel.add(primaryButton);

        JPanel switchButtonPanel = new JPanel();
        switchButtonPanel.add(switchButton);

        buttonPanel.add(primaryButtonPanel);
        buttonPanel.add(switchButtonPanel);

        // Layout principale
        add(cardPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        aggiornaStatoBottoni();
        setVisible(true);
    }

    private void confBottonePrincipale() {
        primaryButton.setPreferredSize(new Dimension(200, 35));
        primaryButton.setFont(new Font("Arial", Font.BOLD, 14));
        primaryButton.setBackground(new Color(50, 50, 50));
        primaryButton.setForeground(Color.WHITE);
        primaryButton.addActionListener(e -> azionePrincipale());
    }

    private void confSwitch() {
        switchButton.setFont(new Font("Arial", Font.PLAIN, 12));
        switchButton.setForeground(new Color(50, 50, 50));
        switchButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        switchButton.setContentAreaFilled(false);
        switchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        switchButton.addActionListener(e -> cambiaPannello());
    }

    private void azionePrincipale() {
        if (isRegistrationPanelVisible()) {
            if (registrazione.validaRegistrazione()) {
                JOptionPane.showMessageDialog(this, "Registrazione completata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            if (loginPanel.validaLogin()) {
                JOptionPane.showMessageDialog(this, "Accesso effettuato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void cambiaPannello() {
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.next(cardPanel);
        aggiornaStatoBottoni();
    }

    private void mostraPannelloRegistrazione() {
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, "Registrazione");
    }

    public void mostraPannelloLogin() {
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, "Login");
    }

    private void aggiornaStatoBottoni() {
        if (isRegistrationPanelVisible()) {
            primaryButton.setText("Registrati");
            switchButton.setText("Hai già un account? Accedi");
        } else {
            primaryButton.setText("Accedi");
            switchButton.setText("Non hai un account? Registrati");
        }
    }

    private boolean isRegistrationPanelVisible() {
        return cardPanel.getComponent(0).isVisible();
    }
}