package src.utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import src.models.Prodotto;

public class ElencoMagazzino extends JPanel {
    JTextArea textArea = new JTextArea();
    ArrayList<Prodotto> prodotti = new ArrayList<>();
    Inserimento inserimento;

    public ElencoMagazzino(Inserimento inserimento){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        add(new JLabel("Elenco Prodotti", JLabel.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}