package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;

public class AccessoriPC extends Prodotto {
    private final String tipoAccessorio;
    private final String connettivita;
    private final boolean rgb;
    private final String compatibilitaSistema;
    private final String dimensioni;
    private final boolean ergonomico;


    public AccessoriPC(String nome, String id, String marca, String categoria, ImageIcon icon, double prezzo, String descrizioneOggetto, String tipoAccessorio, String connettivita, boolean rgb, String compatibilitaSistema, String dimensioni, boolean ergonomico) {
        super(nome, id, marca, categoria, icon, prezzo, descrizioneOggetto);

        if (tipoAccessorio == null || tipoAccessorio.trim().isEmpty()) {
            throw new IllegalArgumentException("Il tipo di accessorio non può essere vuoto");
        }
        if (connettivita == null || connettivita.trim().isEmpty()) {
            throw new IllegalArgumentException("La connettività non può essere vuota");
        }
        if (compatibilitaSistema == null || compatibilitaSistema.trim().isEmpty()) {
            throw new IllegalArgumentException("La compatibilità del sistema non può essere vuota");
        }
        if (dimensioni == null || dimensioni.trim().isEmpty()) {
            throw new IllegalArgumentException("Le dimensioni non possono essere vuote");
        }

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

    public String getConnettivita() {
        return connettivita;
    }

    public boolean isRgb() {
        return rgb;
    }

    public String getCompatibilitaSistema() {
        return compatibilitaSistema;
    }


    public String getDimensioni() {
        return dimensioni;
    }

    public boolean isErgonomico() {
        return ergonomico;
    }

}