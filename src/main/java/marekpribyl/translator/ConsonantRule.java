package marekpribyl.translator;

/**
 Words that start with a consonant:
 have their first letter moved to the end of the word and the letters “ay” added to the end.
 Hello becomes Ellohay
 */
public class ConsonantRule extends TranslationRule {

    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";

    private static final String SUFFIX = "ay";

    @Override
    boolean applyTo(String src) {
        return src.length() > 0 && CONSONANTS.contains(src.substring(0,1));
    }

    @Override
    String doTranslate(String src) {
        return src.substring(1, src.length()) + src.charAt(0) + SUFFIX;
    }

    @Override
    int order() {
        return ANY_PRECEDENCE;
    }

}
