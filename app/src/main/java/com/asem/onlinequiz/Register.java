package com.asem.onlinequiz;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Register extends AppCompatActivity {
    EditText Name;
    EditText Email;
    EditText Phone;
    EditText Password;
    Button Register;
    FirebaseAuth auth;
    DatabaseReference reference;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Name = findViewById(R.id.editTextTextUsername);
        Email = findViewById(R.id.editTextRegisterEmailAddress);
        Phone = findViewById(R.id.editTextRegisterPhone);
        Password = findViewById(R.id.editTextRegisterPassword);
        Register = findViewById(R.id.ButtonRegister);



        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Users");


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String email = Email.getText().toString();
                String mail = email.replaceAll("@gmail.com", "");
                String phone = Phone.getText().toString();
                String password = Password.getText().toString();
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebaseUser = task.getResult().getUser();
                            DatabaseReference newUser = reference.child(mail);
                            newUser.child("Full Name").setValue(name);
                            newUser.child("Email").setValue(email);
                            newUser.child("Phone").setValue(phone);
                            Intent intent = new Intent(Register.this, MainActivity.class);
                            Toast.makeText(Register.this, "Registration Done", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        } else
                            Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

}