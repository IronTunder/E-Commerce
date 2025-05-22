package src.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import src.models.Prodotto;
import src.utils.FileManager;

public class BottoniInserimento extends JPanel implements ActionListener {
    JButton aggiungi = new JButton("Aggiungi Prodotto");
    JButton salva = new JButton("Salva");
    JButton carica = new JButton("Carica");
    Inserimento inserimento;
    ElencoMagazzino elencoMagazzino;
    FileManager fileManager;

    public BottoniInserimento(Inserimento inserimento, ElencoMagazzino elencoMagazzino){
        this.inserimento = inserimento;
        this.elencoMagazzino = elencoMagazzino;
        this.fileManager = new FileManager();
        setLayout(new FlowLayout());
        styleButton(aggiungi, new Color(46, 125, 50)); // Verde
        styleButton(salva, new Color(41, 98, 255));    // Blu
        styleButton(carica, new Color(158, 158, 158)); // Grigio
        setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        add(aggiungi);
        aggiungi.addActionListener(this);
        add(salva);
        salva.addActionListener(this);
        add(carica);
        carica.addActionListener(this);

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
            JOptionPane.showMessageDialog(null, "Prodotti caricati con successo!");
        }
    }
}