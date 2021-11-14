package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Classroom;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.ClassroomRepo;

import java.util.List;

public class ClassroomViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private ClassroomRepo classroomRepo;
    private LiveData<Integer> insertResult;
    private LiveData<List<Classroom>> allClassrooms;
    //
    public ClassroomViewModel(@NonNull Application application) {
        super(application);
        classroomRepo = new ClassroomRepo(application);
        insertResult = classroomRepo.getInsertResult();
        allClassrooms = classroomRepo.getAllClassrooms();
    }
    //calls repository to insert a person
    public void insert(Classroom classroom) {
        classroomRepo.insert(classroom);
    }
    //gets insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }
    //returns query results as live data object
    public LiveData<List<Classroom>> getAllClassrooms() { return allClassrooms; }

}
