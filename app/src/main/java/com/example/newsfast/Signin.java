package com.example.newsfast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Signin extends AppCompatActivity {
    EditText data;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        btn=findViewById(R.id.proceed);
        data=findViewById(R.id.number);
        if(!data.getText().toString().trim().isEmpty()) {
                if(data.getText().toString().length()==10) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + data.getText().toString(), 60,
                            TimeUnit.SECONDS, Signin.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                Toast.makeText(Signin.this,"verification success",Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    Toast.makeText(Signin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    Intent i=new Intent(getApplicationContext(),otpverification.class);
                                    i.putExtra("mobileno",data.getText().toString());
                                    i.putExtra("backendotp",backendotp);
                                    startActivity(i);
                                }
                            });
                }else
                {
                    Toast.makeText(Signin.this, "Check the phone number", Toast.LENGTH_SHORT).show();

                }
        }
    }
}