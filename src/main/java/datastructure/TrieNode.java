package datastructure;

import java.util.ArrayList;
import java.util.List;

public class TrieNode {

    char value;
    boolean isTerminating;
    List<TrieNode> children;

    public TrieNode(char value) {
        this.value = value;
        this.isTerminating = false;
        this.children = new ArrayList<>();
    }
}
