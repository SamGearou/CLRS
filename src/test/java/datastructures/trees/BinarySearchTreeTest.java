package datastructures.trees;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> tree;

    @Before
    public void setup() {
        tree = new BinarySearchTree<>();
        tree.insert(5);
        tree.insert(6);
        tree.insert(4);
        tree.insert(9);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(2);
        tree.insert(8);
        tree.insert(10);
    }

    @Test
    public void insert() {
        Stack<BinarySearchTree.BinaryNode<Integer>> stack = new Stack<>();
        stack.push(tree.getRoot());
        while (!stack.isEmpty()) {
            BinarySearchTree.BinaryNode<Integer> node = stack.pop();
            int leftVal = node.getLeft() == null ? Integer.MIN_VALUE : node.getLeft().getVal();
            int rightVal = node.getRight() == null ? Integer.MAX_VALUE : node.getRight().getVal();
            assertThat(node.getVal()).isGreaterThanOrEqualTo(leftVal);
            assertThat(node.getVal()).isLessThanOrEqualTo(rightVal);
        }
    }

    @Test
    public void deleteNoChildren() {
        //delete 10
        tree.delete(tree.getRoot().getRight().getRight().getRight());
        assertThat(tree.getRoot().getRight().getRight().getRight()).isNull();
    }

    @Test
    public void deleteLeftChild() {
        //delete 3
        tree.delete(tree.getRoot().getLeft().getLeft());
        assertThat(tree.getRoot().getLeft().getLeft().getVal()).isEqualTo(1);
        assertThat(tree.getRoot().getLeft().getLeft().getRight().getVal()).isEqualTo(2);
        assertThat(tree.getRoot().getLeft().getLeft().getLeft()).isNull();
    }

    @Test
    public void deleteRootBothChildren() {
        //delete 5
        tree.delete(tree.getRoot());
        assertThat(tree.getRoot().getVal()).isEqualTo(6);
    }

    @Test
    public void deleteBothChildren() {
        //delete 9
        tree.delete(tree.getRoot().getRight().getRight());
        assertThat(tree.getRoot().getRight().getRight().getVal()).isEqualTo(10);
    }

    @Test
    public void succ() {
        //no succ of 10
        assertThat(tree.succ(tree.getRoot().getRight().getRight().getRight())).isNull();
        //succ of 8
        assertThat(tree.succ(tree.getRoot().getRight().getRight().getLeft().getRight()).getVal()).isEqualTo(9);
    }

    @Test
    public void pred() {
        //no pred of 1
        assertThat(tree.pred(tree.getRoot().getLeft().getLeft().getLeft())).isNull();
        //pred of 7
        assertThat(tree.pred(tree.getRoot().getRight().getRight().getLeft()).getVal()).isEqualTo(6);
    }

    @Test
    public void preOrder() {
        assertThat(tree.preOrder(tree.getRoot(), new ArrayList<>()).stream().map(node -> node.getVal()).collect(Collectors.toList()))
                .isEqualTo(List.of(5, 4, 3, 1, 2, 6, 9, 7, 8, 10));
    }

    @Test
    public void postOrder() {
        assertThat(tree.postOrder(tree.getRoot(), new ArrayList<>()).stream().map(node -> node.getVal()).collect(Collectors.toList()))
                .isEqualTo(List.of(2, 1, 3, 4, 8, 7, 10, 9, 6, 5));
    }

    @Test
    public void inOrder() {
        assertThat(tree.inOrder(tree.getRoot(), new ArrayList<>()).stream().map(node -> node.getVal()).collect(Collectors.toList()))
                .isEqualTo(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }
}