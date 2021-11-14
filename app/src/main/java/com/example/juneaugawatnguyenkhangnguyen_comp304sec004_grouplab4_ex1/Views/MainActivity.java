package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Classroom;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Professor;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Student;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.R;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.ViewModel.ClassroomViewModel;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.ViewModel.ProfessorViewModel;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.ViewModel.StudentViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProfessorViewModel professorViewModel;
    private ClassroomViewModel classroomViewModel;
    private StudentViewModel studentViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        professorViewModel = ViewModelProviders.of(this).get(ProfessorViewModel.class);
        classroomViewModel = ViewModelProviders.of(this).get(ClassroomViewModel.class);
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        professorViewModel.getAllProfessors().observe(this, new Observer<List<Professor>>() {
            @Override
            public void onChanged(@Nullable List<Professor> result) {
                /*String output="";
                for(Professor p : result) {
                    output+= p.getFirstname() +"\n"+p.getLastname()+"\n"+p.getDepartment()+"\n"+p.getPassword();
                }
                textView.setText(output);*/
            }
        });
        classroomViewModel.getAllClassrooms().observe(this, new Observer<List<Classroom>>() {
            @Override
            public void onChanged(List<Classroom> classrooms) {

            }
        });
        studentViewModel.getAllStudents().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {

            }
        });
        ListView activityView = (ListView) findViewById(R.id.activityListView);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.activities, android.R.layout.simple_list_item_1);
        activityView.setAdapter(adapter);
        activityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                } else if(i==1){
                    startActivity(new Intent(MainActivity.this, StudentActivity.class));
                } else if(i==2){
                    startActivity(new Intent(MainActivity.this,ClassroomActivity.class));
                } else if(i==3){
                    startActivity(new Intent(MainActivity.this, ViewClassroomInfoActivity.class));
                } else if (i==4){
                    startActivity(new Intent(MainActivity.this, UpdateInfoActivity.class));
                }
            }
        });
    }
}