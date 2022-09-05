package org.example.toropchin;
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



        int flag = 1;
        while (flag != 0) {
            System.out.print("Все данные обновлены. Что вы хотите сделать дальше\n" +
                    "1-добвить студента\n " +
                    "2-удалить студента\n " +
                    "3-добавить факультет\n " +
                    "4-удалить факультет\n " +
                    "5-получить информацию по факультету\n " +
                    "6-получить информацию по всем факультетам\n " +
                    "7-получить информацию по студенту\n " +
                    "8-получить информацию по всем студентам\n " +
                    "9-добавить преподавателя в факультет\n" +
                    "10-удалить преподавателя из факультета\n" +
                    "11-получить информацию по всем преподавателям\n"
            );

            int action = scanner.nextInt();
            String st = scanner.nextLine();
            switch (action) {
                case 1:
                    univer.addStudent();
                    break;
                case 2: {
                    System.out.print("Введите id студента, которого необходимо удалить:\n");
                    int ID = scanner.nextInt();
                    st = scanner.nextLine();
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
                case 5: {
                    System.out.print("Введите название факультета, по которому необходима информация:\n");
                    String name = scanner.nextLine();
                    univer.getDepartament(name);
                    break;
                }
                case 6: {
                    univer.getAllDepartament();
                    break;
                }
                case 7: {
                    System.out.print("Введите id студента, по которому нужна информация:\n");
                    int ID = scanner.nextInt();
                    st = scanner.nextLine();
                    univer.getStudent(ID);
                    break;
                }
                case 8: {
                    univer.getAllStudent();
                    break;
                }
                case 9: {
                    System.out.print("Введите название факультета, в который необходимо добавить преподавателя\n");
                    String named = scanner.nextLine();
                    for (int i = 0; i < univer.departaments.length; i++) {
                        if (univer.departaments[i].name.equals(named)) {
                            univer.departaments[i].addInstructor();
                        }
                    }
                    break;
                }
                case 10: {
                    System.out.print("Введите название факультета, из которого необходимо удалить преподавателя\n");
                    String named = scanner.nextLine();
                    for (int i = 0; i < univer.departaments.length; i++) {
                        if (univer.departaments[i].name.equals(named)) {
                            System.out.print("Введите имя преподавателя, которого необходимо удалить:\n");
                            String name = scanner.nextLine();
                            univer.departaments[i].removeInstructor(name);
                        }
                    }
                    break;
                }
                case 11: {
                    for(int i=0; i<univer.departaments.length; i++){
                        univer.departaments[i].getAllInstructors();
                    }
                    break;
                }
            }
            System.out.print("Для завершения работы программы введите 0, иначе - введите любую цифру");
            flag = scanner.nextInt();
            st = scanner.nextLine();
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
    int countst = 0;

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
        String st=scanner.nextLine();
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
                System.out.printf("Имя студента: %s id студента: %d\n", students[i].name, students[i].studentID);
            }
        }
    }

    public void getAllStudent(){
        for (int i = 0; i < this.students.length; i++) {
            System.out.printf("Имя студента: %s id студента: %d\n", students[i].name, students[i].studentID);
        }
    }

    public void getDepartament(String name){
        for(int i=0; i<this.departaments.length; i++){
            if (this.departaments[i].name.equals(name)) {
                System.out.printf("Наименование факультета: %s\n", name);
                System.out.printf("Преподаватели факультета:\n");
                for (int j=0; j<this.departaments[i].instructors.length; j++){
                    System.out.printf("%s\n",this.departaments[i].instructors[j]);
                }
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
    Instructor[] instructors = new Instructor[0];
    int countin = 0;

    public void addInstructor() {
        System.out.print("введите имя преподавателя\n");
        String name = scanner.nextLine();
        countin++;
        Instructor[] tmp = new Instructor[countin];
        System.arraycopy(instructors, 0, tmp, 0, countin - 1);
        tmp[countin - 1] = new Instructor(name);
        this.instructors = tmp;
    }


    public void removeInstructor(String name) {

        for (int i = 0; i < this.countin; i++) {
            if (name.equals(this.instructors[i].name)) {

                Instructor[] arr = new Instructor[countin - 1];
                int remainingElements = countin - (i + 1);
                System.arraycopy(instructors, 0, arr, 0, i);
                System.arraycopy(instructors, i + 1, arr, i, remainingElements);

                this.instructors = arr;
                countin--;
            }
        }
    }

    public void getAllInstructors() {
        for (int i = 0; i < this.instructors.length; i++) {
            System.out.printf("%s\n", instructors[i].name);
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
