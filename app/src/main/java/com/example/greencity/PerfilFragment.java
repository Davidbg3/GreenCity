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
import android.widget.Toast;

import com.example.greencity.Intercambio.DeleteUsuarioResponse;
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

        txtNombres = rootView.findViewById(R.id.txtNombres);
        txtApellidos = rootView.findViewById(R.id.txtApellidos);
        txtTelefono = rootView.findViewById(R.id.txtTelefono);
        txtCorreo = rootView.findViewById(R.id.txtCorreo);
        //txtPassword = rootView.findViewById(R.id.txtPassword);
        btnActualizar = rootView.findViewById(R.id.btnActualizar);
        btnEliminar = rootView.findViewById(R.id.btnEliminar);

        /* Cargar datos almacenados en la clase Global */
        txtNombres.setText(Global.NombresUsuario);
        txtApellidos.setText(Global.ApellidosUsuario);
        txtTelefono.setText(Global.TelefonoUsuario);
        txtCorreo.setText(Global.CorreoUsuario);
        //txtPassword.setText("");

        final UpdateUsuarioRequest oReq = new UpdateUsuarioRequest();

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oReq.setCod_usuario(Global.IdUsuario);
                oReq.setNom_usuario(txtNombres.getText().toString());
                //oReq.setPass_usuario(txtPassword.getText().toString());
                oReq.setPass_usuario(Global.PasswordUsuario);
                oReq.setNombres(txtNombres.getText().toString());
                oReq.setApellidos(txtApellidos.getText().toString());
                oReq.setTelefono(txtTelefono.getText().toString());
                oReq.setCorreo(txtCorreo.getText().toString());
                oReq.setLatitud(Global.latitudUsuario);
                oReq.setLongitud(Global.longitudUsuario);
                oReq.setCod_tipo_usuario(Global.IdTipoUsuario);
                ActualizarUsuario(oReq);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                EliminarUsuario();
           }
        });

        return rootView;
    }

    public void ActualizarUsuario(UpdateUsuarioRequest oReq){
        try{
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Global.URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UsuarioServicio usuarioServicio =retrofit.create(UsuarioServicio.class);
            Call<UpdateUsuarioResponse> call = usuarioServicio.UpdateUsuario(oReq, "Reader " + Global.Token);
            call.enqueue(new Callback<UpdateUsuarioResponse>() {
                @Override
                public void onResponse(Call<UpdateUsuarioResponse> call, Response<UpdateUsuarioResponse> response) {
                    if (response.isSuccessful()){
                        UpdateUsuarioResponse oRes = response.body();
                        Log.i("Update Usuario","Usuario Actualizado. " + oRes.getMensaje());
                        Toast.makeText(getActivity(),"Datos Actualizados.", Toast.LENGTH_SHORT).show();
                    }else{
                        Log.i("Update Usuario","Error response" + response.message());
                        Toast.makeText(getActivity(),"Error al Actualizar datos.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UpdateUsuarioResponse> call, Throwable t) {
                    Log.i("Update Usuario","Error al Actualizar.");
                }
            });
        }catch (Exception ex){
            Log.i("Update Usuario","Error: " + ex.getMessage());
            Toast.makeText(getActivity(),"Error : " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void EliminarUsuario(){
        try{
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Global.URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UsuarioServicio usuarioServicio =retrofit.create(UsuarioServicio.class);
            Call<DeleteUsuarioResponse> call = usuarioServicio.DeleteUsuario(Global.IdUsuario, "Reader " + Global.Token);
            call.enqueue(new Callback<DeleteUsuarioResponse>() {
                @Override
                public void onResponse(Call<DeleteUsuarioResponse> call, Response<DeleteUsuarioResponse> response) {
                    if (response.isSuccessful()){
                        DeleteUsuarioResponse oRes = response.body();
                        Log.i("Delete Usuario", oRes.getMensaje());
                        Toast.makeText(getActivity(),"Usuario Eliminado.", Toast.LENGTH_SHORT).show();
                    }else{
                        Log.i("Delete Usuario","Error response: " + response.message());
                        Toast.makeText(getActivity(),"Error al eliminar.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DeleteUsuarioResponse> call, Throwable t) {
                    Log.i("Delete Usuario","Error al Eliminar.");
                }
            });
        }catch (Exception ex){
            Log.i("Delete Usuario","Error: " + ex.getMessage());
            Toast.makeText(getActivity(),"Error : " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
