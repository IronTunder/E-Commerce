package src.view.admin.ordini;

import src.controllers.OrdersController;
import src.models.Ordine;
import src.models.Prodotto;
import src.models.Utente;
import src.view.HomePage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class OrderPanel extends JFrame {

    private final OrdersController ordersController;
    private JPanel mainPanel;

    public OrderPanel(HomePage homePage) {
        this.ordersController = homePage.getOrdersController();

        setTitle("Gestione Ordini - Admin Panel");
        setSize(1000, 700);
        setLocationRelativeTo(homePage);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(homePage.getIconImage());

        initUI();
        setResizable(false);
        setVisible(true);
    }

    private void initUI() {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(248, 248, 248));

        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(new Color(248, 248, 248));
        JLabel titleLabel = new JLabel("Gestione Ordini");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(50, 50, 50));
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Orders panel (scrollable)
        JPanel ordersPanel = new JPanel();
        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));
        ordersPanel.setBackground(new Color(248, 248, 248));

        JScrollPane scrollPane = new JScrollPane(ordersPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(248, 248, 248));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        loadOrders(ordersPanel);

        add(mainPanel);
    }

    private void loadOrders(JPanel ordersPanel) {
        ordersPanel.removeAll();
        ordersController.caricaOrdini();
        List<Ordine> ordini = ordersController.getOrdini();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.ITALY);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        if (ordini.isEmpty()) {
            JLabel emptyLabel = new JLabel("Nessun ordine presente", JLabel.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 18));
            emptyLabel.setForeground(new Color(150, 150, 150));
            ordersPanel.add(emptyLabel);
        } else {
            for (Ordine ordine : ordini) {
                ordersPanel.add(createOrderPanel(ordine, currencyFormat, dateFormatter));
                ordersPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            }
        }

        ordersPanel.revalidate();
        ordersPanel.repaint();
    }

    private JPanel createOrderPanel(Ordine ordine, NumberFormat currencyFormat, DateTimeFormatter dateFormatter) {
        JPanel orderPanel = new JPanel(new BorderLayout(15, 10));
        orderPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        orderPanel.setBackground(Color.WHITE);
        orderPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        // Order info panel
        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        infoPanel.setBackground(Color.WHITE);

        Utente utente = ordine.getUtente();
        String orderInfo = "<html><b>Ordine #" + ordine.getId() + "</b> - " +
                dateFormatter.format(ordine.getDate()) + "</html>";
        String userInfo = "<html>Cliente: " + utente.getUsername() + " (" + utente.getEmail() + ")</html>";
        String addressInfo = "<html>Indirizzo: " + ordine.getIndirizzo() + "</html>";
        String totalInfo = "<html>Totale: " + currencyFormat.format(ordine.getTotal()) + "</html>";

        JLabel orderLabel = new JLabel(orderInfo);
        JLabel userLabel = new JLabel(userInfo);
        JLabel addressLabel = new JLabel(addressInfo);
        JLabel totalLabel = new JLabel(totalInfo);

        orderLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));

        infoPanel.add(orderLabel);
        infoPanel.add(userLabel);
        infoPanel.add(addressLabel);
        infoPanel.add(totalLabel);

        // Products panel
        JPanel productsPanel = new JPanel(new BorderLayout());
        productsPanel.setBackground(Color.WHITE);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Prodotto p : ordine.getProdottiOrdinati()) {
            listModel.addElement(p.getNome() + " - " + currencyFormat.format(p.getPrezzo()));
        }

        JList<String> productsList = new JList<>(listModel);
        productsList.setFont(new Font("Arial", Font.PLAIN, 12));
        productsList.setBackground(new Color(248, 248, 248));
        productsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane listScrollPane = new JScrollPane(productsList);
        listScrollPane.setPreferredSize(new Dimension(300, 100));

        productsPanel.add(new JLabel("Prodotti:"), BorderLayout.NORTH);
        productsPanel.add(listScrollPane, BorderLayout.CENTER);

        // Status and buttons panel
        JPanel statusPanel = new JPanel(new BorderLayout(10, 10));
        statusPanel.setBackground(Color.WHITE);

        JLabel statusLabel = new JLabel("Stato: " + ordine.getStato());
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel.setForeground(getStatusColor(ordine.getStato()));

        JButton shipButton = new JButton("Spedisci");
        shipButton.setFont(new Font("Arial", Font.BOLD, 12));
        shipButton.setBackground(new Color(76, 175, 80));
        shipButton.setForeground(Color.WHITE);
        shipButton.setPreferredSize(new Dimension(100, 30));
        shipButton.setEnabled(!ordine.getStato().equals("Spedito"));
        shipButton.addActionListener(e -> {
            ordine.setStato("Spedito");
            ordersController.salvaOrdini();
            loadOrders((JPanel) ((JScrollPane) mainPanel.getComponent(1)).getViewport().getView());
            JOptionPane.showMessageDialog(this,
                    "Ordine #" + ordine.getId() + " marcato come spedito",
                    "Ordine Spedito",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        statusPanel.add(statusLabel, BorderLayout.CENTER);
        statusPanel.add(shipButton, BorderLayout.EAST);

        // Main content panel
        JPanel contentPanel = new JPanel(new BorderLayout(15, 10));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.add(infoPanel, BorderLayout.WEST);
        contentPanel.add(productsPanel, BorderLayout.CENTER);
        contentPanel.add(statusPanel, BorderLayout.SOUTH);

        orderPanel.add(contentPanel, BorderLayout.CENTER);

        return orderPanel;
    }

    private Color getStatusColor(String stato) {
        switch (stato) {
            case "Spedito":
                return new Color(0, 128, 0); // Verde
            case "In elaborazione":
                return new Color(255, 165, 0); // Arancione
            case "Annullato":
                return Color.RED;
            default:
                return Color.BLACK;
        }
    }
}