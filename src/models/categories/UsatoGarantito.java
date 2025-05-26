package src.models.categories;

import src.models.Prodotto;

import javax.swing.*;

public class UsatoGarantito extends Prodotto {
    private final String statoUsura;
    private final int annoProduzione;
    private final int mesiGaranziaResidua;
    private final boolean includeAccessoriOriginali;

    public UsatoGarantito(String nome, String id, String marca, String categoria, String urlImage, double prezzo, String descrizioneOggetto, String statoUsura, int annoProduzione, int mesiGaranziaResidua, boolean includeAccessoriOriginali) {
        super(nome, id, marca, categoria, urlImage, prezzo, descrizioneOggetto);

        if (statoUsura == null || statoUsura.trim().isEmpty()) {
            throw new IllegalArgumentException("Lo stato di usura non può essere vuoto");
        }
        if (annoProduzione <= 0) {
            throw new IllegalArgumentException("L'anno di produzione non valido");
        }
        if (mesiGaranziaResidua < 0) {
            throw new IllegalArgumentException("I mesi di garanzia residua non possono essere negativi");
        }

        this.statoUsura = statoUsura;
        this.annoProduzione = annoProduzione;
        this.mesiGaranziaResidua = mesiGaranziaResidua;
        this.includeAccessoriOriginali = includeAccessoriOriginali;
    }

    public String getStatoUsura() {
        return statoUsura;
    }

    public int getAnnoProduzione() {
        return annoProduzione;
    }

    public int getMesiGaranziaResidua() {
        return mesiGaranziaResidua;
    }

    public boolean isIncludeAccessoriOriginali() {
        return includeAccessoriOriginali;
    }

}
