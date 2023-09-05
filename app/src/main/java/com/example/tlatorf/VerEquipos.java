package com.example.tlatorf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VerEquipos extends AppCompatActivity {
    private Toolbar toolbar, toolbar2;
    //private ImageButton btnImgh1;
    private ImageButton btnImg1;
    private ArrayList<Miequipo> listaEquipos;
    private EditText nombre;
    private String valorRecibido, cad="";
    private String[][] equipossArr;
    private ListView lv;
    private static final String TAG = VerEquipos.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_equipos);
        btnImg1 = findViewById(R.id.btn_equipos_add);
        lv = findViewById(R.id.lv_show_equipos);
        Intent intent = getIntent();
        valorRecibido = intent.getStringExtra("clave_valor");
        getTeamsbyLeague();

    }
    public void postTeam(View view)
    {
        String valMat, valPass;
        Boolean contt = saveVal();
        if(contt){
            HashMap<String, String> params = new HashMap<>();
            params.put("EQUIPO_ID", cad);
            valMat = cad;
            params.put("NOMBRE", nombre.getText().toString());
            params.put("TORNEO_FK", valorRecibido);
            valPass = valorRecibido;
            if(valMat.equals("") || valPass.equals("")){
                Toast.makeText(this, "No deben haber parametros vacios", Toast.LENGTH_SHORT).show();
                cad = "";
                return;
            }
            //Toast.makeText(this, "trying", Toast.LENGTH_SHORT).show();
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String url = "http://34.174.8.8/ar/equiponuevo.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Manejar la respuesta del servidor
                            getTeamsbyLeague();
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
            cad = "";
            clearVal();
            Toast.makeText(this, "Equipo registrado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Comprueba los valores", Toast.LENGTH_SHORT).show();
        }
    //getTeamsbyLeague();
    }
    public void getTeamsbyLeague()
    {
        listaEquipos = new ArrayList<>();
        equipossArr = new String[30][2];
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://34.174.8.8/ar/teamsbyleague.php?TORNEO_FK="+valorRecibido;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    // Procesar la respuesta JSON
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);

                            // Obtener los datos de cada fila
                            String tid = jsonObject.getString("EQUIPO_ID");
                            String tnombre = jsonObject.getString("NOMBRE");
                            String tapertura = jsonObject.getString("TORNEO_FK");
                            Miequipo equipo = new Miequipo(tid, tnombre, tapertura);
                            listaEquipos.add(equipo);
                        }

                        // Crear el ArrayAdapter y establecerlo en el ListView
                        int contar = 0;
                        ArrayList<String> listaNombres = new ArrayList<>();
                        for (Miequipo equipo : listaEquipos) {
                            listaNombres.add(equipo.getNombre());
                            equipossArr[contar][0]=equipo.getNombre();
                            equipossArr[contar][1]=equipo.getEquipo_id();
                            contar++;
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_torneos, listaNombres);
                        lv.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },
                error -> Log.e(TAG, "Error: " + error.getMessage()));

        // Agregar la solicitud a la cola de solicitudes de Volley
        Volley.newRequestQueue(this).add(request);

    }
    public void clearVal(){
        nombre.setText("");
    }
    public Boolean saveVal(){
        String appert;
        char[] nmb, nmbid;
        nombre = findViewById(R.id.et_equipo_nombre);
        appert = nombre.getText().toString();
        try {
            nmb = appert.toCharArray();
            nmbid = valorRecibido.toCharArray();
            cad += (nmb[0]+""+nmb[1]+""+nmb[2]+""+nmb[3]+"");
            if(cad.equals("") || cad.equals(" ") || cad.equals("  ") || cad.equals("    ")){
                Toast.makeText(this, "Respeta la gramatica", Toast.LENGTH_SHORT).show();
                return false;
            }
            cad += (nmbid[0]+""+nmbid[1]+""+nmbid[2]+"");

        }catch (Exception e){
            return false;
        }
        return true;

    }
    public void startListener()
    {
        // nuevo torneo
        btnImg1.setOnClickListener(v ->
        {
            Intent intent = new Intent(this, TorneoAdmin.class);
            startActivity(intent);
            //overridePendingTransition(R.anim.slide_in_right, 0);
        });
    }
    public void printMsg(String texto){ Toast.makeText(this, texto, Toast.LENGTH_SHORT).show(); }
}