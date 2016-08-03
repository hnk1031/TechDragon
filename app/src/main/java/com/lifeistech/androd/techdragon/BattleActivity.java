package com.lifeistech.androd.techdragon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class BattleActivity extends AppCompatActivity {

    ProgressBar dragonHPProgress;
    ProgressBar playerHPProgress;
    ImageView playerImageView;
    ImageView dragonImageView;
    TextView playerDamageTextView;
    TextView dragonDamageTextView;
    TextView attackPoint;
    TextView playerName;

    //変数
    int dragonHP = 100;
    int playerHP = 100;
    int power = 0;
    String playerNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        dragonHPProgress = (ProgressBar)findViewById(R.id.dragonHP);
        playerHPProgress = (ProgressBar)findViewById(R.id.playerHP);
        playerImageView = (ImageView)findViewById(R.id.playerImage);
        dragonImageView = (ImageView)findViewById(R.id.dragonImageView);
        playerDamageTextView = (TextView)findViewById(R.id.playerDamageTextView);
        dragonDamageTextView = (TextView)findViewById(R.id.damageTextView);
        attackPoint = (TextView)findViewById(R.id.attackPoint);
        playerName = (TextView)findViewById(R.id.playerName);

        //progressBarの初期設定
        dragonHPProgress.setMax(100);
        dragonHPProgress.setProgress(100);
        playerHPProgress.setMax(100);
        playerHPProgress.setProgress(100);

        //プレイヤーの名前設置
        SharedPreferences pref = getSharedPreferences("TechDragon",MODE_PRIVATE);
        playerNameText =pref.getString("player_name","");
        playerName.setText(playerNameText);
    }


    public void dragonAttack() {
        Random random = new Random();
        int dragonAttackPower = random.nextInt(5);
        playerHP = playerHP - dragonAttackPower;
        playerHPProgress.setProgress(playerHP);
        damagePlayerAnimation(dragonAttackPower);

        if (playerHP <= 0) {
            attackPoint.setText("ゲームオーバー！");

        }
    }

    public void charge(View v) {
        power = power + 10;
        attackPoint.setText(String.valueOf(power));
        dragonAttack();

    }

    public void attack(View v) {
        dragonHP = dragonHP - power;
        dragonHPProgress.setProgress(dragonHP);
        dragonAttack();
        damageAnimation(power);
        power = 0;
        attackPoint.setText(String.valueOf(power));

        if (dragonHP <= 0) {
            Intent intent = new Intent(this, ClearActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void reset(View v) {
        dragonHPProgress.setMax(100);
        dragonHPProgress.setProgress(100);
        playerHPProgress.setMax(100);
        playerHPProgress.setProgress(100);
        power = 0;
        dragonHP = 100;
        playerHP = 100;
        attackPoint.setText("攻撃力"+String.valueOf(power));
    }

    public void damageAnimation(int damage) {
        if (damage > 0) {
            dragonDamageTextView.setText(String.valueOf(damage));
        } else {
            dragonDamageTextView.setText("miss");
        }
        AttackAnimation attackAnim = new AttackAnimation(
                                                         getApplicationContext(),
                                                         dragonImageView, dragonDamageTextView);
        dragonImageView.startAnimation(attackAnim);
    }
    
    public void damagePlayerAnimation(int damage) {
        if (damage > 0) {
            playerDamageTextView.setText(String.valueOf(damage));
        } else {
            playerDamageTextView.setText("miss");
        }
        AttackAnimation attackAnim = new AttackAnimation(
                                                         getApplicationContext(),
                                                         playerImageView, playerDamageTextView);
        playerImageView.startAnimation(attackAnim);
    }
}
