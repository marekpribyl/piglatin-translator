package marekpribyl.translator;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 Words that end in “way” are not modified.
 stairway stays as stairway
 */
public class NoOpRule extends TranslationRule {

    private static final String PUNCTUATION_REGEX = "\\p{Punct}";

    @Override
    boolean applyTo(String src) {
        return src.endsWith("way")
                || StringUtils.isBlank(src)
                || Pattern.matches(PUNCTUATION_REGEX, src);
    }

    @Override
    String doTranslate(String src) {
        return src;
    }

    @Override
    int order() {
        return HIGHEST_PRECEDENCE;
    }

}
