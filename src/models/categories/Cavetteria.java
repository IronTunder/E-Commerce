package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;

public class Cavetteria extends Prodotto {
    private final String tipoConnettori;
    private final double lunghezzaMetri;
    private final boolean supporta4K;
    private final boolean rinforzato;

    public Cavetteria(String nome, String id, String marca, String categoria, String urlImage, double prezzo, String descrizioneOggetto, String tipoConnettori, double lunghezzaMetri, boolean supporta4K, boolean rinforzato) {
        super(nome, id, marca, categoria, urlImage, prezzo, descrizioneOggetto);

        if (tipoConnettori == null || tipoConnettori.trim().isEmpty()) {
            throw new IllegalArgumentException("Il tipo di connettori non può essere vuoto");
        }
        if (lunghezzaMetri <= 0) {
            throw new IllegalArgumentException("La lunghezza deve essere maggiore di 0");
        }

        this.tipoConnettori = tipoConnettori;
        this.lunghezzaMetri = lunghezzaMetri;
        this.supporta4K = supporta4K;
        this.rinforzato = rinforzato;
    }

    public String getTipoConnettori() {
        return tipoConnettori;
    }

    public double getLunghezzaMetri() {
        return lunghezzaMetri;
    }

    public boolean isSupporta4K() {
        return supporta4K;
    }

    public boolean isRinforzato() {
        return rinforzato;
    }

}
