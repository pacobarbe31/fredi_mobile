package com.fmobile.client;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Classe métier d'un LigneFrais
 * Décrit les données d'un LigneFrais
 */
public class LigneFrais {

    /**
     * ID du LigneFrais
     */
    //public int id;
    /**
     * Date du LigneFrais
     */
    public String date;
    /**
     * mibelle du trajet
     */
    public String trajet;
    /**
     * distance
     */
    public String km;
    /**
     * montant péage
     */
    public String peage;
    /**
     * montant repas
     */
    public String repas;
    /**
     * montant hebergement
     */
    public String heberg;
    /**
     * mibelle motif
     */
    //public String motif;
    /**
     * mibelle Club
     */
    //public String nom_club;

    /**
     * Constructeur
     * Construit un objet à partir d'un JSONObject
     * @param jsonObject
     */
    public LigneFrais(JSONObject jsonObject) {
        try {
            //id = jsonObject.getInt("IdBordereau");
            trajet = jsonObject.getString("trajet");
            date = jsonObject.getString("date");
            km = jsonObject.getString("km");
            peage = jsonObject.getString("peage");
            repas = jsonObject.getString("repas");
            heberg = jsonObject.getString("heberg");
            //motif = jsonObject.getString("Motif");
            //nom_club = jsonObject.getString("NomClub");
        } catch (JSONException e) {
            Log.d(MainActivity.LOG_TAG,"Erreur lors de la conversion de l'objet JSON en objet LigneFrais");
            e.printStackTrace();
        }
    }

    /**
     * Convertit l'objet courant en array
     * @return le tableau
     */
    public String[] toArray() {
        String data[] = {
                //Integer.toString(id),
                trajet,
                date,
                km,
                peage,
                repas,
                heberg
                //motif,
                //nom_club
        };
        return data;
    }

}
