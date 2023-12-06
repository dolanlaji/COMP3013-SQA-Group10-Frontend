package com.example.group10_sqa_mentalhealthapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Fragment for managing and displaying goals.
 *
 * <p>This fragment provides a user interface for managing and displaying both to-do and completed goals.
 */
public class GoalsFragment extends Fragment {
    // Activity result launcher for handling goal creation activity results
    ActivityResultLauncher<Intent> activityResultLauncher;
    // Lists to store to-do and completed goals
    private List<GoalCard> todoList;
    private List<GoalCard> doneList;
    // RecyclerViews for displaying to-do and completed goals
    private RecyclerView rvTodo;
    private RecyclerView rvDone;
    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate views.
     * @param container          If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return Return the View for the fragment's UI, or null.
     */
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_goals, container, false);
        // Find UI elements
        Button addGoalButt = view.findViewById(R.id.add_goal_button);
        rvTodo = view.findViewById(R.id.todo_recycler);
        rvDone = view.findViewById(R.id.done_recycler);
        // Initialize goal lists and adapters
        todoList = new ArrayList<>();
        doneList = new ArrayList<>();

        GoalAdapter rvTodoAdapter = new GoalAdapter(todoList, this);
        GoalAdapter rvDoneAdapter = new GoalAdapter(doneList, this);
        // Set up RecyclerViews with adapters and layout managers
        rvTodo.setAdapter(rvTodoAdapter);
        rvDone.setAdapter(rvDoneAdapter);
        rvTodo.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDone.setLayoutManager(new LinearLayoutManager(getContext()));
        // Set up activity result launcher for goal creation
        // todo: maybe instead of an activity we can get like a modal to come up?
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    int resultCode = result.getResultCode();

                    if(resultCode == Activity.RESULT_OK) {

                        Intent intent = result.getData();
                        assert intent != null;

                        Bundle bundle = intent.getExtras();
                        assert bundle != null;
                        // Create a new goal card and add it to the to-do list

                        GoalCard card = new GoalCard();
                        card.title = bundle.getString("goalTitle");

                        todoList.add(card);
                        // Notify the adapter of the data change

                        rvTodoAdapter.notifyItemInserted(todoList.size()-1);
                        rvTodo.scrollToPosition(todoList.size()-1);
                    }
                }
        );
        // Set up click listener for adding a new goal

        View.OnClickListener addGoalListener = clickListenerView -> {
            Intent i = new Intent(getActivity(), GoalCreation.class);
            activityResultLauncher.launch(i);
        };
        // Assign the click listener to the "Add Goal" button

        addGoalButt.setOnClickListener(addGoalListener);

        return view;
    }
    /**
     * Passes a completed goal from the to-do list to the completed goals list.
     *
     * @param card The completed goal card.
     */
    public void passToDone(GoalCard card) {
        doneList.add(card);
        // Notify the adapter of the data change in the completed goals list
        Objects.requireNonNull(rvDone.getAdapter()).notifyItemInserted(doneList.size()-1);
        rvDone.scrollToPosition(doneList.size()-1);
    }
}