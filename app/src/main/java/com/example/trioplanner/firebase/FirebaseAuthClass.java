package com.example.trioplanner.firebase;

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
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthClass implements ISignUp.IModel {
    private FirebaseAuth mAuth;
    private String er = "No Error";
    boolean f;
    ISignUp.IPresenter presenter;
    public FirebaseAuthClass(ISignUp.IView view) {
        mAuth = FirebaseAuth.getInstance();
        presenter = new SignupPresenter(view);
    }

    public void createNewUser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "createUserWithEmail:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                            changeF(true);
                            presenter.onResult(f,"done");
                            //updateUI(user);
                        } else {

                            er = task.getException().getMessage();
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(SignupActivity.class, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
                           // updateUI(null);
                            changeF(false);
                            presenter.onResult(f,er);
                        }

                        // ...

                    }

                });

    }
    private void changeF(boolean flag){
        f = flag;
    }

    public String getEr() {
        return er;
    }
}
