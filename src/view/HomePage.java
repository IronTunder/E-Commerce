package src.view;

import src.controllers.AuthController;
import src.controllers.OrdersController;
import src.models.Prodotto;
import src.controllers.ProductsController;
import src.view.admin.ordini.OrderPanel;
import src.view.admin.prodotti.ProductsPanel;
import src.view.auth.InterfacciaAuth;
import src.view.carrello.InterfacciaCart;
import src.view.components.PannelloCategoria;
import src.view.components.PannelloLaterale;
import src.view.prodotto.ProdottoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HomePage extends JFrame implements ActionListener {

    private InterfacciaAuth interfacciaAuth;
    private ProductsPanel productsPanel;
    private OrderPanel orderPanel;
    private InterfacciaCart interfacciaCart;
    private final AuthController authController = new AuthController(this);
    private final ProductsController productsController = new ProductsController();
    private final OrdersController ordersController = new OrdersController();

    public HomePage() {
        super("Nucifora's Hub");
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon("src/icon.png").getImage());
        PannelloLaterale pannelloLaterale = new PannelloLaterale(this);
        add(pannelloLaterale, BorderLayout.WEST);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 240, 240));

        String[] categorie = {
                "AccessoriPC", "AccessoriSmartPhone", "Audio,VideoeGaming",
                "CasaeUfficio", "Cavetteria", "ComponentiPC",
                "ComputerDesktop", "Consumabili", "NotebookeAccessori",
                "UsatoGarantito"
        };
        productsController.caricaProdotti();
        for (String categoria : categorie) {
            ArrayList<Prodotto> prodotti = new ArrayList<>();
            for (Prodotto p : productsController.getProdotti()) {
                if(p.getCategoria().equals(categoria)) {
                    System.out.println(p);
                    prodotti.add(p);
                }
            }
            PannelloCategoria categoriaPanel = new PannelloCategoria(categoria, prodotti,this);
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
        setSize(1280, 720);
        setResizable(false);
        setJMenuBar(creaBarraMenu());
        updateAuthUI();
        setVisible(true);
    }

    public void aggiornaVisualizzazioneProdotti() {
        Point location = getLocation();
        boolean resizable = isResizable();
        getContentPane().removeAll();

        setLayout(new BorderLayout());
        PannelloLaterale pannelloLaterale = new PannelloLaterale(this);
        add(pannelloLaterale, BorderLayout.WEST);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 240, 240));

        String[] categorie = {
                "AccessoriPC", "AccessoriSmartPhone", "Audio,VideoeGaming",
                "CasaeUfficio", "Cavetteria", "ComponentiPC",
                "ComputerDesktop", "Consumabili", "NotebookeAccessori",
                "UsatoGarantito"
        };

        for (String categoria : categorie) {
            ArrayList<Prodotto> prodotti = new ArrayList<>();
            for (Prodotto p : productsController.getProdotti()) {
                if(p.getCategoria().equals(categoria)) {
                    prodotti.add(p);
                }
            }
            PannelloCategoria categoriaPanel = new PannelloCategoria(categoria, prodotti,this);
            categoriaPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
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

        setLocation(location);
        setResizable(resizable);

        setJMenuBar(creaBarraMenu());
        updateAuthUI();

        revalidate();
        repaint();
    }

    public JPanel aggiungiAuth() {
        return creaPannelloMenu(FlowLayout.LEFT,
                creaBottone("Accedi", "Clicca per accedere"),
                creaBottone("Registrati", "Clicca per registrarti"));
    }

    public void rimuoviAuth() {
        JMenuBar menuBar = this.getJMenuBar();
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
            if (authController.getLogin().isAdmininstrator()) {

                JButton adminButton = creaBottone("PRODOTTI", "Pannello di amministrazione");
                adminButton.setActionCommand("AdminPanel");
                adminButton.setBackground(new Color(200, 0, 0));
                adminButton.setForeground(Color.WHITE);
                adminButton.addActionListener(this);
                leftPanel.add(adminButton);


                JButton ordersButton = creaBottone("ORDINI", "Gestisci gli ordini");
                ordersButton.setActionCommand("OrderManagement");
                ordersButton.setBackground(new Color(0, 100, 200));
                ordersButton.setForeground(Color.WHITE);
                ordersButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mostraPannelloOrdini();
                    }
                });
                leftPanel.add(ordersButton);

                JPanel rightPanel = creaPannelloMenu(FlowLayout.RIGHT,
                        creaBottone("Esci", "Clicca per uscire"));
                menuBar.add(rightPanel, BorderLayout.EAST);
            }
        } else {
            JButton accedi = creaBottone("Accedi", "Clicca per accedere");
            JButton registrati = creaBottone("Registrati", "Clicca per registrarti");
            leftPanel.add(accedi);
            leftPanel.add(registrati);
        }
        menuBar.add(leftPanel, BorderLayout.WEST);


        JPanel centerPanel = creaPannelloMenu(FlowLayout.CENTER,
                creaBottone("Carrello", "Clicca per visualizzare il carrello"));
        menuBar.add(centerPanel, BorderLayout.CENTER);

        menuBar.revalidate();
        menuBar.repaint();
    }

    public void onLoginSuccess() {
        updateAuthUI();
    }

    private JMenuBar creaBarraMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        menuBar.setBackground(new Color(50, 50, 50));
        menuBar.setLayout(new BorderLayout());
        JPanel centerPanel = creaPannelloMenu(FlowLayout.CENTER,
                creaBottone("Carrello", "Clicca per visualizzare il carrello"));
        menuBar.add(aggiungiAuth(), BorderLayout.WEST);
        menuBar.add(centerPanel, BorderLayout.CENTER);
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

    private void mostraPannelloProdotti() {
        if(productsPanel != null)
            productsPanel.dispose();
        productsPanel = new ProductsPanel(this);
        productsPanel.toFront();
    }

    public void mostraProdotto(Prodotto prodotto) {
        getContentPane().removeAll();


        PannelloLaterale pannelloLaterale = new PannelloLaterale(this);
        add(pannelloLaterale, BorderLayout.WEST);


        JScrollPane scrollPane = new JScrollPane(new ProdottoPanel(this, prodotto));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public void mostraHomePage() {
        getContentPane().removeAll();

        PannelloLaterale pannelloLaterale = new PannelloLaterale(this);
        add(pannelloLaterale, BorderLayout.WEST);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 240, 240));

        String[] categorie = {
                "AccessoriPC", "AccessoriSmartPhone", "Audio,VideoeGaming",
                "CasaeUfficio", "Cavetteria", "ComponentiPC",
                "ComputerDesktop", "Consumabili", "NotebookeAccessori",
                "UsatoGarantito"
        };

        for (String categoria : categorie) {
            ArrayList<Prodotto> prodotti = new ArrayList<>();
            for (Prodotto p : productsController.getProdotti()) {
                if(p.getCategoria().equals(categoria)) {
                    prodotti.add(p);
                }
            }
            PannelloCategoria categoriaPanel = new PannelloCategoria(categoria, prodotti, this);
            categoriaPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
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

        revalidate();
        repaint();
    }

    private void mostraPannelloOrdini() {
        if(orderPanel != null)
            orderPanel.dispose();
        orderPanel = new OrderPanel(this);
        orderPanel.toFront();
    }

    public void mostraCategoria(String categoria) {
        getContentPane().removeAll();

        PannelloLaterale pannelloLaterale = new PannelloLaterale(this);
        add(pannelloLaterale, BorderLayout.WEST);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 240, 240));

        ArrayList<Prodotto> prodotti = new ArrayList<>();
        for (Prodotto p : productsController.getProdotti()) {
            if(p.getCategoria().equals(categoria)) {
                prodotti.add(p);
            }
        }

        PannelloCategoria categoriaPanel = new PannelloCategoria(categoria, prodotti, this);
        categoriaPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        mainPanel.add(categoriaPanel);
        mainPanel.add(Box.createVerticalGlue());

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(scrollPane, BorderLayout.CENTER);

        revalidate();
        repaint();
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
                if(authController.isLoggedIn()){
                    authController.logout();
                    JOptionPane.showMessageDialog(this,"Logout eseguito correttamente");
                    updateAuthUI();
                }
                break;
            case "Accedi":
                if (interfacciaAuth == null || !interfacciaAuth.isVisible()) {
                    interfacciaAuth = new InterfacciaAuth(authController);
                    interfacciaAuth.mostraPannelloLogin();
                }
                break;
            case "Carrello":
                if (authController.isLoggedIn()) {
                    if (interfacciaCart == null || !interfacciaCart.isVisible()) {
                        interfacciaCart = new InterfacciaCart(this);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Accedere per utilizzare il carrello", "Attenzione", JOptionPane.WARNING_MESSAGE);
                    if (interfacciaAuth == null || !interfacciaAuth.isVisible()) {
                        interfacciaAuth = new InterfacciaAuth(authController);
                        interfacciaAuth.mostraPannelloLogin();
                    }
                }
                break;
            case "AdminPanel":
                mostraPannelloProdotti();
                break;
        }
    }

    public ProductsController getFileManager() {
        return productsController;
    }

    public AuthController getAuthController() {
        return authController;
    }

    public ProductsPanel getAdminPanel() {
        return productsPanel;
    }

    public InterfacciaAuth getInterfacciaAuth() {
        return interfacciaAuth;
    }

    public OrdersController getOrdersController() {
        return ordersController;
    }

    public InterfacciaCart getInterfacciaCart() {
        return interfacciaCart;
    }

    public ProductsController getProductsController() {
        return productsController;
    }

    public Image getIconImage() {
        return new ImageIcon("src/icon.png").getImage();
    }
}