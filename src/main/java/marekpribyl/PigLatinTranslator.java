package marekpribyl;

import static java.util.stream.Collectors.joining;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Splitter;
import marekpribyl.token.CompoundToken;
import marekpribyl.token.SimpleToken;
import marekpribyl.token.Token;
import marekpribyl.translator.TranslatorEngine;


public class PigLatinTranslator {

    private static final String WORD_SEPARATOR = " ";

    private final TranslatorEngine engine = new TranslatorEngine();

    public String translate(String src) {
        if (StringUtils.isBlank(src)) {
            return src;
        }

        return Splitter
                .on(WORD_SEPARATOR)
                .omitEmptyStrings()
                .splitToList(src)
                .stream()
                .map(this::mapToToken)
                .map(Token::getTranslation)
                .collect(joining(WORD_SEPARATOR));
    }

    private Token mapToToken(String src) {
        List<String> split = Splitter
                .on("-")
                .omitEmptyStrings()
                .splitToList(src);
        if (split.size() == 1) {
            return new SimpleToken(src, engine);
        } else {
            return new CompoundToken(split, engine);
        }
    }

}
