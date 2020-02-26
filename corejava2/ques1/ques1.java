package ques1;
//1. Create Java classes having suitable attributes for Library management system.Use OOPs concepts in your design.
// Also try to use interfaces and abstract classes.

abstract class Library {

    String bookName;
    int bookID;
}

class Student extends Library {
    String studentName;
    int rollNum;
    int deptID;
    int bookCount = 0 ;
    String deptName;

    public Student(String studentName, int rollNum, int deptID, String deptName){
        this.deptID = deptID;
        this.deptName = deptName;
        this.rollNum = rollNum;
        this.studentName = studentName;

    }
    public void issueBook(int bookID , String bookName){
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookCount++;
    }

    public void getDetails(){
        System.out.println("Dept ID = "+this.deptID);
        System.out.println("Dept Name = "+this.deptName);
        System.out.println("Roll Number = " + this.rollNum);
        System.out.println("Student Name = " + this.studentName);
        System.out.println("book ID = " + this.bookID);
        System.out.println("book name = "+ this.bookName);
        System.out.println("book count = " + this.bookCount);
    }
}

public class ques1 {

    public static void main(String[] args) {
            Student s1 = new Student("Lakshya", 6, 7 , "To the new");
            s1.issueBook(8,"intro to java");
            s1.getDetails();
            s1.issueBook(9,"Intro to C++");
            s1.getDetails();

    }
}



