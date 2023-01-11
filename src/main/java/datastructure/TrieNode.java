package datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TrieNode {

    char value;
    boolean isTerminating;
    List<TrieNode> children;

    public TrieNode(char value) {
        this.value = value;
        this.isTerminating = false;
        this.children = new ArrayList<>();
    }

    public void setChild(TrieNode trieNode) {
       this.children.add(trieNode);
    }

    public Optional<TrieNode> getChildByCharacter(char value) {
        return this.children.stream().filter(node -> node.value == value).findFirst();
    }

    public void setTerminating(boolean terminating) {
        isTerminating = terminating;
    }

    public void insert(String restOfString) {
        if (restOfString.length() == 1) {
            char character = restOfString.charAt(0);
            TrieNode newNode = new TrieNode(character);
            newNode.setTerminating(true);
            setChild(newNode);
        } else {
            char firstCharacter = restOfString.charAt(0);
            String restOfRestOfString = restOfString.substring(1);
            TrieNode newNode = new TrieNode(firstCharacter);
            setChild(newNode);
            newNode.insert(restOfRestOfString);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrieNode trieNode = (TrieNode) o;
        return value == trieNode.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, isTerminating, children);
    }


}
