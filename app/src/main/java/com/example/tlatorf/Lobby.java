package com.example.tlatorf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.ImageButton;
import android.widget.Button;

public class Lobby extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        Toolbar toolbar = findViewById(R.id.person);
        setSupportActionBar(toolbar);
    }

    public void toSingup(View view)
    {
        Intent aActivity = new Intent(this, SingUpActivity.class);
        startActivity(aActivity);
    }
    public void toLogin(View view)
    {
        Intent aActivity = new Intent(this, LogIn.class);
        startActivity(aActivity);
    }
    public void toGuest(View view)
    {
        Intent aActivity = new Intent(this, Minicio.class);
        startActivity(aActivity);
    }
}