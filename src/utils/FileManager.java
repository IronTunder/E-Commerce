package src.utils;

import src.models.Prodotto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String PRODUCTS_FILE = "files/products.dat";
    private final List<Prodotto> prodotti = new ArrayList<>();

    public FileManager() {
        new File("files").mkdirs();
    }

    public void salvaProdotti() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(PRODUCTS_FILE))) {
            objectOutputStream.writeObject(prodotti);
            System.out.println("Prodotti salvati: " + prodotti);
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
            List<Prodotto> caricati = (List<Prodotto>) ois.readObject();
            prodotti.addAll(caricati);
            System.out.println("Prodotti caricati: " + prodotti);
        }catch (EOFException e){
            System.out.println("File vuoto");
        }
        catch (ClassNotFoundException | IOException e) {
            System.err.println("Errore durante il caricamento: " + e.getMessage());
            e.printStackTrace();
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
        return prodotti;
    }


//    public boolean rimuoviProdotto(String id) {
//        // Crea una copia della lista per evitare ConcurrentModificationException
//        List<Prodotto> prodottiDaRimuovere = new ArrayList<>();
//
//        for (Prodotto p : prodotti) {
//            if (p.getId().equals(id)) {
//                prodottiDaRimuovere.add(p);
//            }
//        }
//
//        boolean rimosso = false;
//        for (Prodotto p : prodottiDaRimuovere) {
//            rimosso = prodotti.remove(p) || rimosso;
//        }
//
//        if (rimosso) {
//            salvaProdotti();
//        }
//
//        return rimosso;
//    }
}