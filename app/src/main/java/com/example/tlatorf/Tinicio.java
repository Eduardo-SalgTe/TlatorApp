package com.example.tlatorf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.util.Log;
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

public class Tinicio extends AppCompatActivity {
    private Toolbar toolbar, toolbar2;
    private ImageButton btnImgh1;
    private ImageButton btnImg1;
    private Button btnAction1;
    private Button btnAction2;
    private Button btnAction3;
    private Button btnAction4;
    private ListView lv;
    private String codigo;
    private String [] yyear = {"apertura isc - 2022.2", "apertura iin - 2022.`", "apertura MEC - 2023.2"};
    private String [] mmonth = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
    private String[][] torneosArr;
    private  int tamLg = 0;
    private ArrayList<Director> listaDirectores;
    private static final String TAG = Tinicio.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinicio);
        toolbar = findViewById(R.id.per_toolb);
        toolbar2 = findViewById(R.id.per_toolb_a);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar2);
        btnImgh1 = toolbar2.findViewById(R.id.header_imgbtn_a);
        btnImg1 = toolbar.findViewById(R.id.toolb_imgbtn_team);
        btnAction1 = toolbar.findViewById(R.id.toolb_btn_home);
        btnAction2 = toolbar.findViewById(R.id.toolb_btn_match);
        btnAction3 = toolbar.findViewById(R.id.toolb_btn_stats);
        btnAction4 = toolbar.findViewById(R.id.toolb_btn_league);
        lv = findViewById(R.id.lv_show_equipos);
        getSize();
        //LlenarList();
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_torneos, yyear);
        //lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //tvCrp.setText(lv1.getItemAtPosition(i) + ", " + mat[1][i]);

                codigo = torneosArr[i][1];

                printMsg(codigo);
                toCreate();
            }
        });
        startListener();
    }
    public void getSize()
    {
        final int[] tamLg = {0};
        final int[] numeral = new int[1];
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://34.174.8.8/ar/countleagues.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Analizar la respuesta JSON
                            JSONObject jsonObject = new JSONObject(response);

                            // Obtener los valores del objeto JSON
                            String tamano = jsonObject.getString("TAM");
                            String tamfin = "";
                            for(int x = 0; x < tamano.length(); x++){
                                if (tamano.charAt(x)!=' '){
                                    tamfin += tamano.charAt(x);
                                }
                            }
                            //tamLg[0] = Integer.parseInt(tamano);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            printMsg("error al consultar tamano");
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
        LlenarList();
    }
    public void LlenarList() {
        listaDirectores = new ArrayList<>();
        torneosArr = new String[30][2];
        // URL del archivo PHP
        String url = "http://34.174.8.8/ar/showleagues.php";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    // Procesar la respuesta JSON
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);

                            // Obtener los datos de cada fila
                            String tid = jsonObject.getString("TORNEO_ID");
                            String tnombre = jsonObject.getString("NOMBRE");
                            String tapertura = jsonObject.getString("APERTURA");
                            Director director = new Director(tid, tnombre, tapertura);
                            listaDirectores.add(director);
                        }

                        // Crear el ArrayAdapter y establecerlo en el ListView
                        int contar = 0;
                        ArrayList<String> listaNombres = new ArrayList<>();
                        for (Director director : listaDirectores) {
                            listaNombres.add(director.getNombre());
                            torneosArr[contar][0]=director.getNombre();
                            torneosArr[contar][1]=director.getTorn_id();
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

    public void startListener()
    {
        // nuevo torneo
        btnImgh1.setOnClickListener(v ->
        {
            Intent intent = new Intent(this, TorneoAdmin.class);
            startActivity(intent);
            //overridePendingTransition(R.anim.slide_in_right, 0);
        });
        btnImg1.setOnClickListener(view -> {toTeam();
        });
        btnAction1.setOnClickListener(v -> {toHome();
        });
        btnAction2.setOnClickListener(v -> {toMatches();
        });
        btnAction3.setOnClickListener(v -> {toStats();
        });
    }
    public void toHome()
    {
        Intent aActivity = new Intent(this, Minicio.class);
        startActivity(aActivity);finish();
    }
    public void toTeam()
    {
        Intent aActivity = new Intent(this, Equipo.class);
        startActivity(aActivity);finish();
    }
    public void toMatches()
    {
        Intent aActivity = new Intent(this, Pinicio.class);
        startActivity(aActivity);finish();
    }
    public void toStats()
    {
        Intent aActivity = new Intent(this, Einicio.class);
        startActivity(aActivity);finish();
    }
    public void toMenu()
    {
        Intent aActivity = new Intent(this, LogIn.class);
        startActivity(aActivity);
    }
    public void printMsg(String texto){ Toast.makeText(this, texto, Toast.LENGTH_SHORT).show(); }
    public void toCreate()
    {
        Intent intent = new Intent(this, VerEquipos.class);
        intent.putExtra("clave_valor", codigo);

        startActivity(intent);
        //finish();
    }
}