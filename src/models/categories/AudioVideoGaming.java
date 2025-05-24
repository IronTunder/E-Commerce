package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;

public class AudioVideoGaming extends Prodotto {
    private String tipoProdotto;           // qua dico che tipo di prodotto è se cuffie tv ...
    private int pollici;
    private int hz;
    private boolean supportaHDR;
    private boolean wireless;

    public AudioVideoGaming(String nome, String id, String marca, String categoria, ImageIcon icon, double prezzo, String descrizioneOggetto, String tipoProdotto, int pollici, int hz, boolean supportaHDR, boolean wireless) {
        super(nome, id, marca, categoria, icon, prezzo, descrizioneOggetto);
        this.tipoProdotto = tipoProdotto;
        this.pollici = pollici;
        this.hz = hz;
        this.supportaHDR = supportaHDR;
        this.wireless = wireless;
    }

    public String getTipoProdotto() {
        return tipoProdotto;
    }

    public void setTipoProdotto(String tipoProdotto) {
        this.tipoProdotto = tipoProdotto;
    }

    public int getPollici() {
        return pollici;
    }

    public void setPollici(int pollici) {
        this.pollici = pollici;
    }

    public int getHz() {
        return hz;
    }

    public void setHz(int hz) {
        this.hz = hz;
    }

    public boolean isSupportaHDR() {
        return supportaHDR;
    }

    public void setSupportaHDR(boolean supportaHDR) {
        this.supportaHDR = supportaHDR;
    }

    public boolean isWireless() {
        return wireless;
    }

    public void setWireless(boolean wireless) {
        this.wireless = wireless;
    }
}
