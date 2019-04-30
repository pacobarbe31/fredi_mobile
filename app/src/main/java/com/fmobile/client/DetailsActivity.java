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
    TextView myViewId;
    TextView myViewdate;
    TextView myViewTrajets;
    TextView myViewkm;
    TextView myViewPeage;
    TextView myViewRepas;
    TextView myViewheberg;
    TextView myViewMotif;
    TextView myViewnom_club;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Récupère les données à afficher
        myIntent = getIntent();
        String myData[] = myIntent.getStringArrayExtra(MyAsyncTask.EXTRA_MESSAGE);

        // Affiche les données dans le layout
        myViewId = (TextView) findViewById(R.id.tv_id);
        myViewId.setText("ID bordereau : " + myData[0]);

        myViewdate = (TextView) findViewById(R.id.tv_date);
        myViewdate.setText(myData[1]);

        myViewTrajets = (TextView) findViewById(R.id.tv_Trajets);
        myViewTrajets.setText("Trajets : " + myData[2]);

        myViewkm = (TextView) findViewById(R.id.tv_km);
        myViewkm.setText("km : " + myData[3]);

        myViewPeage = (TextView) findViewById(R.id.tv_Peage);
        myViewPeage.setText("Peage : " + myData[4]);

        myViewRepas = (TextView) findViewById(R.id.tv_repas);
        myViewRepas.setText("Repas : " + myData[5]);

        myViewheberg = (TextView) findViewById(R.id.tv_heberg);
        myViewheberg.setText("heberg : " + myData[6]);

        myViewMotif = (TextView) findViewById(R.id.tv_motif);
        myViewMotif.setText("Motif : " + myData[7]);

        myViewnom_club = (TextView) findViewById(R.id.tv_nom_club);
        myViewnom_club.setText("nom_club : " + myData[8]);

    }
/*
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ListActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }*/
}
