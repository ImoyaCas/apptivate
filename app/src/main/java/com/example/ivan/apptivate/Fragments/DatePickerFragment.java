package com.example.ivan.apptivate.Fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by ivan on 12/06/2016.
 */


@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    EditText fecha;

    public DatePickerFragment(View v) {
        fecha = (EditText)v;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);



        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        month=month+1;
        fecha.setText(year+"-"+month+ "-"+day);
    }

   /* private void actualizarLaFechaEnTextView() {
        tvFechaHora.setText(new StringBuilder()
                .append(dia).append("/")
                .append(mes + 1).append("/")
                .append(año));
    }*/
}
