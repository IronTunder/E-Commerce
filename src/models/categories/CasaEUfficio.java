package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;


public class CasaEUfficio extends Prodotto {
    private static final long serialVersionUID = 4528761292692648348L;
    private final String tecnologia;
    private final int velocitaStampaPPM;
    private final boolean fronteRetroAutomatico;
    private final boolean wifiIntegrato;

    public CasaEUfficio(String nome, String id, String marca, String categoria, String urlImage, double prezzo, String descrizioneOggetto, String tecnologia, int velocitaStampaPPM, boolean fronteRetroAutomatico, boolean wifiIntegrato) {
        super(nome, id, marca, categoria, urlImage, prezzo, descrizioneOggetto);

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

    @Override
    public String toString() {
        return super.toString() +
                " | Tecnologia: " + tecnologia +
                " | Velocità Stampa: " + velocitaStampaPPM + "ppm" +
                " | Fronte/Retro: " + (fronteRetroAutomatico ? "Auto" : "Manuale") +
                " | WiFi: " + (wifiIntegrato ? "Sì" : "No");
    }

}
