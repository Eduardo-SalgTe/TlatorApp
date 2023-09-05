package com.example.tlatorf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SingUpActivity extends AppCompatActivity {

    private EditText matricula;
    private EditText nombre;
    private EditText correo;
    private String tipo;
    private EditText contrasena;
    private String equipo_fk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        Toolbar toolbar = findViewById(R.id.person);
        setSupportActionBar(toolbar);

    }

    public void postUser(View view)
    {
        String valMat, valPass = "";
        saveVal();
        HashMap<String, String> params = new HashMap<>();
        params.put("MATRICULA", matricula.getText().toString());
        valMat = matricula.getText().toString();
        params.put("NOMBRE", nombre.getText().toString());
        params.put("CORREO", correo.getText().toString());
        params.put("TIPO", tipo);
        params.put("CONTRASENA", contrasena.getText().toString());
        valPass = contrasena.getText().toString();
        params.put("EQUIPO_FK", equipo_fk);
        if(valMat.equals("") || valPass.equals("")){
            Toast.makeText(this, "No deben haber parametros vacios", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://34.174.8.8/ar/usuarionuevo.php";

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
        clearVal();
        Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
    }
    public void clearVal(){
        matricula.setText("");
        nombre.setText("");
        correo.setText("");
        contrasena.setText("");
    }
    public void saveVal(){
        matricula = findViewById(R.id.etMatricula);
        nombre = findViewById(R.id.etNombre);
        correo = findViewById(R.id.etCorreo);
        tipo = "0";
        contrasena = findViewById(R.id.etContrasena);
        equipo_fk = "NULL";
    }
    public void toPicture(View view)
    {
        Intent aActivity = new Intent(this, Gif.class);
        startActivity(aActivity);
    }
}