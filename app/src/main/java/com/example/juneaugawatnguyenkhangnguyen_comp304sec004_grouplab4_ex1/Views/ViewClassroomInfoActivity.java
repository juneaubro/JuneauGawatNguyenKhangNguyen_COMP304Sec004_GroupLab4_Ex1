package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Classroom;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Student;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.R;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.ViewModel.ClassroomViewModel;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.ViewModel.StudentViewModel;

import java.util.List;

public class ViewClassroomInfoActivity extends AppCompatActivity {
    private StudentViewModel studentViewModel;
    private ClassroomViewModel classroomViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_classroom_info);
        TextView s = (TextView)findViewById(R.id.textView3);
        TextView c = (TextView)findViewById(R.id.textView2);
        classroomViewModel = ViewModelProviders.of(this).get(ClassroomViewModel.class);
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.getAllStudents().observe((LifecycleOwner) this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> result) {
                if(LoginActivity.loggedIn) {
                    String output = "";
                    for (Student s : result) {
                        //output += String.format("First name: %s\nLast name: %s\nDepartment: %s\n\n", s.getFirstname(), s.getLastname(), s.getDepartment());
                        output += String.format("StudentID: %s\nFirst name: %s\nLast name: %s\nDepartment: %s\nProfessorID: %s\nClassroomID: %s\n\n",s.getStudentId(), s.getFirstname(), s.getLastname(), s.getDepartment(),s.getProfessorId(),s.getClassroomId());
                    }
                    s.setText(output);
                } else{
                    s.setText("Professor must login first.");
                }
            }
        });
        classroomViewModel.getAllClassrooms().observe((LifecycleOwner) this, new Observer<List<Classroom>>() {
            @Override
            public void onChanged(@Nullable List<Classroom> result) {
                if(LoginActivity.loggedIn) {
                    String output = "";
                    for (Classroom c : result) {
                        //output += String.format("First name: %s\nLast name: %s\nDepartment: %s\n\n", s.getFirstname(), s.getLastname(), s.getDepartment());
                        output += String.format("ClassroomID: %s\nFloor: %s\nAirconditioned: %s\n\n",c.getClassroomId(),c.getFloor(),c.getAriconditioned());
                    }
                    c.setText(output);
                }
            }
        });
    }
}