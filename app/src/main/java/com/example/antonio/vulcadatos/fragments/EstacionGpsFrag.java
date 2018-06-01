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
import com.example.antonio.vulcadatos.adapter.EstacionesGpsAdapter;
import com.example.antonio.vulcadatos.models.EstacionesGPS;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class EstacionGpsFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER




    private Retrofit retrofit;
    private static final String TAG = "DATO";
    private RecyclerView recyclerView;
    private FragmentActivity myContext;
    private EstacionesGpsAdapter estacionesGpsAdapter;


    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    public EstacionGpsFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_estacion_gps, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        estacionesGpsAdapter = new EstacionesGpsAdapter(getContext());
        recyclerView.setAdapter(estacionesGpsAdapter);
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
        Call<ArrayList<EstacionesGPS>> datosVolcaCall = service.obtenerListaGps();

        datosVolcaCall.enqueue(new Callback<ArrayList<EstacionesGPS>>() {
            @Override
            public void onResponse(Call<ArrayList<EstacionesGPS>> call, Response<ArrayList<EstacionesGPS>> response) {
                if (response.isSuccessful())
                {
                    ArrayList<EstacionesGPS> datosGPS = response.body();
                    estacionesGpsAdapter.adicionarLista(datosGPS);
                }
                else {
                    Log.e(TAG, "onResponse: "+response.errorBody());
                }

            }
            @Override
            public void onFailure(Call<ArrayList<EstacionesGPS>> call, Throwable t) {
                Log.e(TAG, " onFailure: "+t.getMessage());
            }


        });

    }

}
