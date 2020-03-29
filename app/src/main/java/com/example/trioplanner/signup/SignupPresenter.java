package com.example.trioplanner.signup;

import android.view.View;

import com.example.trioplanner.firebase.FirebaseAuthClass;

public class SignupPresenter implements ISignUp.IPresenter {
    ISignUp.IView iView;

    public SignupPresenter(ISignUp.IView iView) {
        this.iView = iView;
    }

    @Override
    public void onGoClicked(String email , String password) {
        FirebaseAuthClass firebaseAuthClass = new FirebaseAuthClass(iView);
//        boolean flag =
        firebaseAuthClass.createNewUser(email,password);

    }

    @Override
    public void onLoginClicked() {

    }

    @Override
    public void onResult(boolean flag , String msg) {
        if(flag){
            iView.onSuccess();
        } else {
            iView.onFailure(msg);
        }
    }
}
