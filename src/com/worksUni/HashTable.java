package com.worksUni;

/**
 * HashTable
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class DataItem {
    private final int iData;

    public DataItem(int ii) {
        iData = ii;
    }

    public int getKey() {
        return iData;
    }
}

class HashTable {
    private final DataItem[] hashArray; // массив для хеш таблицы
    private final int arraySize;
    private final DataItem nonItem; // для удалённых элементов

    /**
     * Конструктор
     */
    public HashTable(int size) {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1); // если элемент удалён то значинеие -1
    }

    /**
     * Вывод
     */
    public void displayTable() {
        System.out.print("Table: ");
        for (int j = 0; j < arraySize; j++) {
            if (hashArray[j] != null)
                System.out.print(hashArray[j].getKey() + " ");
            else
                System.out.print("** ");
        }
        System.out.println(" ");
    }

    /**
     * Хэш функция
     */
    public int hashFunc(int key) {
        return key % arraySize;
    }

    /**
     * Вставка элемента
     *
     * @param item элемент, который нужно вставить
     */
    public void insert(DataItem item) {
        int key = item.getKey(); // извлекаеться ключ элемента
        int hashVal = hashFunc(key); // хэширование ключа
        //вставка происходит при условии что есть либо свободные, либо удлённые элементы
        while (hashArray[hashVal] != null &&
                hashArray[hashVal].getKey() != -1) {
            ++hashVal; // переход к следующей ячейке
            hashVal = hashFunc(hashVal);
        }
        hashArray[hashVal] = item; // вставка элемента
    }

    /*Если происходит колизия то мы обращаемся к следующей ячейке даль смотрим остачу от деления на размер массива */

    /**
     * Удаление
     *
     * @param key ключ(значение) удаляемого элемента
     */
    public void delete(int key) {
        int hashVal = hashFunc(key); // хэширование ключа
        while (hashArray[hashVal] != null) // пока ячейка не пустая и такой элемент есть
        {
            if (hashArray[hashVal].getKey() == key) {
                // сохраняю элемент в временную переменную
                hashArray[hashVal] = nonItem; // удаляю элемент
                return; // возвращаю элемент
            }
            ++hashVal; // переход к следующей ячейке
            hashVal = hashFunc(hashVal);
        }
    }

    /**
     * Поиск
     *
     * @param key ключ(значение) элемента
     */
    public DataItem find(int key) {
        int hashVal = hashFunc(key); // хэширование ключа
        while (hashArray[hashVal] != null) // пока ячейка не пустая происходит поиск элемента
        {
            if (hashArray[hashVal].getKey() == key) {
                return hashArray[hashVal];
            } // если элемент есть выводиться его значение
            ++hashVal; // переход к следующей ячейке
            hashVal = hashFunc(hashVal);
        }
        return null; // если такого элемента нет
    }
}

class HashTableApp {
    public static void main(String[] args) throws IOException {
        DataItem aDataItem;
        int aKey, size, number;
        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        number = getInt();

        HashTable theHashTable = new HashTable(size); //создание таблицы
        for (int j = 0; j < number; j++) // заполнение рандомными значениями
        {
            aKey = (int) (java.lang.Math.random() * 2 * size);
            aDataItem = new DataItem(aKey);
            theHashTable.insert(aDataItem);
        }
        while (true) {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, delete, or find:");
            char choice = getChar();
            switch (choice) {
                case 's':
                    theHashTable.displayTable();
                    break;
                case 'i':
                    System.out.print("Enter key value to insert: ");
                    aKey = getInt();
                    aDataItem = new DataItem(aKey);
                    theHashTable.insert(aDataItem);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = getInt();
                    theHashTable.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = getInt();
                    aDataItem = theHashTable.find(aKey);
                    if (aDataItem != null) {
                        System.out.println("Found " + aKey);
                    } else
                        System.out.println("Could not find " + aKey);
                    break;
                case 'b':
                    break;
                default:
                    System.out.print("Invalid entry\n");
            }
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}