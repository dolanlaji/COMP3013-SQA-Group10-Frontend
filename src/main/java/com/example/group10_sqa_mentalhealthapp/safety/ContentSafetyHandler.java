package com.example.group10_sqa_mentalhealthapp.safety;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group10_sqa_mentalhealthapp.R;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContentSafetyHandler {
    private static ContentSafetyHandler instance;
    private final Set<String> safetyWords;
    private Toast currentDistressToast;

    private ContentSafetyHandler() {
        // Initialize the set of safety words
        // todo: obviously consult a professional list instead of hardcore some terms lol
        safetyWords = new HashSet<>(Arrays.asList("suicide", "kill myself", "abuse", "abusive", "kill me", "end myself", "i want to die"));
    }

    public static synchronized ContentSafetyHandler getInstance() {
        if (instance == null) {
            instance = new ContentSafetyHandler();
        }
        return instance;
    }

    public boolean checkContents(String text) {
        for (String word : safetyWords) {
            if (text.toLowerCase().contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void showDistressMessage(LayoutInflater inflater, Context context)
    {
        if (currentDistressToast != null)
        {
            currentDistressToast.cancel();
        }

        View layout = inflater.inflate(R.layout.custom_toast_layout, null);

        TextView text = layout.findViewById(R.id.custom_toast_message);
        
        text.setText(context.getString(
                R.string.distress_message)
                + System.lineSeparator()
                + System.lineSeparator()
                + context.getString(R.string.distress_message_contact));

        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

        currentDistressToast = toast;
    }
}
