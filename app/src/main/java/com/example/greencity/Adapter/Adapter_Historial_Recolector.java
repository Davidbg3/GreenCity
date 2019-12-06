package com.example.greencity.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greencity.Modelo.historial_Recolector;
import com.example.greencity.R;

import java.util.List;

public class Adapter_Historial_Recolector extends BaseAdapter {

    Context contexto;
    List<historial_Recolector> listaObjetos;

    Integer[] imgID = {R.drawable.pendiente,R.drawable.aceptado,R.drawable.cerrado};

    public Adapter_Historial_Recolector(Context contexto, List<historial_Recolector> listaObjetos) {
        this.contexto = contexto;
        this.listaObjetos = listaObjetos;
    }

    @Override
    public int getCount() {
        return listaObjetos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaObjetos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaObjetos.get(i).getCod_recojo();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) { //se encarga de cargar el contenido de cada item del listview



        View vista = convertView;
        LayoutInflater inflate = LayoutInflater.from(contexto);
        vista= inflate.inflate(R.layout.itemview_historial_recolector,null);

        ImageView imagen = vista.findViewById(R.id.img_foto);
        TextView material = vista.findViewById(R.id.txt_material);
        TextView direccion = vista.findViewById(R.id.txt_direccion);
        TextView peso = vista.findViewById(R.id.txt_kilos);
        TextView monto = vista.findViewById(R.id.txt_soles);


        material.setText(listaObjetos.get(i).getMaterial());
        direccion.setText("Punto de Recogo: " + listaObjetos.get(i).getDireccion());
        peso.setText(listaObjetos.get(i).getPeso().toString() + "Kg.");
        monto.setText("S/ " + listaObjetos.get(i).getMonto().toString());



//        imagen.setImageResource(listaObjetos.get(i).getImage());
//        imagen.setImageResource(imgID[listaObjetos.get(i).getCod_estado()]);
        imagen.setImageResource(R.drawable.aceptado);



        return vista;

    }
}
