package autocomplete;

import datastructure.Trie;
import datastructure.TrieNode;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class Autocomplete {
    public Trie trie;

    public Autocomplete(Trie trie) {
        this.trie = trie;
    }

    public List<String> getMatches(String prefix) {
        List<String> results = new ArrayList<>();
        AtomicReference<TrieNode> tempNode = new AtomicReference<>(trie.root);
        try {
            prefix.chars()
                    .mapToObj(character -> (char) character)
                    .forEach(character -> tempNode.set(getChildNodeIfExists(character, tempNode.get())));
        } catch (NoSuchElementException noSuchElementException) {
            return results;
        }
        if (tempNode.get().isTerminating()) {
            results.add(prefix);
        }
        checkIfAnyChildIsTerminating(results, prefix, tempNode.get());
        return results;
    }

    private void checkIfAnyChildIsTerminating(List<String> results, String substring,
                                                                  TrieNode trieNode) {
        List<TrieNode> children = trieNode.getChildren();
        for (TrieNode child : children) {
            String currentSubstring = substring + child.getValue();
            if (child.isTerminating()) {
                results.add(currentSubstring);
            }
            checkIfAnyChildIsTerminating(results, currentSubstring, child);
        }
    }

    private TrieNode getChildNodeIfExists(char character, TrieNode trieNode) {
        Optional<TrieNode> optionalTrieNode = trieNode.getChildByCharacter(character);
        if (optionalTrieNode.isPresent()) {
            return optionalTrieNode.get();
        } else {
            throw new NoSuchElementException();
        }
    }
}
