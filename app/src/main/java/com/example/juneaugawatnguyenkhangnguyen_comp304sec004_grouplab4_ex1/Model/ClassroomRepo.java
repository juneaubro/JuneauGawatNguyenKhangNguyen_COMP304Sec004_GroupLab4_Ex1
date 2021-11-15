package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.AppDatabase;

import java.util.List;

public class ClassroomRepo {
    private final ClassroomDAO classroomDAO;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Classroom>> classList;
    //
    public ClassroomRepo(Context context) {
        //create a database object
        AppDatabase db = AppDatabase.getInstance(context);
        //create an interface object
        classroomDAO = db.classroomDAO();
        //call interface method
        classList = classroomDAO.getAllClassrooms();
    }
    // returns query results as LiveData object
    public LiveData<List<Classroom>> getAllClassrooms() {
        return classList;
    }
    //inserts a person asynchronously
    public void insert(Classroom classroom) {
        insertAsync(classroom);
    }
    // returns insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    private void insertAsync(final Classroom classroom) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    classroomDAO.insert(classroom);
                    insertResult.postValue(1);
                } catch (Exception e) {
                    System.out.println("ERROR"+e);
                    insertResult.postValue(0);
                }
            }
        }).start();
    }
}
