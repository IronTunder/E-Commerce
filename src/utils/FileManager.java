package src.utils;

import src.models.Prodotto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String PRODUCTS_FILE = "files/products.dat";
    private final List<Prodotto> prodotti;

    public FileManager() {
        this.prodotti = new ArrayList<>();
        new File("files").mkdirs();
    }

    public void salvaProdotti() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(PRODUCTS_FILE))) {

            for (Prodotto prodotto : prodotti) {
                objectOutputStream.writeObject(prodotto);
            }
        } catch (IOException e) {
            throw new RuntimeException("Errore durante il salvataggio dei prodotti", e);
        }
    }

    public void caricaProdotti() {
        prodotti.clear();
        File file = new File(PRODUCTS_FILE);

        if (!file.exists() || file.length() == 0) {
            return;
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(file))) {
            while (true) {
                try {
                    Prodotto prodotto = (Prodotto) objectInputStream.readObject();
                    prodotti.add(prodotto);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Formato file non valido", e);
        } catch (IOException e) {
            throw new RuntimeException("Errore durante la lettura dei prodotti", e);
        }
    }

    public boolean aggiungiProdotto(Prodotto prodotto) {
        if (prodotto == null) {
            return false;
        }
        for (Prodotto p : prodotti) {
            if (p.equals(prodotto)) {
                return false;
            }
        }
        prodotti.add(prodotto);
        return true;
    }

    public List<Prodotto> getProdotti() {
        return new ArrayList<>(prodotti);
    }
}