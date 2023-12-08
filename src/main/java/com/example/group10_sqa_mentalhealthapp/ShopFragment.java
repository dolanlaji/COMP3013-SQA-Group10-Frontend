package com.example.group10_sqa_mentalhealthapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.group10_sqa_mentalhealthapp.R;
//TODO
//Buying hats only once (might need shared preference)
//3 different hat colors for 2 different hats D
//Show coin price D
//Coins need to be deducted from coin total
//Pass hats bought to Owned Hats Fragment(changing image view from locked to colored hat)
//Equip hats onto pet

public class ShopFragment extends Fragment {

    private Button pinkButton, pinkButton2, tealButton, tealButton2, redButton, redButton2;
    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        pinkButton = view.findViewById(R.id.pinkButton);
        pinkButton2 = view.findViewById(R.id.pinkButton2);
        tealButton = view.findViewById(R.id.tealButton);
        tealButton2 = view.findViewById(R.id.tealButton2);
        redButton = view.findViewById(R.id.redButton);
        redButton2 = view.findViewById(R.id.redButton2);

        pinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinkButton.setEnabled(false);
                pinkButton.setBackgroundResource(R.drawable.circle_pink_sold);
                sharedViewModel.setBeaniePinkVisibility(View.VISIBLE);
            }
        });

        pinkButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinkButton2.setEnabled(false);
                pinkButton2.setBackgroundResource(R.drawable.circle_pink_sold);
                sharedViewModel.setHatPinkVisibility(View.VISIBLE);
            }
        });

        tealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tealButton.setEnabled(false);
                tealButton.setBackgroundResource(R.drawable.circle_teal_sold);
                sharedViewModel.setBeanieTealVisibility(View.VISIBLE);
            }
        });

        tealButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tealButton2.setEnabled(false);
                tealButton2.setBackgroundResource(R.drawable.circle_teal_sold);
                sharedViewModel.setHatTealVisibility(View.VISIBLE);
            }
        });

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redButton.setEnabled(false);
                redButton.setBackgroundResource(R.drawable.circle_red_sold);
                sharedViewModel.setBeanieRedVisibility(View.VISIBLE);
            }
        });

        redButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redButton2.setEnabled(false);
                redButton2.setBackgroundResource(R.drawable.circle_red_sold);
                sharedViewModel.setHatRedVisibility(View.VISIBLE);
            }
        });

        return view;
        }

}