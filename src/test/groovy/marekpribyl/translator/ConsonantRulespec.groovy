package marekpribyl.translator

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll


class ConsonantRulespec extends Specification {

    @Subject
    ConsonantRule rule = new ConsonantRule()

    @Unroll
    def "should translate '#src' to '#translation'"() {
        expect:
            rule.translate(src) == translation
        where:
            src     || translation
            'b'     || 'bay'
            'book'  || 'ookbay'
            'cook'  || 'ookcay'
            'stop.' || 'top.say'
            "f'ing" || "'ingfay"
    }

    @Unroll
    def "should #orNot apply to '#src'"() {
        expect:
            rule.applyTo(src) == result
        where:
            src     || result
            'b'     || true
            'book'  || true
            'alpha' || false
            "'"     || false
            ' '     || false
            ''      || false
            orNot = result ? '' : 'not'
    }

}
