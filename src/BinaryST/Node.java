package BinaryST;

class Node {
    private Integer data;
    private Integer height;
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
        this.leftChild = leftChild;
    }

    Node getRightChild() {
        return rightChild;
    }

    void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    Integer getHeight() {
        return height;
    }

    void setHeight(Integer height) {
        this.height = height;
    }


    Integer smallest() {
        if (this.leftChild == null) {
            return this.data;
        }
        return this.leftChild.smallest();
    }

    Integer largest() {
        if (this.rightChild == null) {
            return this.data;
        }
        return this.rightChild.largest();
    }

}