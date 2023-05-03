package com.asem.onlinequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentScore extends AppCompatActivity {
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_score);
        user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        email = email.replaceAll("@gmail.com","");
        score = findViewById(R.id.tv_score);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(email);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("score").exists())
                {
                    score.setText("Your Last Quiz score is: " + snapshot.child("score").getValue().toString());

                }
                else
                {
                    score.setText("You don't hava any quiz score");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}