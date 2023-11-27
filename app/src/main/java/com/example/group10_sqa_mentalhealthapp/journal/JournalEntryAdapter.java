package com.example.group10_sqa_mentalhealthapp.journal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.group10_sqa_mentalhealthapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class JournalEntryAdapter extends RecyclerView.Adapter<JournalEntryAdapter.JournalEntryHolder> {
    private List<JournalEntry> entries;
    private final OnJournalItemClickListener listener;

    public JournalEntryAdapter(List<JournalEntry> entries, OnJournalItemClickListener listener) {
        this.entries = entries;
        this.listener = listener;
    }

    public void setEntries(List<JournalEntry> entries)
    {
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault());
        JournalEntry entry = entries.get(position);
        holder.contentsTextView.setText(entry.content);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(entry));
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
