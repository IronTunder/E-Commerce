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
    private final String descrizioneOggetto;

    public Prodotto(String nome, String id, String marca, String categoria, ImageIcon icon, double prezzo, String descrizioneOggetto) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome non può essere vuoto");
        }
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("L'ID non può essere vuoto");
        }
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca non può essere vuota");
        }
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoria non può essere vuota");
        }
        if (prezzo <= 0) {
            throw new IllegalArgumentException("Il prezzo deve essere maggiore di 0");
        }
        if (descrizioneOggetto == null || descrizioneOggetto.trim().isEmpty()) {
            throw new IllegalArgumentException("La descrizione non può essere vuota");
        }

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

    @Override
    public String toString() {
        return "Prodotto{" +
                "nome='" + nome + '\'' +
                ", id='" + id + '\'' +
                ", marca='" + marca + '\'' +
                ", categoria='" + categoria + '\'' +
                ", icon=" + icon +
                ", prezzo=" + prezzo +
                ", descrizioneOggetto='" + descrizioneOggetto + '\'' +
                '}';
    }
}