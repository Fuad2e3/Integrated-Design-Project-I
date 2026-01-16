package com.plantive.idp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.checkbox.MaterialCheckBox;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> tasks;
    private OnTaskClickListener listener;

    public interface OnTaskClickListener {
        void onTaskEditClick(int position);
        void onTaskStatusChanged(int position, boolean isChecked);
    }

    public TaskAdapter(List<Task> tasks, OnTaskClickListener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.tvTitle.setText(task.getTitle());
        holder.tvDesc.setText(task.getDescription());
        
        // Remove listener before setting to avoid triggering during scroll
        holder.cbTask.setOnCheckedChangeListener(null);
        holder.cbTask.setChecked(task.isCompleted());
        
        holder.cbTask.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setCompleted(isChecked);
            if (listener != null) {
                listener.onTaskStatusChanged(position, isChecked);
            }
        });

        holder.ivEdit.setOnClickListener(v -> {
            if (listener != null) {
                listener.onTaskEditClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDesc;
        MaterialCheckBox cbTask;
        ImageView ivEdit;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTaskTitle);
            tvDesc = itemView.findViewById(R.id.tvTaskDescription);
            cbTask = itemView.findViewById(R.id.cbTask);
            ivEdit = itemView.findViewById(R.id.ivEditTask); // Need to check if this ID exists
        }
    }
}
