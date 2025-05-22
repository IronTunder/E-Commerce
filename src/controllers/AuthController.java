package src.controllers;

import src.models.Utente;
import src.view.HomePage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AuthController {
    private Utente login = null;
    HomePage homePage;
    private static final String USERS_FILE = "files/users.dat";

    public AuthController(HomePage homePage) {
        this.homePage = homePage;
        new File("files").mkdirs();
    }

    public void registraUtente(Utente utente) {
        ArrayList<Utente> users = caricaUtenti();
        for (Utente user : users) {
            if (user.equals(utente)) {
                throw new RuntimeException("Utente già esistente");
            }
        }
        users.add(utente);
        salvaUtenti(users);

        accedi(utente.getUsername(), utente.getPassword());
    }

    public boolean accedi(String nomeUtente, char[] password) {
        ArrayList<Utente> users = caricaUtenti();

        for (Utente user : users) {
            if ((user.getUsername().equals(nomeUtente) || user.getEmail().equals(nomeUtente))
                    && Arrays.equals(user.getPassword(), password)) {
                login = user;
                homePage.rimuoviAuth();
                return true;
            }
        }
        return false;
    }

    private ArrayList<Utente> caricaUtenti() {
        ArrayList<Utente> users = new ArrayList<>();
        File file = new File(USERS_FILE);

        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    try {
                        users.add((Utente) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Errore durante la lettura degli utenti", e);
            }
        }
        return users;
    }

    private void salvaUtenti(ArrayList<Utente> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            for (Utente user : users) {
                oos.writeObject(user);
            }
        } catch (Exception e) {
            throw new RuntimeException("Errore durante il salvataggio degli utenti", e);
        }
    }

    public void logout() {
        login = null;
        homePage.getJMenuBar().add(homePage.aggiungiAuth());
    }

    public boolean isLoggedIn() {
        return login != null;
    }

    public Utente getLogin() {
        return login;
    }
}