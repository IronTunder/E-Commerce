package src.controllers;

import src.models.Utente;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class AuthController {
    private Utente login = null;


    public void registraUtente(Utente utente){
        try{
            FileInputStream fileInputStream = new FileInputStream("users.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Utente> users = new ArrayList<>();
            while(true){
                try {
                    users.add((Utente) objectInputStream.readObject());
                } catch (IOException e) {
                    break;
                }
            }
            fileInputStream.close();
            objectInputStream.close();
            FileOutputStream fileOutputStream = new FileOutputStream("users.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            users.add(utente);
            for (Utente user : users) {
                objectOutputStream.writeObject(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void accedi(String nomeUtente,char[] password){
        try {
            FileInputStream fileInputStream = new FileInputStream("users.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while(true){
                try {
                    if((((Utente) objectInputStream.readObject()).getUsername().equals(nomeUtente) || ((Utente) objectInputStream.readObject()).getEmail().equals(nomeUtente)) && Arrays.equals(((Utente) objectInputStream.readObject()).getPassword(), password)){
                        login = (Utente) objectInputStream.readObject();
                    }
                } catch (IOException e) {
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isLoggedIn(){
        return login != null;
    }
}
