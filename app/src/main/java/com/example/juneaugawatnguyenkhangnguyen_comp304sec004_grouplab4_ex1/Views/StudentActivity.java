package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Professor;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Student;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.R;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.ViewModel.ProfessorViewModel;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.ViewModel.StudentViewModel;

import java.util.List;

public class StudentActivity extends AppCompatActivity {
    private StudentViewModel studentViewModel;
    private EditText f;
    private EditText l;
    private EditText d;
    private EditText cc;
    private EditText pp;
    private EditText ss;
    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_layout);
        TextView textView = (TextView) findViewById(R.id.stuinfo);
        f = (EditText)findViewById(R.id.firstn);
        l = (EditText)findViewById(R.id.lastn);
        d = (EditText)findViewById(R.id.ddt);
        cc = (EditText)findViewById(R.id.cid);
        pp = (EditText)findViewById(R.id.pid);
        ss = (EditText)findViewById(R.id.ss);
        b = (Button) findViewById(R.id.enterDataBtn);
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.getAllStudents().observe((LifecycleOwner) this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> result) {
                if(LoginActivity.loggedIn) {
                    String output = "";
                    b.setVisibility(View.VISIBLE);
                    f.setVisibility(View.VISIBLE);
                    l.setVisibility(View.VISIBLE);
                    d.setVisibility(View.VISIBLE);
                    cc.setVisibility(View.VISIBLE);
                    pp.setVisibility(View.VISIBLE);
                    ss.setVisibility(View.VISIBLE);
                    for (Student s : result) {
                        output += String.format("StudentID: %s\nFirst name: %s\nLast name: %s\nDepartment: %s\nProfessorID: %s\nClassroomID: %s\n\n",s.getStudentId(), s.getFirstname(), s.getLastname(), s.getDepartment(),s.getProfessorId(),s.getClassroomId());
                    }
                    textView.setText(output);
                } else{
                    textView.setText("Professor must login first.");
                }
            }
        });
    }
    public void EnterData(View view){
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.insert(new Student(Integer.parseInt(ss.getText().toString()),f.getText().toString(),l.getText().toString(),d.getText().toString(),Integer.parseInt(pp.getText().toString()),Integer.parseInt(cc.getText().toString())));
        studentViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(StudentActivity.this, "Student successfully added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StudentActivity.this, "Error saving Student", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}