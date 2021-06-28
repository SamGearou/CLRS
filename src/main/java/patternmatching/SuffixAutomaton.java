package patternmatching;

import java.util.HashMap;
import java.util.Map;

//The number of states in a suffix automaton of the string s of length n doesn't exceed 2n−1 (for n≥2)
//The number of transitions in a suffix automaton of a string s of length n doesn't exceed 3n−4 (for n≥3)
//Time Complexity to build suffix automaton: O(n)
//Space Complexity of suffix automaton: O(nk), where k is the size of the alphabet
//Best article on Suffix Automaton: https://cp-algorithms.com/string/suffix-automaton.html
public class SuffixAutomaton {
    private State[] states;
    private int size;
    private int last;

    public SuffixAutomaton(int len) {
        states = new State[len * 2];
        last = 0;
        size = 1;
        states[0] = new State(0, -1);
    }

    public State[] getStates() {
        return states;
    }

    public void setStates(State[] states) {
        this.states = states;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public SuffixAutomaton buildAutomaton(String str) {
        for (int i = 0; i < str.length(); i++) {
            extend(str.charAt(i));
        }
        int p = last;
        //mark terminal states
        while (p > 0) {
            states[p].isTerminal = true;
            p = states[p].link;
        }
        return this;
    }

    //Runtime: O(n)
    public void extend(char c) {
        int curr = size++;
        int len = states[last].len + 1;
        states[curr] = new State(len, -1);
        int p = last;
        while (p != -1 && !states[p].neighbors.containsKey(c)) {
            states[p].neighbors.put(c, curr);
            p = states[p].link;
        }
        if (p == -1) {
            states[curr].link = 0;
        } else {
            int q = states[p].neighbors.get(c);
            if (states[p].len + 1 == states[q].len) {
                states[curr].link = q;
            } else {
                int clone = size++;
                states[clone] = new State(0, -1);
                states[clone].len = states[p].len + 1;
                states[clone].neighbors = new HashMap<>(states[q].neighbors);
                states[clone].link = states[q].link;
                while (p != -1 && states[p].neighbors.get(c) == q) {
                    states[p].neighbors.put(c, clone);
                    p = states[p].link;
                }
                states[q].link = clone;
                states[curr].link = clone;
            }
        }
        last = curr;
    }

    private class State {
        private int len;
        private int link;
        Map<Character, Integer> neighbors;
        private boolean isTerminal;

        public State(int len, int link) {
            this.len = len;
            this.link = link;
            neighbors = new HashMap<>();
            isTerminal = false;
        }

        public int getLen() {
            return len;
        }

        public void setLen(int len) {
            this.len = len;
        }

        public int getLink() {
            return link;
        }

        public void setLink(int link) {
            this.link = link;
        }

        public Map<Character, Integer> getNeighbors() {
            return neighbors;
        }

        public void setNeighbors(Map<Character, Integer> neighbors) {
            this.neighbors = neighbors;
        }

        public boolean isTerminal() {
            return isTerminal;
        }

        public void setTerminal(boolean terminal) {
            isTerminal = terminal;
        }

        @Override
        public String toString() {
            return "State{" +
                    "len=" + len +
                    ", link=" + link +
                    ", neighbors=" + neighbors +
                    ", isTerminal=" + isTerminal +
                    '}';
        }
    }

    /**
     * Find the last (lexicographically) suffix of a String
     */
    public String findLastSubString(SuffixAutomaton SA) {
        StringBuilder sb = new StringBuilder();
        int currState = 0;
        while (currState < SA.last) {
            Map<Character, Integer> neighbors = SA.states[currState].neighbors;
            char maxChar = 'A';
            for (char neighbor : neighbors.keySet()) {
                if (neighbor > maxChar) {
                    maxChar = neighbor;
                }
            }
            currState = SA.states[currState].neighbors.get(maxChar);
            sb.append(maxChar);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SuffixAutomaton automaton = new SuffixAutomaton("jhhhhhhhhhhjjjjjjjjjjjddddddddjdjdjdjdjddjjdjdjdjdjfjfjfjfjtrttyyyyyyyyyyyhyyyyyy".length());
        automaton.buildAutomaton("jhhhhhhhhhhjjjjjjjjjjjddddddddjdjdjdjdjddjjdjdjdjdjfjfjfjfjtrttyyyyyyyyyyyhyyyyyy");
        for (char key : automaton.states[0].neighbors.keySet()) {
            System.out.println(key);
        }
    }
}