package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Views;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
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
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Student;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.R;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.ViewModel.ClassroomViewModel;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.ViewModel.StudentViewModel;

public class ClassroomActivity extends AppCompatActivity {
    private ClassroomViewModel classroomViewModel;
    private EditText c;
    private EditText a;
    private EditText floor;
    private Button b;
    private TextView textView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classroomactl);
        c = (EditText) findViewById(R.id.ccid);
        floor = (EditText) findViewById(R.id.floor);
        a = (EditText) findViewById(R.id.airc);
        b = (Button) findViewById(R.id.addc);
        textView = (TextView) findViewById(R.id.textView4);
        if(LoginActivity.loggedIn){
            c.setVisibility(View.VISIBLE);
            floor.setVisibility(View.VISIBLE);
            a.setVisibility(View.VISIBLE);
            b.setVisibility(View.VISIBLE);
            textView.setVisibility(View.INVISIBLE);
        }
    }
    public void AddClassroom(View view){
        classroomViewModel = ViewModelProviders.of(this).get(ClassroomViewModel.class);
        classroomViewModel.insert(new Classroom(Integer.parseInt(c.getText().toString()),floor.getText().toString(),a.getText().toString(),0,0));
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
