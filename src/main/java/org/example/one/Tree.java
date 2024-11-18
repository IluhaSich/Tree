package org.example.one;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class Tree<T extends Comparable<T>> {
    private T key;
    private Tree<T> leftChildren;
    private Tree<T> rightChildren;

    public Tree() {
    }

    public Tree(T key) {
        this.key = key;
    }

    public void forEachInOrder(){
        Consumer<? super String> printConsumer = System.out::print;
        String[] intValues = preOrder().toString().split(" ");
        Stream<String> values = Stream.of(intValues);
        values.sorted().forEach(printConsumer);
    }

    public void insert(T value){
        if (key == null) {
            key = value;
            return;
        }
        if (rightChildren == null){
            rightChildren = new Tree<>(value);
            return;
        }
        if (leftChildren == null){
            leftChildren = new Tree<>(value);
        } else leftChildren.insert(value);
    }

    public StringBuilder preOrder() {
        StringBuilder result = new StringBuilder();
        if (key != null) {
            result.append(key).append(" ");
            if (leftChildren != null) {
                result.append(leftChildren.preOrder());
            }
            if (rightChildren != null) {
                result.append(rightChildren.preOrder());
            }
        } else return null;
        return result;
    }

    public StringBuilder inOrder() {
        StringBuilder result = new StringBuilder();
        if (key != null) {
            if (leftChildren != null) {
                result.append(leftChildren.inOrder());
            }
            result.append(key).append(" ");
            if (rightChildren != null) {
                result.append(rightChildren.inOrder());
            }
        } else return null;
        return result;
    }

    public StringBuilder postOrder() {
        StringBuilder result = new StringBuilder();
        if (key != null) {
            if (leftChildren != null) {
                result.append(leftChildren.postOrder());
            }
            if (rightChildren != null) {
                result.append(rightChildren.postOrder());
            }
            result.append(key).append(" ");
        } else return null;
        return result;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(sb, "");
        return sb.toString();
    }

    private void toString(StringBuilder sb, String indent) {
        if (rightChildren != null) {
            rightChildren.toString(sb, indent + "   ");
        }

        sb.append(indent).append(key).append("\n");

        if (leftChildren != null) {
            leftChildren.toString(sb, indent + "   ");
        }
    }
}
