package marekpribyl.translator;

import java.util.Map;
import java.util.WeakHashMap;

/**
 *
 FILTERS: (as-is to output)

 Words that end in “way” are not modified.
 stairway stays as stairway

 Blank strings

 punctuations only

 [:alpha:] only

 MODIFIERS:

 Words that start with a consonant:
 have their first letter moved to the end of the word and the letters “ay” added to the end.
 Hello becomes Ellohay

 Words that start with a vowel:
 have the letters “way” added to the end.
 apple becomes appleway

 RESTORERS: (need to record capitals and punctuations)

 Punctuation must remain in the same relative place from the end of the word.
 can’t becomes antca’y
 end. becomes endway.

 Capitalization must remain in the same place.
 Beach becomes Eachbay
 McCloud becomes CcLoudmay

 COMPOSITION: (split and process one-by-one)

 Hyphens are treated as two words
 this-thing becomes histay-hingtay
 */
public class TranslatorEngine {

    private static final int CACHE_SIZE = 1_000;

    //TODO limit cache size
    private final Map<String, String> cache = new WeakHashMap<>(CACHE_SIZE);

    public String translate(String source) {
        if (cache.containsKey(source)) {
            return cache.get(source);
        }
        //TODO implement it
        String translated = source;
        cache.putIfAbsent(source, translated);

        return translated;
    }

}
