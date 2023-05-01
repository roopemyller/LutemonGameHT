package com.example.lutemongameht.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemongameht.AddNewLutemonActivity;
import com.example.lutemongameht.LutemonListAdapter;
import com.example.lutemongameht.R;
import com.example.lutemongameht.Storage;

public class LutemonListFragment extends Fragment {
    private RecyclerView recyclerView;
    private Button newLutemonButton;
    public LutemonListFragment(){

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lutemon_list_fragment, container, false);

        Storage s = Storage.getInstance();
        s.loadLutemons(getContext());

        recyclerView = view.findViewById(R.id.rvLutemonList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new LutemonListAdapter(getContext(), s.getLutemons()));
        recyclerView.getAdapter().notifyDataSetChanged();

        newLutemonButton = view.findViewById(R.id.newLutemonButton);
        newLutemonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity();
            }
        });


        return view;
    }
    public void onResume(){
        super.onResume();

        Storage s = Storage.getInstance();
        s.loadLutemons(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new LutemonListAdapter(getContext(), s.getLutemons()));
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void changeActivity(){
        Intent intent = new Intent(getActivity(), AddNewLutemonActivity.class);
        startActivity(intent);
    }
}
