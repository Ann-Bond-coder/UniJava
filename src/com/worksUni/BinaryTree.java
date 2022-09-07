package com.worksUni;

/**
 * Binary search tree
 */

import java.util.Stack;

class Node {
    private int value; // ключ узла
    private Node leftChild; // Левый узел потомок
    private Node rightChild; // Правый узел потомок

    public void printNode() { // Вывод значения узла в консоль
        System.out.println(" Выбранный узел имеет значение :" + value);
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

    public Node getLeftChild() {
        return this.leftChild;
    }

    public void setLeftChild(final Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return this.rightChild;
    }

    public void setRightChild(final Node rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }
}

class Tree {
    public Node rootNode; // корневой узел
    public Tree() { // Пустое дерево
        rootNode = null;
    }

    /**
     * Поиск узла по значению
     *
     * @param value искомое значение
     */
    public Node findNodeByValue(int value) {
        Node currentNode = rootNode; // начало с корневого узла
        while (currentNode.getValue() != value) {
            if (value < currentNode.getValue()) { // если значение меньше, то поиск по левой стороне
                currentNode = currentNode.getLeftChild();
            } else { //иначе по правой
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null) { // если потомка нет, возвращение null
                System.out.println("None");
                return null;
            }
        }
        System.out.println("True");
        return currentNode;
    }

    /**
     * Вставка узла с заданным ключом
     *
     * @param value искуемое значение
     */
    public void insertNode(int value) {
        Node newNode = new Node(); // создание нового узла
        newNode.setValue(value); // заполнение данными
        if (rootNode == null) { // если узла нет, то созданный эемент и есть корневой
            rootNode = newNode;
        } else { // если корневой узел занят, то нужно начинать с корневого узла
            Node currentNode = rootNode;
            Node parentNode;
            while (true) {
                parentNode = currentNode;
                if (value == currentNode.getValue()) {   // если такой элемент уже есть, не вставлять и выйти
                    return;
                } else if (value < currentNode.getValue()) {   // если значение меньше, то цепочька идёт влево
                    currentNode = currentNode.getLeftChild();
                    // если был достигнут конец цепочки, то вставить в самый левый конец и выйти
                    if (currentNode == null) {
                        parentNode.setLeftChild(newNode);
                        return;
                    }
                } else { // в другом случае; дойти до конца цепочки и вставить в крайний правый и выйти
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        parentNode.setRightChild(newNode);
                        return;
                    }
                }
            }
        }
    }

    /**
     * Удаление узла с заданным ключом
     *
     * @param value искуемое значение
     */
    public boolean deleteNode(int value) {
        Node currentNode = rootNode;
        Node parentNode = rootNode;
        boolean isLeftChild = true;
        while (currentNode.getValue() != value) {
            parentNode = currentNode;
            if (value < currentNode.getValue()) { // если значение меньше, то цепочка идёт влево
                isLeftChild = true;
                currentNode = currentNode.getLeftChild();
            } else { // иначе вправо
                isLeftChild = false;
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null) {
                return false;
            } // yзел не найден
        }

        // узел просто удаляется, если не потомков
        if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {
            if (currentNode == rootNode) // если узел - корень, то дерево очищается
                rootNode = null;
            else if (isLeftChild)
                parentNode.setLeftChild(null); // если нет - узел отсоединяется, от родителя
            parentNode.setRightChild(null);
        } else if (currentNode.getRightChild() == null) { // узел заменяется левым поддеревом, если правого потомка нет
            if (currentNode == rootNode)
                rootNode = currentNode.getLeftChild();
            else if (isLeftChild)
                parentNode.setLeftChild(currentNode.getLeftChild());
            else
                parentNode.setRightChild(currentNode.getLeftChild());

        } else { // если есть два потомка, узел заменяется преемником
            Node heir = receiveHeir(currentNode);// поиск преемника для удаляемого узла
            if (currentNode == rootNode)
                rootNode = heir;
            else if (isLeftChild)
                parentNode.setLeftChild(heir);
            else
                parentNode.setRightChild(heir);
        }
        return true; // элемент успешно удалён
    }

    /**
     * Метод для возвращения следующего элемента.
     * Для сначала переходим к правому потомку, а потом по цепочке левых потомков этого узла.
     *
     * @param node значение элемента
     */
    private Node receiveHeir(Node node) {
        Node parentNode = node;
        Node heirNode = node;
        Node currentNode = node.getRightChild(); // Переход к правому потомку
        while (currentNode != null) // Пока есть левые потомки, потомка задаём как текущий узел
        {
            parentNode = heirNode;
            heirNode = currentNode;
            currentNode = currentNode.getLeftChild(); // переход к левому потомку
        }
        parentNode.setLeftChild(heirNode.getRightChild());
        heirNode.setRightChild(node.getRightChild());
        heirNode.setLeftChild(node.getLeftChild());
        return heirNode;// возвращаем приемника
    }

    /**
     * Вывод всего дерева в консоль
     */
    public void printTree() {
        Stack<Node> globalStack = new Stack<>(); // общий стек для значений дерева
        globalStack.push(rootNode);
        int distance = 50; //расстояние между элементами
        boolean isRowEmpty = false;
        System.out.println();
        while (!isRowEmpty) {
            Stack<Node> localStack = new Stack<>(); // создание локального стека для задания потомков элемента
            isRowEmpty = true;
            for (int j = 0; j < distance + 4; j++) {
                System.out.print(' ');
            }
            while (!globalStack.isEmpty()) { // пока есть элементы
                Node temp = globalStack.pop(); // взять следующий элемент, удаляя его
                if (temp != null) {
                    System.out.print(temp.getValue()); // выводим его значение в консоли
                    localStack.push(temp.getLeftChild()); // соохраняем в локальный стек детей текущего элемента
                    localStack.push(temp.getRightChild());
                    if (temp.getLeftChild() != null || temp.getRightChild() != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("**");// - если элемент пустой
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < distance * 2; j++)
                    System.out.print(" ");
            }
            System.out.println();
            distance /= 2;// уменьшать расстояние с каждым уровнем
            while (!localStack.isEmpty())
                globalStack.push(localStack.pop()); // переместить все элементы из локального стека в глобальный
        }
        System.out.println();
    }

    /**
     * Поиск минимального элемента
     */
    public int getMin() {
        int min = rootNode.getValue();
        if (rootNode.getLeftChild() != null) {
            if (min > rootNode.getLeftChild().getValue())
                min = rootNode.getLeftChild().getValue();
            int x = rootNode.getLeftChild().getLeftChild().getValue();
            if (min > x) min = x;
        }
        return min;
    }
}

public class BinaryTree {
    public static void main(String[] args) {
        Tree tree = new Tree();
        // вставка
        tree.insertNode(46);
        tree.insertNode(26);
        tree.insertNode(2);
        tree.insertNode(79);
        tree.insertNode(76);
        tree.insertNode(99);
        tree.insertNode(41);
        tree.insertNode(37);
        tree.insertNode(51);
        tree.insertNode(6);
        tree.insertNode(97);
        tree.insertNode(35);
        tree.insertNode(93);
        tree.insertNode(10);
        tree.insertNode(21);
        tree.printTree();

        // поиск
        System.out.println("Поиск элементов: ");
        Node foundNode = tree.findNodeByValue(97);
        foundNode.printNode();
        Node foundNode2 = tree.findNodeByValue(10);
        foundNode2.printNode();
        Node foundNode3 = tree.findNodeByValue(35);
        foundNode3.printNode();
        System.out.println("Минимальный элемент: " + tree.getMin());

        // удаление
        System.out.println("\nУдаление элементов: 46, 99, 26");
        tree.deleteNode(46);
        tree.deleteNode(99);
        tree.deleteNode(26);
        tree.printTree();
    }
}
