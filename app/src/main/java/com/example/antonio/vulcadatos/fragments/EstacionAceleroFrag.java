package com.example.antonio.vulcadatos.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.antonio.vulcadatos.IInterfaz.IVulcaDatos;
import com.example.antonio.vulcadatos.R;
import com.example.antonio.vulcadatos.adapter.EstacionesAceleroAdapter;
import com.example.antonio.vulcadatos.models.EstacionesAcelero;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EstacionAceleroFrag extends Fragment {



    private Retrofit retrofit;
    private static final String TAG = "DATO";
    private RecyclerView recyclerView;
    private FragmentActivity myContext;
    private EstacionesAceleroAdapter estacionesSismoAdapter;


    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    public EstacionAceleroFrag(){
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_estacion_sismo, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        estacionesSismoAdapter = new EstacionesAceleroAdapter(getContext());
        recyclerView.setAdapter(estacionesSismoAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);



        retrofit=new Retrofit.Builder()
                .baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        procesarDatos();
        return view;
    }

    private void procesarDatos() {
        IVulcaDatos service = retrofit.create(IVulcaDatos.class);
        Call<ArrayList<EstacionesAcelero>> datosVolcaCall = service.obtenerListaSismo();

        datosVolcaCall.enqueue(new Callback<ArrayList<EstacionesAcelero>>() {
            @Override
            public void onResponse(Call<ArrayList<EstacionesAcelero>> call, Response<ArrayList<EstacionesAcelero>> response) {
                if (response.isSuccessful())
                {
                    ArrayList<EstacionesAcelero> datosGPS = response.body();
                    estacionesSismoAdapter.adicionarLista(datosGPS);
                }
                else {
                    Log.e(TAG, "onResponse: "+response.errorBody());
                }

            }
            @Override
            public void onFailure(Call<ArrayList<EstacionesAcelero>> call, Throwable t) {
                Log.e(TAG, " onFailure: "+t.getMessage());
            }


        });

    }
}
