package com.example.greencity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
    }

    public void registro (View view){
        Intent registro = new Intent(this,RegisterActivity.class);
        startActivity(registro);
    }

    public void login (View view){
//        Intent intent = new Intent(getApplicationContext(), MapsActivity2.class);
//        startActivity(intent);

        Intent login = new Intent(this,LoginActivity.class);
        startActivity(login);

    }


}
