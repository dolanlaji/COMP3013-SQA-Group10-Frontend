package com.example.group10_sqa_mentalhealthapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<Integer> bPinkVisibility = new MutableLiveData<>();
    private MutableLiveData<Integer> bRedVisibility = new MutableLiveData<>();
    private MutableLiveData<Integer> bTealVisibility = new MutableLiveData<>();
    private MutableLiveData<Integer> hPinkVisibility = new MutableLiveData<>();
    private MutableLiveData<Integer> hRedVisibility = new MutableLiveData<>();
    private MutableLiveData<Integer> hTealVisibility = new MutableLiveData<>();

    public void setBeaniePinkVisibility(int visibility) {
        bPinkVisibility.setValue(visibility);
    }

    public LiveData<Integer> getBeaniePinkVisibility() {
        return bPinkVisibility;
    }

    public void setBeanieRedVisibility(int visibility) {
        bRedVisibility.setValue(visibility);
    }

    public LiveData<Integer> getBeanieRedVisibility() {
        return bRedVisibility;
    }

    public void setBeanieTealVisibility(int visibility) {
        bTealVisibility.setValue(visibility);
    }

    public LiveData<Integer> getBeanieTealVisibility() {
        return bTealVisibility;
    }

    public void setHatPinkVisibility(int visibility) {
        hPinkVisibility.setValue(visibility);
    }

    public LiveData<Integer> getHatPinkVisibility() {
        return hPinkVisibility;
    }

    public void setHatRedVisibility(int visibility) {
        hRedVisibility.setValue(visibility);
    }

    public LiveData<Integer> getHatRedVisibility() {
        return hRedVisibility;
    }

    public void setHatTealVisibility(int visibility) {
        hTealVisibility.setValue(visibility);
    }

    public LiveData<Integer> getHatTealVisibility() {
        return hTealVisibility;
    }
}

