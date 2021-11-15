package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Professor;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.R;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.ViewModel.ProfessorViewModel;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private ProfessorViewModel professorViewModel;
    //preference data variable
    private SharedPreferences myPreference;
    //variable to modify preference data
    SharedPreferences.Editor prefEditor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //setup SharedPreference
        myPreference = getSharedPreferences("info",0);
        prefEditor = myPreference.edit();

        TextView textView = (TextView)findViewById(R.id.textView);
        professorViewModel = ViewModelProviders.of(this).get(ProfessorViewModel.class);
        professorViewModel.getAllProfessors().observe((LifecycleOwner) this, new Observer<List<Professor>>() {
            @Override
            public void onChanged(@Nullable List<Professor> result) {
                String output="";
                for(Professor p : result) {
                    output+= "ID: "+p.getProfessorId()+"\nPass: "+p.getPassword()+"\n\n";
                }
                textView.setText(output);
            }
        });
    }
    public void Login(View view){
        EditText loginEd = (EditText) findViewById(R.id.loginEd);
        EditText passEd = (EditText) findViewById(R.id.passEd);
        professorViewModel = ViewModelProviders.of(this).get(ProfessorViewModel.class);
        professorViewModel.getAllProfessors().observe((LifecycleOwner) this, new Observer<List<Professor>>() {
            @Override
            public void onChanged(@Nullable List<Professor> result) {
                for(Professor p : result) {
                    if(Integer.parseInt(loginEd.getText().toString())==p.getProfessorId()){
                        if(passEd.getText().toString().equals(p.getPassword()))
                        {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                        }
                        prefEditor.putBoolean("loggedIn", true);
                        prefEditor.putInt("loggedInProfessorID", p.getProfessorId());
                        prefEditor.commit();
                    }
                }
            }
        });
    }
}
