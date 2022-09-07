package com.worksUni;

import java.util.*;

public class TreeMap {

    public static void main(String[] args) {

        System.out.println("Введите число строк (не больше 100)");
        Scanner in = new Scanner(System.in);
        String number = in.nextLine();
        int number1 = Integer.parseInt(number);
        if ((number1 > 1) & (number1 <= 100)) {
            System.out.println("Введите слово и текст ");
            String word = in.nextLine();
            if (word.length() <= 100) {
                String[] array = new String[number1];
                for (int i = 0; i < number1; i++) {
                    array[i] = in.nextLine();
                }
                java.util.TreeMap<Integer, String> treeMap = new java.util.TreeMap<>();
                int count = 0;
                for (int i = 0; i < number1; i++) {
                    count = (array[i].length() - array[i].replace(word, "").length()) / word.length();
                    treeMap.put(count, array[i]);
                }
                Collection<String> sout = treeMap.values();
                System.out.println("Ответ: ");
                for (String sin : sout) {
                    System.out.println(sin);
                }
            } else {
                System.out.println("Вы ввели неправельные данные. Попробуйте снова. ");
            }
        } else {
            System.out.println("Вы ввели неправельное число. Попробуйте снова. ");
        }
    }
}

