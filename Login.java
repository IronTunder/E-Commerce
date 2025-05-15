import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    JTextField nomeUtente = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton login = new JButton("Accedi");

    public Login() {
        super("Login");

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);

        JPanel campi = new JPanel(new GridLayout(2, 1, 10, 10));
        TitledBorder nometitle = BorderFactory.createTitledBorder("Nome Utente / Email");
        TitledBorder passwtitle = BorderFactory.createTitledBorder("Password");
        nometitle.setTitleJustification(TitledBorder.CENTER);
        passwtitle.setTitleJustification(TitledBorder.CENTER);

        JPanel nome = new JPanel(new BorderLayout());
        JPanel password = new JPanel(new BorderLayout());
        nome.setBorder(nometitle);
        password.setBorder(passwtitle);

        nomeUtente.setPreferredSize(new Dimension(200, 30));
        passwordField.setPreferredSize(new Dimension(200, 30));
        nome.add(nomeUtente, BorderLayout.CENTER);
        password.add(passwordField, BorderLayout.CENTER);

        campi.add(nome);
        campi.add(password);

        add(campi, BorderLayout.CENTER);
        login.setFont(new Font("Arial", Font.BOLD, 14));
        login.setBackground(new Color(100, 149, 237));
        login.setForeground(Color.WHITE);
        add(login, BorderLayout.PAGE_END);
        login.addActionListener(this);

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (nomeUtente.getText().length() < 4 || nomeUtente.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "Inserire un nome utente valido", "Errore", JOptionPane.ERROR_MESSAGE);
        } else if (passwordField.getPassword().length < 8 || passwordField.getPassword().length > 20) {
            JOptionPane.showMessageDialog(this, "Inserire una password valida", "Errore", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Accesso effettuato con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}