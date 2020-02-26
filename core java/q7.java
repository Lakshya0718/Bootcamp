package assessment;

//Q7. Write a program to print your Firstname,LastName & age using static block,static method & static variable respectively

public class q7 {
    static{
        System.out.println("Static block called");
        System.out.println("Lakshya");
        System.out.println("Sharma");
        System.out.println(21);
    }

    static String ftitle = "Static variable";
    static String fname = "lakshya";
    static  String lname = "sharma";
    static int age = 22 ;


    public static void main(String[] args) {

        System.out.println(q7.ftitle);
        System.out.println(q7.fname);
        System.out.println(q7.lname);
        System.out.println(q7.age);
        myfun();
    }

    static void myfun(){
        System.out.println("static function");
        System.out.println(fname);
        System.out.println(lname);
        System.out.println(age);
    }
}
