package src.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Ordine implements Serializable {

    private static final long serialVersionUID = 7104571500815490481L;
    private final Utente utente;
    private final int id;
    private final String indirizzo;
    private final LocalDateTime date;
    private final List<Prodotto> prodottiOrdinati;
    private final double total;
    private String stato;

    public Ordine(Utente utente,int id, List<Prodotto> prodottiOrdinati, LocalDateTime date, String indirizzo, double total,String stato) {
        this.utente = utente;
        this.id = id;
        this.prodottiOrdinati = prodottiOrdinati;
        this.date = date;
        this.indirizzo = indirizzo;
        this.total = total;
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Ordine{" +
                "utente=" + utente +
                ", indirizzo='" + indirizzo + '\'' +
                ", date=" + date +
                ", prodottiOrdinati=" + prodottiOrdinati +
                ", total=" + total +
                '}';
    }

    public Utente getUtente() {
        return utente;
    }

    public double getTotal() {
        return total;
    }

    public List<Prodotto> getProdottiOrdinati() {
        return prodottiOrdinati;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public int getId() {
        return id;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stringa) {
        this.stato = stringa;
    }
}
