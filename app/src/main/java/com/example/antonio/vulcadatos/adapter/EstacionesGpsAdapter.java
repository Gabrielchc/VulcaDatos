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


import com.example.antonio.vulcadatos.fragments.InfGps;
import com.example.antonio.vulcadatos.mapas.mapaGps;
import com.example.antonio.vulcadatos.models.EstacionesGPS;

import java.util.ArrayList;


public class EstacionesGpsAdapter extends RecyclerView.Adapter<EstacionesGpsAdapter.ViewHolder> {


    private ArrayList<EstacionesGPS> datos;
    private Context context;
    private Double latitud;
    private Double longitud;


    public EstacionesGpsAdapter(Context context) {
        this.context = context;
        datos = new ArrayList<>();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carddatosgps, parent, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final EstacionesGPS p = datos.get(position);
        holder.TV1.setText(p.getNombre());
        holder.TV2.setText(p.getTipoDeEstaciN());
        holder.TV3.setText(p.getVolcN());

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latitud=Double.valueOf(p.getLatitud());
                longitud=Double.valueOf(p.getLongitud());
                Intent i=new Intent(context, mapaGps.class);
                i.putExtra("nombre", p.getNombre());
                i.putExtra("longitud", longitud);
                i.putExtra("latitud",latitud);
                context.startActivity(i);
            }
        });


        holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(context, InfGps.class);
                i.putExtra("altitud", p.getAltitud());
                i.putExtra("fecha", p.getFechaDeInstalaciN());
                i.putExtra("id", p.getId());
                i.putExtra("longitud", p.getLongitud());
                i.putExtra("latitud",p.getLatitud());
                i.putExtra("nombre", p.getNombre());
                i.putExtra("ovs", p.getOvs());
                i.putExtra("tipo", p.getTipoDeEstaciN());
                i.putExtra("volcan", p.getVolcN());

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




    public void adicionarLista(ArrayList<EstacionesGPS> listaGPS) {
        datos.addAll(listaGPS);
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
            btn = (Button) itemView.findViewById(R.id.button);
            btn1= (Button) itemView.findViewById(R.id.button3);






        }
    }
}
