package com.fmobile.client;
//
// Top 14 client
//
// Liste des clubs

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView  myListView;  // La liste des clubs
    ArrayList<String> myArrayList;
    Intent myIntent;  // L'intent qui vient de LoginActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        // Récupère l'intent envoyé par LoginActivity
        myIntent = getIntent();
        // Récupère la listView
        // TODO : est ce qu'il manque le cast en "ListView" ?
        myListView = findViewById(R.id.lv_liste);

        // Récupère des infos sur l'état de la connexion Internet
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // Si la connexion Internet est OK
        if (networkInfo != null && networkInfo.isConnected() ) {
            Toast.makeText(getApplicationContext(), "Connexion en cours ...", Toast.LENGTH_SHORT).show();
            // Récupère le user et le mot de passe
            String myData[] = myIntent.getStringArrayExtra(MyAsyncTask.EXTRA_MESSAGE);
            // Lance la tâche asynchrone
            new MyAsyncTask(myListView,this).execute(myData[0],myData[1]);
        } else {
            // Message d'erreur général
            Toast.makeText(getApplicationContext(), "Erreur : pas de connexion Internet !", Toast.LENGTH_LONG).show();
        }

    }

}
