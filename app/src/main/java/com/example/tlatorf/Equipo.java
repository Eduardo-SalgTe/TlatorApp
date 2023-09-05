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

import java.util.ArrayList;
import java.util.List;

public class Equipo extends AppCompatActivity {
    private ListView lv1;
    private ListView lv2;
    private Toolbar toolbar, toolbar2;
    private ImageButton btnImgh1;
    private ImageButton btnImg1;
    private Button btnAction1;
    private Button btnAction2;
    private Button btnAction3;
    private Button btnAction4;
    //Adding elements in the List

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo);
        lv1 = findViewById(R.id.lvT1);
        lv2 = findViewById(R.id.lvT2);
        List<String> list=new ArrayList<>();
        list.add("Jorge");
        list.add("Maria");
        list.add("Miguel");
        list.add("Rosa");
        //list.add("Mango");
        //list.add("Apple");
        //list.add("Banana");
        //list.add("Grapes");
        List<String> listd=new ArrayList<>();
        listd.add("Informe mesa directiva");
        listd.add("Atento llamado");
        listd.add("Informacion importante");
        listd.add("Convocan junta");
        listd.add("Lesionanods");
        listd.add("Solicitud");
        listd.add("Respuesta de liga");
        listd.add("Grapes");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lv1.setAdapter(adapter);
        ArrayAdapter<String> adapterd = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listd);
        lv2.setAdapter(adapterd);
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

        startListener();
    }
    public void startListener()
    {
        btnImgh1.setOnClickListener(v ->
        {
            Intent intent = new Intent(this, PopupActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, 0);
        });
        btnAction1.setOnClickListener(v -> {toHome();
            finish();
        });
        btnAction2.setOnClickListener(v -> {toMatches();
            finish();
        });
        btnAction3.setOnClickListener(v -> {toStats();
            finish();
        });
        btnAction4.setOnClickListener(v -> {toLeagues();
            finish();
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