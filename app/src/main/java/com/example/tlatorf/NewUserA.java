package com.example.tlatorf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NewUserA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
    }

    public void toNextWelcome(View view)
    {
        Intent aLobby = new Intent (this, NewUser2.class);
        startActivity(aLobby);
        finish();
    }

}