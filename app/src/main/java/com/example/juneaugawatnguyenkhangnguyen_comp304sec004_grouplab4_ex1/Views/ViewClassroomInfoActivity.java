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

import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Classroom;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Student;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.R;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.ViewModel.ClassroomViewModel;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.ViewModel.StudentViewModel;

import java.util.List;

public class ViewClassroomInfoActivity extends AppCompatActivity {
    private SharedPreferences myPreference;

    private StudentViewModel studentViewModel;
    private ClassroomViewModel classroomViewModel;
    int selectedClassroomOfStudent;
    private EditText chooseStudentIDEd;
    private Button chooseStudentBtn;
    private TextView classroomInfoTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_classroom_info);

        myPreference = getSharedPreferences("info",MODE_PRIVATE);

        chooseStudentIDEd = (EditText)findViewById(R.id.choosenStudentIDEd2);
        chooseStudentBtn = (Button)findViewById(R.id.chooseStudentBtn2);
        classroomInfoTxt = (TextView)findViewById(R.id.classroomInfoTxt);
        TextView loggedInTxt3 = (TextView) findViewById(R.id.loggedInTxt3);
        loggedInTxt3.setText("Logged in as: " + myPreference.getInt("loggedInProfessorID", 0));

        classroomViewModel = ViewModelProviders.of(this).get(ClassroomViewModel.class);
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);

        if(myPreference.getBoolean("loggedIn", false)){
            chooseStudentIDEd.setVisibility(View.VISIBLE);
            chooseStudentBtn.setVisibility(View.VISIBLE);

        }

    }
    public void ViewClassroomInfo(View view)
    {
        studentViewModel.getAllStudents().observe((LifecycleOwner) this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> result) {
                for (Student s : result) {
                    if (s.getStudentId() == Integer.parseInt(chooseStudentIDEd.getText().toString()))
                    {
                        selectedClassroomOfStudent = s.getClassroomId();
                    }
                }
            }
        });

        classroomViewModel.getAllClassrooms().observe((LifecycleOwner) this, new Observer<List<Classroom>>() {
            @Override
            public void onChanged(@Nullable List<Classroom> result) {
                if(myPreference.getBoolean("loggedIn", false)) {
                    String output = "";
                    for (Classroom c : result) {
                        if (c.getClassroomId() == selectedClassroomOfStudent)
                        {
                            output += String.format("ClassroomID: %s\nStudentID: %s\nProfessorID: %s\nFloor: %s\nAirconditioned: %s\n\n",c.getClassroomId(),c.getStudentId(),c.getProfessorId(),c.getFloor(),c.getAriconditioned());
                        }
                    }
                    classroomInfoTxt.setText(output);
                }
            }
        });
    }
}