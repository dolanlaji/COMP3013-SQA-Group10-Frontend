package com.example.group10_sqa_mentalhealthapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class OwnedHatsFragment extends Fragment {

    private ImageView beaniePink, beanieRed, beanieTeal, hatPink, hatRed, hatTeal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_owned_hats, container, false);

        beaniePink = view.findViewById(R.id.beanie_pink);
        beanieRed = view.findViewById(R.id.beanie_red);
        beanieTeal = view.findViewById(R.id.beanie_teal);
        hatPink = view.findViewById(R.id.hat_pink);
        hatRed = view.findViewById(R.id.hat_red);
        hatTeal = view.findViewById(R.id.hat_teal);

        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        viewModel.getBeaniePinkVisibility().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer visibility) {
                beaniePink.setVisibility(visibility);
            }
        });

        viewModel.getBeanieRedVisibility().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer visibility) {

                beanieRed.setVisibility(visibility);
            }
        });

        viewModel.getBeanieTealVisibility().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer visibility) {

                beanieTeal.setVisibility(visibility);
            }
        });

        viewModel.getHatPinkVisibility().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer visibility) {

                hatPink.setVisibility(visibility);
            }
        });

        viewModel.getHatRedVisibility().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer visibility) {

                hatRed.setVisibility(visibility);
            }
        });

        viewModel.getHatTealVisibility().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer visibility) {

                hatTeal.setVisibility(visibility);
            }
        });
        return view;
    }
}