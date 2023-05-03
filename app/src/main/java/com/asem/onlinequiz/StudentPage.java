package com.asem.onlinequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class StudentPage extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    public static ArrayList<Questions> questions;
    Button joinQuiz , yourResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);
        joinQuiz = findViewById(R.id.joinQuiz);
        yourResult = findViewById(R.id.yourResult);
        questions = new ArrayList<>();

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getInstance().getReference("Quiz");


        joinQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            String QuestionNum = dataSnapshot.getKey().toString();
                            String Question = dataSnapshot.child("Ques").getValue().toString();
                            String Option1 = dataSnapshot.child("first_option").getValue().toString();
                            String Option2 = dataSnapshot.child("second_option").getValue().toString();
                            String Option3 = dataSnapshot.child("third_option").getValue().toString();
                            String Option4 = dataSnapshot.child("fourth_option").getValue().toString();
                            String ans = dataSnapshot.child("Answer").getValue().toString();

                            questions.add(new Questions(Question,QuestionNum,Option1,Option2,Option3,Option4 , ans));


                        }

                        // Create a Bundle and put the questions ArrayList in it

                        // Create an Intent and pass the Bundle to it
                        Intent intent = new Intent(StudentPage.this, Quiz.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(StudentPage.this, "Failll", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        yourResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentPage.this , StudentScore.class);
                startActivity(intent);
            }
        });
    }
}