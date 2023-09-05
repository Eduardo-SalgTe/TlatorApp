package com.example.tlatorf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class Main extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private Handler handler;
    private static final int DELAY_TIME = 3000; // 3s
    private Intent intent;
    //public static boolean iFR = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //insertVr();
        handler = new Handler();
        sharedPreferences = getSharedPreferences("App preferences", Context.MODE_PRIVATE);
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        if(isFirstRun)
        {
            //iFR = true;
            // Guardar valor actualizado
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstRun", false);
            editor.apply();
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // chng intf B
                if(isFirstRun == true)
                {
                    // Nuevo
                    intent = new Intent(Main.this, NewUserA.class);
                }else if(isFirstRun == false){
                    // No nuevo
                    intent = new Intent(Main.this, Lobby.class);
                }
                startActivity(intent);
                finish();
            }
        }, DELAY_TIME);
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(handler != null)
        {
            handler.removeCallbacksAndMessages(null);
        }
    }
    public void toLobby(View view)
    {
        Intent aLobby = new Intent (this, Lobby.class);
        startActivity(aLobby);

    }
}