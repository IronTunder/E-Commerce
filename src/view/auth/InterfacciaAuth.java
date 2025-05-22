package src.view.auth;

import src.controllers.AuthController;
import src.models.Utente;

import javax.swing.*;
import java.awt.*;


public class InterfacciaAuth extends JFrame {

    private final JPanel cardPanel = new JPanel(new CardLayout());
    private final Registrazione registrazione = new Registrazione();
    private final Login loginPanel = new Login();
    private final JButton primaryButton = new JButton();
    private final JButton switchButton = new JButton();
    private final AuthController authController;

    public InterfacciaAuth(AuthController authController) {
        super("Registrazione/Login");
        this.authController = authController;
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
        JPanel buttonPanel = new JPanel();
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
                try {
                    authController.registraUtente(new Utente(registrazione.getEmail(),registrazione.getPassword(),registrazione.getUsername(),registrazione.isAdmin()));
                    JOptionPane.showMessageDialog(this, "Registrazione completata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Errore durante la registrazione (L'utente è gia registrato?)", "Errore", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(e);
                }
            }
        } else {
            if (loginPanel.validaLogin()) {
                try {
                    if(authController.accedi(loginPanel.getUsername(),loginPanel.getPassword())){
                        JOptionPane.showMessageDialog(this, "Accesso completata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Errore durante l'accesso (Controlla le credenziali o registrati!)", "Errore", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(e);
                }
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
        aggiornaStatoBottoni();
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