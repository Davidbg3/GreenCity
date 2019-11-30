package com.example.greencity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.greencity.Intercambio.GetUsuarioResponse;
import com.example.greencity.Intercambio.LoginRequest;
import com.example.greencity.Intercambio.LoginResponse;
import com.example.greencity.Servicio.UsuarioServicio;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnIniciarSesion;
    //Global oGlobal= new Global();
    private Boolean resultado;
    List<GetUsuarioResponse> lstGetUsuarioResponse;
    private String passUsuario;





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
                passUsuario = txtPassword.getText().toString();
                resultado = LoginApi(txtEmail.getText().toString(), txtPassword.getText().toString());
            }
        });
    }

    public Boolean LoginApi(String email, String password){
        resultado = false;
        try {
            final LoginRequest oReq = new LoginRequest(email, password);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Global.URL_API)
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
                            Global.IdUsuario = Integer.parseInt(oRes.getSuccess().getCod_usuario());
                            Global.Token = oRes.getSuccess().getToken();
                            Log.i("Login","Inicio de sesión exitoso");
//<<<<<<< Updated upstream

                            Retrofit retrofit1 = new Retrofit.Builder()
                                    .baseUrl(Global.URL_API)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
                            UsuarioServicio usuarioServicio1 = retrofit1.create(UsuarioServicio.class);
                            Call<List<GetUsuarioResponse>> call1 = usuarioServicio1.GetUsuario(Global.IdUsuario, "Bearer " + Global.Token);
                            call1.enqueue(new Callback<List<GetUsuarioResponse>>() {
                                @Override
                                public void onResponse(Call<List<GetUsuarioResponse>> call, Response<List<GetUsuarioResponse>> response) {
                                    if (response.isSuccessful()){
                                        //GetUsuarioResponse oRes = response.body();
                                        List<GetUsuarioResponse> lstRes = response.body();

                                        for (GetUsuarioResponse oRes : lstRes){
                                            Global.NombresUsuario = oRes.getNom_usuario();
                                            Global.PasswordUsuario = passUsuario;
                                            Global.ApellidosUsuario = oRes.getApellidos();
                                            Global.TelefonoUsuario = oRes.getTelefono();
                                            Global.CorreoUsuario = oRes.getCorreo();
                                            Global.latitudUsuario = oRes.getLatitud();
                                            Global.longitudUsuario = oRes.getLongitud();
                                            Global.IdTipoUsuario = oRes.getCod_tipo_usuario();
                                        }

                                        resultado = true;
                                        /* 1 : Vecino , 2 : Recolector */
                                        if (Global.IdTipoUsuario == 2){
                                            Toast.makeText(LoginActivity.this,"Inicio de sesión Recolector exitoso.",Toast.LENGTH_SHORT).show();
                                            Intent intent2 = new Intent(LoginActivity.this, SesionRActivity.class);
                                            startActivity(intent2);
                                        }else{
                                            Toast.makeText(LoginActivity.this,"Inicio de sesión Vecino exitoso.",Toast.LENGTH_SHORT).show();
                                            Intent intent1 = new Intent(LoginActivity.this, SesionActivity.class);
                                            startActivity(intent1);
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<GetUsuarioResponse>> call, Throwable t) {
                                    Log.i("Login","Error al obtener datos de usuario.");
                                }
                            });

//=======
                            Toast.makeText(LoginActivity.this,"Inicio de sesión exitoso.",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this,SesionRActivity.class);
                            startActivity(intent);
                            resultado = true;
//>>>>>>> Stashed changes
                        }else{
                            Log.i("Login","Inicio de sesión fallida, no se encontró token");
                            Toast.makeText(LoginActivity.this,"Inicio de sesión fallida, no se encontró token",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Log.i("Login","Inicio de sesión fallida");
                        Toast.makeText(LoginActivity.this,"Inicio de sesión fallida.",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.i("Login","Inicio de sesión fallida");
                    Toast.makeText(LoginActivity.this,"Inicio de sesión fallida",Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Log.i("Login","Error: " + e.getMessage());
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
