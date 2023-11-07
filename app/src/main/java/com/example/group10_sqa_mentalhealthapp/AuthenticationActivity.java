package com.example.group10_sqa_mentalhealthapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class AuthenticationActivity extends AppCompatActivity {

    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;

    public static final okhttp3.MediaType JSON_MEDIA_TYPE
            = okhttp3.MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient httpClient = new OkHttpClient.Builder().hostnameVerifier(new HostnameVerifier() {
        // todo: this is very very bad, we need to verify local cert hostname somehow i'll be on it - elliot
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }).build();

    private static final String TAG = "AuthenticationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        Button googleAuthButton = (Button) findViewById(R.id.googleauthbtn);
        googleAuthButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SetupOTP();
            }
        });

        Button basicAuthButton = (Button) findViewById(R.id.basicauthbtn);
        basicAuthButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finishAuthentication();
            }
        });
    }

    private void SetupOTP()
    {
        Log.d(TAG, "Creating google one tap client");
        oneTapClient = Identity.getSignInClient(this);
        signInRequest = BeginSignInRequest.builder()
                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                        .setSupported(true)
                        .build())
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.default_web_client_id))
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(false)
                        .build())
                // Automatically sign in when exactly one credential is retrieved.
                .setAutoSelectEnabled(true)
                .build();

        Log.d(TAG, "Initiating google sign in");
        googleSignIn();
    }

    private final ActivityResultLauncher<IntentSenderRequest> loginResultHandler = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), result -> {
        // handle intent result here
        if (result.getResultCode() == RESULT_OK) {
            SignInCredential credential = null;
            try {
                credential = oneTapClient.getSignInCredentialFromIntent(result.getData());
                String idToken = credential.getGoogleIdToken();
                String username = credential.getId();
                String password = credential.getPassword();
                if (idToken != null) {
                    // Got an ID token from Google. Use it to authenticate
                    // with your backend.
                    Log.d(TAG, "Got ID token.");
                    sendBackendAuthenticationRequest("google", idToken);
                } else if (password != null) {
                    // Got a saved username and password. Use them to authenticate
                    // with your backend.
                    Log.d(TAG, "Got password.");
                    sendBackendAuthenticationRequest("basic", username + ":" + password);
                }
            } catch (ApiException e) {
                e.printStackTrace();
            }
        } else {
            Log.w(TAG, "warning: result code not OK, code was " + result.getResultCode());
        }
    });

    private void sendBackendAuthenticationRequest(String type, String secret)
    {
        // todo: clean up thread
        Thread networkAuthenticationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // create your json here
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("AuthenticationType", type);
                    jsonObject.put("AuthenticationSecret", secret);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, jsonObject.toString()); // new
                // RequestBody body = RequestBody.create(JSON, json); // old

                // use 10.0.2.2 to access your actual machine, localhost is a loopback to the emulator
                // learned the hard way lol - elliot
                Request request = new Request.Builder()
                        .url(getString(R.string.server_auth_endpoint))
                        .post(body)
                        .build();
                Response response = null;
                try {
                    Call call = httpClient.newCall(request);
                    response = call.execute();
                } catch (IOException e) {
                    // todo: handle gracefully, if you crashed here its because the server isnt running / cant be connected to
                    Log.e(TAG, "Could not connect to the backend server, is it running?");
                    throw new RuntimeException(e);
                }

                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    Log.d(TAG, responseBody.toString());
                } else {
                    // todo: handle gracefully
                    Log.d(TAG, "Response body was null, finishing authentication regardless for debug");
                }
                finishAuthentication();

            }
        });
        networkAuthenticationThread.start();
        Log.d(TAG, "Running network authentication thread: " + networkAuthenticationThread.getName());
    }

    private void finishAuthentication()
    {
        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(i);
    }

    private  void googleSignIn()
    {
        oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(this, new OnSuccessListener<BeginSignInResult>() {
                    @Override
                    public void onSuccess(BeginSignInResult result) {
                        try{
                            loginResultHandler.launch(new IntentSenderRequest.Builder(result.getPendingIntent().getIntentSender()).build());
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // No saved credentials found. Launch the One Tap sign-up flow, or
                        // do nothing and continue presenting the signed-out UI.
                        // todo: handle gracefully
                        Log.d(TAG, Objects.requireNonNull(e.getLocalizedMessage()));
                    }
                });
    }
}
