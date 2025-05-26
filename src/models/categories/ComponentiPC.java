package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;

public class ComponentiPC extends Prodotto {
    private final String tipoComponente;
    private final String specificaTecnica;
    private final int capacitaGB;
    private final String compatibilita;

    public ComponentiPC(String nome, String id, String marca, String categoria, String urlImage, double prezzo, String descrizioneOggetto, String tipoComponente, String specificaTecnica, int capacitaGB, String compatibilita) {
        super(nome, id, marca, categoria, urlImage, prezzo, descrizioneOggetto);

        if (tipoComponente == null || tipoComponente.trim().isEmpty()) {
            throw new IllegalArgumentException("Il tipo di componente non può essere vuoto");
        }
        if (specificaTecnica == null || specificaTecnica.trim().isEmpty()) {
            throw new IllegalArgumentException("La specifica tecnica non può essere vuota");
        }
        if (capacitaGB <= 0) {
            throw new IllegalArgumentException("La capacità deve essere maggiore di 0");
        }
        if (compatibilita == null || compatibilita.trim().isEmpty()) {
            throw new IllegalArgumentException("La compatibilità non può essere vuota");
        }

        this.tipoComponente = tipoComponente;
        this.specificaTecnica = specificaTecnica;
        this.capacitaGB = capacitaGB;
        this.compatibilita = compatibilita;
    }

    public String getTipoComponente() {
        return tipoComponente;
    }

    public String getSpecificaTecnica() {
        return specificaTecnica;
    }

    public int getCapacitaGB() {
        return capacitaGB;
    }

    public String getCompatibilita() {
        return compatibilita;
    }

}

