import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class GestionePannelloRegistrazione extends JPanel {

    JLabel email = new JLabel("Email: ");
    JLabel username = new JLabel("Username: ");
    JLabel password1 = new JLabel("Password: ");
    JLabel password2 = new JLabel("Conferma Password: ");

    JTextField emailDaInserire = new JTextField(20);
    JTextField usernameDaInserire = new JTextField(20);
    JPasswordField password1DaInserire = new JPasswordField(20);
    JPasswordField password2DaInserire = new JPasswordField(20);

    JCheckBox termini = new JCheckBox("Accetto i termini di utilizzo");

    public GestionePannelloRegistrazione() {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 248, 255));

        GridBagConstraints b = new GridBagConstraints();
        b.insets = new Insets(10, 10, 10, 10);

        b.gridx = 0;
        b.gridy = 0;
        add(email, b);
        b.gridx = 1;
        add(emailDaInserire, b);

        b.gridx = 0;
        b.gridy = 1;
        add(username, b);
        b.gridx = 1;
        add(usernameDaInserire, b);

        b.gridx = 0;
        b.gridy = 2;
        add(password1, b);
        b.gridx = 1;
        add(password1DaInserire, b);

        b.gridx = 0;
        b.gridy = 3;
        add(password2, b);
        b.gridx = 1;
        add(password2DaInserire, b);

        b.gridx = 0;
        b.gridy = 4;
        b.gridwidth = 2;
        add(termini, b);
    }

    public void scrittura() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("registrazione.dat"))) {
            Persona p = new Persona(emailDaInserire.getText(),
                    password1DaInserire.getPassword(),
                    password2DaInserire.getPassword(),
                    usernameDaInserire.getText());
            ArrayList<Persona> persone = lettura();
            persone.add(p);
            out.writeObject(persone);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Persona> lettura() {
        try (ObjectInputStream inp = new ObjectInputStream(new FileInputStream("registrazione.dat"))) {
            return (ArrayList<Persona>) inp.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}