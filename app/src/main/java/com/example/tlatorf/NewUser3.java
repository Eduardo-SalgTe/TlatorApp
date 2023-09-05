package com.example.tlatorf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NewUser3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user3);
    }
    public void toLobby(View view)
    {
        Intent aLobby = new Intent (this, Lobby.class);
        startActivity(aLobby);
        finish();
    }
}