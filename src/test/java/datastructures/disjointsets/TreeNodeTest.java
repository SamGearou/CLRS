package datastructures.disjointsets;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TreeNodeTest {
    private static final int SIZE = 20;

    @Test
    public void makeSet() {
        List<TreeNode<Integer>> disjointSet = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            disjointSet.add(new TreeNode<>(i));
        }
        for (int i = 0; i < SIZE; i++) {
            assertThat(disjointSet.get(i).getData()).isEqualTo(i);
            assertThat(disjointSet.get(i).getRank()).isEqualTo(0);
        }
    }

    @Test
    public void unionAndFindSet() {
        List<TreeNode<Integer>> disjointSet = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            disjointSet.add(new TreeNode<>(i));
        }
        for (int i = 0; i < SIZE / 2 - 1; i++) {
            disjointSet.get(i).union(disjointSet.get(i).getParent(), disjointSet.get(i + 1).getParent());
        }
        TreeNode<Integer> representative = disjointSet.get(0).getParent();
        for (int i = 1; i < SIZE / 2 - 1; i++) {
            if (i < SIZE / 2 - 1) {
                assertThat(disjointSet.get(i).getParent()).isEqualTo(representative);
            } else {
                assertThat(disjointSet.get(i).getParent()).isNotEqualTo(representative);
            }
        }
    }
}