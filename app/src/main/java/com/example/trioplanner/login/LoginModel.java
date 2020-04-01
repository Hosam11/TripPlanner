package com.example.trioplanner.login;
import androidx.annotation.NonNull;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.GoogleAuthProvider;


public class LoginModel implements ILogin.IModel{
    private ILogin.OnLoginListener loginListener;

    public LoginModel(ILogin.OnLoginListener loginListener) {
        this.loginListener = loginListener;
    }

    @Override
    public void performFirebaseLogin(String email, String password) {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            loginListener.onSuccess();
                        }
                        else {
                            loginListener.onFailure(task.getException().getMessage());
                        }
                    }
                });
    }

    @Override
    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loginListener.onSuccess();
                        } else {
                            loginListener.onFailure(task.getException().getMessage());
                        }
                    }
                });
    }


}
