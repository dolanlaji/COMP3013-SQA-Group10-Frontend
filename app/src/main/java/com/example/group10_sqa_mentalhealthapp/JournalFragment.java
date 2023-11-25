package com.example.group10_sqa_mentalhealthapp;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.view.LayoutInflater;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class JournalFragment extends Fragment {
    private EditText entryEditText;
    private Button submitButton;
    private RecyclerView entriesRecyclerView;
    private List<JournalEntry> entries;
    private JournalEntryAdapter adapter;

    private Switch modeSwitch;
    private boolean isBlurtMode = false;
    private TextView tvMode;
    private TextView tvWordCount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journal, container, false);

        tvMode = view.findViewById(R.id.tvMode);
        tvWordCount = view.findViewById(R.id.tvWordCount);
        entryEditText = view.findViewById(R.id.entryEditText);
        submitButton = view.findViewById(R.id.submitEntryButton);
        entriesRecyclerView = view.findViewById(R.id.entriesRecyclerView);
        modeSwitch = view.findViewById(R.id.modeSwitch);

        entries = new ArrayList<>();
        adapter = new JournalEntryAdapter(entries);

        entriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        entriesRecyclerView.setAdapter(adapter);

        setupSwitch();
        setupSubmitButton(view);
        setupTextWatcher();

        view.setOnTouchListener((v, event) -> {
            view.performClick();
            hideKeyboard(v);
            return false;
        });

        return view;
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
                // Not used
            }

            @Override
            public void afterTextChanged(Editable s) {
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

    private void setupSubmitButton(View view) {
        submitButton.setOnClickListener(v -> {
            String text = entryEditText.getText().toString();
            if (!text.isEmpty()) {
                if (!isBlurtMode) {
                    // Journal mode: Add to the journal
                    JournalEntry entry = new JournalEntry();
                    entry.content = text;
                    entry.localDateTime = new Date();
                    entries.add(0, entry);
                    adapter.notifyItemInserted(0);
                }
                // Clear the text whether it's Blurt or Journal mode
                entryEditText.setText("");
                entriesRecyclerView.scrollToPosition(0);
            }
        });
    }
}
