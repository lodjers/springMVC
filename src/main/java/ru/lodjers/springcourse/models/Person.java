package ru.lodjers.springcourse.models;

import javax.validation.constraints.*;


public class Person {
    private int id;
    private String FIO;
    private int yearOfBirth;

    public Person() {
    }

    public Person(int id, String FIO, int yearOfBirth) {
        this.id = id;
        this.FIO = FIO;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
