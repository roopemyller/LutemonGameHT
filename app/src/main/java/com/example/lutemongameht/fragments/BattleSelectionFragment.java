package com.example.lutemongameht.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemongameht.BattleActivity;
import com.example.lutemongameht.BattleListAdapter;
import com.example.lutemongameht.R;
import com.example.lutemongameht.Storage;

public class BattleSelectionFragment extends Fragment {

    private Button goToBattle;
    private TextView alertText;
    private RecyclerView recyclerView;
    private EditText lutemon1ID, lutemon2ID;
                   public BattleSelectionFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.battle_selection_fragment, container, false);

        goToBattle = view.findViewById(R.id.goToBattleButton);
        alertText = view.findViewById(R.id.alertText);
        lutemon1ID = view.findViewById(R.id.battleLutemon1ID);
        lutemon2ID = view.findViewById(R.id.battleLutemon2Id);

        Storage s = Storage.getInstance();

        recyclerView = view.findViewById(R.id.rvBattleList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new BattleListAdapter(getContext(), s.getLutemons()));
        recyclerView.getAdapter().notifyDataSetChanged();

        goToBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lutemon1ID.getText().toString().isEmpty() || lutemon2ID.getText().toString().isEmpty()){
                    alertText.setText("CHOOSE 2 LUTEMONS");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            alertText.setText("");
                        }
                    }, 1500);
                }else if(!isNumeric(lutemon1ID.getText().toString()) && !isNumeric(lutemon2ID.getText().toString())){
                    alertText.setText("INVALID ID. USE NUMBERS");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            alertText.setText("");
                        }
                    }, 1500);
                }
                else if(Integer.parseInt(lutemon1ID.getText().toString()) < 0 || Integer.parseInt(lutemon1ID.getText().toString()) > s.getLutemons().size() - 1 || Integer.parseInt(lutemon2ID.getText().toString()) < 0 || Integer.parseInt(lutemon2ID.getText().toString()) > s.getLutemons().size() - 1){
                    alertText.setText("INVALID ID");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            alertText.setText("");
                        }
                    }, 1500);
                }else if(lutemon1ID.getText().toString().equals(lutemon2ID.getText().toString())){
                    alertText.setText("MUST BE 2 DIFFERENT LUTEMONS");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            alertText.setText("");
                        }
                    }, 1500);
                }else {
                    Intent intent = new Intent(getContext(), BattleActivity.class);
                    intent.putExtra("lutemon1ID", Integer.parseInt(lutemon1ID.getText().toString()));
                    intent.putExtra("lutemon2ID", Integer.parseInt(lutemon2ID.getText().toString()));
                    lutemon1ID.setText("");
                    lutemon2ID.setText("");
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    public void onResume() {
        super.onResume();

        Storage s = Storage.getInstance();
        s.loadLutemons(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new BattleListAdapter(getContext(), s.getLutemons()));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public static boolean isNumeric(String str) {
        return str.chars().allMatch( Character::isDigit );
    }
}

