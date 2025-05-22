package src.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Utente implements Serializable {
    private final String email;
    private final char[] password;
    private final String username;
    private final boolean isAdmininstrator;
    ArrayList<Prodotto> carrello = new ArrayList<>();

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

    public ArrayList<Prodotto> getCarrello() {
        return carrello;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Utente)) return false;

        Utente utente = (Utente) o;
        return Objects.equals(email, utente.email) && Objects.equals(username, utente.username);
    }
}