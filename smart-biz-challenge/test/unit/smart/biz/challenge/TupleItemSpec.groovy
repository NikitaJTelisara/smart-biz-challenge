package smart.biz.challenge
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Unit test suite for the class TupleItem
 *
 * @author Nikita Telisara
 */
@TestFor(TupleItem)
class TupleItemSpec extends Specification {
    TupleItem tuple
    static String item_description = "Salt"
    static float weight = 1.2f
    static float weight_conversionRate = 0.454f

    void "test constructor TupleItem()"() {
        when:
        tuple = new TupleItem(item_description, weight, Unit_Of_Measurement.lb);

        then:
        tuple != null
        assertEquals(item_description, tuple.item_description)
        assert weight.equals(tuple.weight)
        assertEquals(Unit_Of_Measurement.lb, tuple.unit)
    }

    void "test getWeightInKilograms()"() {
        when: "Unit is Kilograms"
        tuple = new TupleItem(item_description, weight, Unit_Of_Measurement.kg);
        float weightInKg = tuple.getWeightInKilograms()

        then:
        assert weight.equals(weightInKg)

        when: "Unit is Pounds"
        tuple = new TupleItem(item_description, weight, Unit_Of_Measurement.lb)
        float expectedWeightInKgs = weight * weight_conversionRate
        weightInKg = tuple.getWeightInKilograms()

        then:
        assert expectedWeightInKgs.equals(weightInKg)

    }

    void "test getWeightInPounds()"() {
        when: "Unit is Pounds"
        tuple = new TupleItem(item_description, weight, Unit_Of_Measurement.lb);
        float weightInLb = tuple.getWeightInPounds()

        then:
        assert weight.equals(weightInLb)

        when: "Unit is Kilograms"
        tuple = new TupleItem(item_description, weight, Unit_Of_Measurement.kg)
        float expectedWeightInLb = weight / weight_conversionRate
        weightInLb = tuple.getWeightInPounds()

        then:
        assert expectedWeightInLb.equals(weightInLb)
    }
}
