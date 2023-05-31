package com.guy.class23b_and_6;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Activity_Login extends AppCompatActivity {

    private Button open;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        open = findViewById(R.id.open);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openApp();
            }
        });
        open.setEnabled(false);



        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            login();
        } else {
            checkIfUserInMyServer();
        }

    }

    private void registerUser() {
        EditText editTextText = findViewById(R.id.editTextText);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        User user = new User()
                .setId(firebaseUser.getUid())
                .setPhone(firebaseUser.getPhoneNumber())
                .setName(editTextText.getText().toString());

        MyRTFB.saveNewUser(user);

        open.setEnabled(true);
    }

    private ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            (result) -> {
                IdpResponse response = result.getIdpResponse();

                if (result.getResultCode() == RESULT_OK) {
                    checkIfUserInMyServer();
                } else {
                    // Sign in failed
                    if (response == null) {
                        // User pressed back button
                        showSnackbar("Sign in cancelled");
                        return;
                    }

                    if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                        showSnackbar("No internet connection");
                        return;
                    }

                    showSnackbar("Unknown error");
                    Log.e("pttt", "Sign-in error: ", response.getError());
                }
            });



    private void login() {
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(Arrays.asList(
                        new AuthUI.IdpConfig.PhoneBuilder().build()
                        ))
                .build();

        signInLauncher.launch(signInIntent);
    }

    private void openApp() {
        startActivity(new Intent(Activity_Login.this, MainActivity.class));
        finish();
    }

    private void checkIfUserInMyServer() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        MyRTFB.getUserData(firebaseUser.getUid(), new MyRTFB.CB_User() {
            @Override
            public void data(User user) {
                if (user == null) {
                    registerUser();
                } else {
                    Toast.makeText(Activity_Login.this, "Welcome back " + user.getName(), Toast.LENGTH_LONG).show();
                    openApp();
                }
            }
        });
    }

    private void showSnackbar(String message) {
        Snackbar
                .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setAction("OK", null)
                .show();
    }
}