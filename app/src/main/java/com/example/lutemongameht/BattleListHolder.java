package com.example.lutemongameht;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BattleListHolder extends RecyclerView.ViewHolder {


    ImageView lutemonImage;

    TextView lutemonName, lutemonHealth, lutemonExp, lutemonWins, lutemonLoses, lutemonId;



    public BattleListHolder(@NonNull View itemView) {
        super(itemView);
        lutemonImage = itemView.findViewById(R.id.battleImageMon);
        lutemonName = itemView.findViewById(R.id.battleMonName);
        lutemonHealth = itemView.findViewById(R.id.battleMonHealth);
        lutemonExp = itemView.findViewById(R.id.battleMonExp);
        lutemonWins = itemView.findViewById(R.id.battleMonWonBattles);
        lutemonLoses = itemView.findViewById(R.id.battleMonBattleLost);
        lutemonId = itemView.findViewById(R.id.battleMonId);

    }
}
