package src.controllers;

import src.models.Ordine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersController {
    private static final String PRODUCTS_FILE = "files/orders.dat";
    private final List<Ordine> ordini = new ArrayList<>();

    public OrdersController() {
        new File("files").mkdirs();
    }

    public void salvaOrdini() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(PRODUCTS_FILE))) {
            objectOutputStream.writeObject(ordini);
            System.out.println("Ordini salvati: " + ordini);
        } catch (IOException e) {
            throw new RuntimeException("Errore durante il salvataggio dei Ordini", e);
        }
    }



    public void caricaOrdini() {
        ordini.clear();
        File file = new File(PRODUCTS_FILE);

        if (!file.exists() || file.length() == 0) {
            System.out.println("File non trovato o vuoto: " + file.getAbsolutePath());
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<Ordine> caricati = (List<Ordine>) ois.readObject();
            ordini.addAll(caricati);
            System.out.println("Ordini caricati: " + ordini);
        }catch (EOFException e){
            System.out.println("File vuoto");
        }
        catch (ClassNotFoundException | IOException e) {
            System.err.println("Errore durante il caricamento: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void aggiungiOrdine(Ordine ordine) {
        if (ordine == null) {
            return;
        }
        for (Ordine o : ordini) {
            if (o.equals(ordine)) {
                return;
            }
        }
        ordini.add(ordine);
    }

    public List<Ordine> getOrdini() {
        return ordini;
    }
}
