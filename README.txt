README
Tema 2 POO - Tunaru Stefania Emilia 321CB

Clasa Student
Aceasta cuprinde: 
- nume de tip String
- medie de tip float
- cursRepartizat de tip String
- preferinteCurs de tip ArrayList ce stocheaza variabile String unde se vor retine preferintele de repartizare ale studentului respectiv
Am ales folosirea unui ArrayList care stocheaza date de tip String datorita avantajelor oferite de acesta: dinamicitatea dimensiunii listei, dimensiunea sa poate fi ajustata eficient; eficienta oferita in cazul adaugarii unui student nou; accesibilitatea la elementele listei; genericitatea.
- un constructor cu parametrul nume care intializeaza numele si seteaza media initiala a studentului la 0
- settere si gettere pentru nume si medie (setMedie, setNume, getMedie, getNume)

Clasa StudentLicenta - extinde clasa Student
Acesta cuprinde:
- Constructor pentru StudentLicenta

Clasa StudentMaster - extinde clasa Student
Acesta cuprinde:
- Constructor pentru StudentMaster


Clasa Curs - clasa generica ce extinde clasa Student
Aceasta cuprinde:
- numeCurs de tip String
- capacitateMaxima de tip int
- studentiInrolati de tip ArrayList ce stocheaza variabile String unde se vor retine numele studentilor repartizati la cursul respectiv
Am ales folosirea unui ArrayList care stocheaza date de tip String datorita avantajelor oferite de acesta: dinamicitatea dimensiunii listei, dimensiunea sa poate fi ajustata eficient; eficienta oferita in cazul adaugarii unui student nou; accesibilitatea la elementele listei; genericitatea.
- constructor cu parametrii numeCurs si capacitateMaxima care seteaza campurile cursului corespunzator
- gettere pentru numele cursului si pentru capacitatea maxima a cursului (getNumeCurs si getCapacitateMaxima)


Clasa Secretariat
Aceasta contine:
- studenti de tip ArrayList ce cuprinde variabile de tip Student in care sunt stocati toti studentii din baza de date a secretariatului
- cursuri de tip ArrayList ce cuprinde variabile de tip Curs in care sunt stocate toatre cursurile din baza de date a secretariatului
Am ales folosirea de ArrayList-uri pentru stocarea studentilor si a cursurilor datorita avantajelor pe care le ofera precum: genericitatea ArrayList-urilor; dinamicitatea dimensiunii acestora, acestea fiind usor redimensionabile, nu trebuie specificata o dimensiune initiala, dimensiunea se actualizeaza eficient pe parcursul programului; usurinta de acces la elementele array-ului si usurinta in sortarea elementelor listei; usurinta de adaugare a elementelor in lista.
- functia adaugaStudent cu parametrii programStudiu de tip String si nume de tip String. Aceasta verifica in prima instanta daca in array-ul de studenti exista deja un student cu numele primit ca parametru, caz in care se arunca exceptia ExceptieStudentDuplicat. In cazul in care studentul nu exista in baza de date a secretariatului, se creeaza o noua instanta de tip Student. Se verifica in ce tip de program de studiu se doreste a fi inscris studentul si se initializeaza corespunzator instanta Student (StudentLicenta sau StudentMaster), dupa care se adauga in array-ul studenti.
- functia adaugaCurs cu parametrii programStudiu de tip String, numeCurs de tip String, capacitateMaxima de tip int. Acesta verifica tipul cursului, dupa care creeaza un nou obiect de tip curs (cursLicenta sau cursMaster) in functie de tipul sau si il adauga in array-ul cursuri.
- functia citesteMediile ce primeste ca parametru numele unui director (de tip String). Se declara un vector in care vor fi retinute numele tuturor fisierelor din director, dupa care se parcurge acest vector pentru a identifica fisierele ce incep cu sirul "note_" pentru a putea fi preluate notele studentilor. Pentru fiecare dintre fisierele ce indeplinesc aceasta conditie se foloseste comanda split pentru a separa parametrii comenzilor care vor fi retinuti in vectorul de String-uri aux. Astfel, primul parametru retinut din citire va fi numele studentului, al doilea va fi media acestuia. Dupa ce se retin aceste date, este parcurs array-ul studenti pentru a identifica studentul cu numele retinut, iar dupa identificare ii este atribuita media corespunzatoare. In cazul in care nu se poate realiza citirea se arunca RuntimeException.
- functia posteazaMediile ce primeste ca parametru numele unui fisier. In aceasta functie se sorteaza array-ul de studenti dupa media lor, dupa care se parcurge array-ul studenti si se afiseaza mediile conform cerintei. In cazul in care nu se poate scrie in fisier se arunca exceptia RuntimeException.
- functia contestatieNota cu parametrii numele unui student de tip String si media noua a studentului de tip float. Este parcurs array-ul studenti si este identificat studentul cu numele cautat, se verifica daca media lui curenta a fost actualizata la cea noua, iar in caz contrar, se actualizeaza media studentului.
- functia adaugaPreferinte cu parametrii numele unui studenti de tip String si un String cursuri in care este retinut tot sirul de caractere ce contine cursurile de preferinta ale studentului. Prin intermediul comenzii split se retin cursurile de preferinta ale studentului in vectorul de String-uri aux, dupa care este identifivcat studentul cu numele cautat prin parcurgerea array-ului studenti si ii sunt adaugate cursurile in ordine in array-ul preferinteCurs.
- functia repartizeaza. In prima instanta este fortat array-ul studenti dupa medie, dupa care acestia sunt sortati si dupa nume pentru a corespunde afisarii cerute. Este parcurs array-ul studenti si este introdusa variabila contor ok ce retine daca studentul curent a fost repartizat la un curs sau nu. Studentului curent ii este parcurs array-ul preferinteCurs, apoi este parcurs array-ul cursuri pentru a putea fi identificat cursul curent din preferinteCurs. Cand este identificat cursul, se verifica daca acestuia i-a fost depasita capacitatea maxima, caz in care se verifica daca media ultimului student repartizat la acest curs este agala cu media studentului curent. In caz afirmativ, studentul curent este si el alocat la cursul curent si se seteaza variabila contor ok la 1. In caz contrar, se trece la urmatorul curs din lista preferinteCurs a studentului curent. In cazul in care nu este atinsa capacitatea maxima a cursului curent, studentul este repartizat la acesta si se seteaza variabila contor la 1.
- functia posteazaCurs cu parametrii numele unui fisier, de tip String si numele unui cors, de tip String. Acesta incepe scrierea in fisier conform cerintei, dupa care parcurge array-ul cursuri pentru a fi identificat cursul primit ca parametru, ii este afisat numele, capcaitatea maxima, dupa care este sortat alfabetic array-ul studentiInrolati al cursului si sunt afisati studentii cursului, alaturi de media lor. In cazul in care nu se poate scrie in fisier se arunca exceptia RuntimeException.
- functia posteazaStudent cu parametrii numele unui fisier, de tip String, si numele unui student, de tip String. Acesta incepe scrierea in fisier conform cerintei, dupa care este parcurs array-ul studenti si este identificat studentul cu numele cautat. Se verifica la ce tip de program de invatamant este inscris (licenta sau master), si se afiseaza corespunzator cerintei. In cazul in care nu se poate face scrierea in fisier se arunca exceptia RuntimeException.


Clasa Main - programul principal
Se creaza un obiect de tip secretariat, dupa care se retin numele testului, numele directorului, fisierul de input si fisierul de output. Se incepe citirea din fisierul de input linie cu linie si se separa parametrii folosind comanda split. Primul parametru este retinut in variabila de tip String comanda.
Se verifica tipul comenzii.
Daca comanda este "adauga_student", se retine al doilea parametru in String-ul programStudiu, iar al treilea parametru se retine in String-ul numeStudent, dupa care este apelata functia adaugaStudent, iar in cazul in care aceasta arunca exceptia de student duplicat, se arunca exceptia si se scrie in fisier mesajul corespunzator cerintei.
Daca comanda este "adauga_curs", se retine al doilea parametru in String-ul programStudiu, al doilea in String-ul numeCurs, iar al treilea in variabila de tip int capacitateMaxima. Se apeleaza functia adaugaCurs.
Daca comanda este "citeste_mediile" se apeleaza functia citesteMediile.
Daca comanda este "posteaza_mediile" se apeleaza functia posteazaMediile.
Daca comanda este "contestatie" se retine al doilea parametru in String-ul numeStudent, iar al doilea in mediaNoua de tip float. Se apeleaza functia contestatieNota.
Daca comanda este "adauga_preferinte", se retine al doilea parametru in String-ul nume, apoi se retin cursurile in vectorul cursuri si se apeleaza functia adaugaPreferinte.
Daca comanda este "repartizeaza" se apeleaza functia repartizeaza.
Daca comanda este "posteaza_curs, se retine al doilea parametru in String-ul curs si se apeleaza functia posteazaCurs.
Daca comanda este "posteaza_student", se retine al doilea parametru in String-ul nume si se apeleaza functia posteazStudent.
Daca nu se poate citi din fisier se arunca exceptia RuntimeException.