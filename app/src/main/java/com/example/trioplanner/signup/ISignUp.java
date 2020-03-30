package com.example.trioplanner.signup;

import com.example.trioplanner.IBase;

public interface ISignUp {
    interface View extends IBase {
        void onRegistrationSuccess();
        void onRegistrationFailure(String message);
    }

    interface Presenter{
        void register(String email, String password);
    }

    interface Model{
        void performFirebaseRegistration(String email, String password);
    }

    interface onRegistrationListener{
        void onSuccess();
        void onFailure(String message);
    }
}
