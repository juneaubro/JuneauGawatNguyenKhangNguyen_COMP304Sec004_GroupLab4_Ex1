package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int studentId;
    private String firstname;
    private String lastname;
    private String department;
    private int professorId;
    private int classroomId;

    public int getStudentId() {
        return studentId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getDepartment() {
        return department;
    }

    public int getProfessorId() {
        return professorId;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public Student(int studentId,String firstname, String lastname, String department,int professorId,int classroomId) {
        this.studentId = studentId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.professorId = professorId;
        this.classroomId = classroomId;
    }
}
