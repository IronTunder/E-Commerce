package src.view;

import src.controllers.AuthController;
import src.models.Prodotto;
import src.utils.FileManager;
import src.view.auth.InterfacciaAuth;
import src.view.components.PannelloCategoria;
import src.view.components.PannelloLaterale;
import src.utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HomePage extends JFrame implements ActionListener {

    private JButton accountButton;
    private InterfacciaAuth interfacciaAuth;
    private final AuthController authController = new AuthController(this);

    public HomePage() {
        super("Nucifora's Hub");
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon("./icon.png").getImage());
        PannelloLaterale pannelloLaterale = new PannelloLaterale();
        add(pannelloLaterale, BorderLayout.WEST);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 240, 240));

        String[] categorie = {
                "Accessori PC", "Accessori SmartPhone", "Audio, Video e Gaming",
                "Casa e Ufficio", "Cavetteria", "Componenti PC",
                "Computer Desktop", "Consumabili", "Notebook e Accessori",
                "Usato Garantito"
        };

        FileManager fileManager = new FileManager();
        fileManager.caricaProdotti();

        for (String categoria : categorie) {
            ArrayList<Prodotto> prodotti = new ArrayList<>();
            for (int i = 0; i < fileManager.getProdotti().size(); i++) {
                if(fileManager.getProdotti().get(i).getCategoria().equals(categoria)){
                    prodotti.add(fileManager.getProdotti().get(i));
                }
            }
            PannelloCategoria categoriaPanel = new PannelloCategoria(categoria, prodotti);

            categoriaPanel.setMaximumSize(new Dimension(
                    Integer.MAX_VALUE,
                    250
            ));

            mainPanel.add(categoriaPanel);
            mainPanel.add(Box.createVerticalStrut(20));
        }

        mainPanel.add(Box.createVerticalGlue());

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        setLocation(10, 10);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 720);
        setUndecorated(true);
        setJMenuBar(creaBarraMenu());
        updateAuthUI();
        setVisible(true);
    }

    private JMenuBar creaBarraMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        menuBar.setBackground(new Color(50, 50, 50));
        menuBar.setLayout(new BorderLayout());
        JPanel centerPanel = creaPannelloMenu(FlowLayout.CENTER,
                creaBottone("Carrello", "Clicca per visualizzare il carrello"));

        JPanel rightPanel = creaPannelloMenu(FlowLayout.RIGHT,
                creaBottone("Esci", "Clicca per uscire"));
        menuBar.add(aggiungiAuth(), BorderLayout.WEST);
        menuBar.add(centerPanel, BorderLayout.CENTER);
        menuBar.add(rightPanel, BorderLayout.EAST);
        return menuBar;
    }

    public JPanel aggiungiAuth() {
        return creaPannelloMenu(FlowLayout.LEFT,
                creaBottone("Accedi", "Clicca per accedere"),
                creaBottone("Registrati", "Clicca per registrarti"));
    }

    public void rimuoviAuth() {
        JMenuBar menuBar = this.getJMenuBar();
        menuBar.remove(0);

        if (accountButton == null) {
            ImageIcon imageIcon = new ImageIcon("./icons/login.png");
            Image scaledImage = imageIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            accountButton = creaBottone(new ImageIcon(scaledImage), "Visualizza Account", "Account");
        }

        JPanel accountPanel = creaPannelloMenu(FlowLayout.LEFT, accountButton);
        menuBar.add(accountPanel, BorderLayout.WEST);

        menuBar.revalidate();
        menuBar.repaint();
    }

    public void updateAuthUI() {
        JMenuBar menuBar = this.getJMenuBar();
        if (menuBar == null) {
            menuBar = new JMenuBar();
            setJMenuBar(menuBar);
        }

        menuBar.removeAll();

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(50, 50, 50));

        if (authController.isLoggedIn()) {
            if (accountButton == null) {
                ImageIcon imageIcon = new ImageIcon("./icons/login.png");
                Image scaledImage = imageIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
                accountButton = creaBottone(new ImageIcon(scaledImage), "Visualizza Account", "Account");
            }
            leftPanel.add(accountButton);

            // Bottone admin più visibile
            if (authController.getLogin().isAdmininstrator()) {
                JButton adminButton = creaBottone("ADMIN PANEL", "Pannello di amministrazione");
                adminButton.setActionCommand("AdminPanel");
                adminButton.setBackground(new Color(200, 0, 0)); // Rosso per maggiore visibilità
                adminButton.setForeground(Color.WHITE);
                adminButton.addActionListener(this);
                leftPanel.add(adminButton);
            }
        } else {
            JButton accedi = creaBottone("Accedi", "Clicca per accedere");
            JButton registrati = creaBottone("Registrati", "Clicca per registrarti");
            leftPanel.add(accedi);
            leftPanel.add(registrati);
        }
        menuBar.add(leftPanel, BorderLayout.WEST);

        // Pannello centrale (carrello)
        JPanel centerPanel = creaPannelloMenu(FlowLayout.CENTER,
                creaBottone("Carrello", "Clicca per visualizzare il carrello"));
        menuBar.add(centerPanel, BorderLayout.CENTER);

        // Pannello destro (esci)
        JPanel rightPanel = creaPannelloMenu(FlowLayout.RIGHT,
                creaBottone("Esci", "Clicca per uscire"));
        menuBar.add(rightPanel, BorderLayout.EAST);

        menuBar.revalidate();
        menuBar.repaint();
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

    public void onLoginSuccess() {
        updateAuthUI();
    }

    private JButton creaBottone(ImageIcon imageIcon, String tooltip, String actionCommand) {
        JButton button = new JButton(imageIcon);
        button.setPreferredSize(new Dimension(100, 30));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(100, 100, 100));
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setFocusable(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);
        button.setToolTipText(tooltip);
        button.setActionCommand(actionCommand);
        return button;
    }

    private void mostraPannelloAdmin() {
        // Controlla se il frame admin è già aperto
        for (Window window : Window.getWindows()) {
            if (window instanceof JFrame && "Pannello Amministratore".equals(((JFrame) window).getTitle())) {
                window.toFront();
                return;
            }
        }

        JFrame adminFrame = new JFrame("Pannello Amministratore");
        adminFrame.setResizable(false);
        adminFrame.setIconImage(new ImageIcon("./icon.png").getImage());
        adminFrame.setLayout(new BorderLayout());
        adminFrame.setSize(900, 650);
        adminFrame.setLocationRelativeTo(this);

        // Pannello principale con bordo
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));

        FileManager fileManager = new FileManager();
        fileManager.caricaProdotti();

        Inserimento inserimento = new Inserimento();
        ElencoMagazzino elencoMagazzino = new ElencoMagazzino(inserimento);
        BottoniInserimento bottoniInserimento = new BottoniInserimento(inserimento, elencoMagazzino);

        // Stilizzazione componenti
        inserimento.setBackground(new Color(240, 240, 240));
        elencoMagazzino.setBackground(new Color(240, 240, 240));
        bottoniInserimento.setBackground(new Color(240, 240, 240));

        // Area di testo con scroll
        JScrollPane scrollPane = new JScrollPane(elencoMagazzino.getTextArea());
        scrollPane.setBorder(BorderFactory.createTitledBorder("Elenco Prodotti"));
        elencoMagazzino.remove(elencoMagazzino.getTextArea());
        elencoMagazzino.add(scrollPane);

        // Aggiunta dei componenti al pannello principale
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(new JLabel("Inserimento Prodotti", JLabel.CENTER), BorderLayout.NORTH);
        northPanel.add(inserimento, BorderLayout.CENTER);
        northPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(elencoMagazzino, BorderLayout.CENTER);
        mainPanel.add(bottoniInserimento, BorderLayout.SOUTH);

        adminFrame.add(mainPanel);
        adminFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Registrati":
                if (interfacciaAuth == null || !interfacciaAuth.isVisible()) {
                    interfacciaAuth = new InterfacciaAuth(authController);
                }
                break;
            case "Esci":
                System.exit(0);
                break;
            case "Accedi":
                if (interfacciaAuth == null || !interfacciaAuth.isVisible()) {
                    interfacciaAuth = new InterfacciaAuth(authController);
                    interfacciaAuth.mostraPannelloLogin();
                }
                break;
            case "Carrello":
                if (authController.isLoggedIn()) {
                    //TODO: Implementa logica per carrello
                } else {
                    JOptionPane.showMessageDialog(this, "Accedere per utilizzare il carrello", "Attenzione", JOptionPane.WARNING_MESSAGE);
                    if (interfacciaAuth == null || !interfacciaAuth.isVisible()) {
                        interfacciaAuth = new InterfacciaAuth(authController);
                        interfacciaAuth.mostraPannelloLogin();
                    }
                }
                break;
            case "AdminPanel":
                mostraPannelloAdmin();
                break;
        }
    }
}