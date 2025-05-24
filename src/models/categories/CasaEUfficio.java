package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;

// stampanti, sccanner ...
public class CasaEUfficio extends Prodotto {
    private String tecnologia;             // nel senso se è toner o cartucce o altro
    private int velocitaStampaPPM;         // quante pagine stampa per minuto
    private boolean fronteRetroAutomatico;
    private boolean wifiIntegrato;

    public CasaEUfficio(String nome, String id, String marca, String categoria, ImageIcon icon, double prezzo, String descrizioneOggetto, String tecnologia, int velocitaStampaPPM, boolean fronteRetroAutomatico, boolean wifiIntegrato) {
        super(nome, id, marca, categoria, icon, prezzo, descrizioneOggetto);
        this.tecnologia = tecnologia;
        this.velocitaStampaPPM = velocitaStampaPPM;
        this.fronteRetroAutomatico = fronteRetroAutomatico;
        this.wifiIntegrato = wifiIntegrato;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public int getVelocitaStampaPPM() {
        return velocitaStampaPPM;
    }

    public void setVelocitaStampaPPM(int velocitaStampaPPM) {
        this.velocitaStampaPPM = velocitaStampaPPM;
    }

    public boolean isFronteRetroAutomatico() {
        return fronteRetroAutomatico;
    }

    public void setFronteRetroAutomatico(boolean fronteRetroAutomatico) {
        this.fronteRetroAutomatico = fronteRetroAutomatico;
    }

    public boolean isWifiIntegrato() {
        return wifiIntegrato;
    }

    public void setWifiIntegrato(boolean wifiIntegrato) {
        this.wifiIntegrato = wifiIntegrato;
    }
}
