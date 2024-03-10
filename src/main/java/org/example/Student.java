package org.example;

import java.util.ArrayList;

public class Student {
    private String nume;
    private float medie = -1;
    public String cursRepartizat;
    ArrayList<String> preferinteCurs = new ArrayList<>();
    public Student(String nume) {
        this.nume = nume;
        this.medie = 0;
    }
    public void setMedie(float medie) {
        this.medie = medie;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public String getNume() {
        return nume;
    }
    public float getMedie() {
        return medie;
    }
}
