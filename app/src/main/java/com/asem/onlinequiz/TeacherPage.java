package com.asem.onlinequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherPage extends AppCompatActivity {

    Button makeQuiz;
    Button studentsResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_page);

        makeQuiz = findViewById(R.id.makeQuiz);
        studentsResult = findViewById(R.id.studentResult);

        makeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherPage.this , MakeQuiz.class);
                startActivity(intent);
            }
        });

        studentsResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherPage.this , StudentResult.class);
                startActivity(intent);
            }
        });
    }
}