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

public class GoalsFragment extends Fragment {

    ActivityResultLauncher<Intent> activityResultLauncher;
    private List<GoalCard> todoList;
    private List<GoalCard> doneList;
    private RecyclerView rvTodo;
    private RecyclerView rvDone;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_goals, container, false);

        Button addGoalButt = view.findViewById(R.id.add_goal_button);
        rvTodo = view.findViewById(R.id.todo_recycler);
        rvDone = view.findViewById(R.id.done_recycler);

        todoList = new ArrayList<>();
        doneList = new ArrayList<>();

        GoalAdapter rvTodoAdapter = new GoalAdapter(todoList, getContext());
        GoalAdapter rvDoneAdapter = new GoalAdapter(doneList, getContext());

        rvTodo.setAdapter(rvTodoAdapter);
        rvDone.setAdapter(rvDoneAdapter);
        rvTodo.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDone.setLayoutManager(new LinearLayoutManager(getContext()));

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

                        GoalCard card = new GoalCard();
                        card.title = bundle.getString("goalTitle");

                        todoList.add(card);

                        rvTodoAdapter.notifyItemInserted(todoList.size()-1);
                        rvTodo.scrollToPosition(todoList.size()-1);
                    }
                }
        );

        View.OnClickListener addGoalListener = clickListenerView -> {
            Intent i = new Intent(getActivity(), GoalCreation.class);
            activityResultLauncher.launch(i);
        };

        addGoalButt.setOnClickListener(addGoalListener);

        return view;
    }

    public void passToDone(GoalCard card) {
        doneList.add(card);
        Objects.requireNonNull(rvDone.getAdapter()).notifyItemInserted(doneList.size()-1);
    }
}