package com.example.group10_sqa_mentalhealthapp.journal;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Date;

import android.view.LayoutInflater;

import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.group10_sqa_mentalhealthapp.R;
import com.example.group10_sqa_mentalhealthapp.safety.ContentSafetyHandler;

public class JournalFragment extends Fragment {
    private EditText entryEditText;
    private Button submitButton;
    private RecyclerView entriesRecyclerView;
    private JournalEntryAdapter adapter;

    private Switch modeSwitch;
    private boolean isBlurtMode = false;
    private TextView tvMode;
    private TextView tvWordCount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journal, container, false);

        // Initialize views
        initializeViews(view);

        setupRecyclerView();

        setupSwitch();
        setupSubmitButton(view);
        setupTextWatcher();

        view.setOnTouchListener((v, event) -> {
            view.performClick();
            hideKeyboard(v);
            return false;
        });

        attachObserver();

        return view;
    }

    private void initializeViews(View view) {
        tvMode = view.findViewById(R.id.tvMode);
        tvWordCount = view.findViewById(R.id.tvWordCount);
        entryEditText = view.findViewById(R.id.entryEditText);
        submitButton = view.findViewById(R.id.submitEntryButton);
        entriesRecyclerView = view.findViewById(R.id.entriesRecyclerView);
        modeSwitch = view.findViewById(R.id.modeSwitch);
    }

    private void setupRecyclerView() {
        adapter = new JournalEntryAdapter(new ArrayList<>(), this::onOpenJournalEntry);
        entriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        entriesRecyclerView.setAdapter(adapter);
    }

    private void attachObserver()
    {
        JournalRepository repository = JournalRepository.getInstance(requireActivity().getApplication());
        // Re-attach the observer
        repository.getAllEntries().removeObservers(getViewLifecycleOwner());
        repository.getAllEntries().observe(getViewLifecycleOwner(), entries -> {
            adapter.setEntries(entries);
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void onOpenJournalEntry(JournalEntry entry)
    {
        Intent journalViewIntent = new Intent(getContext(), JournalViewActivity.class);
        journalViewIntent.putExtra("journalEntry", entry);
        startActivity(journalViewIntent);
    }

    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void setupSwitch() {
        modeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            tvMode.setText(isChecked ? "Blurt" : "Journal");

            isBlurtMode = isChecked;
            Context context = getContext();
            if (context != null) {
                if (isChecked) {
                    // Blurt mode
                    entryEditText.setBackground(ContextCompat.getDrawable(context, R.drawable.edittext_blurt));
                } else {
                    // Journal mode
                    entryEditText.setBackground(ContextCompat.getDrawable(context, R.drawable.edittext_normal));
                }
            }
        });
    }

    private void setupTextWatcher() {
        entryEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Check contents for safety words
                if (ContentSafetyHandler.getInstance().checkContents(s.toString())) {
                    showToastSafetyMessage();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Update word count
                int wordCount = countWords(s.toString());
                tvWordCount.setText(wordCount + " words");
            }

            private int countWords(String str) {
                if (str == null || str.isEmpty()) {
                    return 0;
                }
                String[] words = str.split("\\s+");
                return words.length;
            }
        });
    }

    private void showToastSafetyMessage() {
        // Inflate the custom layout
        LayoutInflater inflater = LayoutInflater.from(getContext());
        ContentSafetyHandler.getInstance().showDistressMessage(inflater, getContext());
    }

    private void setupSubmitButton(View view) {
        submitButton.setOnClickListener(v -> {
            String text = entryEditText.getText().toString();
            if (!text.isEmpty() && !isBlurtMode) {
                JournalEntry entry = new JournalEntry();
                entry.content = text;
                entry.localDateTime = new Date();
                JournalRepository.getInstance(requireActivity().getApplication()).addEntry(entry);
            }
            entryEditText.setText("");
        });
    }
}
