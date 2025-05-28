package src.view.admin.prodotti;

import src.controllers.ProductsController;
import src.view.HomePage;

import javax.swing.*;
import java.awt.*;

public class ProductsPanel extends JFrame {

    public ProductsPanel(HomePage homePage){
        super("Pannello Amministratore");
        ProductsController productsController = homePage.getFileManager();
        setResizable(false);
        setIconImage(new ImageIcon("src/icon.png").getImage());
        setLayout(new BorderLayout());
        setSize(900, 800);
        setLocationRelativeTo(this);
        toFront();


        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));


        Inserimento inserimento = new Inserimento();
        ElencoMagazzino elencoMagazzino = new ElencoMagazzino(productsController.getProdotti());
        BottoniInserimento bottoniInserimento = new BottoniInserimento(inserimento, elencoMagazzino, homePage);


        inserimento.setBackground(new Color(240, 240, 240));
        elencoMagazzino.setBackground(new Color(240, 240, 240));
        bottoniInserimento.setBackground(new Color(240, 240, 240));


        JScrollPane scrollPane = new JScrollPane(elencoMagazzino.getTextArea());
        scrollPane.setBorder(BorderFactory.createTitledBorder("Elenco Prodotti"));
        elencoMagazzino.remove(elencoMagazzino.getTextArea());
        elencoMagazzino.add(scrollPane);


        JPanel northPanel = new JPanel(new BorderLayout());
        JLabel inserimentoProdotti = new JLabel("Inserimento Prodotti", JLabel.CENTER);
        inserimentoProdotti.setFont(new Font("Arial", Font.BOLD, 24));
        northPanel.add(inserimentoProdotti, BorderLayout.NORTH);
        northPanel.add(inserimento, BorderLayout.CENTER);
        northPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(elencoMagazzino, BorderLayout.CENTER);
        mainPanel.add(bottoniInserimento, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}
