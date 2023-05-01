package com.example.lutemongameht;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonListHolder extends RecyclerView.ViewHolder {

    ImageView lutemonImage;

    TextView lutemonName, lutemonColor, lutemonHealth, lutemonExp, lutemonWins, lutemonLoses;

    Button goTrainingButton;


    public LutemonListHolder(@NonNull View itemView) {
        super(itemView);
        lutemonImage = itemView.findViewById(R.id.imageMon);
        lutemonName = itemView.findViewById(R.id.txtMonName);
        lutemonColor = itemView.findViewById(R.id.txtMonColor);
        lutemonHealth = itemView.findViewById(R.id.txtMonHealth);
        lutemonExp = itemView.findViewById(R.id.txtMonExp);
        lutemonWins = itemView.findViewById(R.id.txtMonWonBattles);
        lutemonLoses = itemView.findViewById(R.id.txtMonLostBattles);
        goTrainingButton = itemView.findViewById(R.id.goToTrainingButton);
    }
}
