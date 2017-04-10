package com.example.android.amity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
//import com.google.firebase.auth.FirebaseAuth;

/**
 * Activity to demonstrate basic retrieval of the Google user's ID, email address, and basic
 * profile.
 */
public class LoginActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,
        View.OnClickListener {

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private GoogleApiClient mGoogleApiClient;
    //   private TextView mStatusTextView;
    private ProgressDialog mProgressDialog;
    private boolean logout = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logout = false;

        //  Toast.makeText(LoginActivity.this, "login activity created", Toast.LENGTH_SHORT).show();
        // Views
        // mStatusTextView = (TextView) findViewById(R.id.status);

        // Button listeners
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        //  findViewById(R.id.sign_out_button).setOnClickListener(this);
        //  findViewById(R.id.disconnect_button).setOnClickListener(this);

        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // [END configure_signin]

        // [START build_client]
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        // [END build_client]

        // [START customize_button]
        // Set the dimensions of the sign-in button.
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        // [END customize_button]
    }

    @Override
    public void onStart() {
        super.onStart();
        //  Toast.makeText(LoginActivity.this, "login activity started, logout: " + logout, Toast.LENGTH_SHORT).show();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            //     Toast.makeText(LoginActivity.this, "opr is done", Toast.LENGTH_SHORT).show();
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {

            //   Toast.makeText(LoginActivity.this, "opr is not done", Toast.LENGTH_SHORT).show();
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //     Toast.makeText(LoginActivity.this, "on activity result method", Toast.LENGTH_SHORT).show();

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    // [END onActivityResult]

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {

        //  Toast.makeText(LoginActivity.this, "handleSignInResult:" + result.isSuccess(), Toast.LENGTH_SHORT).show();

        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess() && !logout) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            //   mStatusTextView.setText(acct.getDisplayName() + " signed in");
            updateUI(true);

            Intent mapIntent = new Intent(LoginActivity.this, MapActivity.class);
            startActivity(mapIntent);

        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }
    // [END handleSignInResult]

    // [START signIn]
    private void signIn() {
        //   Toast.makeText(LoginActivity.this, "sign in intent", Toast.LENGTH_SHORT).show();


        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signIn]

    // [START signOut]
    private void signOut() {
      //  FirebaseAuth.getInstance().signOut();
        if (mGoogleApiClient.isConnected()) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {
                    if (status.isSuccess()) {
                        logout = false;
                        updateUI(false);
               //         Toast.makeText(LoginActivity.this, "logged out in profile, logout: " + logout, Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
        //    Toast.makeText(LoginActivity.this, "signed out method called in login, updated ui", Toast.LENGTH_SHORT).show();
    }


    // [END signOut]

//    // [START revokeAccess]
//    private void revokeAccess() {
//        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
//                new ResultCallback<Status>() {
//                    @Override
//                    public void onResult(Status status) {
//                        // [START_EXCLUDE]
//                     //   if (status.isSuccess()) {
//                            logout = false;
//                            updateUI(false);
//                            // [END_EXCLUDE]
//                            Toast.makeText(LoginActivity.this, "revoked access, logout: " + logout, Toast.LENGTH_SHORT).show();
//
//                      //  }
//                    }
//                });
//    }
//    // [END revokeAccess]

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("loading");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            // findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
            //   findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        } else {
            //    mStatusTextView.setText("signed out");
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            //   findViewById(R.id.sign_out_button).setVisibility(View.GONE);
            //  findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
//            case R.id.sign_out_button:
//                signOut();
//                break;
//            case R.id.disconnect_button:
//                revokeAccess();
//                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //    Toast.makeText(LoginActivity.this, "on new intent called", Toast.LENGTH_SHORT).show();
        if (intent.getExtras() == null) {

        } else if (intent.getExtras().get("methodName").equals("signOut")) {
            logout = true;
            //  signOut();
        }


    }

    @Override
    protected void onStop() {
        //   Toast.makeText(LoginActivity.this, "on stop method", Toast.LENGTH_SHORT).show();
        super.onStop();
//        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
//            mGoogleApiClient.stopAutoManage(this);
//            mGoogleApiClient.disconnect();
//            Toast.makeText(LoginActivity.this, "stopped automanage", Toast.LENGTH_SHORT).show();
//        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //       Toast.makeText(LoginActivity.this, "connected in login", Toast.LENGTH_SHORT).show();
        //       Toast.makeText(LoginActivity.this, getIntent().toString(), Toast.LENGTH_LONG).show();
        if (logout) {
            signOut();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

        //    Toast.makeText(LoginActivity.this, "connection supsended in login", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        //   Toast.makeText(LoginActivity.this, "on resume method", Toast.LENGTH_SHORT).show();
        super.onResume();
    }

}