package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.AppDatabase;

import java.util.List;

public class ProfessorRepo {
    private final ProfessorDAO professorDAO;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Professor>> professorList;
    //
    public ProfessorRepo(Context context) {
        //create a database object
        AppDatabase db = AppDatabase.getInstance(context);
        //create an interface object
        professorDAO = db.professorDAO();
        //call interface method
        professorList = professorDAO.getAllProfessors();
    }
    // returns query results as LiveData object
    public LiveData<List<Professor>> getAllProfessors() {
        return professorList;
    }
    //inserts a person asynchronously
    public void insert(Professor professor) {
        insertAsync(professor);
    }
    // returns insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    private void insertAsync(final Professor professor) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    professorDAO.insert(professor);
                    insertResult.postValue(1);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
    }
}
