package Test;

import BinaryST.AVL;

public class Test {

    public static void main(String[] args) {
        AVL avl = new AVL();
        avl.setRoot(avl.insert(avl.getRoot(), 10));
        System.out.println(avl);
        avl.setRoot(avl.insert(avl.getRoot(), 20));
        System.out.println(avl);
        avl.setRoot(avl.insert(avl.getRoot(), 30));
        System.out.println(avl);
        avl.setRoot(avl.insert(avl.getRoot(), 40));
        System.out.println(avl);
        avl.setRoot(avl.insert(avl.getRoot(), 50));
        System.out.println(avl);
        avl.setRoot(avl.insert(avl.getRoot(), 25));
        System.out.println(avl);
        avl.setRoot(avl.insert(avl.getRoot(), 70));
        System.out.println(avl);
        avl.setRoot(avl.insert(avl.getRoot(), 99));
        System.out.println(avl);
        avl.setRoot(avl.insert(avl.getRoot(), 140));
        System.out.println(avl);
        avl.setRoot(avl.insert(avl.getRoot(), 5));
        System.out.println(avl);
        avl.setRoot(avl.insert(avl.getRoot(), 2));
        System.out.println(avl);
        avl.setRoot(avl.insert(avl.getRoot(), 7));
        System.out.println(avl);
        avl.setRoot(avl.insert(avl.getRoot(), 6));
        System.out.println(avl);
        System.out.print("Inorder: ");
        avl.inOrder(avl.getRoot());
        System.out.println();
        System.out.print("Pre order: ");
        avl.preOrder(avl.getRoot());
        System.out.println();
        System.out.print("Post order: ");
        avl.postOrder(avl.getRoot());
        System.out.println();
        System.out.println("Smallest node is: " + avl.smallest());
        System.out.println("Largest node is: " + avl.largest());
        System.out.println("Delete started: delete 10");
        avl.delete(10);
        System.out.println(avl);
        System.out.println("delete 7");
        avl.delete(7);
        System.out.println(avl);
        System.out.println("delete 140");
        avl.delete(140);
        System.out.println(avl);
    }
}
