package com.example.lutemongameht.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.lutemongameht.R;
import com.example.lutemongameht.Storage;
import com.example.lutemongameht.lutemons.Lutemon;

public class HomeFragment extends Fragment {

    private Button clearAll;
    private TextView numberOfLutemons, numberOfBattles, totalExp, trainingDays, daysPassed;

    public HomeFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        clearAll = view.findViewById(R.id.deleteAllBtn);
        numberOfLutemons = view.findViewById(R.id.txtNumberOfLutemons);
        numberOfBattles = view.findViewById(R.id.txtNumOfBattles);
        totalExp = view.findViewById(R.id.txtTotalExp);
        trainingDays = view.findViewById(R.id.txtTotalTrainingDays);
        daysPassed = view.findViewById(R.id.txtTotalDaysPassed);

        Storage s = Storage.getInstance();
        s.loadLutemons(getContext());


        numberOfLutemons.setText("Number of Lutemons: " + s.getLutemons().size());
        numberOfBattles.setText("Number of Battles: " + s.getNumberOfBattles());
        int totalExperience = 0;
        for (Lutemon l : Storage.getInstance().getLutemons()){
            totalExperience += l.getExp();
        }
        totalExp.setText("Total Experience: " + totalExperience + " EXP");

        trainingDays.setText("Training days: " + s.getNumberOfTrainingDays());
        daysPassed.setText("Days passed: " + s.getNumberOfDaysPassed());


        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Storage.getInstance().clearList();
                Storage.getInstance().saveLutemons(view.getContext());
            }
        });

        return view;
    }

    public void onResume() {
        super.onResume();

        Storage s = Storage.getInstance();
        s.loadLutemons(getContext());

        numberOfLutemons.setText("Number of Lutemons: " + s.getLutemons().size());
        numberOfBattles.setText("Number of Battles: " + s.getNumberOfBattles());
        int totalExperience = 0;
        for (Lutemon l : Storage.getInstance().getLutemons()){
            totalExperience += l.getExp();
        }
        totalExp.setText("Total Experience: " + totalExperience + " EXP");
        trainingDays.setText("Training days: " + s.getNumberOfTrainingDays());
        daysPassed.setText("Days passed: " + s.getNumberOfDaysPassed());
    }
}
