package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;

public class AccessoriSmartPhone extends Prodotto {
    private static final long serialVersionUID = 6884949766886544946L;
    private final String tipoMateriale;
    private final int capacitaPowerBank;
    private final boolean ricaricaWireless;
    private final boolean magnetico;

    public AccessoriSmartPhone(String nome, String id, String marca, String categoria, String urlImage, double prezzo, String descrizioneOggetto, String tipoMateriale, int capacitaPowerBank, boolean ricaricaWireless, boolean magnetico) {
        super(nome, id, marca, categoria, urlImage, prezzo, descrizioneOggetto);

        if (tipoMateriale == null || tipoMateriale.trim().isEmpty()) {
            throw new IllegalArgumentException("Il tipo di materiale non può essere vuoto");
        }
        if (capacitaPowerBank < 0) {
            throw new IllegalArgumentException("La capacità della power bank non può essere negativa");
        }

        this.tipoMateriale = tipoMateriale;
        this.capacitaPowerBank = capacitaPowerBank;
        this.ricaricaWireless = ricaricaWireless;
        this.magnetico = magnetico;
    }

    public String getTipoMateriale() {
        return tipoMateriale;
    }

    public int getCapacitaPowerBank() {
        return capacitaPowerBank;
    }

    public boolean isRicaricaWireless() {
        return ricaricaWireless;
    }

    public boolean isMagnetico() {
        return magnetico;
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Materiale: " + tipoMateriale +
                " | Capacità PowerBank: " + (capacitaPowerBank > 0 ? capacitaPowerBank + "mAh" : "N/A") +
                " | Wireless: " + (ricaricaWireless ? "Sì" : "No") +
                " | Magnetico: " + (magnetico ? "Sì" : "No");
    }
}
