package com.company.banu.SignIn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.company.banu.ShowProfile.ViewShowProfile;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class PresenterSingIn {
    static final int RC_SIGN_IN = 2323;

    Activity activity;
    public PresenterSingIn(Activity activity) {
        this.activity = activity;
    }

    public void signIn() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build());

        activity.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

    public void signInReturn(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == activity.RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                Toast.makeText(activity, "Sign In succeed " + user.getEmail(), Toast.LENGTH_LONG).show();
                activity.finish();
            } else {
                Toast.makeText(activity, "Sign In failed", Toast.LENGTH_LONG).show();
            }
        }
    }
}
