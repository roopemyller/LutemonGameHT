package com.example.lutemongameht;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lutemongameht.lutemons.Lutemon;

public class BattleActivity extends AppCompatActivity {

    private Button goBackButton, startBattleButton;
    private TextView lutemon1Name, lutemon2Name, battleText;
    private ImageView lutemon1Image, lutemon1Sword, lutemon2Image, lutemon2Sword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle);

        goBackButton = findViewById(R.id.battleGoBackButton);
        startBattleButton = findViewById(R.id.startBattleButton);
        lutemon1Name = findViewById(R.id.lutemon1Name);
        lutemon2Name = findViewById(R.id.lutemon2Name);
        battleText = findViewById(R.id.battleText);
        lutemon1Image = findViewById(R.id.lutemon1Image);
        lutemon1Sword = findViewById(R.id.lutemon1Sword);
        lutemon2Image = findViewById(R.id.lutemon2Image);
        lutemon2Sword = findViewById(R.id.lutemon2Sword);


        Bundle extras = getIntent().getExtras();

        Storage s = Storage.getInstance();

        Lutemon lutemon1 = s.getLutemon((Integer) extras.get("lutemon1ID"));
        Lutemon lutemon2 = s.getLutemon((Integer) extras.get("lutemon2ID"));

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lutemon1.restoreHealth();
                lutemon2.restoreHealth();

                s.saveLutemons(BattleActivity.this);
                finish();
            }
        });

        lutemon1Name.setText(lutemon1.getName());
        lutemon1Image.setImageResource(lutemon1.getImage());
        lutemon1Sword.setImageResource(R.drawable.sword);

        lutemon2Name.setText(lutemon2.getName());
        lutemon2Image.setImageResource(lutemon2.getImage());
        lutemon2Sword.setImageResource(R.drawable.sword);

        startBattleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fightText = "";
                while(true){
                    fightText += "\n" + addLutemonStats(lutemon1, lutemon2);

                    lutemon2.defence(lutemon1);
                    fightText += "\n " + lutemon1.getName() + " attacked " + lutemon2.getName() + " ";

                    if(lutemon2.getHealth() <= 0){
                        fightText += lutemon2.getName() + " HAS DIED";
                        break;
                    }else if(lutemon2.getHealth() < 7){
                        fightText += lutemon2.getName() + " is hurt badly!";
                    }else{
                        fightText += lutemon2.getName() + " got hurt but didn't die";
                    }
                    fightText += "\n" + addLutemonStats(lutemon1, lutemon2);

                    lutemon1.defence(lutemon2);
                    fightText += "\n " + lutemon2.getName() + " attacked " + lutemon1.getName();

                    if(lutemon1.getHealth() <= 0){
                        fightText +=  lutemon1.getName() + " HAS DIED";
                        break;
                    }else if(lutemon1.getHealth() < 7){
                        fightText += lutemon1.getName() + " is hurt badly!";
                    }else{
                        fightText += lutemon1.getName() + " got hurt but didn't die";
                    }
                }
                fightText += "\n" + addLutemonStats(lutemon1, lutemon2);

                if(lutemon1.getHealth() > lutemon2.getHealth()){
                    fightText += "\n" + lutemon1.getName() + " HAS WON! + 2 EXP";
                    lutemon1.addNumberOfWins();
                    lutemon1.setExp(2);

                }else{
                    fightText += "\n" + lutemon2.getName() + " HAS WON! + 2 EXP";
                    lutemon2.addNumberOfWins();
                    lutemon2.setExp(2);
                }

                lutemon1.addNumberOfBattles();
                lutemon2.addNumberOfBattles();

                startBattleButton.setVisibility(View.GONE);
                startAnimation();
                String finalFightText = fightText;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lutemon1AttackAnimation();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                lutemon2AttackAnimation();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(lutemon1.getHealth() > lutemon2.getHealth()){
                                            lutemon2Sword.animate().xBy(30).yBy(30).setDuration(0);
                                            lutemon2Sword.setImageResource(R.drawable.damage);
                                        }else{
                                            lutemon1Sword.animate().xBy(-30).yBy(30).setDuration(0);
                                            lutemon1Sword.setImageResource(R.drawable.damage);
                                        }
                                        battleText.setText(finalFightText);
                                        goBackButton.setVisibility(View.VISIBLE);
                                    }
                                }, 1500);
                            }
                        }, 1500);
                    }
                }, 2200);
            }
        });

    }

    private String addLutemonStats( Lutemon lutemon1, Lutemon lutemon2){
        String l1Stats = "1: " + lutemon1.getName() + " (" + lutemon1.getColor() +  ") ATT: " + lutemon1.getAttack() + ", DEF: " + lutemon1.getDefence() + " EXP: " + lutemon1.getExp() + " HP: " + lutemon1.getHealth();
        String l2Stats = "2: " + lutemon2.getName() + " (" + lutemon2.getColor() +  ") ATT: " + lutemon2.getAttack() + ", DEF: " + lutemon2.getDefence() + " EXP: " + lutemon2.getExp() + " HP: " + lutemon2.getHealth();
        return l1Stats + "\n" + l2Stats;
    }

    private void startAnimation(){
        lutemon2Sword.animate().rotationBy(-60f).xBy(-170).yBy(20).setDuration(400);
        lutemon2Image.animate().xBy(-100).setDuration(400);

        lutemon1Sword.animate().rotation(60f).xBy(170).yBy(20).setDuration(400);
        lutemon1Image.animate().xBy(100).setDuration(400);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lutemon2Sword.animate().rotationBy(60f).xBy(170).yBy(-20).setDuration(400);
                lutemon2Image.animate().xBy(100).setDuration(400);

                lutemon1Sword.animate().rotationBy(-60f).xBy(-170).yBy(-20).setDuration(400);
                lutemon1Image.animate().xBy(-100).setDuration(400);
            }
        }, 1500);
    }

    private void lutemon1AttackAnimation(){
        lutemon1Sword.animate().rotation(60f).xBy(240).yBy(20).setDuration(300);
        lutemon1Image.animate().xBy(170).setDuration(300);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lutemon1Sword.animate().rotationBy(-60f).xBy(-240).yBy(-20).setDuration(300);
                lutemon1Image.animate().xBy(-170).setDuration(300);
            }
        }, 800);
    }

    private void lutemon2AttackAnimation(){
        lutemon2Sword.animate().rotationBy(-60f).xBy(-240).yBy(20).setDuration(300);
        lutemon2Image.animate().xBy(-170).setDuration(300);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lutemon2Sword.animate().rotationBy(60f).xBy(240).yBy(-20).setDuration(300);
                lutemon2Image.animate().xBy(170).setDuration(300);
            }
        }, 800);
    }




}
