package src.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Ordine implements Serializable {

    private static final long serialVersionUID = 7104571500815490481L;
    private final Utente utente;
    private final String indirizzo;
    private final Date date;
    private final List<Prodotto> prodottiOrdinati;

    public Ordine(Utente utente, List<Prodotto> prodottiOrdinati, Date date, String indirizzo) {
        this.utente = utente;
        this.prodottiOrdinati = prodottiOrdinati;
        this.date = date;
        this.indirizzo = indirizzo;
    }

    @Override
    public String toString() {
        return "Ordine{" +
                "utente=" + utente +
                ", indirizzo='" + indirizzo + '\'' +
                ", date=" + date +
                ", prodottiOrdinati=" + prodottiOrdinati +
                '}';
    }
}
