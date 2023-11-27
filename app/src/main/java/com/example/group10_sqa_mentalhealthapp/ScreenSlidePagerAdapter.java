package com.example.group10_sqa_mentalhealthapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ScreenSlidePagerAdapter extends FragmentStateAdapter {
    private JournalFragment journalFragment;
    private HomeFragment homeFragment;
    private GoalsFragment goalsFragment;
    public ScreenSlidePagerAdapter(FragmentManager fm, Lifecycle lifecycle) {
        super(fm, lifecycle);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                journalFragment = new JournalFragment();
                return journalFragment;
            case 2:
                goalsFragment = new GoalsFragment();
                return goalsFragment;
            default:
                homeFragment = new HomeFragment();
                return homeFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
