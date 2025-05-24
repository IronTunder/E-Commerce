package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;

public class AudioVideoGaming extends Prodotto {
    private final String tipoProdotto;
    private final int pollici;
    private final int hz;
    private final boolean supportaHDR;
    private final boolean wireless;

    public AudioVideoGaming(String nome, String id, String marca, String categoria, ImageIcon icon, double prezzo, String descrizioneOggetto, String tipoProdotto, int pollici, int hz, boolean supportaHDR, boolean wireless) {
        super(nome, id, marca, categoria, icon, prezzo, descrizioneOggetto);

        if (tipoProdotto == null || tipoProdotto.trim().isEmpty()) {
            throw new IllegalArgumentException("Il tipo di prodotto non può essere vuoto");
        }
        if (pollici <= 0) {
            throw new IllegalArgumentException("I pollici devono essere maggiori di 0");
        }
        if (hz <= 0) {
            throw new IllegalArgumentException("La frequenza (Hz) deve essere maggiore di 0");
        }

        this.tipoProdotto = tipoProdotto;
        this.pollici = pollici;
        this.hz = hz;
        this.supportaHDR = supportaHDR;
        this.wireless = wireless;
    }

    public String getTipoProdotto() {
        return tipoProdotto;
    }

    public int getPollici() {
        return pollici;
    }

    public int getHz() {
        return hz;
    }

    public boolean isSupportaHDR() {
        return supportaHDR;
    }

    public boolean isWireless() {
        return wireless;
    }

}
