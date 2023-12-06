package com.example.group10_sqa_mentalhealthapp;

public class PetStatCard {
    String prompt;
    String answer;

    public PetStatCard() {
        this.prompt = "Loved?";
        this.answer = "Yes";
    }
    public PetStatCard(String prompt, int answer) {
        this.prompt = prompt;
        this.answer = "" + answer;
    }

    public PetStatCard(String prompt, int numerator, int denominator){
        this.prompt = prompt;
        this.answer = numerator + "/" + denominator;
    }
}
