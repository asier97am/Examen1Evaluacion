package com.asier.aranda.examen1evaluacion;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.ViewHolder> {

    private List<String> divisas;
    private LayoutInflater mInflater;

    private TextView ultimaSeleccion;

    private int posicionUltimaSeleccion;//inicializo en constructor//al ser private necesito un get

    MiAdaptador(Context contexto,List<String>divisas){//elemento que contenia toda la informacion
        this.divisas=divisas;
        this.mInflater=LayoutInflater.from(contexto);
        posicionUltimaSeleccion=-1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    //onBind = enlazar
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //guardo el texto y lo gurado
        String divisa=divisas.get(position);//tipo texto igual al texto contenido en mi listado de divisas en la posicion que recibo como argumento
        //mostrar en recycled los nombres del arraylist
        holder.myTextView.setText(divisa);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView divisa= view.findViewById(R.id.tvNombreDivisas);

                //desmarcar si ya esta seleccionado
//                ultimaSeleccion esta en null
//                y posicionUltimaseleccion esta en -1
                if(divisa==ultimaSeleccion){
                    posicionUltimaSeleccion=-1;
                    ultimaSeleccion=null;//con esto lo desmarco
                    divisa.setBackgroundColor(Color.WHITE);
                }else{
                    //si hay otro marcado de antes lo desmarco
                    if(ultimaSeleccion!=null){
                        //me pone lo seleccionado antes en blanco
                        ultimaSeleccion.setBackgroundColor(Color.WHITE);
                    }
                    divisa.setBackgroundColor(Color.BLUE);
                    posicionUltimaSeleccion=position;
                    ultimaSeleccion=divisa;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return divisas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       TextView myTextView ;

       ViewHolder(View itemView){
           super(itemView);
           myTextView=itemView.findViewById(R.id.tvNombreDivisas);
       }
    }

    public int getElemetoSeleccionado(){

        return this.posicionUltimaSeleccion;
    }
}
