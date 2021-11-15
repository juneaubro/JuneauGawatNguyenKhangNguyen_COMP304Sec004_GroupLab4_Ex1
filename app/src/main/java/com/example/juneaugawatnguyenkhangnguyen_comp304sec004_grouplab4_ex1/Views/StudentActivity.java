package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Student;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.R;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.ViewModel.StudentViewModel;

import java.util.List;

public class StudentActivity extends AppCompatActivity {
    private SharedPreferences myPreference;

    private StudentViewModel studentViewModel;
    private EditText firstnameEd;
    private EditText lastnameEd;
    private EditText departmentIDEd;
    private EditText classroomIDEd;
    private EditText professorIDEd;
    private EditText studentIDEd;
    private Button enterDataBtn;
    private Button viewDataBtn;
    private TextView stuinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        myPreference = getSharedPreferences("info",MODE_PRIVATE);

        stuinfo = (TextView) findViewById(R.id.stuinfo);
        studentIDEd = (EditText)findViewById(R.id.studentIDEd);
        firstnameEd = (EditText)findViewById(R.id.firstnameEd);
        lastnameEd = (EditText)findViewById(R.id.lastnameEd);
        departmentIDEd = (EditText)findViewById(R.id.departmentIDEd);
        classroomIDEd = (EditText)findViewById(R.id.classroomIDEd);
        professorIDEd = (EditText)findViewById(R.id.professorIDEd);
        TextView loggedInTxt = (TextView) findViewById(R.id.loggedInTxt);
        loggedInTxt.setText("Logged in as: " + myPreference.getInt("loggedInProfessorID", 0));

        enterDataBtn = (Button) findViewById(R.id.enterDataBtn);
        viewDataBtn = (Button) findViewById(R.id.viewDataBtn);
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.getAllStudents().observe((LifecycleOwner) this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> result) {
                if(myPreference.getBoolean("loggedIn", false)) {
                    String output = "";
                    firstnameEd.setVisibility(View.VISIBLE);
                    lastnameEd.setVisibility(View.VISIBLE);
                    departmentIDEd.setVisibility(View.VISIBLE);
                    classroomIDEd.setVisibility(View.VISIBLE);
                    professorIDEd.setVisibility(View.VISIBLE);
                    studentIDEd.setVisibility(View.VISIBLE);
                    enterDataBtn.setVisibility(View.VISIBLE);
                    viewDataBtn.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    public void EnterStudentInfo(View view){
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.insert(new Student(Integer.parseInt(studentIDEd.getText().toString()), firstnameEd.getText().toString(), lastnameEd.getText().toString(), departmentIDEd.getText().toString(),Integer.parseInt(professorIDEd.getText().toString()),Integer.parseInt(classroomIDEd.getText().toString())));
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

    public void ViewStudentInfo(View view){
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.getAllStudents().observe((LifecycleOwner) this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> result) {
                if(myPreference.getBoolean("loggedIn", false)) {
                    String output = "";
                    for (Student s : result) {

                        output += String.format("StudentID: %s\nFirst name: %s\nLast name: %s\nDepartment: %s\nProfessorID: %s\nClassroomID: %s\n\n",s.getStudentId(), s.getFirstname(), s.getLastname(), s.getDepartment(),s.getProfessorId(),s.getClassroomId());
                    }
                    stuinfo.setText(output);
                } else{
                    stuinfo.setText("Professor must login first.");
                }
            }
        });
    }
}