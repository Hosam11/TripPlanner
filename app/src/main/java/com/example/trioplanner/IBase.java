package com.example.trioplanner;

public interface IBase {
    public void showProgress();
    public void endProgress();

    public void onSuccess();
    public void onFailure(String error);
}
