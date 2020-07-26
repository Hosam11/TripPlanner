package com.example.trioplanner.FirebaseDBOperation;

import android.util.Log;

import com.example.trioplanner.data.Trip;

import java.util.List;


public class HomePresenterImpl implements HomeContract.HomePresenter,
        HomeContract.FirebaseModel.SaveTripListener,
        HomeContract.FirebaseModel.UpcomingTripLisnter,
        HomeContract.FirebaseModel.UpdateTripLisnter,
        HomeContract.FirebaseModel.HistoryTripsLisntnener,
        HomeContract.FirebaseModel.DeleteTripListener {

    public static final String TAG = "hproj";
    HomeContract.FirebaseModel firebaseModel;
    HomeContract.HomeView homeView;
    HomeContract.AddTripView addTripView;
    HomeContract.EditTripView editTripView;
    HomeContract.HistoryView historyView;

    public HomePresenterImpl(HomeContract.FirebaseModel firebaseModel,
                             HomeContract.DeleteTrip deleteTrip) {
        this.firebaseModel = firebaseModel;
        this.deleteTrip = deleteTrip;
    }

    HomeContract.DeleteTrip deleteTrip;

    public HomePresenterImpl(HomeContract.FirebaseModel firebaseModel,
                             HomeContract.HistoryView historyView) {
        this.firebaseModel = firebaseModel;
        this.historyView = historyView;
    }

    public HomePresenterImpl(HomeContract.FirebaseModel firebaseModel,
                             HomeContract.EditTripView editTripView) {
        this.firebaseModel = firebaseModel;
        this.editTripView = editTripView;
    }

    public HomePresenterImpl(HomeContract.FirebaseModel firebaseModel,
                             HomeContract.HomeView homeView) {
        this.firebaseModel = firebaseModel;
        this.homeView = homeView;
    }

    public HomePresenterImpl(HomeContract.FirebaseModel firebaseModel,
                             HomeContract.AddTripView addTripView) {
        this.firebaseModel = firebaseModel;
        this.addTripView = addTripView;
    }


    @Override
    public void onSaveTrip(Trip trip) {
        firebaseModel.saveTripToFB(this, trip);
    }

    @Override
    public void onGetUpcomingTrips() {
        Log.i(TAG, "HomePresenterImpl >> onGetAllTrips: ");
        homeView.showProgress();
        firebaseModel.getUpcomingTrips(this);
    }

    @Override
    public void onGetHistoryTrips() {
        historyView.showProgress();
        firebaseModel.getHistroyTrips(this);

    }

    @Override
    public void onDeleteTrip(String id) {
        firebaseModel.deleteTrip(this, id);
    }

    @Override
    public void onUpdateTrip(String id, Trip trip) {
        firebaseModel.updateTrip(id, trip);
    }

    @Override
    public void onFinishedSaved(String status) {
        addTripView.onTripSaveSuccess(status);
    }


    @Override
    public void getUpcomingTripsComplete(List<Trip> trips) {
        homeView.getUpcomingTrips(trips);
        homeView.hideProgress();
    }

    @Override
    public void updatedTripComplete(String status) {
        editTripView.onUpdateTripSuccrss(status);
    }

    @Override
    public void getHistoryTripsCompelet(List<Trip> trip) {
        historyView.getHistoryTrips(trip);
        historyView.hideProgress();

    }

    @Override
    public void deleteTripComplete(String status) {

    }
}
