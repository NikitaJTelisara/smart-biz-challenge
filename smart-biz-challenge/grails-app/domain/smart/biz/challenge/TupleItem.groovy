package smart.biz.challenge

/**
 * The class representing class TupleItem.
 *
 * @author Nikita Telisara
 *
 */
class TupleItem {
    String item_description
    float weight
    static float weight_conversionRate = 0.454f
    Unit_Of_Measurement unit

    /*
     * TupleItem(), Constructor to create tupleItem object
     *
     * @param item_Description ,
     * @param weight ,
     * @param unit , Unit of weight
     */

    TupleItem(String item_Description, float weight, Unit_Of_Measurement unit) {
        this.item_description = item_Description
        this.weight = weight
        this.unit = unit
    }

    /**
     * getWeightInKilograms(), gets weight of this object in kilograms
     *
     * @return , converts weight to kilograms if current unit is lb, else returns as is
     */
    float getWeightInKilograms() {
        return ((unit == Unit_Of_Measurement.kg) ? weight : (weight * weight_conversionRate))
    }

    /**
     * getWeightInPounds(), gets wight of this object in Pounds
     *
     * @return , converts weight to pounds if current unit is kg, else returns as is
     */
    float getWeightInPounds() {
        return ((unit == Unit_Of_Measurement.lb) ? weight : (weight / weight_conversionRate))
    }
}
