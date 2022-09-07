package com.worksUni;

/**
 * Turing machine
 */

import java.util.Objects;

public class TuringMachine {

    public static void main(String[] args) {
        /**Моё слово (х0 х0 х1 х1 х0 x1)*/
        String word = "0 0 1 1 0 1";
        String[] strs = word.split(" ");
        System.out.println("Машина Тьюринга");
        System.out.println("Задане слово:" + word);
        System.out.print("Результат: ");
        turing(strs);
    }

    private static void turing(String[] strs) {
        String continueVert = "E1";
        for (String str : strs) {
            if ((Objects.equals(str, "1")) && (continueVert.equals("E1"))) {
                continueVert = "B1";
                System.out.print("0R ");
            } else if ((Objects.equals(str, "0")) && (continueVert.equals("E1"))) {
                continueVert = "E2";
                System.out.print("0R ");
            } else if ((Objects.equals(str, "1")) && (continueVert.equals("E2"))) {
                continueVert = "G1";
                System.out.print("1S ");
            } else if ((Objects.equals(str, "0")) && (continueVert.equals("E2"))) {
                continueVert = "E1";
                System.out.print("1R ");
            } else if ((Objects.equals(str, "1")) && (continueVert.equals("G1"))) {
                continueVert = "G2";
                System.out.print("0L ");
            } else if ((Objects.equals(str, "0")) && (continueVert.equals("G1"))) {
                continueVert = "G1";
                System.out.print("1R ");
            } else if ((Objects.equals(str, "1")) && (continueVert.equals("G2"))) {
                continueVert = "G0";
                System.out.print("1S ");
            } else if ((Objects.equals(str, "0")) && (continueVert.equals("G2"))) {
                continueVert = "G2";
                System.out.print("0R ");
            } else if ((Objects.equals(str, "1")) && (continueVert.equals("B1"))) {
                continueVert = "D1";
                System.out.print("1S ");
            } else if ((Objects.equals(str, "0")) && (continueVert.equals("B1"))) {
                continueVert = "B2";
                System.out.print("0R ");
            } else if ((Objects.equals(str, "1")) && (continueVert.equals("B2"))) {
                continueVert = "B1";
                System.out.print("1S ");
            } else if ((Objects.equals(str, "0")) && (continueVert.equals("B2"))) {
                continueVert = "B2";
                System.out.print("1L ");
            } else if ((Objects.equals(str, "1")) && (continueVert.equals("D1"))) {
                continueVert = "D0";
                System.out.print("1S ");
            } else if ((Objects.equals(str, "0")) && (continueVert.equals("D1"))) {
                continueVert = "D2";
                System.out.print("0R ");
            } else if ((Objects.equals(str, "1")) && (continueVert.equals("D2"))) {
                continueVert = "D0";
                System.out.print("1S ");
            } else if ((Objects.equals(str, "0")) && (continueVert.equals("D2"))) {
                continueVert = "D1";
                System.out.print("1L ");
            }
        }
    }
}