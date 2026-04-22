class Department {
    String deptName;
    String location;

    Department(String deptName, String location) {
        this.deptName = deptName;
        this.location = location;}
    void displayDepartment() {
        System.out.println("Department: " + deptName);
        System.out.println("Location: " + location);
    }}
class Student {
    String name;
    int id;
    Department dept;  // Association
    Student(String name, int id, Department dept) {
        this.name = name;
        this.id = id;
        this.dept = dept;}
    void displayStudent() {
        System.out.println("Student Name: " + name);
        System.out.println("ID: " + id);
        dept.displayDepartment();}}
class Teacher {
    String name;
    String subject;
    Department dept;  // Association

    Teacher(String name, String subject, Department dept) {
        this.name = name;
        this.subject = subject;
        this.dept = dept;
    }
    void displayTeacher() {
        System.out.println("Teacher Name: " + name);
        System.out.println("Subject: " + subject);
        dept.displayDepartment();}
}
public class Main {
    public static void main(String[] args) {

        Department csDept = new Department("Computer Science", "Block A");

        Student s1 = new Student("Ali", 101, csDept);
        Teacher t1 = new Teacher("Dr. Ahmed", "OOP", csDept);

        System.out.println("Student Info ");
        s1.displayStudent();

        System.out.println("\nTeacher Info ");
        t1.displayTeacher();
    }
}