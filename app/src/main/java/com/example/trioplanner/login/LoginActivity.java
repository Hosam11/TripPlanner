package com.example.trioplanner.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.trioplanner.HomeView.Home;
import com.example.trioplanner.R;
import com.example.trioplanner.signup.SignupActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.shobhitpuri.custombuttons.GoogleSignInButton;

public class LoginActivity extends AppCompatActivity implements ILogin.IView {
    TextInputEditText edtEmail, edtPassword;
    Button toSignUpBtn,loginBtn;
    GoogleSignInButton googleBtn;
    private LoginPresenter loginPresenter;
    ProgressBar progressBar;
    private String email,password;
    ScrollView scrollView;
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = findViewById(R.id.email_login);
        edtPassword = findViewById(R.id.password_login);
        toSignUpBtn = findViewById(R.id.toSignup);
        loginBtn = findViewById(R.id.loginBtn);
        googleBtn = findViewById(R.id.googleBtn);
        progressBar = findViewById(R.id.progressBar);
        scrollView = findViewById(R.id.scrollview);
        loginPresenter = new LoginPresenter(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            finish();
        } else {
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = edtPassword.getText().toString();
                email = edtEmail.getText().toString();
                checkRegistrationDetails();
            }
        });
        toSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
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

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("please enter valid email address");
            edtEmail.requestFocus();
        }
        if (email.equals("")) {
            edtEmail.setError("please enter email address");
            edtEmail.requestFocus();
        }

        if (!email.equals("") &&
                password.length() >= 6 &&
                !password.trim().equals("") &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showProgress();
            loginPresenter.login(email,password);
        }

    }
    @Override
    public void onLoginSuccess() {
        endProgress();
        SaveSharedPreference.setLoggedIn(getApplicationContext(), true);
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailure(String message) {
        endProgress();
     //   Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                showProgress();
               loginPresenter.loginWithGoogle(account);
            } catch (ApiException e) {
              //  Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
