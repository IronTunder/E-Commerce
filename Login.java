import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    JTextField nomeUtente = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton login = new JButton("Accedi");

    public Login(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel nome = new JPanel();
        JPanel password = new JPanel();
        JPanel campi = new JPanel();

        campi.setLayout(new GridLayout(2,1,10,10));

        TitledBorder nometitle = BorderFactory.createTitledBorder("Nome Utente / Email");
        TitledBorder passwtitle = BorderFactory.createTitledBorder("Password");

        nometitle.setTitleJustification(TitledBorder.CENTER);
        passwtitle.setTitleJustification(TitledBorder.CENTER);

        nomeUtente.setPreferredSize(new Dimension(200,30));
        passwordField.setPreferredSize(new Dimension(200,30));
        nome.add(nomeUtente);
        password.add(passwordField);
        nome.setBorder(nometitle);
        password.setBorder(passwtitle);

        campi.add(nome);
        campi.add(password);

        add(campi,BorderLayout.CENTER);
        login.addActionListener(this);
        add(login,BorderLayout.PAGE_END);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(nomeUtente.getText().length() < 4 || nomeUtente.getText().length() > 20)
            JOptionPane.showMessageDialog(this,"Inserire un nome utente valido");
        else if (passwordField.getPassword().length < 8 || passwordField.getPassword().length > 20)
            JOptionPane.showMessageDialog(this,"Inserire una password valida");
    }
}
