package marekpribyl.token;

import lombok.Value;
import marekpribyl.translator.TranslatorEngine;

@Value
public class SimpleToken implements Token {

    private final String source;

    private final TranslatorEngine engine;

    @Override
    public String getTranslation() {
        //TODO uppercase mask
        //TODO punctuation mask
        String translation = engine.translate(source);
        //TODO apply uppercase mask
        //TODO apply punctuation mask

        return translation;
    }

}
