package src.view.admin.prodotti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import src.models.Prodotto;
import src.controllers.ProductsController;
import src.view.HomePage;

public class BottoniInserimento extends JPanel implements ActionListener {
    JButton aggiungi = new JButton("Aggiungi Prodotto");
    JButton salva = new JButton("Salva");
    JButton carica = new JButton("Carica");
    JButton rimuovi = new JButton("Rimuovi Prodotto");
    Inserimento inserimento;
    ElencoMagazzino elencoMagazzino;
    ProductsController productsController;
    HomePage homePage;

    public BottoniInserimento(Inserimento inserimento, ElencoMagazzino elencoMagazzino, HomePage homePage){
        this.inserimento = inserimento;
        this.elencoMagazzino = elencoMagazzino;
        this.productsController = homePage.getFileManager();
        this.homePage = homePage;
        setLayout(new FlowLayout());
        styleButton(aggiungi, new Color(46, 125, 50)); // Verde
        styleButton(salva, new Color(41, 98, 255));    // Blu
        styleButton(carica, new Color(158, 158, 158)); // Grigio
        styleButton(rimuovi, new Color(198, 40, 40));  // Rosso
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
                if(productsController.aggiungiProdotto(prodotto)) {
                    aggiornaInterfaccia();
                    inserimento.resetFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Prodotto già esistente o dati non validi!");
                }
            }
        }
        else if (bottone.getText().equals("Salva")) {
            try {
                productsController.salvaProdotti();
                JOptionPane.showMessageDialog(null, "Prodotti salvati con successo!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Errore durante il salvataggio: " + ex.getMessage());
            }
        }
        else if (bottone.getText().equals("Carica")) {
            productsController.caricaProdotti();
            aggiornaInterfaccia();
            JOptionPane.showMessageDialog(null, "Prodotti caricati con successo!");
        }
        else if (bottone.getText().equals("Rimuovi Prodotto")) {
            String idProdotto = JOptionPane.showInputDialog("Inserisci l'ID del prodotto da rimuovere:");
            if (idProdotto != null && !idProdotto.trim().isEmpty()) {
                rimuoviProdotto(idProdotto);
            }
        }
    }

    private void rimuoviProdotto(String idProdotto) {
        List<Prodotto> prodotti = productsController.getProdotti();
        boolean rimosso = false;

        for (int i = 0; i < prodotti.size(); i++) {
            if (prodotti.get(i).getId().equals(idProdotto)) {
                prodotti.remove(i);
                rimosso = true;
                break;
            }
        }

        if (rimosso) {
            productsController.salvaProdotti(); // Salva lo stato aggiornato nel file
            aggiornaInterfaccia(); // Aggiorna la UI
            JOptionPane.showMessageDialog(null, "Prodotto rimosso con successo!");
        } else {
            JOptionPane.showMessageDialog(null, "Nessun prodotto trovato con l'ID specificato!");
        }
    }


    private void aggiornaInterfaccia() {
        // Aggiorna l'elenco magazzino
        elencoMagazzino.textArea.setText("");
        for(Prodotto p : productsController.getProdotti()) {
            elencoMagazzino.textArea.append(p.toString() + "\n");
        }

        // Aggiorna la homepage
        homePage.aggiornaVisualizzazioneProdotti();
    }
}