package com.salah.ali;

import java.util.HashMap;

/**
 * @author Salah
 * @date 2021/8/26
 */
public class LinkedTest {

    public static void main(String[] args) {
        LinkedNode node1 = new LinkedNode(1);
        LinkedNode node2 = new LinkedNode(2);
        LinkedNode node3 = new LinkedNode(3);

        node1.next = node2;
        node2.next = node3;
        //node3.next = node1;


        boolean isLoop = false;
        HashMap<LinkedNode, Integer> map = new HashMap<>();
        LinkedNode node = node1;
        while (node != null) {
            if (map.containsKey(node)) {
                isLoop = true;
                break;
            }
            map.put(node, 1);
            node = node.next;
        }

        System.out.println("isLoop=" + isLoop);

    }
}

class LinkedNode {
    int value;
    LinkedNode next;

    public LinkedNode(int i) {
        this.value = i;
    }
}