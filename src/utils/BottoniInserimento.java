package src.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import src.models.Prodotto;
import src.view.HomePage;

public class BottoniInserimento extends JPanel implements ActionListener {
    JButton aggiungi = new JButton("Aggiungi Prodotto");
    JButton salva = new JButton("Salva");
    JButton carica = new JButton("Carica");
    JButton rimuovi = new JButton("Rimuovi Prodotto");
    Inserimento inserimento;
    ElencoMagazzino elencoMagazzino;
    FileManager fileManager;
    HomePage homePage;

    public BottoniInserimento(Inserimento inserimento, ElencoMagazzino elencoMagazzino, HomePage homePage){
        this.inserimento = inserimento;
        this.elencoMagazzino = elencoMagazzino;
        this.fileManager = new FileManager();
        this.homePage = homePage;
        setLayout(new FlowLayout());
        styleButton(aggiungi, new Color(46, 125, 50)); // Verde
        styleButton(salva, new Color(41, 98, 255));    // Blu
        styleButton(carica, new Color(158, 158, 158)); // Grigio
        styleButton(rimuovi, new Color(198, 40, 40)); // Rosso
        setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        add(aggiungi);
        aggiungi.addActionListener(this);
        add(salva);
        salva.addActionListener(this);
        add(carica);
        carica.addActionListener(this);
        add(rimuovi);
        rimuovi.addActionListener(this);

    }

    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton bottone = (JButton) e.getSource();

        if(bottone.getText().equals("Aggiungi Prodotto")){
            Prodotto prodotto = inserimento.getProdotto();
            if(prodotto != null) {
                if(fileManager.aggiungiProdotto(prodotto)) {
                    elencoMagazzino.prodotti.add(prodotto);
                    elencoMagazzino.textArea.append(prodotto.toString());
                    inserimento.resetFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Prodotto già esistente o dati non validi!");
                }
            }
        }
        else if (bottone.getText().equals("Salva")) {
            try {
                fileManager.salvaProdotti();
                JOptionPane.showMessageDialog(null, "Prodotti salvati con successo!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Errore durante il salvataggio: " + ex.getMessage());
            }
        }
        else if (bottone.getText().equals("Carica")) {
            fileManager.caricaProdotti();
            elencoMagazzino.prodotti.clear();
            elencoMagazzino.prodotti.addAll(fileManager.getProdotti());
            elencoMagazzino.textArea.setText("");
            for(Prodotto p : elencoMagazzino.prodotti) {
                elencoMagazzino.textArea.append(p.toString());
            }
            homePage.aggiornaVisualizzazioneProdotti();
            JOptionPane.showMessageDialog(null, "Prodotti caricati con successo!");
        }
        else if (bottone.getText().equals("Rimuovi Prodotto")) {
            String idProdotto = JOptionPane.showInputDialog("Inserisci l'ID del prodotto da rimuovere:");
            if (idProdotto != null && !idProdotto.trim().isEmpty()) {
                boolean rimosso = false;
                for (Prodotto p : new ArrayList<>(elencoMagazzino.prodotti)) {
                    if (p.getId().equals(idProdotto)) {
                        elencoMagazzino.prodotti.remove(p);
                        rimosso = true;
                        break;
                    }
                }

                if (rimosso) {
                    elencoMagazzino.textArea.setText("");
                    for(Prodotto p : elencoMagazzino.prodotti) {
                        elencoMagazzino.textArea.append(p.toString());
                    }
                    fileManager.salvaProdotti(); // Salva le modifiche
                    homePage.aggiornaVisualizzazioneProdotti(); // Aggiorna la homepage
                    JOptionPane.showMessageDialog(null, "Prodotto rimosso con successo!");
                } else {
                    JOptionPane.showMessageDialog(null, "Nessun prodotto trovato con l'ID specificato!");
                }
            }
        }

    }
}