package com.example.newsfast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class otpverification extends AppCompatActivity {
EditText otp;
Button verify;
String backendotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);
        verify=findViewById(R.id.verify);
        otp=findViewById(R.id.enteredotp);
        backendotp=getIntent().getStringExtra("backendotp");
        String getotp=otp.getText().toString();
        verify.setOnClickListener(view -> {
            if(otp.getText().toString().length()==6){

                PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(backendotp,getotp);
                FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent intent=new Intent(otpverification.this,MainActivity.class);
                        startActivity(intent);
                    }else
                    {
                        Toast.makeText(otpverification.this, "Check the otp again", Toast.LENGTH_SHORT).show();
                    }
                    }
                });

            }
        });
    }
}