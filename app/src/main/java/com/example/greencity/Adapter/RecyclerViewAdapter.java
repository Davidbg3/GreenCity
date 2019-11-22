package com.example.greencity.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.greencity.Modelo.Alerta;
import com.example.greencity.Modelo.Usuario;
import com.example.greencity.R;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView fotoreciclador;
        private TextView nombreRecolector;
        //private TextView tipoUsuariouno;
        //Button datosRecolector;
        //private Spinner estado;
        private TextView nombreVecino;
        //private TextView tipoUsuariodos;
        //Button detalleRecojo;
        private TextView fechaRecojo;
        private TextView horaRecojo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreRecolector=(TextView)itemView.findViewById(R.id.NombreRecolector);
            //tipoUsuariouno=(TextView)itemView.findViewById(R.id.tipoRecolector);
            //estado=(Spinner)itemView.findViewById(R.id.EstadoAlerta);
            nombreVecino=(TextView)itemView.findViewById(R.id.nombreVecino);
            //tipoUsuariodos=(TextView)itemView.findViewById(R.id.tipoVecino);
            fechaRecojo=(TextView)itemView.findViewById(R.id.fecha);
            horaRecojo=(TextView)itemView.findViewById(R.id.hora);
        }
    }
    public List<Alerta> alertaLista;
    public List<Usuario> usuarioLista;
    public RecyclerViewAdapter(List<Alerta> alertaLista,List<Usuario> usurioLista) {
        this.alertaLista = alertaLista;
        this.usuarioLista = usuarioLista;
    }



    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_alerta,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder holder, int position){

        holder.nombreRecolector.setText(alertaLista.get(position).getNom_usuario_recolector());
        //holder.tipoUsuariouno.setText(alertaLista.get(position).getNom_usuario_recolector());
        //aun no funciona el spinner
        //holder.estado.setText(String.valueOf(alertaLista.get(position).getCod_estado()));
        holder.nombreVecino.setText(alertaLista.get(position).getNom_usuario_vecino());
        //holder.tipoUsuariodos.setText((alertaLista.get(position).getNom_usuario_vecino()));
        holder.fechaRecojo.setText(alertaLista.get(position).getFecha());
        holder.horaRecojo.setText(alertaLista.get(position).getHora());
    }


    public int getItemCount(){
        return alertaLista.size();

    }


}
