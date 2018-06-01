package com.example.antonio.vulcadatos.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.antonio.vulcadatos.R;
import com.example.antonio.vulcadatos.fragments.InfAcel;
import com.example.antonio.vulcadatos.fragments.InfGps;
import com.example.antonio.vulcadatos.mapas.mapaGps;
import com.example.antonio.vulcadatos.models.EstacionesAcelero;

import java.util.ArrayList;

public class EstacionesAceleroAdapter extends RecyclerView.Adapter<EstacionesAceleroAdapter.ViewHolder> {


    private ArrayList<EstacionesAcelero> datos;
    private Context context;
    private Double latitud;
    private Double longitud;

    public EstacionesAceleroAdapter(Context context) {
        this.context = context;
        datos = new ArrayList<>();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carddatoacelero, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final EstacionesAcelero p = datos.get(position);
        holder.TV1.setText(p.getNombreEstacion());
        holder.TV2.setText(p.getMunicipio());
        holder.TV3.setText(p.getTipoEstacion());

        latitud=Double.valueOf(p.getLatitud());
        longitud=Double.valueOf(p.getLongitud());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context, mapaGps.class);
                i.putExtra("nombre", p.getNombreEstacion());
                i.putExtra("longitud",longitud);
                i.putExtra("latitud",latitud);
                context.startActivity(i);
            }
        });

        holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context, InfAcel.class);
                i.putExtra("agencia", p.getAgencia());
                i.putExtra("altitud",p.getAltitud());
                i.putExtra("depar",p.getDepartamento());
                i.putExtra("estado",p.getEstado());
                i.putExtra("fecha",p.getFechaInstalacion());
                i.putExtra("geo",p.getGeologia());
                i.putExtra("idEst",p.getIdEstacion());
                i.putExtra("latitud ",p.getLatitud());
                i.putExtra("longitud",p.getLongitud());
                i.putExtra("munc",p.getMunicipio());
                i.putExtra("nomEst",p.getNombreEstacion());
                i.putExtra("tipoDesc",p.getTipoDescarga());
                i.putExtra("tipoEst",p.getTipoEstacion());
                i.putExtra("topog",p.getTopografia());

                context.startActivity(i);
            }
        });




    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }


    public void adicionarLista(ArrayList<EstacionesAcelero> listaSIS) {
        datos.addAll(listaSIS);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView TV1, TV2, TV3;
        private Button btn, btn1;


        public ViewHolder(View itemView) {
            super(itemView);

            TV1 = (TextView) itemView.findViewById(R.id.TV1);
            TV2 = (TextView) itemView.findViewById(R.id.TV2);
            TV3 = (TextView) itemView.findViewById(R.id.TV3);
            btn = (Button) itemView.findViewById(R.id.button2);
            btn1 = (Button) itemView.findViewById(R.id.button4);
        }
    }
}
