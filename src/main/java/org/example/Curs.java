package org.example;
import java.util.ArrayList;

public class Curs<T extends Student> {
    private String numeCurs;
    private int capacitateMaxima;
    protected ArrayList<String> studentiInrolati = new ArrayList<>();
    public Curs(String numeCurs, int capacitateMaxima) {
        this.numeCurs = numeCurs;
        this.capacitateMaxima = capacitateMaxima;
    }
    public String getNumeCurs() {
        return numeCurs;
    }
    public int getCapacitateMaxima() {
        return capacitateMaxima;
    }
    public void inroleazaStudentLaCurs(T student) {
        if (studentiInrolati.size() < capacitateMaxima) {
            studentiInrolati.add(student.getNume());
        }
    }

}
