package com.anjirwala.project.anjirwalafabrics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Anuj Anjirwala on 31-Jul-17.
 */

public class LoginActivity extends AppCompatActivity {

    private Button mButton;
    private ProgressDialog mProgress;
    private FirebaseAuth mAuth;

    private EditText email;
    private EditText pass;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mProgress = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null)   {
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                }
            }
        };

        email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.password);
        mButton =(Button) findViewById(R.id.signinbtn);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void startSignIn() {
        String emailid = email.getText().toString();
        String password = pass.getText().toString();

        if (!TextUtils.isEmpty(emailid) && !TextUtils.isEmpty(password)) {
            mProgress.setMessage("Authorizing User..");
            mProgress.show();
            mAuth.signInWithEmailAndPassword(emailid, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    mProgress.dismiss();
                    if (task.isSuccessful()) {
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        finish();
                    }
                    else    {
                        Toast.makeText(LoginActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else if(!TextUtils.isEmpty(emailid) && TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this, "Please enter password.", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(emailid) && !TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this, "Please enter Email ID.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(LoginActivity.this, "Please enter Email ID & Password.", Toast.LENGTH_SHORT).show();
        }
    }
}
