package com.example.greencity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements OnMapReadyCallback {

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
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng EstadioNacional = new LatLng(-12.0672516, -77.0337485);
        mMap.addMarker(new MarkerOptions().position(EstadioNacional).title("Estadio Nacional").draggable(true).snippet("Estadio Nacional de Lima, Peru: Recientemente Remodelado").icon(BitmapDescriptorFactory.fromResource(R.drawable.mexico)));//Mostrando con Icono - draggable(true)=para poder arrastrarlo

        LatLng BibliotecaNacional = new LatLng(-12.0874728, -77.0047876);
        mMap.addMarker(new MarkerOptions().position(BibliotecaNacional).title("Biblioteca Nacional").snippet("Biblioteca Nacional Lima, Peru: Recienemente Remodelado").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));//mostrando con default y color personalizado

//----------------------------------------------------------------------------------------------------------------
//        Otra forma de hacer marker
        LatLng prueba=new LatLng(-12.0810941,-77.0010673);
        markerPrueba=googleMap.addMarker(new MarkerOptions().position(prueba).title("Videna: otra forma"));


        LatLng palacioJusticia=new LatLng(-12.05766,-77.0349687);
        markerDrag=googleMap.addMarker(new MarkerOptions().position(palacioJusticia).title("Palacio Justicia: otra forma").draggable(true));

//----------------------------------------------------------------------------------------------------------------
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(EstadioNacional));//Donde se posicionara la camara al arrancar
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(EstadioNacional,12));//Aplicando Zoom

//----------------------------------------------------------------------------------------------------------------

    }
}
