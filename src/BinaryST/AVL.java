package BinaryST;

import java.util.ArrayList;

/**
 * class to insert and remove a node using a Binary Search Tree and balance it afterwards
 */
public class AVL {
    private Node root;

    private Integer height(Node node) {
        return node == null ? 0 : node.getHeight();
    }

    /**
     * Getting the maximum value between two nodes data
     */
    private Integer maximum(Integer dataA, Integer dataB) {
        return dataA > dataB ? dataA : dataB;
    }

    /**
     * Getting the Node with largest data
     */
    public Integer largest() {
        if (root != null) {
            return root.largest();
        }
        return null;
    }

    /**
     * Getting the Node with smallest data
     */
    public Integer smallest() {
        if (root != null) {
            return root.smallest();
        }
        return null;
    }

    /**
     * getting the root of tree
     */
    public Node getRoot() {
        return root;
    }

    /**
     * setting the root of tree
     */
    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * gets a node and returns the rotated node
     * -----------a                            b
     * ---------/ \                         /   \
     * -------b   n4      RR Rotate       c      a
     * ------/ \          -------->     /  \    /  \
     * ----c   n3                     n1  n2  n3  n4
     * --/ \
     * n1   n2
     */
    private Node rightRotate(Node a) {
        Node b = a.getLeftChild();
        Node n3 = b.getRightChild();
        b.setRightChild(a);
        a.setLeftChild(n3);
        setHeights(a, b);
        return b;
    }

    /**
     * gets a node and returns the rotated node
     * ----a                                b
     * --/  \                             /  \
     * n1    b          LL Rotate      a      c
     * --- /  \         -------->    /  \   /  \
     * --n2   c                     n1  n2 n3  n4
     * -----/  \
     * ----n3  n4
     */
    private Node leftRotate(Node a) {
        Node b = a.getRightChild();
        Node n2 = b.getLeftChild();
        b.setLeftChild(a);
        a.setRightChild(n2);
        setHeights(a, b);
        return b;
    }

    /**
     * Sets the height of two nodes + 1 based on right and left child of them after inserting a node as their child
     */
    private void setHeights(Node a, Node b) {
        a.setHeight(maximum(height(a.getLeftChild()), height(a.getRightChild())) + 1);
        b.setHeight(maximum(height(b.getLeftChild()), height(b.getRightChild())) + 1);
    }

    /**
     * after insert or delete rotates the given node based on the new given balance
     */
    private Node rotateCase(Node node, Integer data, Integer balance) {
        if (balance > 1 && data < node.getLeftChild().getData())
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && data > node.getRightChild().getData())
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && data > node.getLeftChild().getData()) {
            node.setLeftChild(leftRotate(node.getLeftChild()));
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && data < node.getRightChild().getData()) {
            node.setRightChild(rightRotate(node.getRightChild()));
            return leftRotate(node);
        }
        return node;
    }

    /**
     * getting the balance of the node based on the right and left child
     */
    private Integer getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeftChild()) - height(node.getRightChild());
    }

    /**
     * inserts a data with getting a root to decide where to insert the data and rotates the tree if it got unbalanced
     *
     * @param node is the root
     */
    public Node insert(Node node, Integer data) {
        if (node == null) {
            return new Node(data);
        } else if (data > node.getData()) {
            if (node.getRightChild() == null)
                node.setRightChild(new Node(data));
            else
                node.setRightChild(insert(node.getRightChild(), data));
        } else if (data < node.getData()) {
            if (node.getLeftChild() == null)
                node.setLeftChild(new Node(data));
            else
                node.setLeftChild(insert(node.getLeftChild(), data));
        } else return node;
        node.setHeight(1 + maximum(height(node.getLeftChild()), height(node.getRightChild())));
        Integer balance = getBalance(node);
        return rotateCase(node, data, balance);
    }

    /**
     * deletes the given data from the tree and rotates it in case of unbalanced
     */
    public void delete(Integer data) {
        ArrayList<Node> parents = new ArrayList<>();
        Node nodeDel = this.root;
        Node parent = this.root;
        Node imBalance;
        Integer balanceFactor;
        boolean isLeftChild = false;
        if (nodeDel == null) {
            return;
        }
        while (nodeDel != null && !nodeDel.getData().equals(data)) {
            parent = nodeDel;
            if (data < nodeDel.getData()) {
                nodeDel = nodeDel.getLeftChild();
                isLeftChild = true;
            } else {
                nodeDel = nodeDel.getRightChild();
                isLeftChild = false;
            }
            parents.add(nodeDel);
        }

        if (nodeDel == null) {
            return;
//        delete a leaf node
        } else if (nodeDel.getLeftChild() == null && nodeDel.getRightChild() == null) {
            if (nodeDel == root) {
                root = null;
            } else {
                if (isLeftChild) {
                    parent.setLeftChild(null);
                } else {
                    parent.setRightChild(null);
                }
            }
        }
//            deleting a node with degree of one
        else if (nodeDel.getRightChild() == null) {
            if (nodeDel == root) {
                root = nodeDel.getLeftChild();
            } else if (isLeftChild) {
                parent.setLeftChild(nodeDel.getLeftChild());
            } else {
                parent.setRightChild(nodeDel.getLeftChild());
            }
        } else if (nodeDel.getLeftChild() == null) {
            if (nodeDel == root) {
                root = nodeDel.getRightChild();
            } else if (isLeftChild) {
                parent.setLeftChild(nodeDel.getRightChild());
            } else {
                parent.setRightChild(nodeDel.getRightChild());
            }
        }
        //            deleting a node with degree of two
        else {
            Integer minimumData = minimumData(nodeDel.getRightChild());
            delete(minimumData);
            nodeDel.setData(minimumData);
        }
        parent.setHeight(maximum(height(parent.getLeftChild()), height(parent.getRightChild())));
        balanceFactor = getBalance(parent);
        if (balanceFactor <= 1 && balanceFactor >= -1) {
            for (int i = parents.size() - 1; i >= 0; i--) {
                imBalance = parents.get(i);
                balanceFactor = getBalance(imBalance);
                if (balanceFactor > 1 || balanceFactor < -1) {
                    if (imBalance.getData() > parent.getData()) {
                        parent.setRightChild(rotateCase(imBalance, data, balanceFactor));
                    } else
                        parent.setLeftChild(rotateCase(imBalance, data, balanceFactor));
                    break;
                }
            }
        }
    }

    /**
     * prints data of the nodes of the tree based on pre-order method
     *
     * @param root is the root of the tree
     */
    public void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.getData() + " ");
            preOrder(root.getLeftChild());
            preOrder(root.getRightChild());
        }
    }

    /**
     * prints data of the nodes of the tree based on in-order method
     *
     * @param root is the root of the tree
     */
    public void inOrder(Node root) {
        if (root != null) {
            inOrder(root.getLeftChild());
            System.out.print(root.getData() + " ");
            inOrder(root.getRightChild());
        }
    }

    /**
     * prints data of the nodes of the tree based on post-order method
     *
     * @param root is the root of the tree
     */
    public void postOrder(Node root) {
        if (root != null) {
            postOrder(root.getLeftChild());
            postOrder(root.getRightChild());
            System.out.print(root.getData() + " ");
        }
    }

    /**
     * returns the minimum data to replace the deleted node
     */
    private Integer minimumData(Node root) {
        Integer min = root.getData();
        while (root.getLeftChild() != null) {
            min = root.getLeftChild().getData();
            root = root.getLeftChild();
        }
        return min;
    }

    /**
     * prints the tree
     */
    @Override
    public String toString() {
        final int height = 5, width = 64;
        int len = width * height * 2 + 2;
        StringBuilder sb = new StringBuilder(len);
        for (int i = 1; i <= len; i++)
            sb.append(i < len - 2 && i % width == 0 ? "\n" : ' ');
        display(sb, width / 2, 1, width / 4, width, root, " ");
        return sb.toString();
    }

    private void display(StringBuilder sb, int c, int r, int d, int w, Node n, String edge) {
        if (n != null) {
            display(sb, c - d, r + 2, d / 2, w, n.getLeftChild(), " /");
            String s = String.valueOf(n.getData());
            int idx1 = r * w + c - (s.length() + 1) / 2;
            int idx2 = idx1 + s.length();
            int idx3 = idx1 - w;
            if (idx2 < sb.length())
                sb.replace(idx1, idx2, s).replace(idx3, idx3 + 2, edge);
            display(sb, c + d, r + 2, d / 2, w, n.getRightChild(), "\\ ");
        }
    }
}
