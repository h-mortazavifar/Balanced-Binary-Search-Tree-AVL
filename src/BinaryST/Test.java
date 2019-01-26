package BinaryST;

public class Test {

    public static void main(String[] args) {
        AVL avl = new AVL();
        avl.setRoot(avl.insert(avl.getRoot(),10));
        avl.setRoot(avl.insert(avl.getRoot(),20));
        avl.setRoot(avl.insert(avl.getRoot(),30));
        avl.setRoot(avl.insert(avl.getRoot(),40));
        avl.setRoot(avl.insert(avl.getRoot(),50));
        avl.setRoot(avl.insert(avl.getRoot(),25));
        avl.setRoot(avl.insert(avl.getRoot(),70));
        avl.setRoot(avl.insert(avl.getRoot(),99));
        avl.setRoot(avl.insert(avl.getRoot(),140));
        avl.setRoot(avl.insert(avl.getRoot(),5));
        avl.setRoot(avl.insert(avl.getRoot(),2));
        avl.setRoot(avl.insert(avl.getRoot(),7));
        avl.setRoot(avl.insert(avl.getRoot(),6));
        System.out.println(avl);

        System.out.println("Inorder: ");
        avl.inOrder(avl.getRoot());
        System.out.println();
//        System.out.println("Preorder: ");
//        avl.preOrder(avl.getRoot());
//        System.out.println();
//        System.out.println("Postorder: ");
//        avl.postOrder(avl.getRoot());


//        avl.delete(25);
//        System.out.println();
//        System.out.println("Inorder: ");
//        avl.inOrder(avl.getRoot());
//        System.out.println(avl);
//        avl.delete(20);
//        System.out.println();
//        System.out.println("Inorder: ");
//        avl.inOrder(avl.getRoot());
//        System.out.println(avl);
        avl.delete(6);
        System.out.println(avl);
//        System.out.println("Inorder: ");
//        avl.inOrder(avl.getRoot());
//        avl.delete(5);
//        System.out.println(avl);

    }
}
