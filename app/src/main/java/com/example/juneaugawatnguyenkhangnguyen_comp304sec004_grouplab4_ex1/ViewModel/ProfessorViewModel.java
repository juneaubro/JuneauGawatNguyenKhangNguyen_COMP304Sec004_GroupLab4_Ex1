package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Professor;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.ProfessorRepo;

import java.util.List;

public class ProfessorViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private ProfessorRepo professorRepo;
    private LiveData<Integer> insertResult;
    private LiveData<List<Professor>> allProfessors;
    //
    public ProfessorViewModel(@NonNull Application application) {
        super(application);
        professorRepo = new ProfessorRepo(application);
        insertResult = professorRepo.getInsertResult();
        allProfessors = professorRepo.getAllProfessors();
    }
    //calls repository to insert a person
    public void insert(Professor professor) {
        professorRepo.insert(professor);
    }
    //gets insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }
    //returns query results as live data object
    public LiveData<List<Professor>> getAllProfessors() { return allProfessors; }

}
