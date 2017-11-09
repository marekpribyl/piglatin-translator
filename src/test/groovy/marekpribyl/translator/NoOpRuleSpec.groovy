package marekpribyl.translator

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll


class NoOpRuleSpec extends Specification {

    @Subject
    NoOpRule rule = new NoOpRule()

    def 'should provide translation as a copy of the source'() {
        expect:
            rule.translate('betterway') == 'betterway'
    }

    @Unroll
    def "should #orNot apply to '#src'"() {
        expect:
            rule.applyTo(src) == result
        where:
            src         || result
            'betterway' || true
            "'"         || true
            ' '         || true
            ''          || true
            'better'    || false
            'Omaha'     || false
            "O'Hara"    || false
            orNot = result ? '' : 'not'
    }

}
