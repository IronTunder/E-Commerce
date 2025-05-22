package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;

public class AccessoriPC extends Prodotto {
    private final String materiale;
    private final String descrizione;

    public AccessoriPC(String nome, String id, String marca, String categoria, ImageIcon icon, double prezzo, String materiale, String descrizione) {
        super(nome, id, marca, categoria, icon, prezzo);
        this.materiale = materiale;
        this.descrizione = descrizione;
    }
}