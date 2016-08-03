package com.lifeistech.androd.techdragon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity {

    EditText playerName;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        pref =getSharedPreferences("TechDragon",MODE_PRIVATE);
        playerName = (EditText)findViewById(R.id.playerName);
        //前回のPlayerNameを読み込む
        playerName.setText(pref.getString("player_name",""));
    }

    public void start (View v) {
        String playerNameText = playerName.getText().toString();
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("player_name",playerNameText);
        editor.commit();

        Intent intent = new Intent(this,BattleActivity.class);
        startActivity(intent);
    }
}
