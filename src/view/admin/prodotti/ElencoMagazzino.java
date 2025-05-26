package src.view.admin.prodotti;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import src.models.Prodotto;

public class ElencoMagazzino extends JPanel {
    JTextArea textArea = new JTextArea();

    public ElencoMagazzino(List<Prodotto> prodotti){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        for (Prodotto prodotto : prodotti) {
            textArea.append(prodotto.toString());
        }
        add(new JLabel("Elenco Prodotti", JLabel.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}