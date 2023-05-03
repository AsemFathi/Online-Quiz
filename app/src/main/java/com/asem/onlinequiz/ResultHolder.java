package com.asem.onlinequiz;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ResultHolder extends RecyclerView.ViewHolder {
    TextView StudentName , StudentResult;
    public ResultHolder(@NonNull View itemView) {
        super(itemView);
        StudentName = itemView.findViewById(R.id.studentName);
        StudentResult = itemView.findViewById(R.id.tv_studentScore);

    }
}
