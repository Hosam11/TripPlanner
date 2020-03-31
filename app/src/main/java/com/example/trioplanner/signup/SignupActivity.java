package com.example.trioplanner.signup;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.trioplanner.login.LoginActivity;
import com.example.trioplanner.R;
import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity implements ISignUp.View {

    TextInputEditText edtEmail, edtPassword , edtPassword2;
    Button signUpBtn, toLoginBtn;
    private SignupPresenter signupPresenter;
    ProgressBar progressBar;
    private String email,password,password2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtEmail = findViewById(R.id.emailEd);
        edtPassword = findViewById(R.id.passwordED);
        edtPassword2 = findViewById(R.id.passwordED2);
        signUpBtn = findViewById(R.id.goBtn);
        toLoginBtn = findViewById(R.id.signupLoginBtn);
        progressBar = findViewById(R.id.progressBar3);
        signupPresenter = new SignupPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtEmail.getText().toString().trim();
                password = edtPassword.getText().toString().trim();
                password2 = edtPassword2.getText().toString().trim();
                checkRegistrationDetails();
            }
        });

        toLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });
    }

    private void checkRegistrationDetails() {
        if (password.length() < 6) {
            edtPassword.setError("password minimum contain 6 character");
                    edtPassword.requestFocus();
        }
        if (password.equals("")) {
            edtPassword.setError("please enter password");
                    edtPassword.requestFocus();
        }
        if (password2.equals("")) {
            edtPassword2.setError("please enter password");
            edtPassword2.requestFocus();
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("please enter valid email address");
                    edtEmail.requestFocus();
        }
        if (email.equals("")) {
            edtEmail.setError("please enter email address");
                    edtEmail.requestFocus();
        }
        if(!password.equals(password2)){
            edtPassword2.setError("passwords don't match");
            edtPassword2.requestFocus();
        }
        if (!email.equals("") &&
                password.length() >= 6 &&
                !password.trim().equals("") &&
                password2.length() >= 6 &&
                !password2.trim().equals("") &&
                password.equals(password2) &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    showProgress();
                    signupPresenter.register(email,password);
                }

    }
    @Override
    public void onRegistrationSuccess() {
        endProgress();
        Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show();
    }

    @Override

    public void onRegistrationFailure(String message) {
        endProgress();
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endProgress() {
        progressBar.setVisibility(View.GONE);
    }

}
