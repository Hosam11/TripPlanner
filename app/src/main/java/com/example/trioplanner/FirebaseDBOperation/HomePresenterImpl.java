package com.example.trioplanner.FirebaseDBOperation;

import android.util.Log;

import com.example.trioplanner.data.Trip;

import java.util.List;


public class HomePresenterImpl implements HomeContract.HomePresenter,
        HomeContract.FirebaseModel.SaveTripListener,
        HomeContract.FirebaseModel.GetAllTripLisnter {

    public static final String TAG = "hproj";
    HomeContract.FirebaseModel firebaseModel;
    HomeContract.HomeView homeView;
    HomeContract.AddTripView addTripView;

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
    public void onGetAllTrips() {
        Log.i(TAG, "HomePresenterImpl >> onGetAllTrips: ");
        homeView.showProgress();
        firebaseModel.getAllTrips(this);
    }

    @Override
    public void onTripSaveFailed(String state) {

    }

    @Override
    public void onDeleteTrip(String id) {
        firebaseModel.deleteTrip(id);
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
    public void onGetAllTripsComplete(List<Trip> trips) {
        homeView.getAllTrips(trips);
        homeView.hideProgress();
    }
}
