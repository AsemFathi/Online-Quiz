package com.asem.onlinequiz;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Quiz extends AppCompatActivity implements View .OnClickListener{

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String ans;

    FirebaseUser user;
    int greenColor = Color.rgb(0 , 255 , 0);
    TextView QuesNum , Ques;
    Button option1, option2, option3, option4 ,next;
    ArrayList<Questions> questions;
    int correct =0;
    int count = 0;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        option1 = findViewById(R.id.quizOption1);
        option2 = findViewById(R.id.quizOption2);
        option3 = findViewById(R.id.quizOption3);
        option4 = findViewById(R.id.quizOption4);
        QuesNum = findViewById(R.id.quizQuestionNumber);
        Ques = findViewById(R.id.quizQuestion);
        questions = StudentPage.questions;
        next = findViewById(R.id.NextBTN);
        user = FirebaseAuth.getInstance().getCurrentUser();

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getInstance().getReference("Users");

        email = user.getEmail();
        email = email.replaceAll("@gmail.com" , "");

        code();

    }

    private void code(){

        QuesNum.setText(questions.get(count).getQuesNum());
        Ques.setText(questions.get(count).getQues());
        option1.setText(questions.get(count).getOption1());
        option2.setText(questions.get(count).getOption2());
        option3.setText(questions.get(count).getOption3());
        option4.setText(questions.get(count).getOption4());
        ans = questions.get(count).getAns();

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        next.setOnClickListener(this);
       /* option1.setOnClickListener(view -> CheckAnswer(option1.getText().toString() , ans ));
        option2.setOnClickListener(view -> CheckAnswer(option2.getText().toString() , ans ));
        option3.setOnClickListener(view -> CheckAnswer(option3.getText().toString() , ans ));
        option4.setOnClickListener(view -> CheckAnswer(option4.getText().toString() , ans ));*/
    }
    private void CheckAnswer(String option, String Answer) {
        if (option.equals(Answer))
            correct++;
        if (count != questions.size() -1)
        {
            count++;
            code();
        }
        else {
           databaseReference.child(email).child("score").setValue(correct);
            new AlertDialog.Builder(this)
                    .setTitle("Quiz score")
                    .setMessage("Score is " + correct +"out of " + (questions.size()))
                    .setPositiveButton("Okay" , (dialogInterface, i) -> goNext())
                    .setCancelable(false)
                    .show();

        }

    }

    private void goNext() {
        Intent intent = new Intent(Quiz.this , StudentPage.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        option1.setBackgroundColor(Color.WHITE);
        option2.setBackgroundColor(Color.WHITE);
        option3.setBackgroundColor(Color.WHITE);
        option4.setBackgroundColor(Color.WHITE);

        Button Clicked = (Button) view;

        if(Clicked.getId() == R.id.NextBTN)
        {
            if (count != questions.size() -1)
            {
                count++;
                code();
            }
            else {
                databaseReference.child(email).child("score").setValue(correct);
                new AlertDialog.Builder(this)
                        .setTitle("Quiz score")
                        .setMessage("Score is " + correct +" out of " + (questions.size()))
                        .setPositiveButton("Okay" , (dialogInterface, i) -> goNext())
                        .setCancelable(false)
                        .show();

            }

        }
        else {
            if (ans.equals(Clicked.getText().toString()))
            {
                Clicked.setBackgroundColor(Color.GREEN);
                correct++;
            }
            else
                Clicked.setBackgroundColor(Color.RED);
        }

    }
}