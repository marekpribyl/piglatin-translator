package marekpribyl.translator;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

abstract class TranslationRule implements Comparable<TranslationRule> {

    static final int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;

    static final int ANY_PRECEDENCE = Integer.MAX_VALUE/2;

    String translate(String src) {
        if (isBlank(src)) {
            return src;
        }
        return doTranslate(src);
    }

    abstract boolean applyTo(String src);

    abstract String doTranslate(String src);

    @Override
    public int compareTo(TranslationRule o) {
        if (isNull(o) || this == o) {
            return 0;
        }
        return this.order() > o.order() ? 1 : -1;
    }

    abstract int order();

}
