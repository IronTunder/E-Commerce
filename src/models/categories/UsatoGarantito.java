package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;

public class UsatoGarantito extends Prodotto {
    private String statoUsura;
    private int annoProduzione;
    private int mesiGaranziaResidua;
    private boolean includeAccessoriOriginali;

    public UsatoGarantito(String nome, String id, String marca, String categoria, ImageIcon icon, double prezzo, String descrizioneOggetto, String statoUsura, int annoProduzione, int mesiGaranziaResidua, boolean includeAccessoriOriginali) {
        super(nome, id, marca, categoria, icon, prezzo, descrizioneOggetto);
        this.statoUsura = statoUsura;
        this.annoProduzione = annoProduzione;
        this.mesiGaranziaResidua = mesiGaranziaResidua;
        this.includeAccessoriOriginali = includeAccessoriOriginali;
    }

    public String getStatoUsura() {
        return statoUsura;
    }

    public void setStatoUsura(String statoUsura) {
        this.statoUsura = statoUsura;
    }

    public int getAnnoProduzione() {
        return annoProduzione;
    }

    public void setAnnoProduzione(int annoProduzione) {
        this.annoProduzione = annoProduzione;
    }

    public int getMesiGaranziaResidua() {
        return mesiGaranziaResidua;
    }

    public void setMesiGaranziaResidua(int mesiGaranziaResidua) {
        this.mesiGaranziaResidua = mesiGaranziaResidua;
    }

    public boolean isIncludeAccessoriOriginali() {
        return includeAccessoriOriginali;
    }

    public void setIncludeAccessoriOriginali(boolean includeAccessoriOriginali) {
        this.includeAccessoriOriginali = includeAccessoriOriginali;
    }
}
