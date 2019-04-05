package smart.biz.challenge

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Unit test suite for class TupleHolder
 *
 * @author Nikita Telisara
 */
@TestFor(TupleHolder)
class TupleHolderSpec extends Specification {
    ArrayList<TupleItem> tupleList
    TupleItem tupleItem

    def setup() {
        domain.weightInKg = 1.2f
        domain.metaClass.addTupleToList = {  TupleItem tupleItem -> return true }

        tupleItem = Mock(TupleItem)
        tupleList = new ArrayList<TupleItem>()
        tupleList.add(tupleItem)
    }

    void "test constructor TupleHolder()"() {
        when:
        TupleHolder tupleHolder = new TupleHolder(Mock(TupleItem))

        then:
        tupleHolder != null
        assert tupleHolder.tuples.size().equals(1)
    }

    void 'test insertTuple()'() {
        given:
        TupleItem tupleItem = Mock(TupleItem)
        tupleItem.getWeightInKilograms(* _) >> { 1.2f }

        when: 'tupleItem weight is equal to this domain weight'
        boolean res = domain.insertTuple(tupleItem)

        then:
        assertEquals(true, res)

        when: 'tupleItem weight is greater than this domain weight'
        TupleItem tupleItem1 = Mock(TupleItem)
        tupleItem1.getWeightInKilograms(* _) >> { 1.8f }
        res = domain.insertTuple(tupleItem1)

        then:
        domain.right != null
        domain.left == null
        assertEquals(true, res)

        when: 'tupleItem weight is lesser than this domain weight'
        TupleItem tupleItem2 = Mock(TupleItem)
        tupleItem2.getWeightInKilograms(* _) >> { 0.8f }
        res = domain.insertTuple(tupleItem2)

        then:
        domain.right != null
        domain.left != null
        assertEquals(true, res)
    }

    void "test getTuplesInOrder()"() {
        given:
        domain.metaClass.getAllTuplesInOrder = { ArrayList listOfTuples -> return tupleList }

        when:
        ArrayList<TupleItem> result = domain.getTuplesInOrder()

        then:
        assert (1.equals(result.size()))
        assertEquals(tupleList, result)
    }

    void "test getAllTuplesInOrder() return TupleHolder in a Inorder Traversal Order"() {
        given:
        TupleItem tupleItem1 = Mock(TupleItem)
        TupleItem tupleItem2 = Mock(TupleItem)
        TupleItem tupleItem3 = Mock(TupleItem)
        TupleItem tupleItem4 = Mock(TupleItem)
        TupleItem tupleItem5 = Mock(TupleItem)
        domain.tuples = tupleList
        domain.left = new TupleHolder(tupleItem1)
        domain.right = new TupleHolder(tupleItem2)
        domain.left.left = new TupleHolder(tupleItem3)
        domain.left.right = new TupleHolder(tupleItem4)
        domain.right.left = new TupleHolder(tupleItem5)

        when:
        ArrayList<TupleItem> result = domain.getAllTuplesInOrder(new ArrayList<TupleItem>())

        then:
        assert 6.equals(result.size())
        assertEquals(tupleItem3, result[0])
        assertEquals(tupleItem1, result[1])
        assertEquals(tupleItem4, result[2])
        assertEquals(tupleItem, result[3])
        assertEquals(tupleItem5, result[4])
        assertEquals(tupleItem2, result[5])
    }
}
