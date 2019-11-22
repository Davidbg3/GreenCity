package com.example.greencity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.greencity.Adapter.RecyclerViewAdapter;
import com.example.greencity.Modelo.Alerta;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlertaFragment extends Fragment {

    private RecyclerView recyclerViewAlerta;
    private Adapter AdapterAlerta;

    public AlertaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_alerta, container, false);

        Spinner spinner = (Spinner) view.findViewById(R.id.EstadoAlerta);
        String[] letra = {"Pendiente","Confirmado","Rechazado","Finalizado"};
        //spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, letra));

        RecyclerView recyclerViewAlerta=(RecyclerView)view.findViewById(R.id.recycler_view);
        //recyclerViewAlerta.setLayoutManager(new LinearLayoutManager());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //AdapterAlerta=new RecyclerViewAdapter(ObtenerDatosAlerta());
        //recyclerViewAlerta.setAdapter(AdapterAlerta);
        recyclerViewAlerta.setLayoutManager(linearLayoutManager);
        //RecyclerViewAdapter recyclerViewAdapter= new RecyclerViewAdapter(buildAlerta(),R.layout.card_view_alerta,getActivity());

        return view;
    }

    public ArrayList<Alerta> buildAlerta(){

        ArrayList<Alerta> alertas=new ArrayList<>();
        alertas.add(new Alerta("En espera...","Jose Miguel","09/09/2019","14:02"));

        return alertas;

    }



}
