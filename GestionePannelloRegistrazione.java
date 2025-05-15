import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class GestionePannelloRegistrazione extends JPanel {

    JLabel email = new JLabel("Email: ");
    JLabel username = new JLabel("Username: ");
    JLabel password1 = new JLabel("Password: ");
    JLabel password2 = new JLabel("Verifica Password");

    JTextField emailDaInserire = new JTextField(20);
    JTextField usernameDaInserire = new JTextField(20);
    JPasswordField password1DaInserire = new JPasswordField(20);
    JPasswordField password2DaInserire = new JPasswordField(20);

    JCheckBox termini = new JCheckBox("Accetto termini di utilizzo");

    JButton registrati = new JButton("Registrati");

    String [] controlloEmail = {"@","@gmail.com","@gmail.it",".it",".com"};



    public GestionePannelloRegistrazione(){
        setLayout(new GridBagLayout());

        GridBagConstraints b = new GridBagConstraints();

        b.gridx = 0;
        b.gridy = 0;
        b.insets = new Insets(10,10,10,10);
        add(email,b);
        b.gridx = 1;
        b.gridy = 0;
        add(emailDaInserire,b);

        b.gridx = 0;
        b.gridy = 1;
        add(username,b);
        b.gridx = 1;
        b.gridy = 1;
        add(usernameDaInserire,b);

        b.gridx = 0;
        b.gridy = 2;
        add(password1,b);
        b.gridx = 1;
        b.gridy = 2;
        add(password1DaInserire,b);

        b.gridx = 0;
        b.gridy = 3;
        add(password2,b);
        b.gridx = 1;
        b.gridy = 3;
        add(password2DaInserire,b);

        b.gridx = 0;
        b.gridy = 4;
        add(termini,b);

        b.gridx = 1;
        b.gridy = 5;
        add(registrati,b);

        registrati.setEnabled(false);


        termini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(termini.isSelected()){
                    registrati.setEnabled(true);
                }
                else{
                    registrati.setEnabled(false);
                }
            }
        });


        registrati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(password1DaInserire.getText().equals(password2DaInserire.getText()))){
                    JOptionPane.showMessageDialog(null,"Password non sono uguali");
                }
                else if (!(emailDaInserire.getText().contains("@") && (emailDaInserire.getText().contains("@gmail.com") || emailDaInserire.getText().contains(".it") || emailDaInserire.getText().contains(".com")))) {
                    JOptionPane.showMessageDialog(null,"email non corretta");
                }
                else if(usernameDaInserire.getText().length() < 4 || usernameDaInserire.getText().length() > 20){
                    JOptionPane.showMessageDialog(null,"Username non valido");
                }
                else{
                    scrittura();
                    JOptionPane.showMessageDialog(null,"Utente registrato con successo");
                }
            }
        });


        /*
        CONTROLLI DA FARE:

            - LA PASSWORD 1 DEVE ESSERE UGUALE ALLA PASSWORD 2
            - FARE IL CONTROLLO DELL'EMAIL
            - SE NON ACCETTA I TERMINI NON TI FA REGISTRARE
         */

    }


    public void scrittura(){
        try {
            ArrayList<Persona> persone = lettura();
            Persona p = new Persona(emailDaInserire.getText(),password1DaInserire.getPassword(),password2DaInserire.getPassword(),usernameDaInserire.getText());
            persone.add(p);
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("registrazione.dat"));
            out.writeObject(persone);
        } catch (Exception e) {
        }
    }

    public ArrayList<Persona> lettura(){
        try {
            ObjectInputStream inp = new ObjectInputStream(new FileInputStream("registrazione.dat"));
            return (ArrayList<Persona>) inp.readObject();
        } catch (IOException e) {
            return new ArrayList<>();
        } catch (ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }



}
