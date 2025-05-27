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
        setSize(900, 720);
        setLocationRelativeTo(this);


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
        northPanel.add(new JLabel("Inserimento Prodotti", JLabel.CENTER), BorderLayout.NORTH);
        northPanel.add(inserimento, BorderLayout.CENTER);
        northPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(elencoMagazzino, BorderLayout.CENTER);
        mainPanel.add(bottoniInserimento, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}
