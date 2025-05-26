package src.view.admin;

import src.utils.FileManager;
import src.view.HomePage;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JFrame {

    public AdminPanel(HomePage homePage){
        super("Pannello Amministratore");
        FileManager fileManager = homePage.getFileManager();
        setResizable(false);
        setIconImage(new ImageIcon("./icon.png").getImage());
        setLayout(new BorderLayout());
        setSize(900, 720);
        setLocationRelativeTo(this);

        // Pannello principale con bordo
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));


        Inserimento inserimento = new Inserimento();
        ElencoMagazzino elencoMagazzino = new ElencoMagazzino(fileManager.getProdotti());
        BottoniInserimento bottoniInserimento = new BottoniInserimento(inserimento, elencoMagazzino, homePage);

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

        add(mainPanel);
        setVisible(true);
    }
}
