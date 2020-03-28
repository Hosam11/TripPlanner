package com.example.trioplanner.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.trioplanner.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignupActivity extends AppCompatActivity implements  ISignUp.IView{
   // TextInputEditText username;
    TextInputEditText email;
    //TextInputEditText email2;
    TextInputEditText password;
    Button goBtn;
   // Button signUpLoginBtn;
    ISignUp.IPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email = findViewById(R.id.emailEd);
        password = findViewById(R.id.passwordED);
        goBtn = findViewById(R.id.goBtn);
        presenter = new SignupPresenter(this);
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onGoClicked(email.getText().toString(),password.getText().toString());
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void endProgress() {

    }

    @Override
    public void onSuccess() {
        Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }


}
