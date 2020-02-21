package ques1;

//Q1. Write Java code to define List . Insert 5 floating point numbers in List, and using an iterator, find the sum of the numbers in List.

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ques1 {

    public static List<Float> number = new ArrayList<Float>();
    public static  float sum;

    public static void main(String[] args) {

        addItem(5);

        getItem();

        System.out.println("Sum of element of List: " + sum());


    }

    private static float sum() {
        Iterator<Float> iterator = number.iterator();
        while (iterator.hasNext()){
            sum = sum + iterator.next();
        }
        return sum;

    }

    private static void getItem() {
        for (int i = 0; i < 5; i++) {
            System.out.println(number.get(i));
        }
    }

    private static void addItem(int size) {
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            float value = random.nextFloat();
            number.add(value);
        }
    }

}