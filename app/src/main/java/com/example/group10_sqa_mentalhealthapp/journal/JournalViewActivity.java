package com.example.group10_sqa_mentalhealthapp.journal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.group10_sqa_mentalhealthapp.R;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class JournalViewActivity extends AppCompatActivity {

    private JournalEntry journalEntry;
    private TextView journalContentTextView;
    private TextView journalTimestampTextView;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journalview);

        // Initialize views and database
        initializeViews();

        // Get the JournalEntry from the intent or saved state
        retrieveJournalEntry(savedInstanceState);

        // Display the journal entry details
        displayJournalEntry();

        // Setup delete button
        setupDeleteButton();
    }

    private void initializeViews() {
        journalContentTextView = findViewById(R.id.journal_content);
        journalTimestampTextView = findViewById(R.id.journal_timestamp);
        deleteButton = findViewById(R.id.delete_button);
    }

    private void retrieveJournalEntry(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("journalEntry")) {
            journalEntry = (JournalEntry) savedInstanceState.getSerializable("journalEntry");
        } else {
            journalEntry = (JournalEntry) getIntent().getSerializableExtra("journalEntry");
        }
    }

    private void displayJournalEntry() {
        if (journalEntry != null) {
            journalContentTextView.setText(journalEntry.content);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            journalTimestampTextView.setText(dateFormat.format(journalEntry.localDateTime));
        }
    }

    private void setupDeleteButton() {
        deleteButton.setOnClickListener(v -> deleteEntry());
    }

    private void deleteEntry() {
        JournalRepository.getInstance(getApplication()).deleteEntry(journalEntry);
        finish(); // Close the activity
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("journalEntry", journalEntry);
    }
}
