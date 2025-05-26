package src.view.carrello;

import src.controllers.AuthController;
import src.models.Prodotto;
import src.models.Utente;
import src.view.HomePage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;

public class InterfacciaCart extends JFrame {

    private final AuthController authController;
    private JPanel mainPanel;
    private JLabel totalLabel;
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.ITALY);
    private boolean cartModified = false;
    private static final int IMG_WIDTH = 80;
    private static final int IMG_HEIGHT = 80;

    public InterfacciaCart(HomePage homePage) {
        this.authController = homePage.getAuthController();

        setTitle("Carrello - " + authController.getLogin().getUsername());
        setSize(900, 650); // Aumentato le dimensioni per accomodare le immagini
        setLocationRelativeTo(homePage);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(homePage.getIconImage());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (cartModified) {
                    updateUserProfile();
                }
            }
        });

        initUI();
        updateCartDisplay();
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
        JLabel titleLabel = new JLabel("Il tuo carrello");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(50, 50, 50));
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Cart items panel (scrollable)
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(new Color(248, 248, 248));

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(248, 248, 248));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom panel with total and buttons
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setBackground(new Color(248, 248, 248));
        bottomPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(15, 0, 0, 0)
        ));

        // Total label
        totalLabel = new JLabel("Totale: ", JLabel.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setForeground(new Color(70, 70, 70));
        bottomPanel.add(totalLabel, BorderLayout.NORTH);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        buttonsPanel.setBackground(new Color(248, 248, 248));

        JButton backButton = new JButton("Continua lo shopping");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setPreferredSize(new Dimension(180, 35));
        backButton.addActionListener(e -> {
            if (cartModified) {
                updateUserProfile();
            }
            this.dispose();
        });

        JButton checkoutButton = new JButton("Procedi all'acquisto");
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        checkoutButton.setBackground(new Color(76, 175, 80));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setPreferredSize(new Dimension(180, 35));
        checkoutButton.addActionListener(e -> proceedToCheckout());

        buttonsPanel.add(backButton);
        buttonsPanel.add(checkoutButton);

        bottomPanel.add(buttonsPanel, BorderLayout.SOUTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void updateUserProfile() {
        if (authController.isLoggedIn()) {
            ArrayList<Utente> utenti = authController.getUtenti();
            for (int i = 0; i < utenti.size(); i++) {
                if (authController.getLogin().equals(utenti.get(i))) {
                    utenti.remove(i);
                    utenti.add(authController.getLogin());
                    break;
                }
            }
            authController.salvaUtenti(utenti);
            cartModified = false;
        }
    }

    private void updateCartDisplay() {
        Utente currentUser = authController.getLogin();
        List<Prodotto> cart = currentUser.getCarrello();

        JScrollPane scrollPane = (JScrollPane) mainPanel.getComponent(1);
        JPanel itemsPanel = (JPanel) scrollPane.getViewport().getView();
        itemsPanel.removeAll();

        if (cart.isEmpty()) {
            JLabel emptyLabel = new JLabel("Il carrello è vuoto", JLabel.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 18));
            emptyLabel.setForeground(new Color(150, 150, 150));
            itemsPanel.add(emptyLabel);
        } else {
            Map<Prodotto, Integer> productQuantities = new HashMap<>();
            for (Prodotto p : cart) {
                productQuantities.put(p, productQuantities.getOrDefault(p, 0) + 1);
            }

            for (Map.Entry<Prodotto, Integer> entry : productQuantities.entrySet()) {
                itemsPanel.add(createCartItemPanel(entry.getKey(), entry.getValue()));
                itemsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            }
        }

        double total = cart.stream().mapToDouble(Prodotto::getPrezzo).sum();
        totalLabel.setText("Totale: " + currencyFormat.format(total));

        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    private JPanel createCartItemPanel(Prodotto prodotto, int quantity) {
        JPanel itemPanel = new JPanel(new BorderLayout(15, 10));
        itemPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

        // Image panel
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setPreferredSize(new Dimension(IMG_WIDTH, IMG_HEIGHT));
        JLabel imgLabel = new JLabel();
        new Thread(() -> {
            try {
                ImageIcon originalIcon = new ImageIcon(new URL(prodotto.getUrlImage()));
                Image scaledImage = originalIcon.getImage().getScaledInstance(
                        160, 100, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                SwingUtilities.invokeLater(() -> {
                    imgLabel.setIcon(scaledIcon);
                    imgLabel.setText("");
                    imgLabel.revalidate();
                    imgLabel.repaint();
                });
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    imgLabel.setIcon(null);
                    imgLabel.setText("Immagine non disponibile");
                });
            }
        }).start();
        imagePanel.add(imgLabel);

        // Product info panel
        JPanel infoPanel = new JPanel(new BorderLayout(5, 10));
        infoPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(prodotto.getNome());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setForeground(new Color(60, 60, 60));

        JLabel priceLabel = new JLabel("Prezzo unitario: " + currencyFormat.format(prodotto.getPrezzo()));
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel totalLabel = new JLabel("Totale: " + currencyFormat.format(prodotto.getPrezzo() * quantity));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalLabel.setForeground(new Color(0, 100, 0));

        infoPanel.add(nameLabel, BorderLayout.NORTH);
        infoPanel.add(priceLabel, BorderLayout.CENTER);
        infoPanel.add(totalLabel, BorderLayout.SOUTH);

        // Quantity control panel
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        quantityPanel.setBackground(Color.WHITE);

        JButton decreaseButton = new JButton("-");
        decreaseButton.setFont(new Font("Arial", Font.BOLD, 12));
        decreaseButton.setPreferredSize(new Dimension(40, 25));
        decreaseButton.setFocusPainted(false);
        decreaseButton.addActionListener(e -> {
            updateQuantity(prodotto, -1);
            cartModified = true;
        });

        JLabel quantityLabel = new JLabel(String.valueOf(quantity));
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        quantityLabel.setPreferredSize(new Dimension(40, 25));
        quantityLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton increaseButton = new JButton("+");
        increaseButton.setFont(new Font("Arial", Font.BOLD, 12));
        increaseButton.setPreferredSize(new Dimension(45, 25));
        increaseButton.addActionListener(e -> {
            updateQuantity(prodotto, 1);
            cartModified = true;
        });

        quantityPanel.add(decreaseButton);
        quantityPanel.add(quantityLabel);
        quantityPanel.add(increaseButton);

        // Remove button
        JButton removeButton = new JButton("Rimuovi");
        removeButton.setFont(new Font("Arial", Font.PLAIN, 12));
        removeButton.setForeground(Color.RED);
        removeButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        removeButton.addActionListener(e -> {
            removeProduct(prodotto);
            cartModified = true;
        });

        // Control panel (quantity + remove)
        JPanel controlPanel = new JPanel(new BorderLayout(10, 10));
        controlPanel.setBackground(Color.WHITE);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(quantityPanel);
        centerPanel.add(removeButton);

        controlPanel.add(centerPanel, BorderLayout.CENTER);

        // Main content panel
        JPanel contentPanel = new JPanel(new BorderLayout(15, 0));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.add(imagePanel, BorderLayout.WEST);
        contentPanel.add(infoPanel, BorderLayout.CENTER);
        contentPanel.add(controlPanel, BorderLayout.EAST);

        itemPanel.add(contentPanel, BorderLayout.CENTER);

        return itemPanel;
    }

    private void updateQuantity(Prodotto prodotto, int delta) {
        Utente currentUser = authController.getLogin();
        java.util.List<Prodotto> cart = currentUser.getCarrello();

        if (delta > 0) {
            cart.add(prodotto);
        } else {
            for (int i = 0; i < cart.size(); i++) {
                if (cart.get(i).equals(prodotto)) {
                    cart.remove(i);
                    break;
                }
            }
        }

        currentUser.setCarrello(cart);
        updateCartDisplay();
    }

    private void removeProduct(Prodotto prodotto) {
        Utente currentUser = authController.getLogin();
        java.util.List<Prodotto> cart = currentUser.getCarrello();
        cart.removeIf(p -> p.equals(prodotto));
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
                "Confermi l'acquisto di " + currentUser.getCarrello().size() + " articoli per " +
                        currencyFormat.format(currentUser.getCarrello().stream().mapToDouble(Prodotto::getPrezzo).sum()) + "?",
                "Conferma acquisto",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            double total = currentUser.getCarrello().stream()
                    .mapToDouble(Prodotto::getPrezzo)
                    .sum();

            currentUser.setCarrello(new ArrayList<>());
            cartModified = true;
            updateUserProfile();

            JOptionPane.showMessageDialog(this,
                    "<html><div style='text-align: center;'>" +
                            "<b>Acquisto completato con successo!</b><br>" +
                            "Totale: " + currencyFormat.format(total) +
                            "</div></html>",
                    "Acquisto completato",
                    JOptionPane.INFORMATION_MESSAGE);

            updateCartDisplay();
            this.dispose();
        }
    }
}