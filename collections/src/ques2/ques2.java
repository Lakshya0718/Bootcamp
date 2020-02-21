package ques2;

//Q2 Write a method that takes a string and returns the number of unique characters in the string.

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ques2 {
    public static void main(String[] args) {
        boolean flag = false;
        String test = "life is awesome";
        System.out.println(test);
        test=test.toLowerCase();
        List<Character> uniqueList = new LinkedList<>();
        List<Character> repeatedlist = new LinkedList<>();
        HashSet<Character> unique = new HashSet<>();
        for (int i=0; i<test.length();i++){
            flag = unique.add(test.charAt(i));
            if (flag==true) {
                uniqueList.add(test.charAt(i));
            }
            else {
                repeatedlist.add(test.charAt(i));
            }
        }
        for (int i = 0; i <uniqueList.size() ; i++) {
            for (int j = 0; j <repeatedlist.size() ; j++) {
                if (uniqueList.get(i).equals(repeatedlist.get(j))){
                    uniqueList.remove(i);
                }

            }

        }
        Iterator<Character> uniqueChars= uniqueList.iterator();
        while (uniqueChars.hasNext()){
            System.out.print(uniqueChars.next());
          }
        System.out.println("\n Number of unique characters: "+ uniqueList.size());
    }
}
