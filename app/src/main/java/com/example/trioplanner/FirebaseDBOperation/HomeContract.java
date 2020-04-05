package com.example.trioplanner.FirebaseDBOperation;

import com.example.trioplanner.data.Trip;

import java.util.List;

public interface HomeContract {

    interface Progress {
        void showProgress();

        void hideProgress();
    }

    interface HomeView extends Progress {
        // return all trips to view to update ui
        void getUpcomingTrips(List<Trip> trips);
    }

    interface AddTripView {
        // return string to ui wheart succes or not
        void onTripSaveSuccess(String state);
    }

    interface DeleteTrip {
        // return string to ui wheaher succes or not
        void onDeleteTripSuccess(String state);
    }

    // any action user want to tack will call those methods
    interface HomePresenter {

        void onSaveTrip(Trip trip);

        void onGetUpcomingTrips();

        void onGetHistoryTrips();
        // will be in the Impl call back method recive data changed succsess or not
        // void onTripSaveFailed(String state);
        void onDeleteTrip(String id);

        void onUpdateTrip(String id, Trip trip);

    }

    interface EditTripView {
        void onUpdateTripSuccrss(String state);
    }

    interface HistoryView extends Progress {
        // return all trips to view to update ui
        void getHistoryTrips(List<Trip> historyTrips);
    }

    interface FirebaseModel {

        void saveTripToFB(SaveTripListener saveTripListener, Trip trip);

        void getUpcomingTrips(UpcomingTripLisnter upcomingTripLisnter);

        void getHistroyTrips(HistoryTripsLisntnener historyTripsLisntnener);

        void deleteTrip(DeleteTripListener  deleteTripListener, String id);

        void updateTrip(String id, Trip trip);

        interface SaveTripListener {
            void onFinishedSaved(String status);
        }

        interface UpcomingTripLisnter {
            void getUpcomingTripsComplete(List<Trip> trip);
        }

        interface UpdateTripLisnter {
            void updatedTripComplete(String status);
        }

        interface HistoryTripsLisntnener {
            void getHistoryTripsCompelet(List<Trip> trip);
        }

        interface DeleteTripListener {
            void deleteTripComplete(String status);
        }

    }


}
