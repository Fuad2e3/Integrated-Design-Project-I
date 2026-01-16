package com.plantive.idp;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<ChatMessage> messages;

    public ChatAdapter(List<ChatMessage> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatMessage message = messages.get(position);
        holder.tvMessage.setText(message.getText());

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.cardMessage.getLayoutParams();
        if (message.isUser()) {
            params.gravity = Gravity.END;
            holder.cardMessage.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.secondary_green));
        } else {
            params.gravity = Gravity.START;
            holder.cardMessage.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.background_green));
        }
        holder.cardMessage.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage;
        MaterialCardView cardMessage;

        ViewHolder(View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tvMessage);
            cardMessage = itemView.findViewById(R.id.cardMessage);
        }
    }
}
