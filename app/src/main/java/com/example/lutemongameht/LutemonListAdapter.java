package com.example.lutemongameht;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemongameht.lutemons.Lutemon;

import java.util.ArrayList;

public class LutemonListAdapter extends RecyclerView.Adapter<LutemonListHolder> {
    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    public LutemonListAdapter(Context context, ArrayList<Lutemon> lutemons) {
        this.lutemons = lutemons;
        this.context = context;
    }

    @NonNull
    @Override
    public LutemonListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonListHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonListHolder holder, int position) {
        int pos = position;
        holder.lutemonColor.setText(lutemons.get(position).getColor());
        holder.lutemonName.setText(lutemons.get(position).getName());
        holder.lutemonHealth.setText(lutemons.get(position).getHealth() + "/" + lutemons.get(position).getMaxHealth() + " HP");
        holder.lutemonExp.setText(lutemons.get(position).getExp() + " EXP");
        holder.lutemonWins.setText("Wins: " + lutemons.get(position).getNumberOfWins());
        holder.lutemonLoses.setText("Lost: " + (lutemons.get(position).getNumberOfBattles() - lutemons.get(position).getNumberOfWins()));

        holder.lutemonImage.setImageResource(lutemons.get(position).getImage());

        holder.goTrainingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //New Intent
                Intent intent = new Intent(context, TrainingActivity.class);
                intent.putExtra("lutemonPosition", pos);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
