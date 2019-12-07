package com.example.greencity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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


    //    private AlertaFragment alertaFragment;
    private HistorialRecolector historialRecolector;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        cod_recojo =  this.getTag();





        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Desea aceptar la solicitud ?")
                .setMessage(datosSolicitudRecojo(cod_recojo))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Eligio:",cod_recojo);


//                        -------------------------------------------------------------------------------------

                        try {

                            URL url = new URL("https://greencityapp.000webhostapp.com/recojos/aceptar");
                            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                            String urlParameters = "cod_recojo=" + cod_recojo + "&cod_usuario_recolector=" + String.valueOf(Global.IdUsuario) + "&cod_estado=2";
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



                            historialRecolector = new HistorialRecolector();
                            FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frame,historialRecolector);
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


    private String datosSolicitudRecojo(String vcod_recojo){

        String msg_txt="";

//        ##########################################################################################
//        ##########################################################################################

        String sql = "http://greencityapp.000webhostapp.com/recojos/" + vcod_recojo;
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


            Double vpeso, vprecio, vmonto;
            String vtipoMaterial, vmaterial, vfecha, vhora;


            for (int i = 0 ;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.d("SA",jsonObject.optString("cod_recojo"));

                vtipoMaterial = jsonObject.optString("nom_tipo_material");
                vmaterial = jsonObject.optString("nom_material");
                vfecha = jsonObject.optString("fecha");
                vhora = jsonObject.optString("hora");
                vpeso = jsonObject.optDouble("peso");
                vprecio = jsonObject.optDouble("precio");
                vmonto = jsonObject.optDouble("peso") * jsonObject.optDouble("precio");

                msg_txt = "\nMaterial a Recoger: " + vmaterial
                        + "\nFecha: " + vhora
                        + "\nPeso: " + vpeso.toString() + "KG.     Monto: S/" + vmonto.toString();

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


        return msg_txt;


    }


}
