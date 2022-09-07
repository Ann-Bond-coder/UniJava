package com.worksUni;

import java.util.Scanner;

class BubbleSort {
    private static int counter;

    void bubbleSort(int arr[], int n) {

        for (int i = 0; i < n - 1; i++) {
            counter++;
            for (int j = 0; j < n - i - 1; j++) {
                counter++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    counter++;
                }
            }
        }
    }

    // Вывод на экран массива
    static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }

    public static void main(String args[]) {
        BubbleSort bub = new BubbleSort();
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
        bub.bubbleSort(arr, amount);
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.println("Відсортований масив (counting sort): ");
        printArray(arr);
        System.out.println("Час виконання: " + timeConsumedMillis);
        System.out.println("Counter = " + counter);
    }
}



