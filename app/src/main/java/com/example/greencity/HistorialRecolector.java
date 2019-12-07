package com.example.greencity;


import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;


import com.example.greencity.Adapter.Adapter_Historial_Recolector;
import com.example.greencity.Modelo.historial_Recolector;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

//import com.example.green.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistorialRecolector extends Fragment {

    ListView listaDatos;
    ArrayList<historial_Recolector> lista;


    public HistorialRecolector() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_historial_recolector, container, false);

        View view = inflater.inflate(R.layout.fragment_historial_recolector, container, false);

        listaDatos = (ListView) view.findViewById(R.id.lstDatos);
        lista = new ArrayList<historial_Recolector>();

//        ---------------------------------------------------------------------------

        String sql="https://greencityapp.000webhostapp.com/recojos/historialrecolector/" + Integer.toString(Global.IdUsuario) ;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url = null;
        HttpsURLConnection conn;

        try {
            url=new URL(sql);
            conn= (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputline;
            StringBuffer response = new StringBuffer();
            String json="";

            while ((inputline = in.readLine()) != null){
                response.append(inputline);
            }

            json = response.toString();
            JSONArray jsonArray = null;
            jsonArray=new JSONArray(json);


            int vcod_recojo, vcod_estado;
            String vmaterial, vdireccion, vfecha, vhora, vtlf;
            Double vpeso, vmonto;


            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                vcod_recojo = jsonObject.optInt("cod_recojo");
                vmaterial = jsonObject.optString("nom_material");
                vdireccion = jsonObject.optString("direccion");
                vfecha = jsonObject.optString("fecha");
                vhora = jsonObject.optString("hora");
                vpeso = jsonObject.optDouble("peso");
                vmonto = jsonObject.optDouble("precio");

                vtlf = jsonObject.optString("telefono");

                vcod_estado = jsonObject.optInt("cod_estado");

                lista.add(new historial_Recolector(vcod_recojo,vcod_estado,vmaterial,vdireccion,vfecha,vhora,vpeso,vmonto,vtlf));

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        ---------------------------------------------------------------------------


//        lista.add(new historial_Recolector(1,"PAPEL","AV. AAAA","01-01-2019","3:00 PM",10.00,100.00));
//        lista.add(new historial_Recolector(2,"VIDRIO","AV. BBBB","05-12-2019","4:00 PM",20.00,200.00));
//        lista.add(new historial_Recolector(3,"VIDRIO","AV. BBBB","05-12-2019","4:00 PM",20.00,200.00));
//        lista.add(new historial_Recolector(4,"VIDRIO","AV. BBBB","05-12-2019","4:00 PM",20.00,200.00));
//        lista.add(new historial_Recolector(5,"VIDRIO","AV. BBBB","05-12-2019","4:00 PM",20.00,200.00));
//        lista.add(new historial_Recolector(6,"VIDRIO","AV. BBBB","05-12-2019","4:00 PM",20.00,200.00));
//        lista.add(new historial_Recolector(7,"VIDRIO","AV. BBBB","05-12-2019","4:00 PM",20.00,200.00));
//        lista.add(new historial_Recolector(8,"VIDRIO","AV. BBBB","05-12-2019","4:00 PM",20.00,200.00));

        Adapter_Historial_Recolector miAdaptador = new Adapter_Historial_Recolector(getActivity(),lista);
        listaDatos.setAdapter(miAdaptador);

        return view;




    }





}
