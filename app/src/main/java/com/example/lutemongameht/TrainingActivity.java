package com.example.lutemongameht;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lutemongameht.lutemons.Lutemon;


public class TrainingActivity extends AppCompatActivity {
    private Button goBack, trainingAttack, trainingDefence;

    private ImageView lutemonImage;

    private TextView lutemonName, lutemonColor, lutemonHealth, lutemonExp, lutemonAttack, lutemonDefence, trainingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_arena);

        goBack = findViewById(R.id.goBackButton);
        trainingAttack = findViewById(R.id.trainingAttack);
        trainingDefence = findViewById(R.id.trainingDefence);

        Bundle extras = getIntent().getExtras();

        Lutemon l = Storage.getInstance().getLutemon((Integer) extras.get("lutemonPosition"));

        lutemonImage = findViewById(R.id.trainingLutemonImage);
        lutemonName = findViewById(R.id.trainingLutemonName);
        lutemonColor = findViewById(R.id.trainingLutemonColor);
        lutemonHealth = findViewById(R.id.trainingLutemonHP);
        lutemonExp = findViewById(R.id.trainingLutemonExp);
        lutemonAttack = findViewById(R.id.trainingLutemonAttack);
        lutemonDefence = findViewById(R.id.trainingLutemonDefence);

        trainingTextView = findViewById(R.id.trainingText);


        lutemonImage.setImageResource(l.getImage());
        lutemonName.setText(l.getName());
        lutemonColor.setText(l.getColor());
        lutemonHealth.setText(l.getHealth() + "/" + l.getMaxHealth() + " HP");
        lutemonExp.setText(l.getExp() + " EXP");
        lutemonDefence.setText("Defence: " + l.getDefence());
        lutemonAttack.setText("Attack: " + l.getAttack());


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Storage.getInstance().saveLutemons(TrainingActivity.this);
                finish();

            }
        });

        trainingAttack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                l.addTrainingDays();
                trainingTextView.setText("TRAINING IN PROGRESS...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        l.setExp(1);
                        l.setAttack(l.getAttack() + 1);
                        lutemonAttack.setText("Attack: " + l.getAttack());
                        lutemonExp.setText(l.getExp() + " EXP");
                        trainingTextView.setText("");
                    }
                }, 1500);
            }
        });

        trainingDefence.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                l.addTrainingDays();
                trainingTextView.setText("TRAINING IN PROGRESS...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        l.setExp(1);
                        l.setDefence(l.getDefence() + 1);
                        lutemonDefence.setText("Defence: " + l.getDefence());
                        lutemonExp.setText(l.getExp() + " EXP");
                        trainingTextView.setText("");
                    }
                }, 1500);
            }
        });
    }
}
