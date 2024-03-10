package org.example;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Secretariat secretariat = new Secretariat();
        String numeTest = args[0];
        String numeDirector = "src/main/resources/" + numeTest;
        String fisierInput = numeDirector + "/" + numeTest + ".in";
        String fisierOutput = numeDirector + "/" + numeTest + ".out";
        FileReader fr = new FileReader(fisierInput);
        try (BufferedReader br = new BufferedReader(fr)) {
            String linieFisier = null;
            while ((linieFisier = br.readLine()) != null) {
                String[] aux = linieFisier.split(" - ");
                String comanda = aux[0];
                if (comanda.equals("adauga_student")) {
                    String programStudiu = aux[1];
                    String numeStudent = aux[2];
                    try {
                        secretariat.adaugaStudent(programStudiu, numeStudent);
                    } catch (ExceptieStudentDuplicat esd) {
                        FileWriter fw = new FileWriter(fisierOutput, true);
                        try (BufferedWriter bw = new BufferedWriter(fw)) {
                            bw.write("***");
                            bw.newLine();
                            bw.write("Student duplicat: " + numeStudent);
                            bw.newLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                if (comanda.equals("adauga_curs")) {
                    String programStudiu = aux[1];
                    String numeCurs = aux[2];
                    int capacitateMaxima = Integer.parseInt(aux[3]);
                    secretariat.adaugaCurs(programStudiu, numeCurs, capacitateMaxima);
                }
                if (comanda.equals("citeste_mediile"))
                    secretariat.citesteMediile(numeDirector);
                if (comanda.equals("posteaza_mediile"))
                    secretariat.posteazaMediile(fisierOutput);
                if (comanda.equals("contestatie")) {
                    String numeStudent = aux[1];
                    float mediaNoua = Float.parseFloat(aux[2]);
                    secretariat.contestatieNota(numeStudent, mediaNoua);
                }
                if (comanda.equals("adauga_preferinte")) {
                    String nume = aux[1];
                    String cursuri = "";
                    for (int i = 2; i < aux.length; i++) {
                        cursuri = cursuri + " - " + aux[i];
                    }
                    secretariat.adaugaPreferinte(nume, cursuri);
                }
                if (comanda.equals("repartizeaza"))
                    secretariat.repartizeaza();
                if (comanda.equals("posteaza_curs")) {
                    String curs = aux[1];
                    secretariat.posteazaCurs(fisierOutput, curs);
                }
                if (comanda.equals("posteaza_student")) {
                    String nume = aux[1];
                    secretariat.posteazaStudent(fisierOutput, nume);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
