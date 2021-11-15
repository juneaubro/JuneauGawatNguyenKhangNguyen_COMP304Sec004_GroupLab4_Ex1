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

public class UpdateInfoActivity extends AppCompatActivity {
    private SharedPreferences myPreference;

    private StudentViewModel studentViewModel;
    private EditText firstnameEd2;
    private EditText lastnameEd2;
    private EditText departmentEd2;
    private EditText classroomIDEd3;
    private EditText professorIDEd2;
    private EditText studentIDEd2;
    private Button updateData;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        myPreference = getSharedPreferences("info",MODE_PRIVATE);

        textView = (TextView) findViewById(R.id.sinfo);
        studentIDEd2 = (EditText)findViewById(R.id.studentIDEd2);
        firstnameEd2 = (EditText)findViewById(R.id.firstnameEd2);
        lastnameEd2 = (EditText)findViewById(R.id.lastnameEd2);
        departmentEd2 = (EditText)findViewById(R.id.departmentEd2);
        classroomIDEd3 = (EditText)findViewById(R.id.classroomIDEd3);
        professorIDEd2 = (EditText)findViewById(R.id.professorIDEd2);
        updateData = (Button) findViewById(R.id.updateData);
        TextView loggedInTxt4 = (TextView) findViewById(R.id.loggedInTxt4);
        loggedInTxt4.setText("Logged in as: " + myPreference.getInt("loggedInProfessorID", 0));

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.getAllStudents().observe((LifecycleOwner) this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> result) {
                if(myPreference.getBoolean("loggedIn", false)) {
                    String output = "";
                    updateData.setVisibility(View.VISIBLE);
                    firstnameEd2.setVisibility(View.VISIBLE);
                    lastnameEd2.setVisibility(View.VISIBLE);
                    departmentEd2.setVisibility(View.VISIBLE);
                    classroomIDEd3.setVisibility(View.VISIBLE);
                    professorIDEd2.setVisibility(View.VISIBLE);
                    studentIDEd2.setVisibility(View.VISIBLE);
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
    public void UpdateData(View view){
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.getAllStudents().observe((LifecycleOwner) this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> result) {
                    for (Student s : result) {
                        if (Integer.parseInt(studentIDEd2.getText().toString()) == s.getStudentId()) {
                            s = new Student(Integer.parseInt(studentIDEd2.getText().toString()), firstnameEd2.getText().toString(), lastnameEd2.getText().toString(), departmentEd2.getText().toString(), Integer.parseInt(professorIDEd2.getText().toString()), Integer.parseInt(classroomIDEd3.getText().toString()));
                            studentViewModel.update(s);
                            Toast.makeText(UpdateInfoActivity.this, "Student successfully updated", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
            }
        });
    }
}