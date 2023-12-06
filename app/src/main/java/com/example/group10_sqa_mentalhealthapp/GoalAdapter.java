package com.example.group10_sqa_mentalhealthapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;
import java.util.Scanner;

/**
 * Adapter for managing the display of goals in a RecyclerView.
 *
 * <p>This adapter is responsible for creating and binding views for each goal card in the RecyclerView.
 */
public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.TodoViewHolder> {
    // List of goal cards
    private final List<GoalCard> tasks;
    // Context associated with the adapter
    private final Context context;
    // Reference to the GoalsFragment
    private GoalsFragment goalsFragment;

    /**
     * Constructs a GoalAdapter with the provided list of goal cards and associated fragment.
     *
     * @param tasks    The list of goal cards to display.
     * @param fragment The GoalsFragment associated with this adapter.
     */
    public GoalAdapter(List<GoalCard> tasks, GoalsFragment fragment) {
        this.tasks = tasks;
        this.goalsFragment = fragment;
        this.context = fragment.getContext();
    }
    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new TodoViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_layout_goals, parent, false);
        return new TodoViewHolder(itemView);
    }
    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder   The ViewHolder that should be updated to represent the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        GoalCard card = tasks.get(position);

        // Bind the data to the ViewHolder
        holder.bind(card.title);

        // Set up button behavior based on goal completion status
        if(card.done) {
            holder.itemView.findViewById(R.id.todo_done_button).setEnabled(false);
        }
        else {
            holder.itemView.findViewById(R.id.todo_done_button)
                    .setOnClickListener((View v) -> {
                                // Mark the goal as done, remove it from the list, and notify the adapter

                                card.done = true;
                                int pos = tasks.indexOf(card);
                                tasks.remove(card);
                                notifyItemRemoved(pos);
                                // Pass the completed goal to the GoalsFragment

                                goalsFragment.passToDone(card);
                            }
                    );
        }
    }
    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return tasks.size();
    }
    /**
     * ViewHolder for representing individual goal items in the RecyclerView.
     */
    public class TodoViewHolder extends RecyclerView.ViewHolder {

        // TextView for displaying the goal name
        TextView goalNameView;
        /**
         * Constructor for TodoViewHolder.
         *
         * @param itemView The View representing an individual goal item.
         */
        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            goalNameView = (TextView) itemView.findViewById(R.id.goal_text);
        }
        /**
         * Binds the provided title to the goalNameView TextView.
         *
         * @param title The title to be displayed.
         */
        public void bind(String title) {
            goalNameView.setText(title);
        }
    }
}
