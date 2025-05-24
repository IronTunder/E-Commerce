package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;

// stampanti, sccanner ...
public class CasaEUfficio extends Prodotto {
    private final String tecnologia;             //
    private final int velocitaStampaPPM;         // quante pagine stampa per minuto
    private final boolean fronteRetroAutomatico;
    private final boolean wifiIntegrato;

    public CasaEUfficio(String nome, String id, String marca, String categoria, ImageIcon icon, double prezzo, String descrizioneOggetto, String tecnologia, int velocitaStampaPPM, boolean fronteRetroAutomatico, boolean wifiIntegrato) {
        super(nome, id, marca, categoria, icon, prezzo, descrizioneOggetto);

        if (tecnologia == null || tecnologia.trim().isEmpty()) {
            throw new IllegalArgumentException("La tecnologia non può essere vuota");
        }
        if (velocitaStampaPPM <= 0) {
            throw new IllegalArgumentException("La velocità di stampa deve essere maggiore di 0");
        }

        this.tecnologia = tecnologia;
        this.velocitaStampaPPM = velocitaStampaPPM;
        this.fronteRetroAutomatico = fronteRetroAutomatico;
        this.wifiIntegrato = wifiIntegrato;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public int getVelocitaStampaPPM() {
        return velocitaStampaPPM;
    }

    public boolean isFronteRetroAutomatico() {
        return fronteRetroAutomatico;
    }

    public boolean isWifiIntegrato() {
        return wifiIntegrato;
    }

}
