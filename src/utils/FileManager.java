package src.utils;

import src.models.Prodotto;

import java.io.*;
import java.util.ArrayList;

public abstract class FileManager {
    ArrayList<Prodotto> prodotti = new ArrayList<>();


    public void salvaProdotti(){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("products.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            prodotti.forEach(prodotto -> {
                try {
                    objectOutputStream.writeObject(prodotto);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void caricaProdotti(){
        try{
            FileInputStream fileInputStream = new FileInputStream("products.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            prodotti.clear();
            while(true){
                try {
                    prodotti.add((Prodotto) objectInputStream.readObject());
                } catch (IOException e) {
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public boolean aggiungiProdotto(Object prodotto){
        if(prodotto.getClass().getPackage().getName().equals("categories")){
            for (Prodotto value : prodotti) {
                if (value.equals(prodotto))
                    return false;
            }
            prodotti.add((Prodotto) prodotto);
            return true;
        }
        return false;
    }


}
