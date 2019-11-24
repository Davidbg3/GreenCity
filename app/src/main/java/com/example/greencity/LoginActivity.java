package com.example.greencity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.greencity.Intercambio.LoginRequest;
import com.example.greencity.Intercambio.LoginResponse;
import com.example.greencity.Servicio.UsuarioServicio;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnIniciarSesion;
    Global oGlobal= new Global();
    private Boolean resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = (EditText)findViewById(R.id.correo);
        txtPassword = (EditText)findViewById(R.id.contrasena);
        btnIniciarSesion = (Button)findViewById(R.id.iniciarSesion);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginApi(txtEmail.getText().toString(), txtPassword.getText().toString())){
                    Intent intent = new Intent(LoginActivity.this,SesionRActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this,"Email o Password incorrecto.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public Boolean LoginApi(String email, String password){
        resultado = false;
        try {
            final LoginRequest oReq = new LoginRequest(email, password);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(oGlobal.URL_API + oGlobal.METODO_LOGIN)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UsuarioServicio usuarioServicio = retrofit.create(UsuarioServicio.class);
            Call<LoginResponse> call = usuarioServicio.LoginUsuario(oReq);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()){
                        LoginResponse oRes = response.body();
                        if (!oRes.getSuccess().getToken().equals("")){
                            oGlobal.setIdUsuarioGlobal(Integer.parseInt(oRes.getSuccess().getCod_usuario()));
                            oGlobal.setToken(oRes.getSuccess().getToken());
                            resultado = true;
                        }else{
                            Log.i("Login","Inicio de sesión fallida, no se encontró token");
                        }
                    }else{
                        Log.i("Login","Inicio de sesión fallida");
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.i("Login","Inicio de sesión fallida");
                }
            });

        }catch (Exception e){

        }
        return resultado;
    }

    public void registro (View view){
        Intent registro = new Intent(this,RegisterActivity.class);
        startActivity(registro);
    }


    public void sesion (View view){
        Intent sesion = new Intent(this,SesionRActivity.class);
        startActivity(sesion);

    }

}
