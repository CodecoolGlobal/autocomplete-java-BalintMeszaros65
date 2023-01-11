import autocomplete.Autocomplete;
import datastructure.Trie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class AutoCompleteTest {

    protected Autocomplete autocomplete;
    protected Trie trie;

    @BeforeEach
    protected void setUp(){
        this.trie = new Trie();
        this.autocomplete = new Autocomplete(trie);
    }

    @Test
    public void testNewAutoCompleteInstanceCreate(){
        assertTrue(this.autocomplete instanceof Autocomplete);
    }
    @Test
    public void testAfterOneInsert_GetMatcherCanReturnTheInsertedWord(){
        this.autocomplete.trie.insert("autocomplete");
        assertFalse(this.autocomplete.getMatches("autocomplete").isEmpty());
    }
    @Test
    public void testAfterInsertAuto_GetMatchesCantFindAutocomplete(){
        this.autocomplete.trie.insert("auto");
        assertTrue(this.autocomplete.getMatches("autocomplete").isEmpty());
    }
    @Test
    public void testAfterInsertCaseSensitiveAutocomplete_GetMatchesCantFindIt_ByCaseInsensitiveAuto(){
        this.autocomplete.trie.insert("Autocomplete");
        assertTrue(this.autocomplete.getMatches("auto").isEmpty());
    }
}
