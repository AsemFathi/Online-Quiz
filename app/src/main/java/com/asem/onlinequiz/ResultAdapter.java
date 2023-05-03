package com.asem.onlinequiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultHolder>  {
   Context context;
   List<Student>students;

    public ResultAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResultHolder(LayoutInflater.from(context).inflate(R.layout.recycle_item ,parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder holder, int position) {
        holder.StudentName.setText(students.get(position).getName());
        holder.StudentResult.setText(students.get(position).getScore());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
