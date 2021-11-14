package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.AppDatabase;

import java.util.List;

public class StudentRepo {
    private final StudentDAO studentDAO;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Student>> studentList;
    //
    public StudentRepo(Context context) {
        //create a database object
        AppDatabase db = AppDatabase.getInstance(context);
        //create an interface object
        studentDAO = db.studentDAO();
        //call interface method
        studentList = studentDAO.getAllStudents();
    }
    // returns query results as LiveData object
    public LiveData<List<Student>> getAllStudents() {
        return studentList;
    }
    //inserts a person asynchronously
    public void insert(Student student) {
        insertAsync(student);
    }
    public void update(Student student){
        updateAsync(student);
    }
    // returns insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    private void updateAsync(final Student student){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    studentDAO.update(student);
                    insertResult.postValue(1);
                } catch (Exception e){
                    insertResult.postValue(0);
                }
            }
        }).start();
    }

    private void insertAsync(final Student student) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    studentDAO.insert(student);
                    insertResult.postValue(1);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
    }
}
