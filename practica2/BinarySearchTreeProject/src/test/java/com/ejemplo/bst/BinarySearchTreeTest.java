package com.ejemplo.bst;

import com.ejemplo.bst.BinarySearchTree;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

public class BinarySearchTreeTest {
    Comparator<Integer> comparator = Integer::compareTo;
    @Test
    public void testAdd() {
        BinarySearchTree bst = new BinarySearchTree<>(comparator);

    }

}
