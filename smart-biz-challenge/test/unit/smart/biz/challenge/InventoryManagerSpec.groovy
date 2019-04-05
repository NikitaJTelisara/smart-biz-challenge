package smart.biz.challenge

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Unit test suite for class InventoryManager
 *
 * @author Nikita Telisara
 */
@TestFor(InventoryManager)
class InventoryManagerSpec extends Specification {
    TupleHolder tupleHolder

    def setup() {
        tupleHolder = Mock(TupleHolder)
    }

    void "test getInstance()"() {
        when:
        InventoryManager manager = InventoryManager.getInstance()
        manager.firstTupleHolder = tupleHolder
        InventoryManager manager1 = InventoryManager.getInstance()

        then:
        assertEquals(tupleHolder, manager1.firstTupleHolder)
    }

    void "test addTuple()"() {
        when: 'firstTupleHolder == null'
        domain.addTuple(Mock(TupleItem))

        then:
        domain.firstTupleHolder != null

        when: 'firstTupleHolder is not null'
        domain.firstTupleHolder = tupleHolder
        domain.addTuple(Mock(TupleItem))

        then:
        domain.firstTupleHolder == tupleHolder
    }
}
