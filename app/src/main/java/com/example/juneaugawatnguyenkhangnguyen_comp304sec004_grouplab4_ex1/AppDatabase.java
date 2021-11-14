package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Classroom;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.ClassroomDAO;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Professor;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.ProfessorDAO;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.Student;
import com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model.StudentDAO;

@Database(entities = {Professor.class, Classroom.class, Student.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    //
    private static volatile AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "lab4Db";
    public abstract ProfessorDAO professorDAO();
    public abstract ClassroomDAO classroomDAO();
    public abstract StudentDAO studentDAO();
    //
    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            //Create database object
            INSTANCE = Room.databaseBuilder(context,
                    AppDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
