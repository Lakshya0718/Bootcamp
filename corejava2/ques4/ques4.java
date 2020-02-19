package ques4;

class Singleton
{
private static Singleton instance = null;
public String s;
private Singleton()
        {
        s = "Hello, my name is Lakshya!";
        }

// static method to create instance of Singleton class
public static Singleton getInstance()
        {
            if (instance == null) {
            instance = new Singleton();
        }
            return instance;
        }
}

public class ques4
{
    public static void main(String args[])
    {

        Singleton x = Singleton.getInstance();
        Singleton y = Singleton.getInstance();
        Singleton z = Singleton.getInstance();


        x.s = (x.s).toUpperCase();

        System.out.println("String from x is " + x.s);
        System.out.println("String from y is " + y.s);
        System.out.println("String from z is " + z.s);
        System.out.println("\n");


        z.s = (z.s).toLowerCase();

        System.out.println("String from x is " + x.s);
        System.out.println("String from y is " + y.s);
        System.out.println("String from z is " + z.s);
    }
}