package marekpribyl

import spock.lang.Specification
import spock.lang.Unroll

class PigLatinTranslatorSpec extends Specification {

    PigLatinTranslator translator = new PigLatinTranslator()

    def 'should return blank source as-is'() {
        expect:
            translator.translate(src) == src
        where:
            src << [null, '', ' ', '    ']
    }

    def 'should translate text'() {
        given:
            String src = 'Hello. Stairway, stairway can`t end . Beach, McCloud, this-Thing'
        expect:
            translator.translate(src) == 'Ellohay. Stairway, stairway antca`y endway . Eachbay, CcLoudmay, histay-Hingtay'
    }

    /**
     * These tests are failing due to known issues
     */
    @Unroll
    def "should translate '#src' text"() {
        expect:
            translator.translate(src) == translation
        where:
            src             || translation
            '123'           || '123'
            'hello--dolly'  || 'ellohay--ollyday'
            'Hello   Anna!' || 'Ellohay   Annaway!'
    }

}
