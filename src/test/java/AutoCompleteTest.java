import autocomplete.Autocomplete;
import datastructure.Trie;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
public class AutoCompleteTest {

    protected Autocomplete autocomplete;
    protected Trie trie;

    @BeforeEach
    protected void setUp(){
        this.trie = new Trie();
        this.autocomplete = new Autocomplete(trie);
    }


    public void testNewAutoCompleteInstanceCreate(){
        assertTrue(this.autocomplete instanceof Autocomplete);
    }
    public void testAfterOneInsert_GetMatcherCanReturnTheInsertedWord(){
        this.autocomplete.trie.insert("autocomplete");
        assertFalse(this.autocomplete.getMatches("autocomplete").isEmpty());
    }
    public void testAfterInsertAuto_GetMatchesCantFindAutocomplete(){
        this.autocomplete.trie.insert("auto");
        assertTrue(this.autocomplete.getMatches("autocomplete").isEmpty());
    }
    public void testAfterInsertCaseSensitiveAutocomplete_GetMatchesCantFindIt_ByCaseInsensitiveAuto(){
        this.autocomplete.trie.insert("Autocomplete");
        assertTrue(this.autocomplete.getMatches("auto").isEmpty());
    }
}
