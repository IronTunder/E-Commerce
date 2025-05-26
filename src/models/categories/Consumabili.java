package src.models.categories;
import src.models.Prodotto;
import javax.swing.*;

public class Consumabili extends Prodotto {

    private final double quantita;
    private final String unitaDiMisura;

    public Consumabili(String nome, String id, String marca, String categoria, String urlImage, double prezzo, String descrizioneOggetto, double quantita, String unitaDiMisura) {
        super(nome, id, marca, categoria, urlImage, prezzo, descrizioneOggetto);

        if (quantita <= 0) {
            throw new IllegalArgumentException("La quantità deve essere maggiore di 0");
        }
        if (unitaDiMisura == null || unitaDiMisura.trim().isEmpty()) {
            throw new IllegalArgumentException("L'unità di misura non può essere vuota");
        }

        this.quantita = quantita;
        this.unitaDiMisura = unitaDiMisura;
    }

    public double getQuantita() {
        return quantita;
    }

    public String getUnitaDiMisura() {
        return unitaDiMisura;
    }
}
