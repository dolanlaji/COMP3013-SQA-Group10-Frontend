package com.example.group10_sqa_mentalhealthapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class JournalEntryAdapter extends RecyclerView.Adapter<JournalEntryAdapter.JournalEntryHolder> {
    private final List<JournalEntry> entries;

    public JournalEntryAdapter(List<JournalEntry> entries) {
        this.entries = entries;
    }

    @NonNull
    @Override
    public JournalEntryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_journal_entry, parent, false);
        return new JournalEntryHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull JournalEntryHolder holder, int position) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        JournalEntry entry = entries.get(position);
        holder.contentsTextView.setText(entry.content);
        holder.dateTextView.setText(dateFormat.format(entry.localDateTime));
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    static class JournalEntryHolder extends RecyclerView.ViewHolder {
        TextView contentsTextView;
        TextView dateTextView;

        JournalEntryHolder (View view) {
            super(view);
            contentsTextView = view.findViewById(R.id.textViewJournalEntry);
            dateTextView = view.findViewById(R.id.textViewJournalDateTime);
        }
    }
}
