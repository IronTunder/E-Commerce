package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;

public class NotebookEAccessori extends Prodotto {
    private double dimensioneDisplayPollici;
    private String risoluzione;
    private boolean touchscreen;
    private int autonomiaOre;
    private String cpu;
    private int ramGB;


    public NotebookEAccessori(String nome, String id, String marca, String categoria, ImageIcon icon, double prezzo, String descrizioneOggetto, double dimensioneDisplayPollici, String risoluzione, boolean touchscreen, int autonomiaOre, String cpu, int ramGB) {
        super(nome, id, marca, categoria, icon, prezzo, descrizioneOggetto);
        this.dimensioneDisplayPollici = dimensioneDisplayPollici;
        this.risoluzione = risoluzione;
        this.touchscreen = touchscreen;
        this.autonomiaOre = autonomiaOre;
        this.cpu = cpu;
        this.ramGB = ramGB;
    }

    public double getDimensioneDisplayPollici() {
        return dimensioneDisplayPollici;
    }

    public void setDimensioneDisplayPollici(double dimensioneDisplayPollici) {
        this.dimensioneDisplayPollici = dimensioneDisplayPollici;
    }

    public String getRisoluzione() {
        return risoluzione;
    }

    public void setRisoluzione(String risoluzione) {
        this.risoluzione = risoluzione;
    }

    public boolean isTouchscreen() {
        return touchscreen;
    }

    public void setTouchscreen(boolean touchscreen) {
        this.touchscreen = touchscreen;
    }

    public int getAutonomiaOre() {
        return autonomiaOre;
    }

    public void setAutonomiaOre(int autonomiaOre) {
        this.autonomiaOre = autonomiaOre;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getRamGB() {
        return ramGB;
    }

    public void setRamGB(int ramGB) {
        this.ramGB = ramGB;
    }
}
