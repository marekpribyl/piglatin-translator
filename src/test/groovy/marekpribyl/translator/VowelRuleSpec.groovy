package marekpribyl.translator

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll


class VowelRuleSpec extends Specification {

    @Subject
    VowelRule rule = new VowelRule()

    @Unroll
    def "should translate '#src' to '#translation'"() {
        expect:
            rule.translate(src) == translation
        where:
            src      || translation
            'a'      || 'away'
            'alpha'  || 'alphaway'
            'upset'  || 'upsetway'
            'ever.'  || 'ever.way'
            "O'Hara" || "O'Haraway"
    }

    @Unroll
    def "should #orNot apply to '#src'"() {
        expect:
            rule.applyTo(src) == result
        where:
            src     || result
            'a'     || true
            'alpha' || true
            'book'  || false
            "'"     || false
            ' '     || false
            ''      || false
            orNot = result ? '' : 'not'
    }

}
