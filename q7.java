package assessment;

//Q7. Write a program to print your Firstname,LastName & age using static block,static method & static variable respectively

public class q7 {
    static{
        System.out.println("Static block called");
        System.out.println("Lakshya");
        }

    static String ftitle = "Static variable";
    static  String lname = "sharma";
    static int age = 22 ;


    public static void main(String[] args) {

        System.out.println(q7.ftitle);
        System.out.println(q7.age);
        myfun();
    }

    static void myfun(){
        System.out.println("Static Method");
        System.out.println(lname);

    }
}
