package com.example.group10_sqa_mentalhealthapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Fragment for displaying the home screen.
 *
 * <p>This fragment represents the home screen of the application.
 */
public class HomeFragment extends Fragment {
    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate views.
     * @param container          If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Call the superclass method

        super.onCreate(savedInstanceState);

        Button petStatButt = (Button) view.findViewById(R.id.pet_stat_button);

        View.OnClickListener addPetStatListener = clickListenerView -> {
            Intent i = new Intent(getActivity(), PetStatsActivity.class);
            startActivity(i);
        };

        petStatButt.setOnClickListener(addPetStatListener);

        return view;
    }


}
