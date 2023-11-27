package com.example.group10_sqa_mentalhealthapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;
import java.util.Scanner;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.TodoViewHolder> {

    private final List<GoalCard> tasks;
    private View itemView;
    private Context context;

    public GoalAdapter(List<GoalCard> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_layout_goals, parent, false);
        return new TodoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        GoalCard card = tasks.get(position);

        holder.bind(card.title);

        if(card.done) {
            itemView.findViewById(R.id.todo_done_button).setEnabled(false);
        }
        else {
            itemView.findViewById(R.id.todo_done_button)
                    .setOnClickListener((View v) -> {
                                card.done = true;
                                int pos = tasks.indexOf(card);
                                tasks.remove(card);
                                notifyItemRemoved(pos);
                                MainActivity activity = (MainActivity) itemView.getContext();
                                ViewPager2 viewPager = (ViewPager2) activity.getViewPager();
                                ScreenSlidePagerAdapter vpAdapter =
                                        (ScreenSlidePagerAdapter) viewPager.getAdapter();
                                assert vpAdapter != null;
                                GoalsFragment goalsFragment = (GoalsFragment) vpAdapter.getFragment(2);
                                goalsFragment.passToDone(card);
                            }
                    );
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView goalNameView;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            goalNameView = (TextView) itemView.findViewById(R.id.goal_text);
        }

        public void bind(String title) {
            goalNameView.setText(title);
        }
    }
}
