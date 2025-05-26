package src.view.carrello;

import src.controllers.AuthController;
import src.models.Prodotto;
import src.models.Utente;
import src.view.HomePage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;

public class InterfacciaCart extends JFrame {

    private HomePage homePage;
    private AuthController authController;
    private JPanel mainPanel;
    private JLabel totalLabel;
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.ITALY);

    public InterfacciaCart(HomePage homePage) {
        this.homePage = homePage;
        this.authController = homePage.getAuthController();

        setTitle("Carrello - " + authController.getLogin().getUsername());
        setSize(800, 600);
        setLocationRelativeTo(homePage);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(homePage.getIconImage());

        initUI();
        updateCartDisplay();
    }

    private void initUI() {
        // Main panel with border layout
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("Il tuo carrello");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Cart items panel (scrollable)
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom panel with total and buttons
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));

        // Total label
        totalLabel = new JLabel("Totale: ", JLabel.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bottomPanel.add(totalLabel, BorderLayout.NORTH);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        JButton backButton = new JButton("Continua lo shopping");
        backButton.addActionListener(e -> this.dispose());

        JButton checkoutButton = new JButton("Procedi all'acquisto");
        checkoutButton.setBackground(new Color(50, 150, 50));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.addActionListener(e -> proceedToCheckout());

        buttonsPanel.add(backButton);
        buttonsPanel.add(checkoutButton);

        bottomPanel.add(buttonsPanel, BorderLayout.SOUTH);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void updateCartDisplay() {
        Utente currentUser = authController.getLogin();
        List<Prodotto> cart = currentUser.getCarrello();

        // Get the items panel from the scroll pane
        JScrollPane scrollPane = (JScrollPane) mainPanel.getComponent(1);
        JPanel itemsPanel = (JPanel) scrollPane.getViewport().getView();
        itemsPanel.removeAll();

        if (cart.isEmpty()) {
            JLabel emptyLabel = new JLabel("Il carrello è vuoto", JLabel.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            itemsPanel.add(emptyLabel);
        } else {
            // Group products by ID and count quantities
            Map<Prodotto, Integer> productQuantities = new HashMap<>();
            for (Prodotto p : cart) {
                productQuantities.put(p, productQuantities.getOrDefault(p, 0) + 1);
            }

            // Create an item panel for each unique product
            for (Map.Entry<Prodotto, Integer> entry : productQuantities.entrySet()) {
                Prodotto prodotto = entry.getKey();
                int quantity = entry.getValue();

                itemsPanel.add(createCartItemPanel(prodotto, quantity));
                itemsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        // Update total
        double total = cart.stream().mapToDouble(Prodotto::getPrezzo).sum();
        totalLabel.setText("Totale: " + currencyFormat.format(total));

        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    private JPanel createCartItemPanel(Prodotto prodotto, int quantity) {
        JPanel itemPanel = new JPanel(new BorderLayout(10, 10));
        itemPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Product info panel
        JPanel infoPanel = new JPanel(new BorderLayout(10, 10));

        JLabel nameLabel = new JLabel(prodotto.getNome());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel priceLabel = new JLabel(currencyFormat.format(prodotto.getPrezzo()));
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel totalLabel = new JLabel("Totale: " + currencyFormat.format(prodotto.getPrezzo() * quantity));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));

        infoPanel.add(nameLabel, BorderLayout.NORTH);
        infoPanel.add(priceLabel, BorderLayout.CENTER);
        infoPanel.add(totalLabel, BorderLayout.SOUTH);

        // Quantity control panel
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));

        JButton decreaseButton = new JButton("-");
        decreaseButton.setPreferredSize(new Dimension(30, 25));
        decreaseButton.addActionListener(e -> updateQuantity(prodotto, -1));

        JLabel quantityLabel = new JLabel(String.valueOf(quantity));
        quantityLabel.setPreferredSize(new Dimension(30, 25));
        quantityLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton increaseButton = new JButton("+");
        increaseButton.setPreferredSize(new Dimension(30, 25));
        increaseButton.addActionListener(e -> updateQuantity(prodotto, 1));

        quantityPanel.add(decreaseButton);
        quantityPanel.add(quantityLabel);
        quantityPanel.add(increaseButton);

        // Remove button
        JButton removeButton = new JButton("Rimuovi");
        removeButton.setForeground(Color.RED);
        removeButton.addActionListener(e -> removeProduct(prodotto));

        // Control panel (quantity + remove)
        JPanel controlPanel = new JPanel(new BorderLayout(10, 10));
        controlPanel.add(quantityPanel, BorderLayout.CENTER);
        controlPanel.add(removeButton, BorderLayout.SOUTH);

        itemPanel.add(infoPanel, BorderLayout.CENTER);
        itemPanel.add(controlPanel, BorderLayout.EAST);

        return itemPanel;
    }

    private void updateQuantity(Prodotto prodotto, int delta) {
        Utente currentUser = authController.getLogin();
        java.util.List<Prodotto> cart = currentUser.getCarrello();

        if (delta > 0) {
            // Add product
            cart.add(prodotto);
        } else {
            // Remove product (find first occurrence)
            for (int i = 0; i < cart.size(); i++) {
                if (cart.get(i).equals(prodotto)) {
                    cart.remove(i);
                    break;
                }
            }
        }

        // Update user's cart
        currentUser.setCarrello(cart);
        updateCartDisplay();
    }

    private void removeProduct(Prodotto prodotto) {
        Utente currentUser = authController.getLogin();
        java.util.List<Prodotto> cart = currentUser.getCarrello();

        // Remove all instances of the product
        cart.removeIf(p -> p.equals(prodotto));

        // Update user's cart
        currentUser.setCarrello(cart);
        updateCartDisplay();
    }

    private void proceedToCheckout() {
        Utente currentUser = authController.getLogin();
        if (currentUser.getCarrello().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Il carrello è vuoto!",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int option = JOptionPane.showConfirmDialog(this,
                "Confermi l'acquisto di " + currentUser.getCarrello().size() + " articoli?",
                "Conferma acquisto",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            // Process checkout
            double total = currentUser.getCarrello().stream()
                    .mapToDouble(Prodotto::getPrezzo)
                    .sum();

            // Clear cart
            currentUser.setCarrello(new ArrayList<>());

            JOptionPane.showMessageDialog(this,
                    "Acquisto completato con successo!\nTotale: " + currencyFormat.format(total),
                    "Acquisto completato",
                    JOptionPane.INFORMATION_MESSAGE);

            updateCartDisplay();
            this.dispose();
        }
    }
}