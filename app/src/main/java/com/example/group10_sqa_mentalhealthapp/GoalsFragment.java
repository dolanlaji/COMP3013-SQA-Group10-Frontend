package com.example.group10_sqa_mentalhealthapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    List<GoalCard> list;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_goals, container, false);

        Button addGoalButt = view.findViewById(R.id.add_goal_button);
        RecyclerView rvTodo = view.findViewById(R.id.todo_recycler);

        list = new ArrayList<>();

        GoalAdapter rvTodoAdapter = new GoalAdapter(list, getContext());

        rvTodo.setAdapter(rvTodoAdapter);
        rvTodo.setLayoutManager(new LinearLayoutManager(getContext()));

        // todo: maybe instead of an activity we can get like a modal to come up?
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    int resultCode = result.getResultCode();

                    if(resultCode == Activity.RESULT_OK) {

                        Intent intent = result.getData();
                        assert intent != null;

                        Bundle bundle = intent.getExtras();
                        assert bundle != null;

                        GoalCard card = new GoalCard();
                        card.title = bundle.getString("goalTitle");

                        list.add(card);

                        rvTodoAdapter.notifyItemInserted(list.size()-1);
                        rvTodo.scrollToPosition(list.size()-1);
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
}