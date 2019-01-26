package BinaryST;

class Node {
    private Integer data;
    private Integer height;
    private Node parent;
    private Node leftChild;
    private Node rightChild;

    Node(Integer data) {
        this.data = data;
        this.height = 1;
    }

    Node find(Integer data) {
        if (this.data == data) {
            return this;
        } else if (data < this.data && leftChild != null) {
            return leftChild.find(data);
        } else if (rightChild != null) {
            return rightChild.find(data);
        }
        return null;
    }

    Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    Node getLeftChild() {
        return leftChild;
    }

    void setLeftChild(Node leftChild) {
        if (leftChild != null)
            leftChild.parent = this;
        this.leftChild = leftChild;
    }

    Node getRightChild() {
        return rightChild;
    }

    void setRightChild(Node rightChild) {
        if (rightChild != null)
            rightChild.parent = this;
        this.rightChild = rightChild;
    }

    Integer getHeight() {
        return height;
    }

    void setHeight(Integer height) {
        this.height = height;
    }

    public Node getParent() {
        return parent;
    }

}