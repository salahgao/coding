package com.salah.ali;

/**
 * 核心思路，交换当前节点的prev与next
 *
 * @author salahgao
 * @date 2020/1/9
 */
public class LinkedListReverse {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node1.next = node2;
        node2.prev = node1;
        node2.next = node3;
        node3.prev = node2;
        node3.next = node4;
        node4.prev = node3;
        node4.next = node5;
        node5.prev = node4;


        MyLL myLL = new MyLL(node1, node5);
        Node node = myLL.first;
        while (node != null) {
            System.out.println("prev=" + node.prev + "\tcurr=" + node + "\tnext=" + node.next);
            node = node.next;
        }

        reverse4(myLL);

        node = myLL.first;
        while (node != null) {
            System.out.println("prev=" + node.prev + "\tcurr=" + node + "\tnext=" + node.next);
            node = node.next;
        }

    }

    public static void reverse(MyLL myLL) {
        //Reverse
        Node node = myLL.first;
        myLL.first = myLL.end;
        myLL.end = node;

        Node prev = null;
        Node next = null;
        if (node != null) {
            next = node.next;
        }
        while (node != null) {
            node.prev = next;
            node.next = prev;
            prev = node;
            node = next;
            if (next != null) {
                next = next.next;
            } else {
                next = null;
            }
        }
    }

    public static void reverse2(MyLL myLL) {
        //Reverse
        Node curr = myLL.first;
        myLL.first = myLL.end;
        myLL.end = curr;

        while (curr != null) {
            Node prev = curr.prev;
            Node next = curr.next;

            curr.prev = next;
            curr.next = prev;

            curr = next;
        }
    }


    public static void reverse3(MyLL myLL) {
        //Reverse
        Node curr = myLL.first;
        myLL.first = myLL.end;
        myLL.end = curr;

        while (curr != null) {
            Node next = curr.next;
            curr.next = curr.prev;
            curr.prev = next;
            curr = next;
        }
    }

    public static void reverse4(MyLL myLL) {
        //Reverse
        Node node = myLL.first;
        myLL.first = myLL.end;
        myLL.end = node;
//        reverseNode(node);
//        reverseNode2(node);
        reverseNode3(node);
    }

    public static Node reverseNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        node.prev = next;
        node.next = prev;

        if (next != null) {
            node = next;
            return reverseNode(node);
        } else {
            return node;
        }
    }

    public static void reverseNode2(Node curr) {
        Node next = curr.next;
        curr.next = curr.prev;
        curr.prev = next;
        if (next != null) {
            reverseNode(next);
        }
        return;
    }

    public static void reverseNode3(Node curr) {
        while (curr != null) {
            Node next = curr.next;
            curr.next = curr.prev;
            curr.prev = next;
            curr = next;
        }
        return;
    }

}

class MyLL {
    Node first;
    Node end;

    public MyLL(Node first, Node end) {
        this.first = first;
        this.end = end;
    }
}

class Node {
    Node prev;
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}