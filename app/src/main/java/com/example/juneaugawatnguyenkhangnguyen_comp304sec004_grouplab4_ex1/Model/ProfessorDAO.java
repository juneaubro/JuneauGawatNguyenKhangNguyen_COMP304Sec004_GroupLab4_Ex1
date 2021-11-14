package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
// this interface declares database functions
// and does the mapping of SQL queries to functions
@Dao
public interface ProfessorDAO {
    //
    @Update
    void update(Professor professor);
    @Insert
    void insert(Professor professor);
    //Monitoring Query Result Changes with Live Data
    @Query("select * from professor_table order by lastname")
    LiveData<List<Professor>> getAllProfessors();
}