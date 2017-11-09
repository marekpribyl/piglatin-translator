package marekpribyl

import spock.lang.Specification

import javax.swing.text.MaskFormatter
import java.util.regex.Matcher
import java.util.regex.Pattern

class PigLatinTranslatorSpec extends Specification {

    PigLatinTranslator translator = new PigLatinTranslator()

    def 'find capital letters position'() {
        when:
            Pattern pattern = Pattern.compile("\\p{Lu}");
            String theString = "A string containing these letters: u 123Uuu Ä Æ É."
            Matcher matcher = pattern.matcher(theString);

            String mask = theString.replaceAll("\\p{Lu}", "U").replaceAll("[^U]", "*")
            String lowerCase = theString.toLowerCase()
            println lowerCase
            def formatter = new MaskFormatter(mask)
            while (matcher.find()) {
                int pos = matcher.start()
//                mask[pos] = true
                System.out.format("Position: %d, letter: %s\n", matcher.start(), matcher.group());
            }
            println mask
        then:
            true == true
    }

    //////////////////////////////////////////////

    def 'should return blank source as-is'() {
        expect:
            translator.translate(src) == src
        where:
            src << [null, '', ' ', '    ']
    }

    def 'should translate text'() {
        given:
            String src = 'Hello. Stairway can’t end .  Beach, McCloud, this-Thing'
        expect:
            translator.translate(src) == 'Ellohay. Stairway antca’y endway .  Eachbay, CcLoudmay, histay-Hingtay '
    }

}
