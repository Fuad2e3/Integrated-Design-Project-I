package com.plantive.idp;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class HomeFragment extends Fragment implements TaskAdapter.OnTaskClickListener {

    private TaskAdapter taskAdapter;
    private List<Task> taskList;
    private RecyclerView rvTodaysCare;
    private static final String PREFS_NAME = "PlantivePrefs";
    private static final String KEY_TASKS = "TaskList";

    private TextView tvTemp, tvDesc, tvLocation, tvPlantVoice;
    private ImageView ivWeatherIcon;
    private View weatherOverlay;
    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;

    private static final double DEFAULT_LAT = 23.8103;
    private static final double DEFAULT_LON = 90.4125;
    
    private static final String API_KEY = "895284ada2d033a9473a307d80ba1d02"; 

    private Calendar selectedReminderTime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvTodaysCare = view.findViewById(R.id.rvTodaysCare);
        FloatingActionButton fabAddTask = view.findViewById(R.id.fabAddTask);
        
        tvTemp = view.findViewById(R.id.tvWeatherTemp);
        tvDesc = view.findViewById(R.id.tvWeatherDesc);
        tvLocation = view.findViewById(R.id.tvLocation);
        ivWeatherIcon = view.findViewById(R.id.ivWeatherIcon);
        tvPlantVoice = view.findViewById(R.id.tvPlantVoice);
        weatherOverlay = view.findViewById(R.id.weatherOverlay);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        tvLocation.setText("Updating Weather...");
        tvTemp.setText("--");

        checkLocationPermission();
        loadTasks();
        setupExtraSections(view);
        
        taskAdapter = new TaskAdapter(taskList, this);
        rvTodaysCare.setAdapter(taskAdapter);
        rvTodaysCare.setLayoutManager(new LinearLayoutManager(requireContext()));

        fabAddTask.setOnClickListener(v -> showAddTaskDialog());

        return view;
    }

    private void setupExtraSections(View view) {
        // Mock data for Harvest Countdown
        RecyclerView rvUpcomingHarvest = view.findViewById(R.id.rvUpcomingHarvest);
        List<Task> harvestList = new ArrayList<>();
        harvestList.add(new Task("Tomato Harvest", "12 days left • Progress: 80%", false));
        harvestList.add(new Task("Chili Peppers", "5 days left • Progress: 95%", false));
        rvUpcomingHarvest.setAdapter(new TaskAdapter(harvestList, null));
        rvUpcomingHarvest.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Mock data for Seasonal Guide
        RecyclerView rvSeasonalGuide = view.findViewById(R.id.rvSeasonalGuide);
        List<Plant> seasonalPlants = new ArrayList<>();
        int green = ContextCompat.getColor(requireContext(), R.color.status_green);
        seasonalPlants.add(new Plant("Winter: Cabbage", "Best for Dec-Jan", green, "Seasonal"));
        seasonalPlants.add(new Plant("Winter: Rose", "Blooming season", green, "Seasonal"));
        seasonalPlants.add(new Plant("Winter: Radish", "Fast growing", green, "Seasonal"));
        rvSeasonalGuide.setAdapter(new PlantAdapter(seasonalPlants));
        rvSeasonalGuide.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        // New Mock data for Buy Plant Section
        RecyclerView rvBuyPlant = view.findViewById(R.id.rvBuyPlant);
        List<Plant> storePlants = new ArrayList<>();
        int blue = Color.parseColor("#2196F3"); // Just a diff color for store
        storePlants.add(new Plant("Aloe Vera (Large)", "৳ 250", blue, "Store"));
        storePlants.add(new Plant("Snake Plant (Small)", "৳ 180", blue, "Store"));
        storePlants.add(new Plant("Money Plant (Bushy)", "৳ 320", blue, "Store"));
        storePlants.add(new Plant("Cactus Mix", "৳ 450", blue, "Store"));
        
        rvBuyPlant.setAdapter(new PlantAdapter(storePlants));
        rvBuyPlant.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getDeviceLocation();
        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            fetchWeatherData(DEFAULT_LAT, DEFAULT_LON, "Bangladesh");
        }
    }

    private void getDeviceLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) return;
        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, null)
            .addOnSuccessListener(location -> {
                if (location != null) fetchWeatherData(location.getLatitude(), location.getLongitude(), "My Location");
                else fetchWeatherData(DEFAULT_LAT, DEFAULT_LON, "Bangladesh");
            });
    }

    private void fetchWeatherData(double lat, double lon, String locationName) {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + lat + "&longitude=" + lon + "&current_weather=true";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONObject current = response.getJSONObject("current_weather");
                        double temp = current.getDouble("temperature");
                        int code = current.getInt("weathercode");
                        updateUIWithWeather(temp, code, locationName);
                    } catch (JSONException e) { Log.e("Weather", "JSON Error", e); }
                }, error -> Log.e("Weather", "Volley Error", error)
        );
        Volley.newRequestQueue(requireContext()).add(request);
    }

    private void updateUIWithWeather(double temp, int code, String locationName) {
        tvTemp.setText(Math.round(temp) + "°C");
        tvLocation.setText(locationName.equals("My Location") ? "Current Location" : locationName);
        String desc = getWeatherDescription(code);
        tvDesc.setText(desc);

        if (code >= 51 && code <= 82) { // Rainy
            weatherOverlay.setBackgroundColor(Color.parseColor("#4A90E2"));
            weatherOverlay.setAlpha(0.2f);
        } else if (temp > 30) { // Hot
            weatherOverlay.setBackgroundColor(Color.parseColor("#FFCC33"));
            weatherOverlay.setAlpha(0.1f);
        } else {
            weatherOverlay.setAlpha(0f);
        }

        String[] hotMsgs = {"Whew, it's hot! I'm thirsty.", "Move me to shade!", "More water please!"};
        String[] rainMsgs = {"Ah, I love the rain!", "Natural bath time!", "No watering needed today."};
        String[] normalMsgs = {"Feeling great today!", "I'm growing taller!", "Thanks for caring."};
        
        Random r = new Random();
        if (temp > 30) tvPlantVoice.setText(hotMsgs[r.nextInt(3)]);
        else if (code >= 51 && code <= 82) tvPlantVoice.setText(rainMsgs[r.nextInt(3)]);
        else tvPlantVoice.setText(normalMsgs[r.nextInt(3)]);
    }

    private String getWeatherDescription(int code) {
        if (code == 0) return "Clear Sky";
        if (code >= 1 && code <= 3) return "Partly Cloudy";
        if (code >= 51 && code <= 67) return "Rainy Day";
        if (code >= 80 && code <= 82) return "Rain Showers";
        return "Cloudy";
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getDeviceLocation();
        }
    }

    private void saveTasks() {
        if (getContext() == null) return;
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(KEY_TASKS, gson.toJson(taskList));
        editor.apply();
    }

    private void loadTasks() {
        if (getContext() == null) { taskList = new ArrayList<>(); return; }
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_TASKS, null);
        Type type = new TypeToken<ArrayList<Task>>() {}.getType();
        taskList = gson.fromJson(json, type);
        if (taskList == null) {
            taskList = new ArrayList<>();
            taskList.add(new Task("Water Monstera", "200ml • Room temp", false));
            saveTasks();
        }
    }

    public void showAddTaskDialog() {
        if (getContext() == null) return;
        selectedReminderTime = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Add New Task");
        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edit_task, (ViewGroup) getView(), false);
        final EditText inputTitle = viewInflated.findViewById(R.id.etTaskTitle);
        final EditText inputDesc = viewInflated.findViewById(R.id.etTaskDesc);
        final MaterialButton btnSetReminder = viewInflated.findViewById(R.id.btnSetReminder);
        final TextView tvReminderTime = viewInflated.findViewById(R.id.tvReminderTime);
        btnSetReminder.setOnClickListener(v -> showTimePicker(tvReminderTime));
        builder.setView(viewInflated);
        builder.setPositiveButton("Add", (dialog, which) -> {
            String title = inputTitle.getText().toString();
            if (!title.isEmpty()) {
                Task newTask = new Task(title, inputDesc.getText().toString(), false);
                if (selectedReminderTime != null) {
                    newTask.setReminderTime(selectedReminderTime.getTimeInMillis());
                    newTask.setReminderActive(true);
                    scheduleNotification(newTask);
                }
                taskList.add(newTask);
                taskAdapter.notifyItemInserted(taskList.size() - 1);
                saveTasks();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void showTimePicker(TextView tvTimeDisplay) {
        Calendar currentTime = Calendar.getInstance();
        new TimePickerDialog(requireContext(), (view, hourOfDay, minuteOfHour) -> {
            selectedReminderTime = Calendar.getInstance();
            selectedReminderTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            selectedReminderTime.set(Calendar.MINUTE, minuteOfHour);
            selectedReminderTime.set(Calendar.SECOND, 0);
            tvTimeDisplay.setText(String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minuteOfHour));
        }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime.get(Calendar.MINUTE), true).show();
    }

    private void scheduleNotification(Task task) {
        Intent intent = new Intent(requireContext(), ReminderReceiver.class);
        intent.putExtra("taskTitle", task.getTitle());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(requireContext(), (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) alarmManager.setExact(AlarmManager.RTC_WAKEUP, task.getReminderTime(), pendingIntent);
    }

    @Override public void onTaskEditClick(int position) { showEditTaskDialog(position); }
    @Override public void onTaskStatusChanged(int position, boolean isChecked) { taskList.get(position).setCompleted(isChecked); saveTasks(); }

    private void showEditTaskDialog(int position) {
        Task task = taskList.get(position);
        selectedReminderTime = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Edit Task");
        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edit_task, (ViewGroup) getView(), false);
        final EditText inputTitle = viewInflated.findViewById(R.id.etTaskTitle);
        final EditText inputDesc = viewInflated.findViewById(R.id.etTaskDesc);
        final MaterialButton btnSetReminder = viewInflated.findViewById(R.id.btnSetReminder);
        final TextView tvReminderTime = viewInflated.findViewById(R.id.tvReminderTime);
        inputTitle.setText(task.getTitle());
        inputDesc.setText(task.getDescription());
        if (task.isReminderActive()) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(task.getReminderTime());
            tvReminderTime.setText(String.format(Locale.getDefault(), "%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE)));
        }
        btnSetReminder.setOnClickListener(v -> showTimePicker(tvReminderTime));
        builder.setView(viewInflated);
        builder.setPositiveButton("Save", (dialog, which) -> {
            task.setTitle(inputTitle.getText().toString());
            task.setDescription(inputDesc.getText().toString());
            if (selectedReminderTime != null) {
                task.setReminderTime(selectedReminderTime.getTimeInMillis());
                task.setReminderActive(true);
                scheduleNotification(task);
            }
            taskAdapter.notifyItemChanged(position);
            saveTasks();
        });
        builder.setNeutralButton("Delete", (dialog, which) -> {
            taskList.remove(position);
            taskAdapter.notifyItemRemoved(position);
            saveTasks();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }
}
