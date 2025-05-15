import javax.swing.*;
import java.awt.*;

public class Registrazione extends JFrame {

    GestionePannelloRegistrazione gestionePannelloRegistrazione = new GestionePannelloRegistrazione();
    Login login;
    JButton registrati = new JButton("Registrati");
    JButton accedi = new JButton("Accedi");
    JPanel bottoni = new JPanel();

    public Registrazione() {
        super("Registrazione");

        setLayout(new BorderLayout());
        setSize(new Dimension(500, 350));
        setResizable(false);
        setLocationRelativeTo(null);
        add(gestionePannelloRegistrazione, BorderLayout.CENTER);

        bottoni.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottoni.add(registrati);
        bottoni.add(accedi);
        accedi.setBackground(new Color(100, 149, 237));
        registrati.setBackground(new Color(60, 179, 113));
        accedi.setForeground(Color.WHITE);
        registrati.setForeground(Color.WHITE);
        accedi.setFont(new Font("Arial", Font.BOLD, 14));
        registrati.setFont(new Font("Arial", Font.BOLD, 14));

        accedi.addActionListener(e -> {
            if (login == null) {
                login = new Login();
            } else {
                login.dispose();
                login = new Login();
            }
        });

        add(bottoni, BorderLayout.PAGE_END);
        registrati.setEnabled(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        gestionePannelloRegistrazione.termini.addActionListener(e -> {
            registrati.setEnabled(gestionePannelloRegistrazione.termini.isSelected());
        });

        registrati.addActionListener(e -> {
            if (!gestionePannelloRegistrazione.password1DaInserire.getText().equals(gestionePannelloRegistrazione.password2DaInserire.getText())) {
                JOptionPane.showMessageDialog(this, "Le due password non coincidono", "Errore", JOptionPane.ERROR_MESSAGE);
            } else if (!(gestionePannelloRegistrazione.emailDaInserire.getText().contains("@") &&
                    (gestionePannelloRegistrazione.emailDaInserire.getText().contains("@gmail.com") ||
                            gestionePannelloRegistrazione.emailDaInserire.getText().contains(".it") ||
                            gestionePannelloRegistrazione.emailDaInserire.getText().contains(".com")))) {
                JOptionPane.showMessageDialog(this, "Email non valida", "Errore", JOptionPane.ERROR_MESSAGE);
            } else if (gestionePannelloRegistrazione.usernameDaInserire.getText().length() < 4 ||
                    gestionePannelloRegistrazione.usernameDaInserire.getText().length() > 20) {
                JOptionPane.showMessageDialog(this, "Username non valido", "Errore", JOptionPane.ERROR_MESSAGE);
            } else {
                gestionePannelloRegistrazione.scrittura();
                JOptionPane.showMessageDialog(this, "Utente registrato con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        new Registrazione();
    }
}