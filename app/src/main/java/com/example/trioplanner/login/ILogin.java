package com.example.trioplanner.login;

import android.app.Activity;

import com.example.trioplanner.IBase;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface ILogin {
    interface IView extends IBase {
        void onLoginSuccess();
        void onLoginFailure(String message);
    }

    interface IPresenter{
        void login( String email, String password);
        void loginWithGoogle(GoogleSignInAccount account);
    }

    interface IModel{
        void performFirebaseLogin(String email, String password);
        void firebaseAuthWithGoogle(GoogleSignInAccount account);
    }

    interface OnLoginListener{
        void onSuccess();
        void onFailure(String message);
    }
}
