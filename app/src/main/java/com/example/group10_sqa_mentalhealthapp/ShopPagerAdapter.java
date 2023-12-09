package com.example.group10_sqa_mentalhealthapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.group10_sqa_mentalhealthapp.journal.JournalFragment;

public class ShopPagerAdapter extends FragmentStateAdapter {
    public ShopPagerAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ShopFragment();
            case 1:
                return new OwnedHatsFragment();
            default:
                return new ShopFragment();
    }
}

    @Override
    public int getItemCount() {
        return 2;
    }
}
