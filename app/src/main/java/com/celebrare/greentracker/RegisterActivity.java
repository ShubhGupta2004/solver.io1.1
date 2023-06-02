package com.celebrare.greentracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.celebrare.greentracker.MainActivity;
import com.celebrare.greentracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText userName;
    private EditText password;
    private Button register;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName=findViewById(R.id.registerUserNameEditText);
        password=findViewById(R.id.registerPasswordEditText);
        register=findViewById(R.id.registerButton);
        auth=FirebaseAuth.getInstance();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_text = userName.getText().toString();
                String password_text = password.getText().toString();

                if(TextUtils.isEmpty(name_text) || TextUtils.isEmpty(password_text)){
                    Toast.makeText(RegisterActivity.this,"Empty credentials",Toast.LENGTH_SHORT).show();
                }else if(password_text.length()<6){
                    Toast.makeText(RegisterActivity.this,"password is too short",Toast.LENGTH_SHORT).show();
                }else {
                    registerUser(name_text,password_text);
                }
            }
        });
    }
    private void registerUser(String text_email, String text_password) {
        auth.createUserWithEmailAndPassword(text_email,text_password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"registration of user is successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                }else {
                    String errorMessage = task.getException().getMessage();
                    Toast.makeText(RegisterActivity.this, "Registration failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

        finish();
    }
}