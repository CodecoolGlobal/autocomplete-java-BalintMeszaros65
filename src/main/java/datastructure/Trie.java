package datastructure;

import java.util.Optional;

public class Trie {

    public TrieNode root;

    public Trie() {
        this.root = new TrieNode('\0');
    }

    public void insert(String word) {
        if (word.length() == 0) {
            throw new IllegalArgumentException("Inserted word must be at least 1 character long!");
        } else if (word.length() == 1) {
            char character = word.charAt(0);
            setTerminatingCharacterInRoot(character);
        } else {
            char firstCharacter = word.charAt(0);
            String restOfString = word.substring(1);
            TrieNode newNode = addFirstCharacterInRoot(firstCharacter);
            newNode.insert(restOfString);
        }
    }

    private void setTerminatingCharacterInRoot(char character) {
        Optional<TrieNode> child = root.getChildByCharacter(character);
        if (child.isPresent()) {
            child.get().setTerminating(true);
        } else {
            TrieNode newNode = new TrieNode(character);
            newNode.setTerminating(true);
            root.setChild(newNode);
        }
    }

    private TrieNode addFirstCharacterInRoot(char character) {
        Optional<TrieNode> child = root.getChildByCharacter(character);
        if (child.isPresent()) {
            return child.get();
        } else {
            TrieNode newNode = new TrieNode(character);
            root.setChild(newNode);
            return newNode;
        }
    }

    public boolean remove(String word) {
        //optional
        // TODO
        return false;
    }

}
