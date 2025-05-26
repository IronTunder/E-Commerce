package src.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utente implements Serializable {
    private static final long serialVersionUID = -3511587265146980805L;
    private final String email;
    private final char[] password;
    private final String username;
    private final boolean isAdmininstrator;
    private List<Prodotto> carrello = new ArrayList<>();
    private List<String> indirizzi = new ArrayList<>();

    public Utente(String email, char[] password, String username, boolean isAdmininstrator) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.isAdmininstrator = isAdmininstrator;
    }

    public String getEmail() {
        return email;
    }

    public char[] getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAdmininstrator() {
        return isAdmininstrator;
    }

    public List<Prodotto> getCarrello() {
        return carrello;
    }

    public List<String> getIndirizzi() {
        return indirizzi;
    }

    public void setIndirizzi(List<String> indirizzi) {
        this.indirizzi = indirizzi;
    }

    public void setCarrello(List<Prodotto> carrello){
        this.carrello = carrello;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Utente)) return false;

        Utente utente = (Utente) o;
        return Objects.equals(email, utente.email) && Objects.equals(username, utente.username);
    }


}