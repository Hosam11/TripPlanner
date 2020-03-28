package com.example.trioplanner.signup;

import com.example.trioplanner.IBase;

public interface ISignUp {
    interface IView extends IBase {}
    interface IPresenter{
        public void onGoClicked(String email,String password);
        public void onLoginClicked();
        public  void onResult(boolean flag,String msg);
    }
    interface IModel{
        public  void createNewUser(String email,String password);

    }
}
