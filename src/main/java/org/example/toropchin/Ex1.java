package org.example.toropchin;
import java.io.IOException;
import java.util.Scanner;
public class Ex1 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String n;
        String a;
        String p;
        System.out.print("Введите наименование Вуза\n");
        n = scanner.nextLine();
        System.out.print("Введите адрес Вуза\n");
        a = scanner.nextLine();
        System.out.print("Введите телефон Вуза\n");
        p = scanner.nextLine();
        University univer = new University(n, a, p);
        System.out.print("Университет создан. Склько факультетов необходимо добавить\n");
        int colf = scanner.nextInt();
        for (int j = 0; j < colf; j++) {
            univer.addDepartament();
        }
        System.out.print("Все данные обновлены. Что вы хотите сделать дальше\n 1-добвить студента\n 2-удалить студента\n 3-добавить факультет\n 4-удалить факультет\n 5-получить информцию по факультету\n 6-получить информацию по студентам\n 7-добавить преподавателя в факультет");
        int action = scanner.nextInt();
        switch (action) {
            case 1:
                univer.addStudent();
                break;
            case 2: {
                System.out.print("Введите id студента, которого необходимо удалить:\n");
                int ID = scanner.nextInt();
                univer.removeStudent(ID);
                break;
            }
            case 3: {
                univer.addDepartament();
                break;
            }
            case 4: {
                System.out.print("Введите название факультета, который необходимо удалить:\n");
                String name = scanner.nextLine();
                univer.removeDepartament(name);
                break;
            }
          //case 5: {

           //     break;
          //  }
            // case 6: {

            //     break;
            //  }
            case 7: {
                System.out.print("Введите название факультета, в который необходимо добавить преподавателя\n");
                String named = scanner.nextLine();
                for (int i = 0; i < univer.departaments.length; i++) {
                    if (univer.departaments[i].name.equals(named)) {
                        univer.departaments[i].addInstructor();
                    }
                }
                break;
            }
        }
    }
}


    class University {
        Scanner scanner = new Scanner(System.in);
        String name;
        String address;
        String phone;

        Student[] students = new Student[0];
        Departament[] departaments = new Departament[0];
        int countd = 0;
        private int countst = 0;

        Departament departament;

        public University(String name, String address, String phone) {
            this.name = name;
            this.address = address;
            this.phone = phone;
        }

        public void addStudent() {
            System.out.print("введите имя стдента\n");
            String name = scanner.nextLine();
            System.out.print("введите id стдента\n");
            int studentID = scanner.nextInt();
            countst++;
            Student[] tmp = new Student[countst];
            System.arraycopy(students, 0, tmp, 0, countst - 1);
            tmp[countst - 1] = new Student(name, studentID);
            this.students = tmp;
        }

        public void addDepartament() {
            System.out.print("введите название факультета\n");
            String name = scanner.nextLine();
            countd++;
            Departament[] tmp = new Departament[countd];
            System.arraycopy(departaments, 0, tmp, 0, countd - 1);
            tmp[countd - 1] = new Departament(name);
            this.departaments = tmp;
        }

        public void removeStudent(int studentID) {
            for (int i = 0; i < this.students.length; i++) {
                if (studentID == this.students[i].studentID) {

                    Student[] arrStudent = new Student[countst - 1];
                    int remainingElements = countst - (i + 1);
                    System.arraycopy(students, 0, arrStudent, 0, i);
                    System.arraycopy(students, i + 1, arrStudent, i, remainingElements);

                    this.students = arrStudent;
                    countst--;
                }
            }
        }

        public void removeDepartament(String name) {
            for (int i = 0; i < this.countd; i++) {
                if (name.equals(this.departaments[i].name)) {

                    Departament[] arrDestination = new Departament[countd - 1];
                    int remainingElements = countd - (i + 1);
                    System.arraycopy(departaments, 0, arrDestination, 0, i);
                    System.arraycopy(departaments, i + 1, arrDestination, i, remainingElements);

                    this.departaments = arrDestination;
                    countd--;
                }
            }
        }

        public void getStudent(int studentID) {
            for (int i = 0; i < this.students.length; i++) {
                if (studentID == this.students[i].studentID) {
                    System.out.printf("student_name %s student_id %d\n", students[i].name, students[i].studentID);
                }
            }
        }


        public void getAllDepartament() {
            System.out.print("Факультеты\n");
            for (int i = 0; i < this.countd; i++) {
                System.out.printf("%s\n", departaments[i].name);
            }
        }

    }

    class Departament {
        Scanner scanner = new Scanner(System.in);
        String name;
        Instructor[] instructors;
        int countin = 0;
        private Instructor instructor;

        public void addInstructor() {
            System.out.print("введите имя преподавателя\n");
            String name = scanner.nextLine();
            countin++;
            //for (int i = 0; i < nameDep.length; i++) {
            //  if (nameDep[i].name.equals(named)){
            Instructor[] tmp = new Instructor[countin];
            System.arraycopy(instructors, 0, tmp, 0, countin - 1);
            tmp[countin - 1] = new Instructor(name);
            this.instructors = tmp;
        }


        public void removeInstructor(String name) {
            for (int i = 0; i < this.instructors.length; i++) {
                if (name.equals(this.instructors[i].name)) {
                    this.instructors[i].name = "";
                }
            }
        }

        public void getAllInstructors() {
            for (int i = 0; i < this.instructors.length; i++) {
                System.out.printf("%s\n", instructors[i]);
            }
        }

        public Departament(String name) {
            this.name = name;
        }
    }

    class Course {
        String name;
        int courseID;

        public void displayInfo() {
            System.out.printf("course_name %s student_id %d\n", name, courseID);
        }

        public Course(String name, int studentID) {
            this.name = name;
            this.courseID = studentID;
        }
    }

    class Student {
        String name;
        int studentID;

        public void displayInfo() {
            System.out.printf("student_name %s student_id %d\n", name, studentID);
        }

        public Student(String name, int studentID) {
            this.name = name;
            this.studentID = studentID;
        }
    }

    class Instructor {
        String name;

        public void displayInfo() {
            System.out.printf("course_name %s\n", name);
        }

        public Instructor(String name) {
            this.name = name;
        }
    }
