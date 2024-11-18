package org.example.three;

public class Main {
    public static void main(String[] args) {
        TreeSearch<Integer> treeSearch = new TreeSearch<>(5);
        treeSearch.insert(3);
        treeSearch.insert(2);
        //
        treeSearch.insert(7);
        treeSearch.insert(7);
        treeSearch.insert(8);
        //
        treeSearch.insert(4);
        treeSearch.insert(6);

        System.out.println(treeSearch);
        System.out.println("search:");
        System.out.println(treeSearch.search(7));
        System.out.println(treeSearch.search(77));
        System.out.println(treeSearch.search(1));
        System.out.println(treeSearch.search(2)+"\n");
        System.out.println("contains:");
        System.out.println(treeSearch.contains(7));
        System.out.println(treeSearch.contains(77));
        System.out.println(treeSearch.contains(1));
        System.out.println(treeSearch.contains(2)+"\n");

        System.out.println("min: " + treeSearch.getMin());
        System.out.println("max: " + treeSearch.getMax());

        System.out.println(treeSearch.preOrder());
        System.out.println(treeSearch.inOrder());
        System.out.println(treeSearch.postOrder());
        treeSearch.forEachInOrder();
    }
}