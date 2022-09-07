package com.worksUni;

/**
 * Moore and Mealy machine
 */

import java.util.Objects;

/**
 * Класс вершины для автомата Мура.
 * В классе хранятся номер вершины и что подаётся на выход
 */
class VertexItem {
    private int Vert;
    private final String exit;

    public VertexItem(int vert, String exitLetter) {
        Vert = vert;
        exit = exitLetter;
    }

    public String getKey() {
        return exit;
    }

    public int getVert() {
        return Vert;
    }

    public void setVert(int vert) {
        Vert = vert;
    }
}

public class MooreMealyMachine {

    public static void main(String[] args) {
        String aKey = " ";
        int numberVert = 7;
        /**Моё слово (х2 х2 х1 х1 х1 х1)*/
        String word = "2 2 1 1 1 1";
        String[] strs = word.split(" ");
        System.out.println("Задане слово: " + word);
        System.out.println("Автомат Мiлі: ");
        Mila(strs);
        System.out.println("\nАвтомат Мура: ");
        Myla(aKey, numberVert, strs);
    }


    /**
     * Автомат Мура
     * Используеться семь вершин, так как показано на графическом способе 2
     *
     * @param aKey       значение которое подаёться на выход у каждой вершины
     * @param numberVert количество вершин
     * @param strs       массив в который заносятся значения результатов, которые подаются на выход
     */
    private static void Myla(String aKey, int numberVert, String[] strs) {
        String[] Exit = new String[7];
        int vert;
        VertexItem aVertexItem = new VertexItem(1, "Y2 ");
        System.out.print("Y2 ");
        for (int j = 1; j <= numberVert; j++) {
            if (j == 1) {
                aKey = "Y2 ";
            } else if (j == 2) {
                aKey = "Y3 ";
            } else if (j == 3) {
                aKey = "Y1 ";
            } else if (j == 4) {
                aKey = "Y2 ";
            } else if (j == 5) {
                aKey = "Y1 ";
            } else if (j == 6) {
                aKey = "Y2 ";
            } else if (j == 7) {
                aKey = "Y2 ";
            } else if (j == 8) {
                aKey = "Y3 ";
            }
            vert = j;
            aVertexItem = new VertexItem(vert, aKey); // создаються вершины с полями (номер вершины, выходное значение)
            Exit[j - 1] = aVertexItem.getKey(); //заполняеться массив выходных значений
        }
        aVertexItem.setVert(1);
        for (String str : strs) {

            if ((Objects.equals(str, "1")) && (aVertexItem.getVert() == 1)) {
                aVertexItem.setVert(7);
                System.out.print(Exit[aVertexItem.getVert() - 1]);

            } else if ((Objects.equals(str, "2")) && (aVertexItem.getVert() == 1)) {
                aVertexItem.setVert(3);
                System.out.print(Exit[aVertexItem.getVert() - 1]);

            } else if ((Objects.equals(str, "1")) && (aVertexItem.getVert() == 2)) {
                aVertexItem.setVert(7);
                System.out.print(Exit[aVertexItem.getVert() - 1]);

            } else if ((Objects.equals(str, "2")) && (aVertexItem.getVert() == 2)) {
                aVertexItem.setVert(3);
                System.out.print(Exit[aVertexItem.getVert() - 1]);

            } else if ((Objects.equals(str, "1")) && (aVertexItem.getVert() == 3)) {
                aVertexItem.setVert(5);
                System.out.print(Exit[aVertexItem.getVert() - 1]);

            } else if ((Objects.equals(str, "2")) && (aVertexItem.getVert() == 3)) {
                aVertexItem.setVert(2);
                System.out.print(Exit[aVertexItem.getVert() - 1]);

            } else if ((Objects.equals(str, "1")) && (aVertexItem.getVert() == 4)) {
                aVertexItem.setVert(5);
                System.out.print(Exit[aVertexItem.getVert() - 1]);

            } else if ((Objects.equals(str, "2")) && (aVertexItem.getVert() == 4)) {
                aVertexItem.setVert(2);
                System.out.print(Exit[aVertexItem.getVert() - 1]);

            } else if ((Objects.equals(str, "1")) && (aVertexItem.getVert() == 5)) {
                aVertexItem.setVert(1);
                System.out.print(Exit[aVertexItem.getVert() - 1]);

            } else if ((Objects.equals(str, "2")) && (aVertexItem.getVert() == 5)) {
                aVertexItem.setVert(6);
                System.out.print(Exit[aVertexItem.getVert() - 1]);

            } else if ((Objects.equals(str, "1")) && (aVertexItem.getVert() == 6)) {
                aVertexItem.setVert(1);
                System.out.print(Exit[aVertexItem.getVert() - 1]);

            } else if ((Objects.equals(str, "2")) && (aVertexItem.getVert() == 6)) {
                aVertexItem.setVert(6);
                System.out.print(Exit[aVertexItem.getVert() - 1]);

            } else if ((Objects.equals(str, "1")) && (aVertexItem.getVert() == 7)) {
                aVertexItem.setVert(4);
                System.out.print(Exit[aVertexItem.getVert() - 1]);

            } else if ((Objects.equals(str, "2")) && (aVertexItem.getVert() == 7)) {
                aVertexItem.setVert(8);
                System.out.print(Exit[aVertexItem.getVert() - 1]);

            } else if ((Objects.equals(str, "1")) && (aVertexItem.getVert() == 8)) {
                aVertexItem.setVert(4);
                System.out.print(Exit[aVertexItem.getVert() - 1]);

            } else if ((Objects.equals(str, "2")) && (aVertexItem.getVert() == 8)) {
                aVertexItem.setVert(8);
                System.out.print(Exit[aVertexItem.getVert() - 1]);
            }
        }
    }

    /**
     * Автомат Мила
     *
     * @param strs массив в который заносятся значения результатов, которые подаются на выход
     */
    private static void Mila(String[] strs) {
        int continueVert = 1;
        for (String str : strs) {
            if ((Objects.equals(str, "1")) && (continueVert == 1)) {
                continueVert = 4;
                System.out.print("Y2 ");
            } else if ((Objects.equals(str, "2")) && (continueVert == 1)) {
                continueVert = 2;
                System.out.print("Y1 ");
            } else if ((Objects.equals(str, "1")) && (continueVert == 2)) {
                continueVert = 3;
                System.out.print("Y1 ");
            } else if ((Objects.equals(str, "2")) && (continueVert == 2)) {
                continueVert = 1;
                System.out.print("Y3 ");
            } else if ((Objects.equals(str, "1")) && (continueVert == 3)) {
                continueVert = 1;
                System.out.print("Y2 ");
            } else if ((Objects.equals(str, "2")) && (continueVert == 3)) {
                System.out.print("Y2 ");
            } else if ((Objects.equals(str, "1")) && (continueVert == 4)) {
                continueVert = 2;
                System.out.print("Y2 ");
            } else if ((Objects.equals(str, "2")) && (continueVert == 4)) {
                System.out.print("Y3 ");
            }
        }
    }
}
