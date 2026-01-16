package com.plantive.idp;

public class Task {
    private String title;
    private String description;
    private boolean isCompleted;
    private long reminderTime;
    private boolean isReminderActive;

    public Task(String title, String description, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.reminderTime = 0;
        this.isReminderActive = false;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    public long getReminderTime() { return reminderTime; }
    public void setReminderTime(long reminderTime) { this.reminderTime = reminderTime; }

    public boolean isReminderActive() { return isReminderActive; }
    public void setReminderActive(boolean reminderActive) { isReminderActive = reminderActive; }
}
