package com.example.juneaugawatnguyenkhangnguyen_comp304sec004_grouplab4_ex1.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "professor_table")
public class Professor {

    @PrimaryKey(autoGenerate = true)
    private int professorId;
    private String firstname;
    private String lastname;
    private String department;
    private String password;

    public int getProfessorId() {
        return professorId;
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

    public String getPassword() {
        return password;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public Professor(int professorId,String firstname, String lastname, String department, String password) {
        this.professorId = professorId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.password = password;
    }
}
