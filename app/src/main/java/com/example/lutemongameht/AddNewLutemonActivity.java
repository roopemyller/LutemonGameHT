package com.example.lutemongameht;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lutemongameht.lutemons.Black;
import com.example.lutemongameht.lutemons.Green;
import com.example.lutemongameht.lutemons.Orange;
import com.example.lutemongameht.lutemons.Pink;
import com.example.lutemongameht.lutemons.White;

public class AddNewLutemonActivity extends AppCompatActivity {

    private Button createNewLutemonButton;
    private RadioGroup colorGroup;
    private RadioButton colorButton;
    private EditText editLutemonName;
    private TextView creatingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_lutemon_activity);

        createNewLutemonButton = findViewById(R.id.addButton);
        colorGroup = findViewById(R.id.radioGroupColor);
        editLutemonName = findViewById(R.id.editTextLutemonName);
        creatingText = findViewById(R.id.creatingText);

        Storage s = Storage.getInstance();

        createNewLutemonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editLutemonName.getText().toString().isEmpty()){
                    editLutemonName.setTextColor(Color.RED);
                    editLutemonName.setText("ADD NAME!");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            editLutemonName.setText("");
                            editLutemonName.setTextColor(Color.BLACK);
                        }
                    }, 1500);
                }else {
                    int colorId = colorGroup.getCheckedRadioButtonId();
                    colorButton = findViewById(colorId);

                    if (colorButton.getText().toString().equals("Black")) {
                        s.addLutemon(new Black(editLutemonName.getText().toString()));
                    } else if (colorButton.getText().toString().equals("Pink")) {
                        s.addLutemon(new Pink(editLutemonName.getText().toString()));
                    } else if (colorButton.getText().toString().equals("White")) {
                        s.addLutemon(new White(editLutemonName.getText().toString()));
                    } else if (colorButton.getText().toString().equals("Orange")) {
                        s.addLutemon(new Orange(editLutemonName.getText().toString()));
                    } else if (colorButton.getText().toString().equals("Green")) {
                        s.addLutemon(new Green(editLutemonName.getText().toString()));
                    }
                    s.saveLutemons(AddNewLutemonActivity.this);
                    creatingText.setText("Creating lutemon...");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            creatingText.setText("");
                            finish();
                        }
                    }, 1500);

                }
            }
        });
    }
    public void checkButton(View view){
        int degreeId = colorGroup.getCheckedRadioButtonId();
        colorButton = findViewById(degreeId);
    }
}