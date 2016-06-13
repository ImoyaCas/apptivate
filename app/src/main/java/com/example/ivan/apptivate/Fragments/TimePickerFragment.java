package com.example.ivan.apptivate.Fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.text.format.DateFormat;

import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by ivan on 12/06/2016.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    EditText time;



    public TimePickerFragment(EditText time) {
        this.time = time;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current time as the default values for the picker
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);



        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hour, int minute) {
        time.setInputType(InputType.TYPE_NULL);
        time.setText(hour+":"+minute);
        // Do something with the time chosen by the user
    }

}

