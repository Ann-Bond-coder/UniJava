package com.worksUni;

import java.util.Scanner;

public class QuickSort {

    private static int counter;

    static void quickSort(int[] source, int leftElement, int rightElement) {
        int leftMarker = leftElement;
        int rightMarker = rightElement;
        int pivot = source[(leftMarker + rightMarker) / 2];
        while (leftMarker <= rightMarker) {
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (source[leftMarker] < pivot) {
                leftMarker++;
            }
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (source[rightMarker] > pivot) {
                rightMarker--;
            }
            // Проверка, не нужно ли обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {
                    int tmp = source[leftMarker];
                    source[leftMarker] = source[rightMarker];
                    source[rightMarker] = tmp;
                }
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarker++;
                rightMarker--;
                counter++;
            }
        }

        // Выполняем рекурсивно для частей
        if (leftMarker < rightElement) {
            quickSort(source, leftMarker, rightElement);
        }
        if (leftElement < rightMarker) {
            quickSort(source, leftElement, rightMarker);
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
        quickSort(arr, 0, arr.length - 1);
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.println("Відсортований масив (insertion sort): ");
        printArray(arr);
        System.out.println("Час виконання: " + timeConsumedMillis);
        System.out.println("Counter = " + counter);
    }
}