package com.example.greencity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RecojoConfirmacion extends AppCompatDialogFragment {
    String cod_recojo;

    private AlertaFragment alertaFragment;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        cod_recojo =  this.getTag();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Solicitud de Recojo")
                .setMessage("Desea aceptar la solicitud ?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Eligio:",cod_recojo);


//                        -------------------------------------------------------------------------------------

                        try {
                            Log.d("Eligio:","XXXXX");
                            URL url = new URL("https://greencityapp.000webhostapp.com/recojos/aceptar");
                            Log.d("Eligio:","CCCCCCC");
                            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                            Log.d("Eligio:","BBBBBB");
                            String urlParameters = "cod_recojo=" + cod_recojo + "&cod_usuario_recolector=" + String.valueOf(Global.IdUsuario) + "&cod_estado=2";
                            Log.d("Eligio:","ZZZZZZ");
                            Log.d("Parametros: ",urlParameters);
                            conn.setRequestMethod("POST");
                            conn.setRequestProperty("USER-AGENT","Mozilla/5.0");
                            conn.setRequestProperty("ACCEPT-LANGUAGE","en-US,en;0.5");

//                            conn.setDoOutput(true);
                            DataOutputStream dStream = new DataOutputStream(conn.getOutputStream());

                            dStream.writeBytes(urlParameters);
                            dStream.flush();
                            dStream.close();

                            int responseCode = conn.getResponseCode();
                            String output = "Request URL " + url;
                            output += System.getProperty("line.separator") + "Request Parameters " + urlParameters;
                            output += System.getProperty("line.separator") + "Response Code " + responseCode;

                            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                            String line= "";
                            StringBuilder responseOutput = new StringBuilder();

                            while ((line = br.readLine()) != null){
                                responseOutput.append(line);
                            }
                            br.close();

                            output += System.getProperty("line.separator") + responseOutput.toString();


                            Log.d("respuesta: ",output);


                            alertaFragment = new AlertaFragment();
                            FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frame,alertaFragment);
                            fragmentTransaction.commit();




                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


//                        -------------------------------------------------------------------------------------




                    }
                })
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("SA", "NO");
                    }
                });
        return builder.create();
    }
}
