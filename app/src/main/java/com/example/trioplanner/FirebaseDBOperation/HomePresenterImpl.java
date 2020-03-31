package com.example.trioplanner.FirebaseDBOperation;

import com.example.trioplanner.data.Trip;

import java.util.List;

public class HomePresenterImpl implements HomeContract.HomePresenter,
        HomeContract.FirebaseModel.SaveTripListener, HomeContract.FirebaseModel.GetAllTripLisnter {

    HomeContract.FirebaseModel firebaseModel;
    HomeContract.HomeView homeView;


    public HomePresenterImpl(HomeContract.FirebaseModel firebaseModel, HomeContract.HomeView homeView) {
        this.firebaseModel = firebaseModel;
        this.homeView = homeView;
    }

    @Override
    public void onSaveTrip(Trip trip) {
        firebaseModel.saveTripToFB(this, trip);
    }

    @Override
    public void onGetAllTrips() {
        homeView.showProgress();
        firebaseModel.getAllTrips(this);
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
        homeView. onTripSaveSuccess(status);
    }


    @Override
    public void onGetAllTripsComplete(List<Trip> trips) {
        homeView.getAllTrips(trips);
        homeView.hideProgress();
    }
}
