package com.example.tlatorf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NewUser2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user2);
    }
    public void toNewUser3(View view)
    {
        Intent aLobby = new Intent (this, NewUser3.class);
        startActivity(aLobby);
        finish();
    }
}