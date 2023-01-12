package datastructure;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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

    private TrieNode getChildIfExists(char character, TrieNode trieNode) {
        Optional<TrieNode> optionalTrieNode = trieNode.getChildByCharacter(character);
        if (optionalTrieNode.isPresent()) {
            return optionalTrieNode.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    public boolean remove(String word) {
        AtomicReference<TrieNode> tempNode = new AtomicReference<>(root);
        try {
            word.chars()
                    .mapToObj(character -> (char) character)
                    .forEach(character -> tempNode.set(getChildIfExists(character, tempNode.get())));
        } catch (NoSuchElementException ignore) {
            return false;
        }
        TrieNode trieNode = tempNode.get();
        if (trieNode.isTerminating()) {
            trieNode.setTerminating(false);
            return true;
        } else {
            return false;
        }
    }

}
