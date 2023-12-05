package com.example.group10_sqa_mentalhealthapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.group10_sqa_mentalhealthapp.journal.JournalFragment;
/**
 * Pager adapter for managing fragments in a ViewPager2.
 *
 * <p>This adapter is responsible for creating and managing the fragments displayed in the ViewPager2.
 */
public class ScreenSlidePagerAdapter extends FragmentStateAdapter {
    // Fragments to be managed by the adapter
    private JournalFragment journalFragment;
    private HomeFragment homeFragment;
    private GoalsFragment goalsFragment;
    /**
     * Constructor for the ScreenSlidePagerAdapter.
     *
     * @param fm        The fragment manager that will interact with this adapter.
     * @param lifecycle The lifecycle of the containing component.
     */
    public ScreenSlidePagerAdapter(FragmentManager fm, Lifecycle lifecycle) {
        super(fm, lifecycle);
    }
    /**
     * Create a new fragment for the specified position.
     *
     * @param position The position of the fragment in the ViewPager2.
     * @return A new instance of the corresponding fragment.
     */
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                // Create and return a new instance of the JournalFragment

                journalFragment = new JournalFragment();
                return journalFragment;
            case 2:
                // Create and return a new instance of the GoalsFragment

                goalsFragment = new GoalsFragment();
                return goalsFragment;
            default:
                // Create and return a new instance of the HomeFragment (default)

                homeFragment = new HomeFragment();
                return homeFragment;
        }
    }
    /**
     * Get the total number of items in the ViewPager2.
     *
     * @return The total number of items.
     */
    @Override
    public int getItemCount() {
        return 3;
    }

}
