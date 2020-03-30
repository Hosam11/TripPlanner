package com.example.trioplanner.signup;

import com.example.trioplanner.firebase.FirebaseAuthClass;

public class SignupPresenter implements ISignUp.Presenter,ISignUp.onRegistrationListener{

    private ISignUp.View view;
    private FirebaseAuthClass authClass;
    @Override
    public void register(String email, String password) {
        authClass.performFirebaseRegistration(email, password);
    }

    @Override
    public void onSuccess() {
        view.onRegistrationSuccess();
    }

    @Override
    public void onFailure(String message) {
        view.onRegistrationFailure(message);
    }

    public SignupPresenter(ISignUp.View view) {
        this.view = view;
        authClass = new FirebaseAuthClass(this);
    }
}
