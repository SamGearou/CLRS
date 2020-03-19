package datastructures;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insert(TrieNode curr, String word) {
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if (curr.getChildren().containsKey(currChar)) {
                curr = curr.getChildren().get(currChar);
            } else {
                TrieNode child = new TrieNode();
                curr.getChildren().put(currChar, child);
                curr = child;
            }
        }
        curr.setWord(true);
    }

    public boolean prefixSearch(TrieNode curr, String word) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            TrieNode child = curr.getChildren().get(word.charAt(i));
            sb.append(word.charAt(i));
            if (child == null) {
                return false;
            }
            curr = child;
        }
        return curr.isWord();
    }

    class TrieNode {
        private Map<Character, TrieNode> children;
        private boolean isWord;

        public TrieNode() {
            children = new HashMap<>();
            isWord = false;
        }

        public boolean isWord() {
            return isWord;
        }

        public void setWord(boolean word) {
            isWord = word;
        }

        public Map<Character, TrieNode> getChildren() {
            return children;
        }
    }
}
