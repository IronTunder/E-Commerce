package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;

public class ComputerDesktop extends Prodotto {
    private String cpu;
    private String gpu;
    private int ramGB;
    private int memoriaGB;
    private String SO;

    public ComputerDesktop(String nome, String id, String marca, String categoria, ImageIcon icon, double prezzo, String descrizioneOggetto, String cpu, String gpu, int ramGB, int memoriaGB, String SO) {
        super(nome, id, marca, categoria, icon, prezzo, descrizioneOggetto);
        this.cpu = cpu;
        this.gpu = gpu;
        this.ramGB = ramGB;
        this.memoriaGB = memoriaGB;
        this.SO = SO;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public int getRamGB() {
        return ramGB;
    }

    public void setRamGB(int ramGB) {
        this.ramGB = ramGB;
    }

    public int getMemoriaGB() {
        return memoriaGB;
    }

    public void setMemoriaGB(int memoriaGB) {
        this.memoriaGB = memoriaGB;
    }

    public String getSO() {
        return SO;
    }

    public void setSO(String SO) {
        this.SO = SO;
    }
}
