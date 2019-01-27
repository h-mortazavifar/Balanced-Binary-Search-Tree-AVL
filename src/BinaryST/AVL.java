package BinaryST;

import java.util.ArrayList;

class AVL {
    private Node root;

    private Integer height(Node node) {
        return node == null ? 0 : node.getHeight();
    }

    private Integer maximum(Integer dataA, Integer dataB) {
        return dataA > dataB ? dataA : dataB;
    }

    public Integer largest() {
        if (root != null) {
            return root.largest();
        }
        return null;
    }

    public Integer smallest() {
        if (root != null) {
            return root.smallest();
        }
        return null;
    }

    Node getRoot() {
        return root;
    }

    void setRoot(Node root) {
        this.root = root;
    }

    /**
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

    private void setHeights(Node a, Node b) {
        a.setHeight(maximum(height(a.getLeftChild()), height(a.getRightChild())) + 1);
        b.setHeight(maximum(height(b.getLeftChild()), height(b.getRightChild())) + 1);
    }

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

    private Integer getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeftChild()) - height(node.getRightChild());
    }

    Node insert(Node node, Integer data) {
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

    void delete(Integer data) {
        ArrayList<Node> parents = new ArrayList<>();
        Node nodeDel = this.root;
        Node parent = this.root;
        Node imBalance = null;
        Integer balanceFactor;
        boolean isLeftChild = false;
        if (nodeDel == null) {
            return;
        }
        while (nodeDel != null && nodeDel.getData() != data) {
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
//            System.out.println("toDeleteOne" + nodeDel.getData());
            delete(minimumData);
//            System.out.println("toDelete" + nodeDel.getData());
            nodeDel.setData(minimumData);
        }
//        System.out.println("parent height at first: " + parent.getHeight() +
//                " Left " + parent.getLeftChild().getData() +
//                " Right " + parent.getRightChild().getData());
        parent.setHeight(maximum(height(parent.getLeftChild()), height(parent.getRightChild())));
        balanceFactor = getBalance(parent);
//        System.out.println("\nthe starting balanceFactor: " + balanceFactor + " parent: "
//                + parent.getData() + " nodeDel: " + nodeDel.getData());
        if (balanceFactor <= 1 && balanceFactor >= -1) {
            for (int i = parents.size() - 1; i >= 0; i--) {
                imBalance = parents.get(i);
                balanceFactor = getBalance(imBalance);
                System.out.println("heyhey " + imBalance.getData() + " size:" + parents.size());
                if (balanceFactor > 1 || balanceFactor < -1) {
                    if (imBalance.getData() > parent.getData()) {
                        parent.setRightChild(rotateCase(imBalance, data, balanceFactor));
                    } else
                        parent.setLeftChild(rotateCase(imBalance, data, balanceFactor));
                    break;
                }
            }
        }
//        System.out.println("\nnew balanceFactor: " + balanceFactor + " and parent is "
//                + parent.getData() + " Left " + parent.getLeftChild().getData() +
//                " Right " + parent.getRightChild().getData());
    }

    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.getData() + " ");
            preOrder(node.getLeftChild());
            preOrder(node.getRightChild());
        }
    }

    void inOrder(Node node) {
        if (node != null) {
            inOrder(node.getLeftChild());
            System.out.print(node.getData() + " ");
            inOrder(node.getRightChild());
        }
    }

    void postOrder(Node node) {
        if (node != null) {
            postOrder(node.getLeftChild());
            postOrder(node.getRightChild());
            System.out.print(node.getData() + " ");
        }
    }

    private Integer minimumData(Node root) {
        Integer min = root.getData();
        while (root.getLeftChild() != null) {
            min = root.getLeftChild().getData();
            root = root.getLeftChild();
        }
        return min;
    }

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
