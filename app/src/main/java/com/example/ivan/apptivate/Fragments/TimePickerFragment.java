package com.example.ivan.apptivate.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;


/**
 * Created by ivan on 12/06/2016.
 */
@SuppressLint("ValidFragment")
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

        if(minute < 10 && minute > 0){
            String cadena = String.valueOf(minute);
            String cero = "0";
            String min = cero+cadena;
            int minu = Integer.parseInt(min);
            Log.i("minu",""+minu);
            minute = minu;
        }

        Log.i("minute",""+minute);


        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hour, int minute) {
        time.setInputType(InputType.TYPE_NULL);
        if(minute < 10 && minute > 0){
            String cadena = String.valueOf(minute);
            String cero = "0";
            String min = cero+cadena;
            int minu = Integer.parseInt(min);
            time.setText(hour+":"+minu);
        }else{
            time.setText(hour+":"+minute);
        }

        // Do something with the time chosen by the user
    }

    /*
    private void actualizarLaHoraEnTextView() {
        time.setText(new StringBuilder()
                .append(hora).append(":")
                .append(minuto));
    }
*/
}

