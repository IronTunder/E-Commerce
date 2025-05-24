package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;

public class Cavetteria extends Prodotto {
    private String tipoConnettori;
    private double lunghezzaMetri;
    private boolean supporta4K;
    private boolean rinforzato;

    public Cavetteria(String nome, String id, String marca, String categoria, ImageIcon icon, double prezzo, String descrizioneOggetto, String tipoConnettori, double lunghezzaMetri, boolean supporta4K, boolean rinforzato) {
        super(nome, id, marca, categoria, icon, prezzo, descrizioneOggetto);
        this.tipoConnettori = tipoConnettori;
        this.lunghezzaMetri = lunghezzaMetri;
        this.supporta4K = supporta4K;
        this.rinforzato = rinforzato;
    }

    public String getTipoConnettori() {
        return tipoConnettori;
    }

    public void setTipoConnettori(String tipoConnettori) {
        this.tipoConnettori = tipoConnettori;
    }

    public double getLunghezzaMetri() {
        return lunghezzaMetri;
    }

    public void setLunghezzaMetri(double lunghezzaMetri) {
        this.lunghezzaMetri = lunghezzaMetri;
    }

    public boolean isSupporta4K() {
        return supporta4K;
    }

    public void setSupporta4K(boolean supporta4K) {
        this.supporta4K = supporta4K;
    }

    public boolean isRinforzato() {
        return rinforzato;
    }

    public void setRinforzato(boolean rinforzato) {
        this.rinforzato = rinforzato;
    }
}
