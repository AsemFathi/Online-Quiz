package com.asem.onlinequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentResult extends AppCompatActivity {
    FirebaseDatabase database;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_result);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        students = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    if (dataSnapshot.child("score").exists()) {
                        String Name = dataSnapshot.getKey();
                        String score = dataSnapshot.child("score").getValue().toString();
                        students.add(new Student(Name , score));
                    }
                }

                recyclerView.setAdapter(new ResultAdapter(StudentResult.this,students));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}