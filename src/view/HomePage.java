package src.view;

import src.models.AccessoriPC;
import src.models.Prodotto;
import src.view.auth.InterfacciaAuth;
import src.view.components.PannelloCategoria;
import src.view.components.PannelloLaterale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HomePage extends JFrame implements ActionListener {
        private InterfacciaAuth interfacciaAuth;
        private final String[] categorie = {
                "Accessori PC", "Accessori SmartPhone", "Audio, Video e Gaming",
                "Casa e Ufficio", "Cavetteria", "Componenti PC",
                "Computer Desktop", "Consumabili", "Notebook e Accessori",
                "Usato Garantito"
        };

    public HomePage() {
        super("Nucifora's Hub");
        setLayout(new BorderLayout());

        PannelloLaterale pannelloLaterale = new PannelloLaterale();
        add(pannelloLaterale, BorderLayout.WEST);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 240, 240));

        for (String categoria : categorie) {
            ArrayList<Prodotto> prodotti = creaProdottiEsempio(categoria);
            PannelloCategoria categoriaPanel = new PannelloCategoria(categoria, prodotti);

            // Imposta dimensioni massime
            categoriaPanel.setMaximumSize(new Dimension(
                    Toolkit.getDefaultToolkit().getScreenSize().width - 250,
                    250
            ));

            mainPanel.add(categoriaPanel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        setLocation(10, 10);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 720);
        setUndecorated(true);
        setJMenuBar(creaBarraMenu());
        setVisible(true);
    }

    private ArrayList<Prodotto> creaProdottiEsempio(String categoria) {
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        String[] marche = {"Samsung", "Logitech", "Razer", "Apple", "Sony", "HP", "Dell", "Lenovo"};

        // Limita a 10 prodotti per categoria
        for (int i = 1; i <= 10; i++) {
            prodotti.add(new AccessoriPC(
                    "Prodotto " + i + " " + categoria,
                    String.valueOf(i),
                    marche[i % marche.length],
                    categoria,
                    null,
                    104.99
            ));
        }
        return prodotti;
    }


    private JMenuBar creaBarraMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
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