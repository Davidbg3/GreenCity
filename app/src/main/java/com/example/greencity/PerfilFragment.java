package com.example.greencity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.view.View;

import com.example.greencity.Intercambio.UpdateUsuarioRequest;
import com.example.greencity.Intercambio.UpdateUsuarioResponse;
import com.example.greencity.Servicio.UsuarioServicio;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PerfilFragment extends Fragment {

    private View rootView;

    private EditText txtNombres;
    private EditText txtApellidos;
    private EditText txtTelefono;
    private EditText txtCorreo;
    private EditText txtPassword;
    private Button btnActualizar;
    private Button btnEliminar;

    Global oGlobal;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_perfil, container, false);

        oGlobal = (Global)getActivity().getApplicationContext();

        txtNombres = rootView.findViewById(R.id.txtNombres);
        txtApellidos = rootView.findViewById(R.id.txtApellidos);
        txtTelefono = rootView.findViewById(R.id.txtTelefono);
        txtCorreo = rootView.findViewById(R.id.txtCorreo);
        txtPassword = rootView.findViewById(R.id.txtPassword);
        btnActualizar = rootView.findViewById(R.id.btnActualizar);

        final UpdateUsuarioRequest oReq = new UpdateUsuarioRequest();
        oReq.setCod_usuario(1);
        oReq.setNom_usuario(txtNombres.getText().toString());
        oReq.setPass_usuario(txtPassword.getText().toString());
        oReq.setNombres(txtNombres.getText().toString());
        oReq.setApellidos(txtApellidos.getText().toString());
        oReq.setTelefono(txtTelefono.getText().toString());
        oReq.setCorreo(txtCorreo.getText().toString());
        oReq.setLatitud("");
        oReq.setLongitud("");
        oReq.setCod_tipo_usuario(1);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActualizarUsuario(oReq);
            }
        });

        return rootView;
    }

    public void ActualizarUsuario(UpdateUsuarioRequest oReq){
        try{
            /*PENDIENTE:
                ALMACENAR URL API EN VARIABLE GLOBAL
            */
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(oGlobal.URL_API + oGlobal.METODO_ACTUALIZAR_USUARIO)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UsuarioServicio usuarioServicio =retrofit.create(UsuarioServicio.class);
            Call<UpdateUsuarioResponse> call = usuarioServicio.UpdateUsuario(oReq, oGlobal.getToken());
            call.enqueue(new Callback<UpdateUsuarioResponse>() {
                @Override
                public void onResponse(Call<UpdateUsuarioResponse> call, Response<UpdateUsuarioResponse> response) {
                    if (response.isSuccessful()){
                        UpdateUsuarioResponse oRes = response.body();
                        Log.i("Update Usuario","Usuario Actualizado. " + oRes.getMensaje());
                    }else{

                    }
                }

                @Override
                public void onFailure(Call<UpdateUsuarioResponse> call, Throwable t) {
                    Log.i("Update Usuario","Error al Actualizar.");
                }
            });
        }catch (Exception ex){

        }

    }

}
