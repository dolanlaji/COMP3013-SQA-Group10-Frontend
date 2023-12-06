package com.example.group10_sqa_mentalhealthapp;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PetStatAdapter extends RecyclerView.Adapter<PetStatAdapter.PetStatViewHolder> {
    List<Pair<PetStatCard, PetStatCard>> statCardList;
    Context context;

    public PetStatAdapter(List<Pair<PetStatCard, PetStatCard>> list, Context context) {
        this.statCardList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PetStatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_pet_stats, parent, false);
        return new PetStatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PetStatViewHolder holder, int position) {
        holder.bind(statCardList.get(position));
    }

    @Override
    public int getItemCount() {
        return statCardList.size();
    }

    public class PetStatViewHolder extends RecyclerView.ViewHolder {
        private final TextView prompt_1;
        private final TextView prompt_2;
        private final TextView answer_1;
        private final TextView answer_2;
        private final ConstraintLayout card_layout_2;

        public PetStatViewHolder(@NonNull View itemView) {
            super(itemView);
            prompt_1 = (TextView) itemView.findViewById(R.id.prompt_stat_card);
            prompt_2 = (TextView) itemView.findViewById(R.id.prompt_stat_card_2);
            answer_1 = (TextView) itemView.findViewById(R.id.ans_stat_card);
            answer_2 = (TextView) itemView.findViewById(R.id.ans_stat_card_2);
            card_layout_2 = (ConstraintLayout) itemView.findViewById(R.id.stat_card_2);
        }

        public void bind(Pair<PetStatCard, PetStatCard> pair) {
            PetStatCard card1 = (PetStatCard) pair.first;
            PetStatCard card2 = (PetStatCard) pair.second;

            prompt_1.setText(card1.prompt);
            answer_1.setText(card1.answer);

            if(card2 == null) {
                card_layout_2.setVisibility(View.GONE);
            }
            else {
                prompt_2.setText(card2.prompt);
                answer_2.setText(card2.answer);
            }
        }
    }
}
