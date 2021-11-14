package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "classroom_table")
public class Classroom {
    @PrimaryKey(autoGenerate = true)
    private int classroomId;
    private String floor;
    private String ariconditioned;
    private int studentId;
    private int professorId;

    public int getClassroomId() {
        return classroomId;
    }

    public String getFloor() {
        return floor;
    }

    public String getAriconditioned() {
        return ariconditioned;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setAriconditioned(String ariconditioned) {
        this.ariconditioned = ariconditioned;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public Classroom(int classroomId,String floor, String ariconditioned,int studentId,int professorId) {
        this.classroomId = classroomId;
        this.floor = floor;
        this.ariconditioned = ariconditioned;
        this.studentId = studentId;
        this.professorId = professorId;
    }
}
