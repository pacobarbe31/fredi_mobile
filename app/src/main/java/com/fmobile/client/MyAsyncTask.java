package com.fmobile.client;
//
// Top 14 client
//
// Tâche asynchrone lancée depuis ListActivity
// NOTA : on est obligé de lancer une tâche asynchrone car la connexion Internet peut prendre
// un certain temps, ce qui bloquerait ListActivity

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyAsyncTask extends AsyncTask<String, Void, String> {

    public static final String EXTRA_MESSAGE = "com.fmobile.client.MESSAGE";

    //String MyURL = "http://172.17.5.60/fredi_antoineba/api/login.php?user=mounny.paul@outlook.fr&password=0000";

    //Adresse PC Limayrac :
    String MyURL = "http://172.17.5.60/fredi_antoineba/api/login.php";
    //String MyUser = "mounny.paul@outlook.fr";
    String MyUser;
    //String MyPassword = "0000";
    String MyPassword;
    ListView myListView;
    Context myContext;
    ArrayList<LigneFrais> myLigneFrais = new ArrayList<>();
    ArrayList<String> myArrayList = new ArrayList<>();
    Activity myActivity;

    /**
     * Constructeur
     * @param listView la listView qui va recevoir le contenu
     */
    public MyAsyncTask(ListView listView, Activity activity) {
        super();
        myListView = listView;
        myContext = listView.getContext();
        myActivity = activity;
    }

    /**
     * Quand on lance la tâche asynchrone (.execute() dans ListActivity)
     * @param authentification le login et le mot de passe
     * @return Chaîne JSON
     */
    @Override
    protected String doInBackground(String... authentification) {
        MyUser = authentification[0];
        MyPassword = authentification[1];

        String url = MyURL + "?user=" + MyUser + "&password=" + MyPassword;
        Log.d(MainActivity.LOG_TAG, "URL=" + url);
        // Accède à Internet, consomme un service Web en RESTful et renvoie un contenu JSON
        return NetworkUtils.request(url);
    }

    /**
     * Quand la tâche asynchrone est terminée
     * @param jsonString le contenu JSON renvoyé par la méthode doInBackground()
     */
    @Override
    protected void onPostExecute(String jsonString) {
        super.onPostExecute(jsonString);

        // Interprète le contenu JSON pour récupérer le token
        if (jsonString != null) {
            try {
                // Récupère le contenu du fichier JSON
                JSONObject jsonObject = new JSONObject(jsonString);

                // Récupère la liste des LigneFrais
                if (jsonObject.isNull("lignes_frais")==false) {
                    JSONArray LigneFraisArray = jsonObject.getJSONArray("lignes_frais");

                    // Boucle de lecture des LigneFrais
                    for (int i = 0; i < LigneFraisArray.length(); i++) {

                        JSONObject LigneFraisJsonObject = LigneFraisArray.getJSONObject(i);

                        // Crée un objet métier LigneFrais à partir de l'objet JSONObject
                        LigneFrais laLigneFrais = new LigneFrais(LigneFraisJsonObject);

                        //laLigneFrais.setTrajet(obj.getString("trajet"));

                        // Ajoute l'objet métier dans la collection ArrayList<LigneFrais>
                        myLigneFrais.add(laLigneFrais);

                        // Ajoute la date du LigneFrais dans la collection ArrayList<String>
                        myArrayList.add("Trajet : " + laLigneFrais.trajet); // (laLigneFrais.trajet)

                        // Affiche un message en bas de liste
                        TextView textView = (TextView) myActivity.findViewById(R.id.tv_message);
                        textView.setText(String.valueOf(LigneFraisArray.length()) + " lignes de frais(s)");
                    }
                } else {
                    // Pas de liste club à afficher
                    myArrayList.add("Rien à afficher !");
                    Toast.makeText(myContext, "Rien à afficher !", Toast.LENGTH_LONG).show();
                    Log.d(MainActivity.LOG_TAG, "Rien à afficher");
                    // Affiche un message en bas de liste
                    TextView textView = (TextView) myActivity.findViewById(R.id.tv_message);
                    textView.setText("Rien à afficher");

                }

            } catch (Exception e) {
                Log.d(MainActivity.LOG_TAG, "Erreur lors de la lecture du fichier JSON");
                e.printStackTrace();
            }
        } else {
            Log.d(MainActivity.LOG_TAG, "Erreur : le fichier JSON est vide !");
        }

        // Remplit la listView
        // Crée l'adaptateur
        final ArrayAdapter<String> myAdapter = new ArrayAdapter<>(myContext, android.R.layout.simple_list_item_1, myArrayList);

        // Associe l'adapteur à la listView
        myListView.setAdapter(myAdapter);

        // Ajoute un gestionnaire d'événement sous la forme d'une classe anonyme
        myListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        // Instancie le ligne  pointé par le clic sur la listView
                        LigneFrais maLigneFrais = myLigneFrais.get(position);
                        // Transforme l'objet Club en array (tableau) pour pouvoir fournir les détails à l'activity suivante
                        // NOTA : les intents n'acceptent pas les objets, seulement des strings et des array de strings
                        String myData[] = maLigneFrais.toArray();
                        // Création de l'intent pour DetailsActivity
                        Intent myIntent = new Intent(myContext, DetailsActivity.class);
                        // Ajoute dans l'intent le tableau contenant les détails du club
                        myIntent.putExtra(EXTRA_MESSAGE, myData);
                        // Lancement de l'activité
                        myActivity.startActivity(myIntent);
                        // test
                        String chaine = myAdapter.getItem(position);
                        Log.d(MainActivity.LOG_TAG, "Clic sur" + chaine);
                    }
                }
        );

    }
}
