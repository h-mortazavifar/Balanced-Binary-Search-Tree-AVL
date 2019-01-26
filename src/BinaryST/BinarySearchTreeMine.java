package BinaryST;

public class BinarySearchTreeMine {
    private Node root;

//    public void insert(Node node,Integer data) {
//        if (root == null) {
//            this.root = new Node(data);
//        } else root.insert(node,data);
//    }

    public Node find(Integer data) {
        if (root != null) {
            root.find(data);
        }
        return null;
    }
}
