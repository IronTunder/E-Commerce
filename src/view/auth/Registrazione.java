package src.view.auth;

import javax.swing.*;
import java.awt.*;

public class Registrazione extends JPanel {
    private final JTextField emailField = new JTextField(20);
    private final JTextField usernameField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);
    private final JPasswordField confirmPasswordField = new JPasswordField(20);
    private final JCheckBox termsCheckbox = new JCheckBox("Accetto i termini di utilizzo");
    private final JCheckBox adminCheckbox = new JCheckBox("Amministratore");
    private final JPasswordField adminPasswordField = new JPasswordField(15);

    public Registrazione() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addField(gbc, 0, "Email:", emailField);
        addField(gbc, 1, "Username:", usernameField);
        addField(gbc, 2, "Password:", passwordField);
        addField(gbc, 3, "Conferma Password:", confirmPasswordField);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(termsCheckbox, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        JPanel adminPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        adminPanel.add(adminCheckbox);
        adminPanel.add(new JLabel("Parola di sicurezza:"));
        adminPanel.add(adminPasswordField);
        adminPasswordField.setEnabled(false);
        add(adminPanel, gbc);

        adminCheckbox.addActionListener(e -> {
            adminPasswordField.setEnabled(adminCheckbox.isSelected());
            if (!adminCheckbox.isSelected()) {
                adminPasswordField.setText("");
            }
        });
    }

    private void addField(GridBagConstraints gbc, int row, String labelText, JComponent field) {
        gbc.gridx = 0; gbc.gridy = row;
        add(new JLabel(labelText), gbc);
        gbc.gridx = 1;
        add(field, gbc);
    }

    public boolean validaRegistrazione() {
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (!email.contains("@") || email.length() < 5) {
            JOptionPane.showMessageDialog(this, "Inserire un'email valida", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (username.length() < 4 || username.length() > 20) {
            JOptionPane.showMessageDialog(this, "Username deve essere tra 4 e 20 caratteri", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (password.length() < 8 || password.length() > 20) {
            JOptionPane.showMessageDialog(this, "Password deve essere tra 8 e 20 caratteri", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Le password non coincidono", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!termsCheckbox.isSelected()) {
            JOptionPane.showMessageDialog(this, "Devi accettare i termini di utilizzo", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (adminCheckbox.isSelected()) {
            String adminPassword = new String(adminPasswordField.getPassword());
            String ADMIN_SECRET_WORD = "pelato<3";
            if (!adminPassword.equals(ADMIN_SECRET_WORD)) {
                JOptionPane.showMessageDialog(this, "Parola di sicurezza amministratore errata", "Errore", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public char[] getPassword() {
        return passwordField.getPassword();
    }

    public boolean isAdmin() {
        return adminCheckbox.isSelected();
    }
}