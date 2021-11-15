package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Classroom;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.R;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.ViewModel.ClassroomViewModel;

public class ClassroomActivity extends AppCompatActivity {
    private SharedPreferences myPreference;

    private ClassroomViewModel classroomViewModel;
    private EditText classroomIDEd2;
    private EditText airconditionedEd;
    private EditText floorEd;
    private Button addClassroomBtn;
    private EditText chooseStudentIDEd;
    private Button chooseStudentBtn;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);

        myPreference = getSharedPreferences("info",MODE_PRIVATE);

        classroomIDEd2 = (EditText) findViewById(R.id.classroomIDEd2);
        floorEd = (EditText) findViewById(R.id.floorEd);
        airconditionedEd = (EditText) findViewById(R.id.airconditionedEd);
        addClassroomBtn = (Button) findViewById(R.id.addClassroomBtn);

        TextView loggedInTxt2 = (TextView) findViewById(R.id.loggedInTxt2);
        loggedInTxt2.setText("Logged in as: " + myPreference.getInt("loggedInProfessorID", 0));
        chooseStudentIDEd = (EditText) findViewById(R.id.choosenStudentIDEd);
        chooseStudentBtn = (Button) findViewById(R.id.chooseStudentBtn);

        if(myPreference.getBoolean("loggedIn", false)){
            chooseStudentIDEd.setVisibility(View.VISIBLE);
            chooseStudentBtn.setVisibility(View.VISIBLE);

        }
    }

    public void ChooseStudent(View view)
    {
        classroomIDEd2.setVisibility(View.VISIBLE);
        floorEd.setVisibility(View.VISIBLE);
        airconditionedEd.setVisibility(View.VISIBLE);
        addClassroomBtn.setVisibility(View.VISIBLE);

    }
    public void AddClassroom(View view){
        classroomViewModel = ViewModelProviders.of(this).get(ClassroomViewModel.class);
        classroomViewModel.insert(new Classroom(Integer.parseInt(classroomIDEd2.getText().toString()), floorEd.getText().toString(), airconditionedEd.getText().toString(),Integer.parseInt(chooseStudentIDEd.getText().toString()) ,myPreference.getInt("loggedInProfessorID", 0)));
        classroomViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(ClassroomActivity.this, "Classroom successfully added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ClassroomActivity.this, "Error saving Classroom", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
