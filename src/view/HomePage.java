package src.view;

import src.view.auth.InterfacciaAuth;
import src.view.components.PannelloLaterale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener {
    private PannelloLaterale pannelloLaterale = new PannelloLaterale();
    private InterfacciaAuth interfacciaAuth;

    public HomePage() {
        super("Nucifora's Hub");
        setLayout(new BorderLayout());
        add(pannelloLaterale, BorderLayout.WEST);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 720);
        setUndecorated(true);
        setJMenuBar(creaBarraMenu());
        setVisible(true);
    }

    private JMenuBar creaBarraMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(50, 50, 50));
        menuBar.setLayout(new BorderLayout());

        JPanel leftPanel = creaPannelloMenu(FlowLayout.LEFT,
                creaBottone("Accedi", "Clicca per accedere"),
                creaBottone("Registrati", "Clicca per registrarti"));

        JPanel centerPanel = creaPannelloMenu(FlowLayout.CENTER,
                creaBottone("Carrello", "Clicca per visualizzare il carrello"));

        JPanel rightPanel = creaPannelloMenu(FlowLayout.RIGHT,
                creaBottone("Esci", "Clicca per uscire"));

        menuBar.add(leftPanel, BorderLayout.WEST);
        menuBar.add(centerPanel, BorderLayout.CENTER);
        menuBar.add(rightPanel, BorderLayout.EAST);

        return menuBar;
    }

    private JPanel creaPannelloMenu(int alignment, JButton... buttons) {
        JPanel panel = new JPanel(new FlowLayout(alignment));
        panel.setBackground(new Color(50, 50, 50));
        for (JButton button : buttons) {
            panel.add(button);
        }
        return panel;
    }

    private JButton creaBottone(String text, String tooltip) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 30));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(100, 100, 100));
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setFocusable(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);
        button.setToolTipText(tooltip);
        button.setActionCommand(text);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Registrati":
                if (interfacciaAuth == null || !interfacciaAuth.isVisible()) {
                    interfacciaAuth = new InterfacciaAuth();
                }
                break;
            case "Esci":
                System.exit(0);
                break;
            case "Accedi":
                if (interfacciaAuth == null || !interfacciaAuth.isVisible()) {
                    interfacciaAuth = new InterfacciaAuth();
                    interfacciaAuth.mostraPannelloLogin();
                }
                break;
        }
    }
}