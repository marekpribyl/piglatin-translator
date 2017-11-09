package marekpribyl.token;

import lombok.Value;
import marekpribyl.translator.TranslatorEngine;

@Value
public class SimpleToken implements Token {

    private final String source;

    private final TranslatorEngine engine;

    @Override
    public String getTranslation() {
        return engine.translate(source);
    }

}
