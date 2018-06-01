package com.example.antonio.vulcadatos.fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.antonio.vulcadatos.R;

public class InfAcel extends AppCompatActivity {


    private TextView agenc, alti, depar, estado, fecha, geo, id, latitud, longitud,
    mun, nomEst, tipoDesc, tipoEst, topog;

    private Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_acel);


        agenc = (TextView) findViewById(R.id.TV1);
        alti = (TextView) findViewById(R.id.TV2);
        depar = (TextView) findViewById(R.id.TV3);
        estado = (TextView) findViewById(R.id.TV4);
        fecha = (TextView) findViewById(R.id.TV5);
        geo = (TextView) findViewById(R.id.TV6);
        id = (TextView) findViewById(R.id.TV7);
        latitud = (TextView) findViewById(R.id.TV8);
        longitud = (TextView) findViewById(R.id.TV9);
        mun = (TextView) findViewById(R.id.TV10);
        nomEst = (TextView) findViewById(R.id.TV11);
        tipoDesc = (TextView) findViewById(R.id.TV12);
        tipoEst = (TextView) findViewById(R.id.TV13);
        topog = (TextView) findViewById(R.id.TV14);
        regresar = (Button) findViewById(R.id.volver);


        regresar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();

            }
        });

        Bundle b = getIntent().getExtras();
        String t = b.getString("agencia");
        agenc.setText(t);

        String al = b.getString("altitud");
        alti.setText(al);

        String de = b.getString("depar");
        depar.setText(de);

        String es = b.getString("estado");
        estado.setText(es);

        String fe = b.getString("fecha");
        fecha.setText(fe);

        String ge = b.getString("geo");
        geo.setText(ge);

        String ids = b.getString("idEst");
        id.setText(ids);

        String lat = b.getString("latitud");
        latitud.setText(lat);

        String lon = b.getString("longitud");
        longitud.setText(lon);

        String muni = b.getString("munc");
        mun.setText(muni);

        String noEs = b.getString("nomEst");
        nomEst.setText(noEs);

        String tiDe = b.getString("tipoDesc");
        tipoDesc.setText(tiDe);

        String tiEs = b.getString("tipoEst");
        tipoEst.setText(tiEs);

        String top = b.getString("topog");
        topog.setText(top);













    }
}
