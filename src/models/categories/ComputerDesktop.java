package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;

public class ComputerDesktop extends Prodotto {
    private final String cpu;
    private final String gpu;
    private final int ramGB;
    private final int memoriaGB;
    private final String SO;

    public ComputerDesktop(String nome, String id, String marca, String categoria, ImageIcon icon, double prezzo, String descrizioneOggetto, String cpu, String gpu, int ramGB, int memoriaGB, String SO) {
        super(nome, id, marca, categoria, icon, prezzo, descrizioneOggetto);

        if (cpu == null || cpu.trim().isEmpty()) {
            throw new IllegalArgumentException("La CPU non può essere vuota");
        }
        if (gpu == null || gpu.trim().isEmpty()) {
            throw new IllegalArgumentException("La GPU non può essere vuota");
        }
        if (ramGB <= 0) {
            throw new IllegalArgumentException("La RAM deve essere maggiore di 0");
        }
        if (memoriaGB <= 0) {
            throw new IllegalArgumentException("La memoria deve essere maggiore di 0");
        }
        if (SO == null || SO.trim().isEmpty()) {
            throw new IllegalArgumentException("Il sistema operativo non può essere vuoto");
        }

        this.cpu = cpu;
        this.gpu = gpu;
        this.ramGB = ramGB;
        this.memoriaGB = memoriaGB;
        this.SO = SO;
    }

    public String getCpu() {
        return cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public int getRamGB() {
        return ramGB;
    }


    public int getMemoriaGB() {
        return memoriaGB;
    }

    public String getSO() {
        return SO;
    }

}
