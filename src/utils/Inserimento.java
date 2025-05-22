package src.utils;

import src.models.Prodotto;

import javax.swing.*;
import java.awt.*;

public class Inserimento extends JPanel{
    JTextField nome = new JTextField();
    JTextField id = new JTextField();
    JTextField quantita = new JTextField();
    JTextField marca = new JTextField();
    JTextField categoria = new JTextField();
    JTextField prezzo = new JTextField();
    JButton selezionaImmagine = new JButton("Seleziona Immagine");
    private ImageIcon iconaSelezionata;

    public Inserimento(){
        setLayout(new GridLayout(8, 2, 10, 10));

        add(new JLabel("Nome:"));
        add(nome);
        add(new JLabel("ID:"));
        add(id);
        add(new JLabel("Quantità:"));
        add(quantita);
        add(new JLabel("Marca:"));
        add(marca);
        add(new JLabel("Categoria:"));
        add(categoria);
        add(new JLabel("Prezzo:"));
        add(prezzo);
        add(new JLabel("Immagine:"));
        add(selezionaImmagine);

        selezionaImmagine.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                ImageIcon icon = new ImageIcon(fileChooser.getSelectedFile().getPath());
                Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                iconaSelezionata = new ImageIcon(image);
            }
        });
    }

    public void resetFields() {
        nome.setText("");
        id.setText("");
        quantita.setText("");
        marca.setText("");
        categoria.setText("");
        prezzo.setText("");
        iconaSelezionata = null;
    }

    public Prodotto getProdotto() {
        try {
            return new Prodotto(
                    nome.getText(),
                    id.getText(),
                    marca.getText(),
                    categoria.getText(),
                    iconaSelezionata,
                    Double.parseDouble(prezzo.getText())
            ) {
                // Implementazione astratta necessaria
                @Override
                public String toString() {
                    return getNome() + " - " + getMarca() + " - " + getPrezzo() + "€\n";
                }
            };
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Inserire un prezzo valido!", "Errore", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}