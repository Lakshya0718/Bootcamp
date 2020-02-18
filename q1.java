package assessment;


//Q1 Write a program to replace a substring inside a string with other string?

public class q1
{
    public static void main(String[] args) {
        String s1="Beautiful";
        String s2="new";
        System.out.println(s1.replace(s1.substring(2,6),s2));
    }

}
