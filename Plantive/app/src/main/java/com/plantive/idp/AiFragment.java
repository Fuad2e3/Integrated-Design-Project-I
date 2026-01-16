package com.plantive.idp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AiFragment extends Fragment {

    private ChatAdapter chatAdapter;
    private List<ChatMessage> messageList;
    private RecyclerView rvChat;
    private EditText etMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ai, container, false);

        rvChat = view.findViewById(R.id.rvChat);
        etMessage = view.findViewById(R.id.etMessage);
        ImageButton btnSend = view.findViewById(R.id.btnSend);

        messageList = new ArrayList<>();
        // Initial AI greeting
        messageList.add(new ChatMessage("Hello! I'm your Plantive AI assistant. How can I help with your plants today?", false));

        chatAdapter = new ChatAdapter(messageList);
        rvChat.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvChat.setAdapter(chatAdapter);

        btnSend.setOnClickListener(v -> {
            String msg = etMessage.getText().toString().trim();
            if (!msg.isEmpty()) {
                addUserMessage(msg);
                etMessage.setText("");
                generateAiResponse(msg);
            }
        });

        return view;
    }

    private void addUserMessage(String message) {
        messageList.add(new ChatMessage(message, true));
        chatAdapter.notifyItemInserted(messageList.size() - 1);
        rvChat.scrollToPosition(messageList.size() - 1);
    }

    private void addAiMessage(String message) {
        messageList.add(new ChatMessage(message, false));
        chatAdapter.notifyItemInserted(messageList.size() - 1);
        rvChat.scrollToPosition(messageList.size() - 1);
    }

    private void generateAiResponse(String userMsg) {
        // Simple mock AI logic
        String response;
        String lowerMsg = userMsg.toLowerCase();

        if (lowerMsg.contains("water")) {
            response = "Most indoor plants like to be watered when the top inch of soil feels dry. Would you like care tips for a specific plant?";
        } else if (lowerMsg.contains("yellow") || lowerMsg.contains("leaves")) {
            response = "Yellow leaves can be a sign of overwatering or lack of nutrients. Check if the soil is too wet.";
        } else if (lowerMsg.contains("sun") || lowerMsg.contains("light")) {
            response = "Different plants need different light. Succulents love direct sun, while Monsteras prefer bright, indirect light.";
        } else if (lowerMsg.contains("hi") || lowerMsg.contains("hello")) {
            response = "Hi there! I'm ready to answer any gardening questions.";
        } else {
            response = "That's a great question! I'm still learning, but you can ask me about watering, yellow leaves, or sunlight requirements.";
        }

        // Simulate typing delay
        new Handler(Looper.getMainLooper()).postDelayed(() -> addAiMessage(response), 1000);
    }
}
