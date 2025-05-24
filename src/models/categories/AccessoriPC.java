package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;

public class AccessoriPC extends Prodotto {
    private String tipoAccessorio;
    private String connettivita;
    private boolean rgb;
    private String compatibilitaSistema;
    private String dimensioni;
    private boolean ergonomico;


    public AccessoriPC(String nome, String id, String marca, String categoria, ImageIcon icon, double prezzo, String descrizioneOggetto, String tipoAccessorio, String connettivita, boolean rgb, String compatibilitaSistema, String dimensioni, boolean ergonomico) {
        super(nome, id, marca, categoria, icon, prezzo, descrizioneOggetto);
        this.tipoAccessorio = tipoAccessorio;
        this.connettivita = connettivita;
        this.rgb = rgb;
        this.compatibilitaSistema = compatibilitaSistema;
        this.dimensioni = dimensioni;
        this.ergonomico = ergonomico;
    }

    public String getTipoAccessorio() {
        return tipoAccessorio;
    }

    public void setTipoAccessorio(String tipoAccessorio) {
        this.tipoAccessorio = tipoAccessorio;
    }

    public String getConnettivita() {
        return connettivita;
    }

    public void setConnettivita(String connettivita) {
        this.connettivita = connettivita;
    }

    public boolean isRgb() {
        return rgb;
    }

    public void setRgb(boolean rgb) {
        this.rgb = rgb;
    }

    public String getCompatibilitaSistema() {
        return compatibilitaSistema;
    }

    public void setCompatibilitaSistema(String compatibilitaSistema) {
        this.compatibilitaSistema = compatibilitaSistema;
    }

    public String getDimensioni() {
        return dimensioni;
    }

    public void setDimensioni(String dimensioni) {
        this.dimensioni = dimensioni;
    }

    public boolean isErgonomico() {
        return ergonomico;
    }

    public void setErgonomico(boolean ergonomico) {
        this.ergonomico = ergonomico;
    }
}