
package com.example.trioplanner;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trioplanner.FirebaseDBOperation.FirebaseModelImpl;
import com.example.trioplanner.FirebaseDBOperation.HomeContract;
import com.example.trioplanner.FirebaseDBOperation.HomePresenterImpl;
import com.example.trioplanner.data.Trip;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.trioplanner.Uitiles.SAVED_OFFLINE;
import static com.example.trioplanner.Uitiles.SAVED_ONLINE;
import static com.example.trioplanner.Uitiles.TAG;

public class
ViewEditTrip extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener,
        HomeContract.EditTripView {


    @BindView(R.id.consAddTrip)
    View consViewGroup;

    @BindView(R.id.name)
    TextInputEditText name;
    //    @BindView(R.id.epoint)
//    TextInputEditText endPoint;
//    @BindView(R.id.spoint)
//    TextInputEditText startPoint;
    @BindView(R.id.date)
    TextInputEditText date;
    @BindView(R.id.time)
    TextInputEditText time;
    @BindView(R.id.notes)
    TextInputEditText notes;
    @BindView(R.id.roundTrip)
    CheckBox round;

    @BindView(R.id.add)
    Button btnViewEdit;

    @BindView(R.id.addTime)
    Button btnAddTime;

    @BindView(R.id.addDate)
    Button btnAddData;

    Trip tripFormIntent;
    boolean isView;

    HomeContract.HomePresenter editTripPresenter;

    String tripNameStartPoint = "";
    String tripNameEndPoint = "";

    private LatLng latLngStartPointloc;
    private LatLng latLngEndPointloc;

    private String LatLngString1;
    private String LatLngString2;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.add)
    void addTrip(View view) {
        String tripName = name.getText().toString();
        String tripType = String.valueOf(isRoundTrip());
        String tripDate = date.getText().toString();
        String tripTime = time.getText().toString();
        String tripNotes = notes.getText().toString();
        String tripStatus = Uitiles.STATUS_UPCOMING;
        Log.i(TAG, "addTrip: Click");

        if (isView) {
            btnViewEdit.setText("Submit");
            name.setEnabled(true);
            date.setEnabled(true);
            time.setEnabled(true);
            notes.setEnabled(true);
            round.setEnabled(true);
            btnAddData.setEnabled(true);
            btnAddTime.setEnabled(true);
            isView = false;
        } else {

            /* start  for names of location
             check for name of loctions if null get it from current object
             else that mean user change it recive that changr in  own variable
            */
            if (tripNameStartPoint.isEmpty()) {
                tripNameStartPoint = tripFormIntent.getStartLoc();
            }

            if (tripNameEndPoint.isEmpty()) {
                tripNameEndPoint = tripFormIntent.getEndLoc();
            }
            // end  for names of location


            // check for latlng for first location object if null get i according string directly from object
            if (latLngStartPointloc == null) {
                LatLngString1 =
                        tripFormIntent.getLatLngString1().split("_")[0]
                        + "_" +
                        tripFormIntent.getLatLngString1().split("_")[1];
                //  LatLngString1 = latLngStartPointloc.latitude + "_" + latLngStartPointloc.longitude;
            } else {
                // returend from methed below
                LatLngString1 = latLngStartPointloc.latitude + "_" + latLngStartPointloc.longitude;
            }

            // check for latlng for second location object if null get i according string directly from object
            if (latLngEndPointloc == null) {
                LatLngString2 =
                        tripFormIntent.getLatLngString2().split("_")[0] +
                        "_" +
                        tripFormIntent.getLatLngString2().split("_")[1];

//                LatLngString2 = latLngEndPointloc.latitude + "_" + latLngEndPointloc.longitude;
            } else {
                // returend from methed below
                LatLngString2 = latLngEndPointloc.latitude + "_" + latLngEndPointloc.longitude;
            }

//            if (tripName.isEmpty() || tripNameStartPoint.isEmpty()
//                    || tripNameEndPoint.isEmpty()|| tripDate.isEmpty()
//                    || tripTime.isEmpty() || tripNotes.isEmpty()
//                    || tripType.isEmpty()) {
//               // Uitiles.showCustomDialog(consViewGroup, "Please fill all fields", this, "Warning!");
//            } else {

            //  name - startLoc - endLoc - date -  time -  type -  notes
            Trip trip = new Trip(tripName,
                    tripNameStartPoint, tripNameEndPoint,
                    tripDate, tripTime,
                    tripType, tripNotes,
                    tripStatus, SAVED_ONLINE);

            trip.setId(tripFormIntent.getId());
            trip.setLatLngString1(LatLngString1);
            trip.setLatLngString2(LatLngString2);

            Log.i(TAG, "##ViewEditTrip>> addTrip: " + trip.toString());

            Log.i(TAG, "The update obj is >> addTrip: ");
            // Edit in db
            if (Uitiles.checkInternetState(this)) {
                Calendar calendar = Calendar.getInstance();

                trip.setIsSavedOnline(SAVED_ONLINE);
                editTripPresenter.onUpdateTrip(trip.getId(), trip);

              /*  AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(ViewEditTrip.this, AlertReceiver.class);
                PendingIntent pi = PendingIntent.getBroadcast(ViewEditTrip.this, 1, intent, 0);
                alarmManager.cancel(pi);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
*/
            } else {
                trip.setIsSavedOnline(SAVED_OFFLINE);
                  /* TODO  2- salah edit in room or shpw that below message
                  if there not in room please sync with firebase
                  in case he clicked the trip then try to to edit
                  not yet that trip move to room
                */
            }

            // after click submit set all inputs to disable to next click
            btnViewEdit.setText("Edit");
            isView = true;
            name.setEnabled(false);
//            startPoint.setEnabled(false);
//            endPoint.setEnabled(false);
            date.setEnabled(false);
            time.setEnabled(false);
            notes.setEnabled(false);
            round.setEnabled(false);
            btnAddData.setEnabled(false);
            btnAddTime.setEnabled(false);
            //finish();
        }
//        }


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
        setContentView(R.layout.activity_view_edit_trip);
        ButterKnife.bind(this);
        isView = true;
        Intent intent = getIntent();
        tripFormIntent = (Trip) intent.getSerializableExtra(Uitiles.KEY_PASS_TRIP);
        name.setText(tripFormIntent.getName());
        // startPoint.setText(tripFormIntent.getStartLoc());
        // endPoint.setText(tripFormIntent.getEndLoc());
        date.setText(tripFormIntent.getDate());
        time.setText(tripFormIntent.getTime());
        notes.setText(tripFormIntent.getNotes());
        round.setChecked(Boolean.parseBoolean(tripFormIntent.getType()));

        editTripPresenter = new HomePresenterImpl(new FirebaseModelImpl(), this);
        Places.initialize(getApplicationContext(), "AIzaSyDuifm35ZNF7OOG7exwhOrda3mb1H8qFnA");
        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment1 = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.spoint1);

        autocompleteFragment1.setText(tripFormIntent.getStartLoc());


        AutocompleteSupportFragment autocompleteFragment2 = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.epoint1);
        autocompleteFragment2.setText(tripFormIntent.getEndLoc());
        // Set up a PlaceSelectionListener to handle the response.
        // Specify the types of place data to return.
        autocompleteFragment1.setPlaceFields(Arrays.asList(Place.Field.LAT_LNG, Place.Field.NAME));
        autocompleteFragment2.setPlaceFields(Arrays.asList(Place.Field.LAT_LNG, Place.Field.NAME));
        autocompleteFragment1.setCountry("EG");
        autocompleteFragment2.setCountry("EG");
        autocompleteFragment1.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Hossam Get info about the selected place.
                tripNameStartPoint = place.getName();
                latLngStartPointloc = place.getLatLng();

            //    Toast.makeText(getApplicationContext(), " " + place.getLatLng(), Toast.LENGTH_SHORT).show();
                Log.i("TAG", "Place: " + place.getName() + ", " + place.getId());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("TAG", "An error occurred: " + status);
            }
        });

        autocompleteFragment2.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Hossam Get info about the selected place.
                tripNameEndPoint = place.getName();
                latLngEndPointloc = place.getLatLng();
           //     Toast.makeText(getApplicationContext(), " " + place.getName(), /.LENGTH_SHORT).show();
                Log.i("TAG", "Place: " + place.getName() + ", " + place.getId());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("TAG", "An error occurred: " + status);
            }
        });
        Log.i(TAG, "onCreate: >> tripFrom Entent >> " + tripFormIntent.toString());

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
    public void onUpdateTripSuccrss(String state) {
        //  Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
    }
}

