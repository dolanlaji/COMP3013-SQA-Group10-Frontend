package com.example.group10_sqa_mentalhealthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;

import com.example.group10_sqa_mentalhealthapp.user.UserEntry;
import com.example.group10_sqa_mentalhealthapp.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class PetStatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_stats);

        UserRepository userRepo = UserRepository.getInstance(getApplication());
        UserEntry userEntry = userRepo.getUser().getValue();

        TextView petNameView = findViewById(R.id.tv_petName);
        // todo: Make this nice and reusable with userEntry.petName
        String petName = "Clayton";
        petNameView.setText(petName);

        String[] petStatsPrompts = getResources().getStringArray(R.array.stat_prompts);
        ArrayList<PetStatCard> petStatCardList = new ArrayList<>();
        PetStatCard card;
        for(int i = 0; i < petStatsPrompts.length; i++) {
            switch (i) {
                // Overall Tasks Completed
                case 0:
                    int totalTasks = 31;
                    card = new PetStatCard(petStatsPrompts[i], totalTasks);
                    petStatCardList.add(card);
                    break;
                // Tasks completed Today
                case 1:
                    int num = 3;
                    int denom = 4;
                    card = new PetStatCard(petStatsPrompts[i], num, denom);
                    petStatCardList.add(card);
                    break;
                // Total Journal Entries Stored
                case 2:
                    int totalEntries = 5;
                    card = new PetStatCard(petStatsPrompts[i], totalEntries);
                    petStatCardList.add(card);
                    break;
                // Affirmation
                default:
                    card = new PetStatCard();
                    petStatCardList.add(card);
                    break;
            }
        }

        List<Pair<PetStatCard, PetStatCard>> pairList =
                itemPairer(petStatCardList);


        RecyclerView statRecyclerView = (RecyclerView) findViewById(R.id.stats_recycler);
        statRecyclerView.setAdapter(new PetStatAdapter(pairList, this));
    }

    private List<Pair<PetStatCard, PetStatCard>> itemPairer(List<PetStatCard> inputList) {

        List<Pair<PetStatCard, PetStatCard>> pairList = new ArrayList<>();
        int loopLen = inputList.size();
        if(loopLen % 2 != 0) {
            loopLen++;
        }

        loopLen /= 2;

        for(int i = 0; i < loopLen; i++) {
            Pair<PetStatCard, PetStatCard> pair =
                    new Pair<>(
                            inputList.get(2*i),
                            inputList.get((2*i) + 1)
                    );
            pairList.add(pair);
        }

        return pairList;
    }
}