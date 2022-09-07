package com.worksUni;

import java.util.Arrays;
import java.util.Scanner;

public class CountingSort {
    static void countSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;
        int count[] = new int[range];
        int output[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }

    static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введіть кількість елементів ");
        int amount = in.nextInt();
        int[] arr = new int[amount];
        for (int i = 0; i < amount; i++) {
            arr[i] = (int) (Math.random() * amount);
        }
        System.out.println("Згенерований масив: ");
        printArray(arr);
        long start = System.currentTimeMillis();
        countSort(arr);
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.println("Відсортований масив (counting sort): ");
        printArray(arr);
        System.out.println("Час виконання: " + timeConsumedMillis);
    }
}

