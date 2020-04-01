package com.example.trioplanner.firebase;


import androidx.annotation.NonNull;

import com.example.trioplanner.signup.ISignUp;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.trioplanner.signup.ISignUp;
import com.example.trioplanner.signup.SignupActivity;
import com.example.trioplanner.signup.SignupPresenter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class FirebaseAuthClass implements ISignUp.Model {
    private ISignUp.onRegistrationListener registrationListener;

    public FirebaseAuthClass(ISignUp.onRegistrationListener registrationListener) {
        this.registrationListener = registrationListener;
    }

    @Override
    public void performFirebaseRegistration(String email, String password) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            registrationListener.onFailure(task.getException().getMessage());
                        }else{
                            registrationListener.onSuccess();
                        }
                    }
                });
    }

}
