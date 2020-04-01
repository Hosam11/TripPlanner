package com.example.trioplanner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trioplanner.FirebaseDBOperation.FirebaseModelImpl;
import com.example.trioplanner.FirebaseDBOperation.HomeContract;
import com.example.trioplanner.FirebaseDBOperation.HomePresenterImpl;
import com.example.trioplanner.data.Trip;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.trioplanner.Uitiles.SAVED_OFFLINE;
import static com.example.trioplanner.Uitiles.SAVED_ONLINE;
import static com.example.trioplanner.Uitiles.checkInternetState;

public class AddTrip extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener,
        HomeContract.AddTripView {

    HomeContract.HomePresenter addTripPresenter;

    @BindView(R.id.name)
    TextInputEditText name;
    @BindView(R.id.epoint)
    TextInputEditText endPoint;
    @BindView(R.id.spoint)
    TextInputEditText startPoint;
    @BindView(R.id.date)
    TextInputEditText date;
    @BindView(R.id.time)
    TextInputEditText time;
    @BindView(R.id.notes)
    TextInputEditText notes;
    @BindView(R.id.roundTrip)
    CheckBox round;

    @OnClick(R.id.add)
    void addTrip(View view) {
        String tripName = name.getText().toString();
        String tripStartPoint = startPoint.getText().toString();
        String tripEndPoint = endPoint.getText().toString();
        String tripType = String.valueOf(isRoundTrip());
        String tripDate = date.getText().toString();
        String tripTime = time.getText().toString();
        String tripNotes = notes.getText().toString();
        String tripStatus = Uitiles.STATUS_UPCOMING;

        //  name - startLoc -  endLoc -  date -  time -  type -  notes
        Trip trip = new Trip(tripName, tripStartPoint, tripEndPoint, tripDate, tripTime,
                tripType, tripNotes, tripStatus, SAVED_ONLINE);
        if (checkInternetState(this)) {
            trip.setIsSavedOnline(SAVED_ONLINE);
            addTripPresenter.onSaveTrip(trip);
        } else {
            trip.setIsSavedOnline(SAVED_OFFLINE);
            // TODO 1- Salah add to room DB
        }

        finish();
    }


    private void getTime() {
        TimePickerFragment timePickerDialogFragment = new TimePickerFragment();
        timePickerDialogFragment.show(getSupportFragmentManager(), "Time Picker");
    }

    private void getDate() {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "Date Picker");
    }

    private Boolean isRoundTrip() {
        return round.isChecked();
    }

    @OnClick(R.id.addDate)
    void addDate(View view) {
        getDate();
    }

    @OnClick(R.id.addTime)
    void addTTime(View view) {
        getTime();
        //Toast.makeText(this,"Butter knife working !",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        ButterKnife.bind(this);

        addTripPresenter = new HomePresenterImpl(new FirebaseModelImpl(), this);


    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.i("TAG", "onTimeSet: ");
        time.setText(hourOfDay + ":" + minute);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date.setText(dayOfMonth + "/" + month + "/" + year);
    }


    @Override
    public void onTripSaveSuccess(String state) {
       // Toast.makeText(this, "Trip Added Successfully", Toast.LENGTH_SHORT).show();
    }
}
