package com.example.antonio.vulcadatos.fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.antonio.vulcadatos.R;

public class InfGps extends AppCompatActivity {

    private TextView altitud, fechaIns, id, latitud, longitud, nombr, ovs, tipoEsta, volcan;
    private Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_gps);
        altitud = (TextView) findViewById(R.id.TV1);
        fechaIns = (TextView) findViewById(R.id.TV2);
        id = (TextView) findViewById(R.id.TV3);
        latitud = (TextView) findViewById(R.id.TV4);
        longitud = (TextView) findViewById(R.id.TV5);
        nombr = (TextView) findViewById(R.id.TV6);
        ovs = (TextView) findViewById(R.id.TV7);
        tipoEsta = (TextView) findViewById(R.id.TV8);
        volcan = (TextView) findViewById(R.id.TV9);
        regresar = (Button) findViewById(R.id.volver);


        regresar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //  Log.e("fdgfdfgfgfgfh--->","SI");
                finish();

            }
        });


        Bundle b = getIntent().getExtras();
        String t = b.getString("altitud");
        altitud.setText(t);

        String f = b.getString("fecha");
        fechaIns.setText(f);

        String i = b.getString("id");
        id.setText(i);

        String lat = b.getString("latitud");
        latitud.setText(lat);

        String lon = b.getString("longitud");
        longitud.setText(lon);

        String nom = b.getString("nombre");
        nombr.setText(nom);

        String ov = b.getString("ovs");
        ovs.setText(ov);

        String ti = b.getString("tipo");
        tipoEsta.setText(ti);

        String vol = b.getString("volcan");
        volcan.setText(vol);


    }
}
