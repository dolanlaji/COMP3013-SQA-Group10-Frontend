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

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

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
