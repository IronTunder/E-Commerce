package src.view.components;

import src.models.Prodotto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PannelloCategoria extends JPanel implements ActionListener {
    public PannelloCategoria(String titoloCategoria, ArrayList<Prodotto> prodotti) {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Titolo della categoria
        JLabel titolo = new JLabel(titoloCategoria);
        titolo.setFont(new Font("Arial", Font.BOLD, 18));
        titolo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        add(titolo, BorderLayout.NORTH);

        // Pannello prodotti con WRAPPER per lo scroll
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(240, 240, 240));

        JPanel prodottiPanel = new JPanel();
        prodottiPanel.setLayout(new BoxLayout(prodottiPanel, BoxLayout.X_AXIS));
        prodottiPanel.setBackground(new Color(240, 240, 240));

        // Limita a 10 prodotti per categoria per evitare overflow
        int maxProdotti = Math.min(prodotti.size(), 10);
        for (int i = 0; i < maxProdotti; i++) {
            JPanel prodottoCard = creaProdottoCard(prodotti.get(i));
            prodottiPanel.add(prodottoCard);
            prodottiPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        }

        // Aggiungi spazio flessibile a destra
        prodottiPanel.add(Box.createHorizontalGlue());

        wrapper.add(prodottiPanel, BorderLayout.CENTER);

        // Configurazione dello scroll pane
        JScrollPane scrollPane = new JScrollPane(wrapper);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

        // Imposta dimensioni fisse per lo scrollpane
        scrollPane.setPreferredSize(new Dimension(800, 300)); // Larghezza fissa

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel creaProdottoCard(Prodotto prodotto) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(5, 5));
        card.setPreferredSize(new Dimension(180, 200));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Immagine placeholder
        JLabel imgLabel = new JLabel("IMG", SwingConstants.CENTER);
        imgLabel.setPreferredSize(new Dimension(160, 100));
        imgLabel.setOpaque(true);
        imgLabel.setBackground(new Color(230, 230, 230));

        // Info prodotto
        JLabel nomeLabel = new JLabel("<html><div style='width:160px'>" + prodotto.getNome() + "</div></html>");
        JLabel marcaLabel = new JLabel("Marca: " + prodotto.getMarca());
        JButton aggiungiAlCarrello = creaBottone("Aggiungi al carrello","Aggiungi " + prodotto.getNome() + " al carrello");
        JLabel prezzoLabel = new JLabel("€ " + String.format("%.2f", prodotto.getPrezzo()), SwingConstants.RIGHT);
        prezzoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        prezzoLabel.setForeground(new Color(0, 100, 0));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(nomeLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(marcaLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        infoPanel.add(prezzoLabel);
        infoPanel.add(aggiungiAlCarrello);

        card.add(imgLabel, BorderLayout.NORTH);
        card.add(infoPanel, BorderLayout.CENTER);

        return card;
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}