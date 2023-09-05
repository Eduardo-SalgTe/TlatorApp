package com.example.tlatorf;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class TorneoAdmin extends AppCompatActivity {
    private String id="";
    private char[] nmb;
    private EditText nombre;
    private char[] apt;
    private EditText apertura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torneo_admin);


    }
    public void postLeague(View view)
    {
        String valMat, valPass;
        Boolean contt = saveVal();
        if(contt){
            HashMap<String, String> params = new HashMap<>();
            params.put("TORNEO_ID", id);
            valMat = id;
            params.put("NOMBRE", nombre.getText().toString());
            params.put("APERTURA", apertura.getText().toString());
            valPass = apertura.getText().toString();
            if(valMat.equals("") || valPass.equals("")){
                Toast.makeText(this, "No deben haber parametros vacios", Toast.LENGTH_SHORT).show();
                id = "";
                return;
            }
            //Toast.makeText(this, "trying", Toast.LENGTH_SHORT).show();
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String url = "http://34.174.8.8/ar/torneonuevo.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Manejar la respuesta del servidor
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Manejar el error de la solicitud
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    return params;
                }
            };

            requestQueue.add(stringRequest);
            id = "";
            clearVal();
            Toast.makeText(this, "Torneo registrado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Comprueba los valores", Toast.LENGTH_SHORT).show();
        }

    }

    public Boolean saveVal(){
        String nomb, appert;
        nombre = findViewById(R.id.et_torneo_name);
        apertura = findViewById(R.id.et_torneo_apert);
        nomb = nombre.getText().toString();
        appert = apertura.getText().toString();

        try {
            nmb = nomb.toCharArray();
            apt = appert.toCharArray();
            id += (nmb[0]+""+nmb[1]+""+nmb[2]+""+nmb[3]+"");
            if(id.equals("") || id.equals(" ") || id.equals("  ") || id.equals("    ")){
                Toast.makeText(this, "Respeta la gramatica", Toast.LENGTH_SHORT).show();
                return false;
            }
            id += (apt[0]+""+apt[1]+""+apt[2]+""+apt[3]+"");

        }catch (Exception e){
           return false;
        }
        return true;

    }
    public void clearVal(){
        nombre.setText("");
        apertura.setText("");
    }
}