package src.utils;

import src.models.Prodotto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String PRODUCTS_FILE = "files/products.dat";
    private List<Prodotto> prodotti;

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
            System.out.println("File non trovato o vuoto: " + file.getAbsolutePath());
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof Prodotto) {
                        prodotti.add((Prodotto) obj);
                        System.out.println("Caricato prodotto: " + obj);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Classe non trovata durante la deserializzazione: " + e.getMessage());
            e.printStackTrace();
        } catch (InvalidClassException e) {
            System.err.println("Problema di versione della classe: " + e.getMessage());
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            System.err.println("Formato file corrotto: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Errore IO durante la lettura: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Totale prodotti caricati: " + prodotti.size());
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

//    public boolean rimuoviProdotto(String id) {
//        for (Prodotto p : new ArrayList<>(prodotti)) {
//            if (p.getId().equals(id)) {
//                prodotti.remove(p);
//                return true;
//            }
//        }
//        return false;
//    }
}