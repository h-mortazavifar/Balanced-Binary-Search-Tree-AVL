package BinaryST;

public class Test {

    public static void main(String[] args) {
        AVL avl = new AVL();
        avl.setRoot(avl.insert(avl.getRoot(), 10));
        avl.setRoot(avl.insert(avl.getRoot(), 20));
        avl.setRoot(avl.insert(avl.getRoot(), 30));
        avl.setRoot(avl.insert(avl.getRoot(), 40));
        avl.setRoot(avl.insert(avl.getRoot(), 50));
        avl.setRoot(avl.insert(avl.getRoot(), 25));
        avl.setRoot(avl.insert(avl.getRoot(), 70));
        avl.setRoot(avl.insert(avl.getRoot(), 99));
        avl.setRoot(avl.insert(avl.getRoot(), 140));
        avl.setRoot(avl.insert(avl.getRoot(), 5));
        avl.setRoot(avl.insert(avl.getRoot(), 2));
        avl.setRoot(avl.insert(avl.getRoot(), 7));
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
        System.out.println("smallest node is: " + avl.smallest());
        System.out.println("largest node is: " + avl.largest());

        avl.delete(10);
        System.out.println(avl);
        avl.delete(7);
        System.out.println(avl);
    }
}
