package marekpribyl.token;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Collectors;

import marekpribyl.translator.TranslatorEngine;

public class CompoundToken implements Token {

    private static final String DELIMITER = "-";

    private final List<Token> tokens;

    public CompoundToken(List<String> sources, TranslatorEngine engine) {
        this.tokens = sources.stream()
                .map(s -> new SimpleToken(s, engine))
                .collect(toList());
    }

    @Override
    public String getTranslation() {
        return tokens.stream()
                .map(Token::getTranslation)
                .collect(Collectors.joining(DELIMITER));
    }

}
