package ques3;
//3. WAP to produce NoClassDefFoundError and ClassNotFoundException exception.

public class ques3 {
    public static void main(String[] args) {
        try{
            Class.forName("Lakshya");
        }catch (ClassNotFoundException e){
            System.out.println(e.getStackTrace());
        }
    }
}