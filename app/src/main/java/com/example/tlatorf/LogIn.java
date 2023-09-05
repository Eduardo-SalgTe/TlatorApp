package com.example.tlatorf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.IOException;

public class LogIn extends AppCompatActivity {
    private Toolbar toolbar, toolbar2;
    private EditText matricula;
    private EditText contrasena;
    private TextView mostrar;
    private Switch sw;
    private File folder, keys;
    private boolean swit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sw = findViewById(R.id.loginSW);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //printMsg("activo");
                    swit = true;
                } else {
                    //printMsg("No activo");
                    swit = false;
                }
            }
        });
        valAndroid();
        //getViews();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_button) {
            Toast.makeText(this, "Boton seleccionado", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_menu) {
            // Crear el PopupMenu
            PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.action_menu));

            // Inflar el menu del submenu
            popupMenu.getMenuInflater().inflate(R.menu.sub_menu, popupMenu.getMenu());

            // Asignar acciones a los elementos del submenu
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    int submenuItemId = item.getItemId();
                    // ...
                    return true;
                }
            });

            // Mostrar el PopupMenu
            popupMenu.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void postUser(View view)
    {
        saveVal();
        //params.put("MATRICULA", matricula.getText().toString());
        //params.put("CONTRASENA", contrasena.getText().toString());
        String mat = matricula.getText().toString();
        String pass = contrasena.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://34.174.8.8/ar/login.php?MATRICULA="+mat+"&CONTRASENA="+pass;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Analizar la respuesta JSON
                            JSONObject jsonObject = new JSONObject(response);

                            // Obtener valores del objeto JSON
                            String matricula = jsonObject.getString("MATRICULA");
                            String nombre = jsonObject.getString("NOMBRE");
                            String correo = jsonObject.getString("CORREO");
                            String tipo = jsonObject.getString("TIPO");
                            String contrasena = jsonObject.getString("CONTRASENA");
                            String equipo_fk = jsonObject.getString("EQUIPO_FK");
                            // ...
                            boolean op = shwTx(matricula,nombre,correo,tipo,contrasena,equipo_fk);
                            if(op){
                                if(swit){
                                    wrCredencial();
                                }
                                toMain(view);
                            }else{

                                printMsg("MATRICULA Y/O CONTRASENA INCORRECTA");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            mostrar.setText("MATRICULA Y/O CONTRASENA INCORRECTOS");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error de la solicitud
                    }
                });

        requestQueue.add(stringRequest);
        //clearVal();

    }
    public void wrCredencials(File file, String mat, String pass) {
        String archivo = "archivo.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.append(mat);
            writer.newLine();
            writer.append(pass);
            writer.newLine();
            writer.close();
            System.out.println("Texto agregado al archivo: " + archivo);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al escribir en el archivo: " + archivo);
        }
    }
    public void wrCredencial(){
        String dir = "credencial" + ".txt";

        //folder = new File(Environment.getExternalStorageDirectory().toString(), "PAGOS/");
        folder = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS + "/appt/");
        if (!folder.exists())
        {
            folder.mkdirs();
        }
        keys = new File(folder, "key.txt");

        if (!keys.exists()) {
            try {
                keys.createNewFile();
                wrCredencials(keys, "201823124","12345");
                System.out.println("Se ha creado el archivo: " + "keys.txt");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al crear el archivo: " + "keys.txt");
            }
        } else {
            System.out.println("El archivo " + "keys.txt" + " ya existe.");
        }
    }
    public boolean shwTx(String Matricula, String nombre, String correo, String tipo, String contrasena, String equipo_fk){
        String cadF = Matricula+nombre+correo+tipo+contrasena+equipo_fk;
        mostrar.setText(cadF);
        if(cadF.length() > 10){
            return true;
        }else{
            return false;
        }

    }
    public void saveVal(){
        matricula = findViewById(R.id.loginETMatri);
        contrasena = findViewById(R.id.loginETContr);
        mostrar = findViewById(R.id.loginTV);
    }
    public void verPermt()
    {
        int rq = 200;
        int perW = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int perR = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        List<String> permissionsToRequest = new ArrayList<>();

        if (perW != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (perR != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!permissionsToRequest.isEmpty()) {
            String[] permissionsArray = permissionsToRequest.toArray(new String[0]);
            requestPermissions(permissionsArray, rq);
        }

    }
    public void valAndroid()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Acceso a almacenamiento externo utilizando Scoped Storage
            // Utiliza metodos > API de almacenamiento en Android 11 y posteriores
            Toast.makeText(this, "Android > 11", Toast.LENGTH_SHORT).show();
            verPermt();
        } else {
            // Acceso a almacenamiento externo utilizando el modelo tradicional
            Toast.makeText(this, "Android <= 11", Toast.LENGTH_SHORT).show();

            verPermt();
        }
    }
    public void toMain(View view)
    {
        Intent aActivity = new Intent(this, Minicio.class);
        startActivity(aActivity);
        finish();
    }
    public void printMsg(String texto){ Toast.makeText(this, texto, Toast.LENGTH_SHORT).show(); }
}