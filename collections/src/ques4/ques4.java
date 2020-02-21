package ques4;

//Q4  Write a program to sort HashMap by value.

import java.util.*;

public class ques4 {

    public static
    Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {

        map.put("Lakshya", 100);
        map.put("Anupam", 80);
        map.put("Tanvi", 70);
        map.put("Subarno", 40);
        map.put("Ashutosh", 10);
        map.put("Chirag", 26);

        printMap(map);
        System.out.println("\n");
        sortMap(map);

        printMap(sortMap(map));


    }

    private static HashMap<String, Integer> sortMap(Map<String, Integer> map) {

        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));

        HashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> map1 : list){
            sortedMap.put(map1.getKey(), map1.getValue());
        }

        return sortedMap;
    }

    private static void printMap(Map map) {
        Iterator<String> keyIterator = map.keySet().iterator();
        Iterator<Integer> valueIterator = map.values().iterator();
        while (keyIterator.hasNext()&&valueIterator.hasNext()){
            System.out.println(keyIterator.next()+": "+valueIterator.next());
        }
    }
}