package com.fmobile.client;
//
// Top 14 client
//
// Détails du club
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    Intent myIntent;
    //TextView myViewId;
    TextView myViewdate;
    TextView myViewTrajets;
    TextView myViewkm;
    TextView myViewPeage;
    TextView myViewRepas;
    TextView myViewheberg;
    //TextView myViewMotif;
    //TextView myViewnom_club;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Récupère les données à afficher
        myIntent = getIntent();
        String myData[] = myIntent.getStringArrayExtra(MyAsyncTask.EXTRA_MESSAGE);

        // Affiche les données dans le layout
        //myViewId = (TextView) findViewById(R.id.tv_id);
        //myViewId.setText("ID Bordereau : " + myData[0]);

        myViewdate = (TextView) findViewById(R.id.tv_date);
        myViewdate.setText("Date du trajet : " + myData[0²]);

        myViewTrajets = (TextView) findViewById(R.id.tv_Trajets);
        myViewTrajets.setText("Trajet : " + myData[0]);

        myViewkm = (TextView) findViewById(R.id.tv_km);
        myViewkm.setText("Distance : " + myData[2] + " kms");

        myViewPeage = (TextView) findViewById(R.id.tv_Peage);
        myViewPeage.setText("Peage : " + myData[3] + " €");

        myViewRepas = (TextView) findViewById(R.id.tv_repas);
        myViewRepas.setText("Repas : " + myData[4] + " €");

        myViewheberg = (TextView) findViewById(R.id.tv_heberg);
        myViewheberg.setText("Hebergement : " + myData[5] + " €");

        //myViewMotif = (TextView) findViewById(R.id.tv_motif);
        //myViewMotif.setText("Motif : " + myData[7]);

        //myViewnom_club = (TextView) findViewById(R.id.tv_nom_club);
        //myViewnom_club.setText("Nom du Club : " + myData[8]);

    }
/*
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ListActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }*/
}