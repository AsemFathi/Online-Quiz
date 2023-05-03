package com.asem.onlinequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

public class MakeQuiz extends AppCompatActivity {

    Button next , finish;
    EditText question , option1 , option2 , option3, option4 , answer;
    TextView questionNum;
    LinearLayout mcqLinear, tfLiner;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_quiz);

        count = 1;
        next = findViewById(R.id.nextQuesBTN);
        finish = findViewById(R.id.finishBTN);
        question = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        questionNum = findViewById(R.id.questionNumber);
        mcqLinear = findViewById(R.id.layoutMCQ);
        tfLiner = findViewById(R.id.tfLayout);
        answer = findViewById(R.id.correct);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();

        mcqLinear.setVisibility(View.VISIBLE);
        tfLiner.setVisibility(View.INVISIBLE);

        questionNum.setText("Question" + count);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Ques = question.getText().toString();
                String first_option = option1.getText().toString();
                String second_option = option2.getText().toString();
                String third_option = option3.getText().toString();
                String fourth_option = option4.getText().toString();
                String ans = answer.getText().toString();

                //Push to firebase realtime
                DatabaseReference newQues = databaseReference.child("Quiz").child("Question"+count);
                newQues.child("Ques").setValue(Ques);
                newQues.child("first_option").setValue(first_option);
                newQues.child("second_option").setValue(second_option);
                newQues.child("third_option").setValue(third_option);
                newQues.child("fourth_option").setValue(fourth_option);
                newQues.child("Answer").setValue(ans);

                //clear after save
                count++;
                question.getText().clear();
                option1.getText().clear();
                option2.getText().clear();
                option3.getText().clear();
                option4.getText().clear();
                answer.getText().clear();

            }
        });
       }

}