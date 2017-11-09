package marekpribyl.translator;

import static java.lang.String.format;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableSortedSet;
import lombok.NonNull;


public class TranslatorEngine {

    private static final String PUNCTUATION_REGEX = "\\p{Punct}";

    private static final String UPPERCASE_REGEX = "\\p{Lu}";

    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile(PUNCTUATION_REGEX);

    private static final Pattern UPPERCASE_PATTERN = Pattern.compile(UPPERCASE_REGEX);

    private final ImmutableSortedSet<TranslationRule> rules = ImmutableSortedSet.<TranslationRule>naturalOrder()
            .add(new ConsonantRule())
            .add(new VowelRule())
            .add(new NoOpRule())
            .build();

    public String translate(@NonNull String source) {
        String toBeTranslated = normalizeForTranslation(source);
        String translation = rules.stream()
                .sorted()
                .filter(translationRule -> translationRule.applyTo(toBeTranslated))
                .findFirst()
                .map(translationRule -> translationRule.translate(toBeTranslated))
                .orElseThrow(() -> new IllegalStateException(format("Something went wrong, string [%s] has not been translated", source)));

        String restoredUppercase = restoreUppercase(translation, source);

        return restorePunctuation(restoredUppercase, source);
    }

    private String normalizeForTranslation(String source) {
        return source.toLowerCase().replaceAll(PUNCTUATION_REGEX, "");
    }

    private String restorePunctuation(String src, String original) {
        String reversedOriginal = StringUtils.reverse(original);
        String reversedSource = StringUtils.reverse(src);

        int position = 0;
        StringBuilder sb = new StringBuilder();
        Matcher matcher = PUNCTUATION_PATTERN.matcher(reversedOriginal);
        while (matcher.find()) {
            int current = matcher.start();
            sb.append(reversedSource.substring(position, current)). append(reversedOriginal.charAt(current));
            position = current;
        }

        sb.append(reversedSource.substring(position, src.length()));

        return StringUtils.reverse(sb.toString());
    }

    private String restoreUppercase(String src, String mask) {
        Matcher matcher = UPPERCASE_PATTERN.matcher(mask);
        char[] charSource = src.toCharArray();
        while (matcher.find()) {
            int position = matcher.start();
            charSource[position] = Character.toUpperCase(charSource[position]);
        }

        return String.valueOf(charSource);
    }

}
