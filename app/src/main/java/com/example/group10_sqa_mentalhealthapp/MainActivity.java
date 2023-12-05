package com.example.group10_sqa_mentalhealthapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
/**
 * Main activity representing the entry point of the application.
 *
 * <p>This activity contains a ViewPager2 with a BottomNavigationView for navigation between different fragments.
 */
public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;
    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Find ViewPager2 and BottomNavigationView in the layout

        viewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        // Set the initial page of the ViewPager2 to the middle page (Home)
        viewPager.setCurrentItem(1, false);

        viewPager.setAdapter(new ScreenSlidePagerAdapter(getSupportFragmentManager(), getLifecycle()));

        // Link ViewPager2 page changes to the BottomNavigationView
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });

        // Handle navigation item clicks
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_journal) {
                viewPager.setCurrentItem(0);
            } else if (itemId == R.id.navigation_home) {
                viewPager.setCurrentItem(1);
            } else if (itemId == R.id.navigation_goals) {
                viewPager.setCurrentItem(2);
            } else {
                return false;
            }
            return true;
        });

        // Set the default selected item in the BottomNavigationView
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }
}