package com.example.lutemongameht;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.lutemongameht.fragments.BattleSelectionFragment;
import com.example.lutemongameht.fragments.HomeFragment;
import com.example.lutemongameht.fragments.LutemonListFragment;

public class TabPagerAdapter extends FragmentStateAdapter {
    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            default:
                case 0:
                    return new HomeFragment();
            case 1:
                return new LutemonListFragment();
            case 2:
                return new BattleSelectionFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
