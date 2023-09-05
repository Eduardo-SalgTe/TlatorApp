package com.example.tlatorf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.ImageButton;
import android.widget.Button;
public class Personal extends AppCompatActivity{
    private ImageButton imgBh1;
    private ImageButton imgB1;

    private Button Btn1;
    private Button Btn2;
    private Button Btn3;
    private Button Btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personalizado);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //(toolbar);
    }
    public void getViews()
    {
        imgBh1 = findViewById(R.id.header_imgbtn_a);
        imgB1 = findViewById(R.id.toolb_imgbtn_team);
        Btn1 = findViewById(R.id.toolb_btn_home);
        Btn2 = findViewById(R.id.toolb_btn_match);
        Btn3 = findViewById(R.id.toolb_btn_stats);
        Btn4 = findViewById(R.id.toolb_btn_league);
        if(Btn1.callOnClick()){
            Toast.makeText(this, "IMGBTN_H1", Toast.LENGTH_SHORT).show();
        }
    }

    public void toHome()
    {
        Intent aActivity = new Intent(this, Minicio.class);
        startActivity(aActivity);
    }
    public void toTeam()
    {
        Intent aActivity = new Intent(this, Equipo.class);
        startActivity(aActivity);
    }
    public void toMatches()
    {
        Intent aActivity = new Intent(this, Pinicio.class);
        startActivity(aActivity);
    }
    public void toStats()
    {
        Intent aActivity = new Intent(this, Einicio.class);
        startActivity(aActivity);
    }
    public void toLeagues()
    {
        Intent aActivity = new Intent(this, Tinicio.class);
        startActivity(aActivity);
    }
    public void toMenu()
    {
        Intent aActivity = new Intent(this, LogIn.class);
        startActivity(aActivity);
    }
}
