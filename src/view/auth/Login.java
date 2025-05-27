package src.view.auth;

import javax.swing.*;
import java.awt.*;


public class Login extends JPanel {
    private final JTextField usernameField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);

    public Login() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Nome Utente / Email:");
        JLabel passwordLabel = new JLabel("Password:");

        gbc.gridx = 0; gbc.gridy = 0;
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);
    }

    public boolean validaLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.length() < 4 || username.length() > 20) {
            JOptionPane.showMessageDialog(this, "Inserire un nome utente valido (4-20 caratteri)", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (password.length() < 8 || password.length() > 20) {
            JOptionPane.showMessageDialog(this, "Inserire una password valida (8-20 caratteri)", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public char[] getPassword() {
        return passwordField.getPassword();
    }
}