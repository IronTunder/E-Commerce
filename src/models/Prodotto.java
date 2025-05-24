package src.models;

import javax.swing.*;
import java.io.Serializable;

public abstract class Prodotto implements Serializable {

    private final String nome;
    private final String id;
    private final String marca;
    private final String categoria;
    private final ImageIcon icon;
    private final double prezzo;
    private String descrizioneOggetto;

    public Prodotto(String nome, String id, String marca, String categoria, ImageIcon icon, double prezzo, String descrizioneOggetto) {
        this.nome = nome;
        this.id = id;
        this.marca = marca;
        this.categoria = categoria;
        this.icon = icon;
        this.prezzo = prezzo;
        this.descrizioneOggetto = descrizioneOggetto;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getNome() {
        return nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public String getDescrizioneOggetto() {return descrizioneOggetto;}
}