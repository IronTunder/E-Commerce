package src.models;

import java.io.Serializable;

public class Utente implements Serializable {
    private String email;
    private char[] password;
    private String username;

    public Utente(String email, char[] password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}