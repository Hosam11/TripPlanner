package com.example.trioplanner.FirebaseDBOperation;

import com.example.trioplanner.data.Trip;

import java.util.List;

public interface HomeContract {

    interface Progress {
        void showProgress();

        void hideProgress();
    }

    interface HomeView extends Progress{


        // return string to ui wheart succes or not
     //   void onTripSaveSuccess(String state);


        void getAllTrips(List<Trip> trips);

    }

    // any action user want to tack will call those methods
    interface HomePresenter {
        // TODO onSaveBtnClick Finished
        void onSaveTrip(Trip trip);

        void onGetAllTrips();

        // will be in the Impl call back method recive data changed succsess or not
        void onTripSaveFailed(String state);

        void onDeleteTrip(String id);

        void onUpdateTrip(String id, Trip trip);

    }

    interface AddTripView {
        // return string to ui wheart succes or not
        void onTripSaveSuccess(String state);

    }


    interface FirebaseModel {

        void saveTripToFB(SaveTripListener saveTripListener, Trip trip);

        void getAllTrips(GetAllTripLisnter getAllTripLisnter);

        void deleteTrip(String id);

        void updateTrip(String id, Trip trip);

        interface SaveTripListener {
            void onFinishedSaved(String status);
        }

        interface GetAllTripLisnter {
            void onGetAllTripsComplete(List<Trip> trip);
        }

    }


}
