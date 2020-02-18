package assessment;

//Q3. Write a program to find the number of occurrences of a character in a string without using loop?
public class q3 {
    public static void main(String[] args) {
        String s1="hello world world is great";
        String c="o";
        System.out.println(s1.length()-(s1.replace(c, "").length()));
    }
}
