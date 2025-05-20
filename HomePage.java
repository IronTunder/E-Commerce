import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener {

    PannelloLaterale pannelloLaterale = new PannelloLaterale();
    Registrazione registrazione;

    public HomePage() {
        super("Nucifora's Hub");
        setLayout(new BorderLayout());
        add(pannelloLaterale,BorderLayout.WEST);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 720);
        setUndecorated(true);
        setJMenuBar(createJMenu());
        setVisible(true);
    }

    public static void main(String[] args) {
        new HomePage();
    }

    private JMenuBar createJMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(50, 50, 50));
        menuBar.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        leftPanel.setBackground(new Color(50, 50, 50));
        centerPanel.setBackground(new Color(50, 50, 50));
        rightPanel.setBackground(new Color(50, 50, 50));

        JButton accedi = createStyledButton("Accedi", "icons/login.png","Clicca per accedere");
        JButton registrati = createStyledButton("Registrati", "icons/register.png","Clicca per registrarti");
        JButton carrello = createStyledButton("Carrello", "icons/cart.png","Clicca per visualizzare il carrello");
        JButton esci = createStyledButton("Esci", "icons/exit.png","Clicca per uscire");

        leftPanel.add(accedi);
        leftPanel.add(registrati);
        centerPanel.add(carrello);
        rightPanel.add(esci);

        menuBar.add(leftPanel, BorderLayout.WEST);
        menuBar.add(centerPanel, BorderLayout.CENTER);
        menuBar.add(rightPanel, BorderLayout.EAST);

        return menuBar;
    }

    private JButton createStyledButton(String text, String iconPath,String tooltiptext) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100,30));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(100, 100, 100));
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setFocusable(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        /*
        try {
            ImageIcon icon = new ImageIcon(iconPath);
            button.setIcon(icon);
        } catch (Exception e) {
            System.out.println("Icona non trovata: " + iconPath);
        }
        */
        button.setToolTipText(tooltiptext);
        button.setActionCommand(text);
        return button;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Registrati")){
            if (registrazione == null) {
                registrazione = new Registrazione();
            } else {
                registrazione.dispose();
                registrazione = new Registrazione();
            }
        }
    }
}
