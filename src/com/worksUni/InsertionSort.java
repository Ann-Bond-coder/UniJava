package com.worksUni;

import java.util.Scanner;

public class InsertionSort {

    private static int counter;

    public static void insertionSort(int arr[]) {
        int size = arr.length;
        for (int i = 1; i < size; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && key < arr[j]) {
                arr[j + 1] = arr[j];
                --j;
                counter++;
            }
            arr[j + 1] = key;
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
        insertionSort(arr);
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.println("Відсортований масив (insertion sort): ");
        printArray(arr);
        System.out.println("Час виконання: " + timeConsumedMillis);
        System.out.println("Counter = " + counter);
    }
}
