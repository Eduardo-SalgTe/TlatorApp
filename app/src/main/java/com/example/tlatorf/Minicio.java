package com.example.tlatorf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Minicio extends AppCompatActivity {
    private Toolbar toolbar, toolbar2;
    private ImageButton btnImgh1;
    private ImageButton btnImg1;
    private Button btnAction1;
    private Button btnAction2;
    private Button btnAction3;
    private Button btnAction4;
    private UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minicio);
        toolbar = findViewById(R.id.per_toolb);
        toolbar2 = findViewById(R.id.per_toolb_a);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar2);
        insertVr();
        btnImgh1 = toolbar2.findViewById(R.id.header_imgbtn_a);
        btnImg1 = toolbar.findViewById(R.id.toolb_imgbtn_team);
        btnAction1 = toolbar.findViewById(R.id.toolb_btn_home);
        btnAction2 = toolbar.findViewById(R.id.toolb_btn_match);
        btnAction3 = toolbar.findViewById(R.id.toolb_btn_stats);
        btnAction4 = toolbar.findViewById(R.id.toolb_btn_league);

        startListener();
    }
    public void insertVr(){
        SQLiteOH admin = new SQLiteOH(this, "administracion", null, 1);
        SQLiteDatabase dbTP = admin.getWritableDatabase();

        //version.put("VERSION", "0");
        //dbTP.insert("APPVERSION", null, version);
        Cursor fila = dbTP.rawQuery("SELECT VERSION FROM APPVERSION", null);
        String txt = "error";
        if(fila.moveToFirst())
        {
            txt = fila.getString(0);
            Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
        }
        dbTP.close();
    }
    public void startListener()
    {
        btnImgh1.setOnClickListener(v ->
        {
            Intent intent = new Intent(this, PopupActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, 0);
        });
        btnImg1.setOnClickListener(view -> {toTeam();
        });
        //btnAction1.setOnClickListener(v -> {toHome(); });
        btnAction2.setOnClickListener(v -> {toMatches();
        });
        btnAction3.setOnClickListener(v -> {toStats();
        });
        btnAction4.setOnClickListener(v -> {toLeagues();
        });
    }
    public void printMsg(String texto){ Toast.makeText(this, texto, Toast.LENGTH_SHORT).show(); }
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
    public void toLeagues()
    {
        Intent aActivity = new Intent(this, Tinicio.class);
        startActivity(aActivity);finish();
    }
    public void toMenu()
    {
        Intent aActivity = new Intent(this, LogIn.class);
        startActivity(aActivity);
    }
}

