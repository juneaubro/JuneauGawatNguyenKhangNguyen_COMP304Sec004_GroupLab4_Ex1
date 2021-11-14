package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Views;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Professor;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.R;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.ViewModel.ProfessorViewModel;

import org.w3c.dom.Text;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private ProfessorViewModel professorViewModel;
    public static boolean loggedIn = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);
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
        EditText name = (EditText) findViewById(R.id.login);
        EditText pass = (EditText) findViewById(R.id.pass);
        professorViewModel = ViewModelProviders.of(this).get(ProfessorViewModel.class);
        professorViewModel.getAllProfessors().observe((LifecycleOwner) this, new Observer<List<Professor>>() {
            @Override
            public void onChanged(@Nullable List<Professor> result) {
                for(Professor p : result) {
                    System.out.println("ID: "+p.getProfessorId()+"\nPassword: "+p.getPassword()+"\nTyped ID: "+name.getText().toString()+"\nTyped Password: "+pass.getText().toString());
                    if(Integer.parseInt(name.getText().toString())==p.getProfessorId() && pass.getText().toString().equals(p.getPassword())){
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        loggedIn = true;
                        break;
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
