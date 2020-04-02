package com.example.trioplanner.login;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class LoginPresenter implements ILogin.IPresenter,ILogin.OnLoginListener {
    private ILogin.IView view;
    private LoginModel model;

    public LoginPresenter(ILogin.IView view) {
        this.view = view;
        model = new LoginModel(this);
    }

    @Override
    public void login(String email, String password) {
        model.performFirebaseLogin(email, password);
    }

    @Override
    public void loginWithGoogle(GoogleSignInAccount account) {
        model.firebaseAuthWithGoogle(account);
    }

    @Override
    public void onSuccess() {
        view.onLoginSuccess();
    }

    @Override
    public void onFailure(String message) {
        view.onLoginFailure(message);
    }
}
