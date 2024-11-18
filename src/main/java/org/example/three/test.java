package org.example.three;

public class test {
    public static void main(String[] args) {
        TreeSearch<String> str = new TreeSearch<>();
//        str.insert("Едет");
//        str.insert("к");
//        str.insert("Нам");
//        str.insert("По");
//        str.insert("Полям");
//        str.insert("Синий");
//        str.insert("Трактор");
        str.insert("едет");
        str.insert("Синий");
        str.insert("По");
        str.insert("Полям");
        str.insert("Трактор");
        str.insert("к");
        str.insert("нам");

        System.out.println(str);
        System.out.println(str.inOrder());
        System.out.println(str.postOrder());
        System.out.println(str.deleteTree("По"));
        System.out.println(str.inOrder());
        System.out.println(str);
        System.out.println(str.deleteTree("едет"));
        System.out.println(str);

    }
}
