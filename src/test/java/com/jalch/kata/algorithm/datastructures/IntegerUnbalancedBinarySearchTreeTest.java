package com.jalch.kata.algorithm.datastructures;

import com.jalch.kata.algorithm.datastructures.IntegerUnbalancedBinarySearchTree.Node;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;


public class IntegerUnbalancedBinarySearchTreeTest {

    private IntegerUnbalancedBinarySearchTree underTest;

    @BeforeEach
    public void setUp() {
        underTest = new IntegerUnbalancedBinarySearchTree();
    }

    @Test
    public void insert_single_element() {
        underTest.insert(20);
        assertEquals(1L, underTest.size());
        assertNode(20, null, null);
        assertEquals(20, underTest.getInOrder(underTest.root).get(0));
        assertEquals(20, underTest.getPreOrder(underTest.root).get(0));
        assertEquals(20, underTest.getPostOrder(underTest.root).get(0));

        assertEquals(singletonList(20), underTest.getInOrder(underTest.root));
        assertEquals(singletonList(20), underTest.getPreOrder(underTest.root));
        assertEquals(singletonList(20), underTest.getPostOrder(underTest.root));
        assertEquals(singletonList(20), underTest.getBreadthFirstTraversalOrder());
    }

    @Test
    public void insert_multiple_elements() {
        underTest.insert(20);
        underTest.insert(10);
        underTest.insert(15);
        underTest.insert(25);
        underTest.insert(25);
        underTest.insert(-20);
        assertEquals(6L, underTest.size());
        assertNode(20, new Node(10), new Node(25));
        assertNode(10, new Node(-20), new Node(15));
        assertNode(15, null, null);
        assertNode(25, new Node(25), null);
        assertNode(-20, null, null);

        assertEquals(asList(-20, 10, 15, 20, 25, 25), underTest.getInOrder(underTest.root));
        assertEquals(asList(20, 10, -20, 15, 25, 25), underTest.getPreOrder(underTest.root));
        assertEquals(asList(-20, 15, 10, 25, 25, 20), underTest.getPostOrder(underTest.root));
        assertEquals(asList(20, 10, 25, -20, 15, 25), underTest.getBreadthFirstTraversalOrder());
    }

    private void assertNode(int value, Node leftData, Node rightData) {
        assertTrue(underTest.contains(value));
        Node found = underTest.findFirstWith(value);
        assertEquals(value, found.value);
        assertEquals(leftData != null ? found.left.value : null, leftData != null ? leftData.value : null);
        assertEquals(rightData != null ? found.right.value : null, rightData != null ? rightData.value : null);

    }
}