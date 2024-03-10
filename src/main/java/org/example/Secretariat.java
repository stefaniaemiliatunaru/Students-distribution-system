package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Secretariat {
    protected ArrayList<Student> studenti = new ArrayList<>();
    protected ArrayList<Curs<?>> cursuri = new ArrayList<>();
    public void adaugaStudent(String programStudiu, String nume) throws ExceptieStudentDuplicat {
        for (int i = 0; i < studenti.size(); i++)
            if (studenti.get(i).getNume().equals(nume))
                throw new ExceptieStudentDuplicat();
        Student student = null;
        if (programStudiu.equals("licenta"))
            student = new StudentLicenta(nume);
        if (programStudiu.equals("master"))
            student = new StudentMaster(nume);
        studenti.add(student);
    }
    public void adaugaCurs(String programStudiu, String numeCurs, int capacitateMaxima) {
        if (programStudiu.equals("licenta")) {
            Curs<StudentLicenta> cursLicenta = new Curs<>(numeCurs, capacitateMaxima);
            cursuri.add(cursLicenta);
        }
        if (programStudiu.equals("master")) {
            Curs<StudentMaster> cursMaster = new Curs<>(numeCurs, capacitateMaxima);
            cursuri.add(cursMaster);
        }
    }
    public void citesteMediile(String numeDirector) throws FileNotFoundException {
        File director = new File(numeDirector);
        File[] fisiere = director.listFiles();
        for (int i = 0; i < fisiere.length; i++) {
            File fisier = fisiere[i];
            if (fisier.getName().startsWith("note_")) {
                FileReader fr = new FileReader(fisier);
                try (BufferedReader br = new BufferedReader(fr)) {
                    String lineFisier = null;
                    while ((lineFisier = br.readLine()) != null) {
                        String[] aux = lineFisier.split(" - ");
                        String nume = aux[0];
                        float medie = Float.parseFloat(aux[1]);
                        for (int j = 0 ; j < studenti.size(); j++)
                            if (studenti.get(j).getNume().equals(nume)) {
                                studenti.get(j).setMedie(medie);
                                break;
                            }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void posteazaMediile(String numeFisier) throws IOException {
        Collections.sort(studenti, new Comparator<Student>() {
            public int compare(Student student1, Student student2) {
                if (student2.getMedie() - student1.getMedie() != 0)
                    return Double.compare(student2.getMedie(), student1.getMedie());
                else
                    return student1.getNume().compareTo(student2.getNume());
            }
        });
        FileWriter fw = new FileWriter(numeFisier, true);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("***");
            bw.newLine();
            for (int i = 0; i < studenti.size(); i++) {
                bw.write(studenti.get(i).getNume() + " - " + studenti.get(i).getMedie());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void contestatieNota(String nume, float medieNoua) {
        for (int i = 0; i < studenti.size(); i++)
            if (studenti.get(i).getNume().equals(nume))
                if (studenti.get(i).getMedie() != medieNoua) {
                    studenti.get(i).setMedie(medieNoua);
                    break;
                }
    }
    public void adaugaPreferinte(String nume, String cursuri) {
        String[] aux = cursuri.split(" - ");
        for (int i = 0 ; i < studenti.size(); i++) {
            if (studenti.get(i).getNume().equals(nume)) {
                for (int j = 0; j < aux.length; j++) {
                    studenti.get(i).preferinteCurs.add(aux[j]);
                }
            }
        }
    }
    public void repartizeaza() {
        Collections.sort(studenti, new Comparator<Student>() {
            public int compare(Student student1, Student student2) {
                if (student2.getMedie() - student1.getMedie() != 0)
                    return Double.compare(student2.getMedie(), student1.getMedie());
                else
                    return student1.getNume().compareTo(student2.getNume());
            }
        });
        for (int i = 0; i < studenti.size(); i++) {
            int ok = 0;
            for (int j = 0; j < studenti.get(i).preferinteCurs.size(); j++) {
                for (int k = 0; k < cursuri.size(); k++) {
                    if (cursuri.get(k).getNumeCurs().equals(studenti.get(i).preferinteCurs.get(j)))
                        if (cursuri.get(k).studentiInrolati.size() >= cursuri.get(k).getCapacitateMaxima()) {
                            for (int l = 0; l < studenti.size(); l++) {
                                if (studenti.get(l).getNume().equals(cursuri.get(k).studentiInrolati.get(cursuri.get(k).studentiInrolati.size() - 1))) {
                                    if (studenti.get(l).getMedie() == studenti.get(i).getMedie()) {
                                        cursuri.get(k).studentiInrolati.add(studenti.get(i).getNume());
                                        studenti.get(i).cursRepartizat = cursuri.get(k).getNumeCurs();
                                        ok = 1;
                                        break;
                                    }
                                    break;
                                }
                            }
                            break;
                        } else {
                            cursuri.get(k).studentiInrolati.add(studenti.get(i).getNume());
                            studenti.get(i).cursRepartizat = cursuri.get(k).getNumeCurs();
                            ok = 1;
                            break;
                        }
                }
                if (ok == 1)
                    break;
            }
        }
    }
    public void posteazaCurs(String numeFisier, String numeCurs) throws IOException {
        FileWriter fw = new FileWriter(numeFisier, true);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("***");
            bw.newLine();
            bw.write(numeCurs + " ");
            for (int i = 0; i < cursuri.size(); i++) {
                if (cursuri.get(i).getNumeCurs().equals(numeCurs)) {
                    bw.write("(" + cursuri.get(i).getCapacitateMaxima() + ")");
                    bw.newLine();
                    Collections.sort(cursuri.get(i).studentiInrolati, new Comparator<String>() {
                        public int compare(String student1, String student2) {
                            return student1.compareTo(student2);
                        }
                    });
                    for (int j = 0; j < cursuri.get(i).studentiInrolati.size(); j++) {
                        bw.write(cursuri.get(i).studentiInrolati.get(j) + " - ");
                        for (int k = 0; k < studenti.size(); k++) {
                            if (studenti.get(k).getNume().equals(cursuri.get(i).studentiInrolati.get(j))) {
                                bw.write(String.valueOf(studenti.get(k).getMedie()));
                                bw.newLine();
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void posteazaStudent(String numeFisier, String nume) throws IOException {
        FileWriter fw = new FileWriter(numeFisier, true);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("***");
            bw.newLine();
            for (int i = 0; i < studenti.size(); i++) {
                if (studenti.get(i).getNume().equals(nume)) {
                    bw.write("Student " );
                    if (studenti.get(i) instanceof StudentLicenta) {
                        bw.write("Licenta: " + nume + " - " + studenti.get(i).getMedie() + " - " + studenti.get(i).cursRepartizat);
                    }
                    if (studenti.get(i) instanceof StudentMaster) {
                        bw.write("Master: " + nume + " - " + studenti.get(i).getMedie() + " - " + studenti.get(i).cursRepartizat);
                    }
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
