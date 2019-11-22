package com.example.greencity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void registro (View view){
        Intent registro = new Intent(this,RegisterActivity.class);
        startActivity(registro);
    }


    public void sesion (View view){
        Intent sesion = new Intent(this,SesionActivity.class);
        startActivity(sesion);

    }

}
