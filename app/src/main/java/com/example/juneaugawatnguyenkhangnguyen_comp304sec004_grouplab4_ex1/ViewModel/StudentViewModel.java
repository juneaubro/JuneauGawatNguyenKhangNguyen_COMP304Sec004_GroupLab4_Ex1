package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Student;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.StudentRepo;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private StudentRepo studentRepo;
    private LiveData<Integer> insertResult;
    private LiveData<List<Student>> allStudents;

    //
    public StudentViewModel(@NonNull Application application) {
        super(application);
        studentRepo = new StudentRepo(application);
        insertResult = studentRepo.getInsertResult();
        allStudents = studentRepo.getAllStudents();
    }

    public void update(Student student){
        studentRepo.update(student);
    }

    //calls repository to insert a person
    public void insert(Student student) {
        studentRepo.insert(student);
    }

    //gets insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    //returns query results as live data object
    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }
}
