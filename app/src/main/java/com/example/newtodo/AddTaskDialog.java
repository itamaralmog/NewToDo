package com.example.newtodo;



import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Calendar;

public class AddTaskDialog extends AppCompatDialogFragment {
    private MyDialog onSaveClickListener;
    private String taskDescription;
    private static final String CHANNEL_ID = "my_channel";
    private static final int NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;

    // Default values for date and time
    private TaskData taskDataDate = new TaskData();
    private TaskData taskDataTime = new TaskData();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        onSaveClickListener = (MyDialog) getParentFragment();
        builder.setView(view)
                .setTitle("Add task")
                .setNegativeButton("Cancel", (dialogInterface, i) -> {
                    onSaveClickListener.onDialogNegativeClick();
                })
                .setPositiveButton("Save", (dialogInterface, i) -> {
                    EditText editTextDescription = view.findViewById(R.id.dialogDescription);
                    taskDescription = editTextDescription.getText().toString();

                    TaskData task = new TaskData("", taskDescription,
                            taskDataDate.getYear(), taskDataDate.getMonth(),
                            taskDataDate.getDay(), taskDataTime.getHour(),
                            taskDataTime.getMinutes());
                    int m = task.getMonth();
                    task.setMonth(m+1);
                    Log.d("AddTaskDialod","AddTaskDialog" + " " + task.getMonth());
                    onSaveClickListener.onDialogPositiveClick(task);
                    task.setMonth(m);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        setNotificationAlarm(task);
                    }

                });
        Button dateButton = view.findViewById(R.id.date);
        Button timeButton = view.findViewById(R.id.time);
        dateButton.setOnClickListener(v -> openDate());
        timeButton.setOnClickListener(v -> openTime());

        return builder.create();

    }


    public void openDate() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(getContext(), (datePicker, year, month, day) ->
                taskDataDate = new TaskData(year, month, day), currentYear, currentMonth, currentDay);
        dialog.show();
    }

    public void openTime() {
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(getContext(), (timePicker, hour, minute) ->
                taskDataTime = new TaskData(hour, minute), currentHour, currentMinute, true);
        dialog.show();
    }


@RequiresApi(api = Build.VERSION_CODES.S)
    public void setNotificationAlarm(TaskData task) {

    AlarmManager manager = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);
    Calendar notification = Calendar.getInstance();
    Log.d("setNotificationAlarm time", "showNotification: enter to setNotification " + task.getDate());
    notification.set(Calendar.YEAR, task.getYear());
    notification.set(Calendar.MONTH, task.getMonth());
    notification.set(Calendar.DAY_OF_MONTH, task.getDay());
    notification.set(Calendar.HOUR_OF_DAY, task.getHour());
    notification.set(Calendar.MINUTE, task.getMinutes());
    notification.set(Calendar.SECOND, 0);

    Intent myIntent = new Intent(getContext(), NotificationReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, myIntent, PendingIntent.FLAG_MUTABLE);
    manager.set(
            AlarmManager.RTC_WAKEUP
            , notification.getTimeInMillis()
            , pendingIntent);
}
}
