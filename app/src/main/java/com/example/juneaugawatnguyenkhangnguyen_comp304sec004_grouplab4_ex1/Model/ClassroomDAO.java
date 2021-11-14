package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClassroomDAO {
    //
    @Update
    void update(Classroom classroom);
    @Insert
    void insert(Classroom classroom);
    //Monitoring Query Result Changes with Live Data
    @Query("select * from classroom_table order by floor")
    LiveData<List<Classroom>> getAllClassrooms();
}
