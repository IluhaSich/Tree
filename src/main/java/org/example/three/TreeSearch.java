package org.example.three;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class TreeSearch<T extends Comparable<T>> {
    private Node root;

    public TreeSearch() {
    }

    public TreeSearch(T value) {
        this.root = new Node(value);
    }

    public StringBuilder preOrder() {
        return preOrder(root, new StringBuilder());
    }

    private StringBuilder preOrder(Node node, StringBuilder result) {
        if (node != null) {
            result.append(node.value).append(" ");
            preOrder(node.leftChildren, result);
            preOrder(node.rightChildren, result);
        }
        return result;
    }

    public StringBuilder inOrder() {
        return inOrder(root, new StringBuilder());
    }

    private StringBuilder inOrder(Node node, StringBuilder result) {
        if (node != null) {
            inOrder(node.leftChildren, result);
            result.append(node.value).append(" ");
            inOrder(node.rightChildren, result);
        }
        return result;
    }

    public StringBuilder postOrder() {
        return postOrder(root, new StringBuilder());
    }

    private StringBuilder postOrder(Node node, StringBuilder result) {
        if (node != null) {
            postOrder(node.leftChildren, result);
            postOrder(node.rightChildren, result);
            result.append(node.value).append(" ");
        }
        return result;
    }

    public void insert(T newValue) {
        root = insert(root, newValue);
    }

    private Node insert(Node node, T newValue) {
        if (node == null) {
            return new Node(newValue);
        }
        if (node.value.compareTo(newValue) > 0) {
            node.leftChildren = insert(node.leftChildren, newValue);
        } else if (node.value.compareTo(newValue) < 0) {
            node.rightChildren = insert(node.rightChildren, newValue);
        } else return node;
        return node;
    }

    public boolean contains(T searchValue) {
        return contains(root, searchValue);
    }

    private boolean contains(Node node, T searchValue) {
        if (node == null) return false;
        if (node.value.equals(searchValue)) return true;
        if (node.value.compareTo(searchValue) > 0) {
            return contains(node.leftChildren, searchValue);
        } else {
            return contains(node.rightChildren, searchValue);
        }
    }

    public void forEachInOrder() {
        Consumer<? super String> printConsumer = System.out::print;
        String[] intValues = preOrder().toString().split(" ");
        Stream<String> values = Stream.of(intValues);
        values.sorted().forEach(printConsumer);
    }

    public T search(T searchValue) {
        Node foundNode = search(root, searchValue);
        return foundNode != null ? foundNode.value : null;
    }

    private Node search(Node node, T searchValue) {
        if (node == null || node.value.equals(searchValue)) return node;
        if (node.value.compareTo(searchValue) > 0) {
            return search(node.leftChildren, searchValue);
        } else {
            return search(node.rightChildren, searchValue);
        }
    }

    public T getMax() {
        return getMax(root);
    }

    private T getMax(Node node) {
        if (node == null) return null;
        while (node.rightChildren != null) node = node.rightChildren;
        return node.value;
    }

    public T getMin() {
        return getMin(root);
    }

    private T getMin(Node node) {
        if (node == null) return null;
        while (node.leftChildren != null) node = node.leftChildren;
        return node.value;
    }

    public boolean deleteTree(T value) {
        return deleteTree(root, value);
    }

    private boolean deleteTree(Node node, T value) {
        if (node == null || node.value.equals(value)) {
            root = null;
            return true;
        }
        if (node.value.compareTo(value) > 0) {
            if (node.leftChildren != null && node.leftChildren.value == value) {
                node.leftChildren = null;
                return true;
            }
            deleteTree(node.leftChildren, value);
        } else {
            if (node.rightChildren != null && node.rightChildren.value == value) {
                node.rightChildren = null;
                return true;
            }
            deleteTree(node.rightChildren, value);
        }
        return false;
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        toString(root, sb, "");
//        return sb.toString();
//    }
//
//    private void toString(Node node, StringBuilder sb, String indent) {
//        if (node != null) {
//            toString(node.rightChildren, sb, indent + "   ");
//            sb.append(indent).append(node.value).append("\n");
//            toString(node.leftChildren, sb, indent + "   ");
//        }
//    }

    public T getValue() {
        return root.value;
    }

    public void setValue(T value) {
        root.value = value;
    }

    public Node getLeftChildren() {
        return root.leftChildren;
    }

    public void setLeftChildren(Node leftChildren) {
        root.leftChildren = leftChildren;
    }

    public Node getRightChildren() {
        return root.rightChildren;
    }

    public void setRightChildren(Node rightChildren) {
        root.rightChildren = rightChildren;
    }

    private class Node {
        private T value;
        private Node leftChildren;
        private Node rightChildren;

        public Node(T value) {
            this.value = value;
        }
    }

//    void delete(T input) {
//        Tree<T> deleted = searchTree(input);
//        if (deleted.value == null) return;
//        if (deleted.value == input) {
//            // удаление лепестка
//            if (deleted.rightChildren == null && deleted.leftChildren == null) {
//                if (deleted.parent != null) {
//                    if (deleted.parent.leftChildren != null &&
//                            deleted.parent.leftChildren.equals(deleted)) {
//                        deleted.parent.leftChildren = null;
//                    } else {
//                        deleted.parent.rightChildren = null;
//                    }
//                }
//                deleted.parent = null;
//                deleted.value = null;
//            }
//            // удаление родителя
//            else {
//                if (deleted.rightChildren != null) {
//                    T del = deleted.rightChildren.getMin();
//                    deleted.delete(del);
//                    deleted.value = del;
//
//                }
//            }
//        }
//    }

//    Tree<T> searchTree(T search) {
//        if (value == null) return null;
//        if (value != search) {
//            if (value.compareTo(search) > 0) {
//                if (leftChildren == null) return null;
//                return leftChildren.searchTree(search);
//            } else {
//                if (rightChildren == null) return null;
//                return rightChildren.searchTree(search);
//            }
//        }
//        return this;
//    }

    private List<List<T>> orderBfs() {
        List<List<T>> levels = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (queue.size() > 0) {
            var level = new ArrayList<Node>();
            while (queue.size() > 0) level.add(queue.remove());

            levels.add(new ArrayList<>());
            levels.get(levels.size() - 1).addAll(level.stream().map((n) -> n.value).toList());

            var levelNodes = level.stream()
                    .flatMap((n) -> Stream.of(n.leftChildren, n.rightChildren)
                            .map((node) -> Objects.requireNonNullElseGet(node, () -> new Node(null)))
                    ).toList();
            if (levelNodes.stream().anyMatch((node) -> node.value != null)) queue.addAll(levelNodes);
        }

        return levels;
    }

    @Override
    public String toString() {
        if (root == null) {
            return null;
        }
        var levels = orderBfs();

        var maxLengthNode = levels.stream()
                .flatMap(List::stream)
                .mapToInt((node) -> node != null ? node.toString().length() : 0)
                .max()
                .orElseGet(() -> 1);

        var maxNodes = levels.get(levels.size() - 1).size();
        var maxSpace = maxNodes - 1;

        var space = " ".repeat(maxLengthNode);

        StringBuilder result = new StringBuilder();

        for (List<T> level : levels) {
            result.append(space.repeat(maxSpace));

            for (T node : level) {
                var symbol = Objects.requireNonNullElseGet(node, () -> space).toString();
                symbol = symbol != null ? " ".repeat(maxLengthNode - symbol.length()) + symbol : space;

                result.append(symbol).append(space.repeat((maxSpace + 1) * 2 - 1));
            }

            result.append("\n");
            maxSpace = ((maxSpace + 1) / 2) - 1;
        }

        return result.toString();
    }

}
