package com.example.greencity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class SesionActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private FrameLayout frameLayout;

    private RecojoFragment recojoFragment;
    private AlertaFragment alertaFragment;
    private EstadisticaFragment estadisticaFragment;
    private PerfilFragment perfilFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);

        navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        frameLayout = (FrameLayout)findViewById(R.id.frame);

        recojoFragment = new RecojoFragment();
        alertaFragment = new AlertaFragment();
        estadisticaFragment = new EstadisticaFragment();
        perfilFragment = new PerfilFragment();
        setFragment(recojoFragment);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.principal:
                        setFragment(recojoFragment);
                        return true;
                    case R.id.alertas:
                        setFragment(alertaFragment);
                        return true;
                    case R.id.estadisticas:
                        setFragment(estadisticaFragment);
                        return true;
                    case R.id.perfil:
                        setFragment(perfilFragment);
                        return true;
                    default:
                        return false;
                }


            }
        });

    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

}
