package com.example.lutemongameht;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemongameht.lutemons.Lutemon;

import java.util.ArrayList;

public class BattleListAdapter extends RecyclerView.Adapter<BattleListHolder> {
    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    public BattleListAdapter(Context context, ArrayList<Lutemon> lutemons) {
        this.lutemons = lutemons;
        this.context = context;
    }

    @NonNull
    @Override
    public BattleListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BattleListHolder(LayoutInflater.from(context).inflate(R.layout.battle_lutemon_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BattleListHolder holder, int position) {
        int pos = position;
        holder.lutemonName.setText(lutemons.get(position).getName());
        holder.lutemonHealth.setText(lutemons.get(position).getHealth() + "/" + lutemons.get(position).getMaxHealth() + " HP");
        holder.lutemonExp.setText(lutemons.get(position).getExp() + " EXP");
        holder.lutemonWins.setText("Wins: " + lutemons.get(position).getNumberOfWins());
        holder.lutemonLoses.setText("Lost: " + (lutemons.get(position).getNumberOfBattles() - lutemons.get(position).getNumberOfWins()));

        holder.lutemonImage.setImageResource(lutemons.get(position).getImage());
        holder.lutemonId.setText("ID: " + lutemons.get(position).getId());
    }


    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
