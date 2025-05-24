package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;

public class AccessoriSmartPhone extends Prodotto {
    private String tipoMateriale;
    private int capacitaPowerBank;
    private boolean ricaricaWireless;
    private boolean magnetico;

    public AccessoriSmartPhone(String nome, String id, String marca, String categoria, ImageIcon icon, double prezzo, String descrizioneOggetto, String tipoMateriale, int capacitaPowerBank, boolean ricaricaWireless, boolean magnetico) {
        super(nome, id, marca, categoria, icon, prezzo, descrizioneOggetto);
        this.tipoMateriale = tipoMateriale;
        this.capacitaPowerBank = capacitaPowerBank;
        this.ricaricaWireless = ricaricaWireless;
        this.magnetico = magnetico;
    }

    public String getTipoMateriale() {
        return tipoMateriale;
    }

    public void setTipoMateriale(String tipoMateriale) {
        this.tipoMateriale = tipoMateriale;
    }

    public int getCapacitaPowerBank() {
        return capacitaPowerBank;
    }

    public void setCapacitaPowerBank(int capacitaPowerBank) {
        this.capacitaPowerBank = capacitaPowerBank;
    }

    public boolean isRicaricaWireless() {
        return ricaricaWireless;
    }

    public void setRicaricaWireless(boolean ricaricaWireless) {
        this.ricaricaWireless = ricaricaWireless;
    }

    public boolean isMagnetico() {
        return magnetico;
    }

    public void setMagnetico(boolean magnetico) {
        this.magnetico = magnetico;
    }
}
