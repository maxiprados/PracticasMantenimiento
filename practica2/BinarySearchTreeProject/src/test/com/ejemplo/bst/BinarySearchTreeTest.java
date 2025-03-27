package com.ejemplo.bst;

import main.java.com.ejemplo.bst.BinarySearchTree;
import org.junit.Test;

import java.util.Comparator;

public class BinarySearchTreeTest {
    Comparator<Integer> comparator = Integer::compareTo;
    @Test
    public void testAdd() {
        BinarySearchTree bst = new BinarySearchTree<>(comparator);

    }

}
