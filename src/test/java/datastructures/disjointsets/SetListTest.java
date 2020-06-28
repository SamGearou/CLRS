package datastructures.disjointsets;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SetListTest {
    private final int SIZE = 20;

    @Test
    public void makeSet() {
        List<SetList<Integer>> disjointSet = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            disjointSet.add(new SetList<>());
            disjointSet.get(i).makeSet(i);
        }
        for (int i = 0; i < SIZE; i++) {
            SetList.Node head = disjointSet.get(i).getHead();
            SetList.Node tail = disjointSet.get(i).getTail();
            assertThat(head).isNotNull();
            assertThat(tail).isNotNull();
            assertThat(head).isEqualTo(tail);
        }
    }

    @Test
    public void unionAndFindSet() {
        List<SetList<Integer>> disjointSet = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            disjointSet.add(new SetList<>());
            disjointSet.get(i).makeSet(i);
        }
        //union first half of all sets
        for (int i = 0; i < SIZE / 2 - 1; i++) {
            disjointSet.get(i).union(disjointSet.get(i).getHead(), disjointSet.get(i + 1).getHead());
        }

        SetList.Node representative = disjointSet.get(0).findSet(disjointSet.get(0).getHead());
        for (int i = 1; i < SIZE / 2 - 1; i++) {
            assertThat(disjointSet.get(i).findSet(disjointSet.get(i).getHead()))
                    .isEqualTo(representative);
        }
    }

    @Test
    public void findSetNullNode() {
        SetList<Integer> set = new SetList<>();
        assertThat(set.findSet(null)).isNull();
    }

    @Test
    public void findSetNullSetList() {
        SetList<Integer> set = new SetList<>();
        assertThat(set.findSet(new SetList.Node(1))).isNull();
    }
}