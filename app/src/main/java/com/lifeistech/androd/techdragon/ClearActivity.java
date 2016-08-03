package com.lifeistech.androd.techdragon;

import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by hnk_1031 on 16/08/03.
 */
public class ClearActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear);

        SharedPreferences pref = getSharedPreferences("TechDragon",MODE_PRIVATE);
        String playerNameText = pref.getString("player_name","");

        TextView message = (TextView)findViewById(R.id.message);
        message.setText("勇者" + playerNameText + "はクリアした！");
    }

    public void back(View v) {
        finish();
    }
}
