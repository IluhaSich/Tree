package org.example.one;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);
        System.out.println(tree);
        System.out.println(tree.preOrder());
        System.out.println(tree.inOrder());
        System.out.println(tree.postOrder());
        tree.forEachInOrder();
    }
}
