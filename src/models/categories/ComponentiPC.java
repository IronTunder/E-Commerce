package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;

public class ComponentiPC extends Prodotto {
    private String tipoComponente;
    private String specificaTecnica;
    private int capacitaGB;
    private String compatibilita;

    public ComponentiPC(String nome, String id, String marca, String categoria, ImageIcon icon, double prezzo, String descrizioneOggetto, String tipoComponente, String specificaTecnica, int capacitaGB, String compatibilita) {
        super(nome, id, marca, categoria, icon, prezzo, descrizioneOggetto);
        this.tipoComponente = tipoComponente;
        this.specificaTecnica = specificaTecnica;
        this.capacitaGB = capacitaGB;
        this.compatibilita = compatibilita;
    }

    public String getTipoComponente() {
        return tipoComponente;
    }

    public void setTipoComponente(String tipoComponente) {
        this.tipoComponente = tipoComponente;
    }

    public String getSpecificaTecnica() {
        return specificaTecnica;
    }

    public void setSpecificaTecnica(String specificaTecnica) {
        this.specificaTecnica = specificaTecnica;
    }

    public int getCapacitaGB() {
        return capacitaGB;
    }

    public void setCapacitaGB(int capacitaGB) {
        this.capacitaGB = capacitaGB;
    }

    public String getCompatibilita() {
        return compatibilita;
    }

    public void setCompatibilita(String compatibilita) {
        this.compatibilita = compatibilita;
    }
}

