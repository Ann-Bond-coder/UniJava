package com.worksUni;

/**
 * Bidirectional cyclic linked list
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Link {
    public String dData; // Данные
    public Link next; // Следующий элемент в списке
    public Link prev;

    public Link(String d) // Конструктор
    {
        dData = d;
    }

    public void displayLinkNormal() // Вывод содержимого элемента
    {
        System.out.print(dData + " ");
    }

    public void displayLink() // Вывод содержимого элемента по правому краю
    {
        System.out.printf("%175s %n", dData);
    }
}

class FirstLastList {
    private Link first; // Ссылка на первый элемент
    private Link last; // Ссылка на последний элемент

    public FirstLastList() // Конструктор
    {
        first = null; // Список пока не содержит элементов
        last = null;
    }

    public boolean isEmpty() // true, если список пуст
    {
        return first == null;
    }

    /*Вычисление длинны списка*/
    public int length() {
        int length = 0;
        Link current = first;

        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    /**
     * @param index индекс конкретного элемента
     *              result элемент с искомым индексом
     */
    public Link getByIndex(int index) {
        Link current = first;
        int c = 0;
        while (current != null && c != index) {
            current = current.next;
            c++;
        }
        return current;
    }

    /**
     * @param theList главный список слов
     *                result Метод меняет местами попарно слова из списка и ссылки на них.
     */
    public void changeList(FirstLastList theList) {
        Link current = first;
        Link l = current.next;

        while (l.next != null) {
            if (current == first) {
                current.next = l.next;
                l.prev = current.prev;
                l.next = current;
                current.prev = l;
                first = l;
                l = current.next.next;
            } else {
                Link h = current.next;
                h.next = l.next;
                l.next.prev = h;
                l.next = h;
                h.prev = l;
                current.next = l;
                l.prev = current;
                current = h;
                l = h.next.next;
            }
            if (l.next == null) {
                Link h = current.next;
                h.next = l.next;
                l.next = h;
                h.prev = l;
                current.next = l;
                l.prev = current;
                break;
            }
        }
    }

    /**
     * @param theList список слов в котором нам нужно изменить порядок букв в словах
     *                result в каждом втором элементе переварачиваеться слово и
     *                список выводиться в консоли
     */
    public void changeWord(FirstLastList theList) {
        for (int i = 1; i <= length(); i += 2) {
            reversWord(getByIndex(i));
        }
        theList.displayList();
    }

    /**
     * @param current ссылка на текущий элемент
     *                result в слове меняеться порядок слов на противоположный
     */
    public void reversWord(Link current) {
        StringBuilder sb = new StringBuilder(current.dData);
        sb.reverse();
        current.dData = String.valueOf(sb);
    }

    /**
     * @param dd элемент, который нужно вставить в список
     *           result вставка элемента в конец списка
     */
    public void insertLast(String dd) {

        Link newLink = new Link(dd); // make new link
        if (isEmpty()) // if empty list,
            first = newLink; // first --> newLink
        else {
            last.next = newLink; // old last --> newLink
            newLink.prev = last; // old last <-- newLink
        }
        last = newLink; // newLink <-- last
    }

    /*Вывод на консоль список*/
    public void displayListNormal() {
        Link current = first; // От начала списка
        while (current != null) // Перемещение до конца списка
        {
            current.displayLinkNormal(); // Вывод данных
            current = current.next; // Переход к следующему элементу
        }
        System.out.println("");
    }

    /*Вывод на консоль список в правой части єкрана*/
    public void displayList() {
        Link current = first; // От начала списка
        while (current != null) // Перемещение до конца списка
        {
            current.displayLink(); // Вывод данных
            current = current.next; // Переход к следующему элементу
        }
        System.out.println("");
    }
}

class FirstLastApp {
    public static void main(String[] args) throws IOException {
        String text = Files.readString(Paths.get("D:\\Lab3.txt"));
        String[] strs = text.split(" ");

        // Создание нового списка
        FirstLastList theList = new FirstLastList();
        //Запись в список
        for (String str : strs) {
            theList.insertLast(str);
        }
        System.out.println("Список слів із тексту: ");
        theList.displayListNormal();
        theList.changeList(theList);
        System.out.println("\n Відредагований текст: ");
        theList.changeWord(theList);
    }
}
