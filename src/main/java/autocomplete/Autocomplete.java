package autocomplete;

import datastructure.Trie;

import java.util.ArrayList;
import java.util.List;

public class Autocomplete {
    public Trie trie;

    public Autocomplete(Trie trie) {
        this.trie = trie;
    }

    public List<String> getMatches(String prefix) {
        // TODO
        return new ArrayList<>();
    }
}
