package com.example.tlatorf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class Einicio extends AppCompatActivity {
    private Toolbar toolbar, toolbar2;
    private ImageButton btnImgh1;
    private ImageButton btnImg1;
    private Button btnAction1;
    private Button btnAction2;
    private Button btnAction3;
    private Button btnAction4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einicio);
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
        btnImg1.setOnClickListener(view -> {toTeam();
        });
        btnAction1.setOnClickListener(v -> {toHome();
        });
        btnAction2.setOnClickListener(v -> {toMatches();
        });
        //btnAction3.setOnClickListener(v -> {toStats(); });
        btnAction4.setOnClickListener(v -> {toLeagues();
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