package marekpribyl.translator;

/**
 Words that start with a vowel:
 have the letters “way” added to the end.
 apple becomes appleway
 */
public class VowelRule extends TranslationRule {

    private static final String VOWELS = "aeiou";

    private static final String SUFFIX = "way";

    @Override
    boolean applyTo(String src) {
        return src.length() > 0 && VOWELS.contains(src.substring(0,1));
    }

    @Override
    String doTranslate(String src) {
        return src + SUFFIX;
    }

    @Override
    int order() {
        return ANY_PRECEDENCE;
    }

}
