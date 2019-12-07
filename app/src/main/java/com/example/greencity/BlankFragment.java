package com.example.greencity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.greencity.Intercambio.Recojo;
import com.example.greencity.Servicio.RocojosServicio;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    SupportMapFragment mapFragment;

    private GoogleMap mMap;
    private Marker markerPrueba, markerDrag ;





    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa3);
        if (mapFragment==null){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.mapa3,mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


//        googleMap.setMyLocationEnabled(true);


        cargaMapa(googleMap);




    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        Log.d("#####", String.valueOf(marker));
        Log.d("#####", String.valueOf(marker.getTag()));
        RecojoConfirmacion vconfirmacion = new RecojoConfirmacion();
        vconfirmacion.show(getActivity().getSupportFragmentManager(), String.valueOf(marker.getTag()));








//        if (marker.equals(markerPrueba)){
//            String meLat,meLong;
//            meLat=Double.toString(marker.getPosition().latitude);
//            meLong=Double.toString(marker.getPosition().longitude);
//            Toast.makeText(this,"aaaaa", Toast.LENGTH_SHORT).show();
//
//        }

        return false;
    }


    public void cargaMapa(GoogleMap googleMap){

        mMap = googleMap;


        LatLng vubicacion=null;

//        ##########################################################################################
//        ##########################################################################################

        String sql = "http://greencityapp.000webhostapp.com/recojos";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url = null;
        HttpURLConnection conn;

        try {
            url = new URL(sql);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputline;
            StringBuffer response = new StringBuffer();
            String json = "";

            while ((inputline = in.readLine()) != null){
                response.append(inputline);
            }

            json= response.toString();

            JSONArray jsonArray = null;
            jsonArray = new JSONArray(json);



            Double vlongitud, vlatitud;
            String vtipoMaterial, vmaterial;
            Integer vcod_tipoMaterial, vicon, vcod_recojo;

            for (int i = 0 ;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.d("SA",jsonObject.optString("cod_recojo"));

                vlatitud = jsonObject.optDouble("latitud");
                vlongitud = jsonObject.optDouble("longitud");
                vtipoMaterial = jsonObject.optString("nom_tipo_material");
                vcod_tipoMaterial = jsonObject.optInt("cod_tipo_material");
                vcod_recojo = jsonObject.optInt("cod_recojo");
                vmaterial = jsonObject.optString("nom_material");
                vicon = 0;

//                LatLng EstadioNacional = new LatLng(vlatitud, vlongitud);

                if (vcod_tipoMaterial==1){
                    vicon = R.drawable.papel;
                }else if (vcod_tipoMaterial==2){
                    vicon = R.drawable.vidrio;
                }else if (vcod_tipoMaterial==3){
                    vicon = R.drawable.envaseligero;
                }else if (vcod_tipoMaterial==4){
                    vicon = R.drawable.lata;
                }else if (vcod_tipoMaterial==5){
                    vicon = R.drawable.plastico;
                }


                vubicacion=new LatLng(vlatitud,vlongitud);
                markerDrag=googleMap.addMarker(new MarkerOptions().position(vubicacion).title(vtipoMaterial).draggable(true).icon(BitmapDescriptorFactory.fromResource(vicon)));
                markerDrag.setTag(vcod_recojo);

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        ##########################################################################################
//        ##########################################################################################


//----------------------------------------------------------------------------------------------------------------
        // Add a marker in Sydney and move the camera
//        LatLng EstadioNacional = new LatLng(-12.0672516, -77.0337485);
//        mMap.addMarker(new MarkerOptions().position(EstadioNacional).title("Estadio Nacional").draggable(true).snippet("Estadio Nacional de Lima, Peru: Recientemente Remodelado").icon(BitmapDescriptorFactory.fromResource(R.drawable.mexico)));//Mostrando con Icono - draggable(true)=para poder arrastrarlo
//
//        LatLng BibliotecaNacional = new LatLng(-12.0874728, -77.0047876);
//        mMap.addMarker(new MarkerOptions().position(BibliotecaNacional).title("Biblioteca Nacional").snippet("Biblioteca Nacional Lima, Peru: Recienemente Remodelado").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));//mostrando con default y color personalizado

//----------------------------------------------------------------------------------------------------------------
//        Otra forma de hacer marker
        LatLng prueba=new LatLng(-12.0810941,-77.0010673);
        markerPrueba=googleMap.addMarker(new MarkerOptions().position(prueba).title("Videna: otra forma"));


        LatLng palacioJusticia=new LatLng(-12.05766,-77.0349687);
        markerDrag=googleMap.addMarker(new MarkerOptions().position(palacioJusticia).title("Palacio Justicia: otra forma").draggable(true));

//----------------------------------------------------------------------------------------------------------------
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(EstadioNacional));//Donde se posicionara la camara al arrancar
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(EstadioNacional,12));//Aplicando Zoom

//----------------------------------------------------------------------------------------------------------------
//        ##########################################################################################
//        ##########################################################################################
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vubicacion,12));//Aplicando Zoom
        googleMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);

    }





}
