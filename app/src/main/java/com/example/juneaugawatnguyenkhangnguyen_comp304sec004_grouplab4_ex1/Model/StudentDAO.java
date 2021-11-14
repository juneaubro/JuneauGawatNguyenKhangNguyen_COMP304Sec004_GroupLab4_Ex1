package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDAO {
    //
    @Update
    void update(Student student);
    @Insert
    void insert(Student student);
    //Monitoring Query Result Changes with Live Data
    @Query("select * from student_table order by lastname")
    LiveData<List<Student>> getAllStudents();
}
