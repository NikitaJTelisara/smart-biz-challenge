package smart.biz.challenge

/**
 * The class representing class TupleHolder
 *
 * @author Nikita Telisara
 *
 */
class TupleHolder {
    float weightInKg
    ArrayList<TupleItem> tuples
    TupleHolder left
    TupleHolder right

    /*
     * TupleHolder(), constructor to create tupleHolder object for given weight in Kilograms
     *
     * @param tupleItem , object of TupleItem class
     */
    TupleHolder(TupleItem tupleItem) {
        weightInKg = tupleItem.getWeightInKilograms()
        tuples = new ArrayList<TupleItem>()
        tuples.add(tupleItem)
    }

    /**
     * insertTuple(), inserts a TupleItem object into the appropriate TupleHolder of binary Tree Structure,
     *                Creates a new Holder if one of the matching weight does not exists, else
     *                adds the TupleItem object in the holder of the matching weight
     *
     * @param tuple , object of TupleItem class
     */
    boolean insertTuple(TupleItem tuple) {
        float tupleWeightInKg = tuple.getWeightInKilograms()

        if (weightInKg == tupleWeightInKg) {
            return (addTupleToList(tuple));
        } else if (tupleWeightInKg < weightInKg) {
            if (left == null) {
                left = new TupleHolder(tuple)
                return (left != null)
            } else {
                left.insertTuple(tuple)
            }
        } else if (tupleWeightInKg > weightInKg) {
            if (right == null) {
                right = new TupleHolder(tuple)
                return (right != null)
            } else {
                right.insertTuple(tuple)
            }
        }
        return false
    }

    /**
     * addTupleToList(), inserts a TupleItem object into the list of TupleItems of this TupleHolder
     *
     * @param tuple , object of TupleItem class
     */
    boolean addTupleToList(TupleItem tuple) {
        return (tuples.add(tuple))
    }

    /**
     * getTuplesInOrder(), gets all TupleItems in ascending order
     *
     * @return result , list of all TupleItems
     */
    ArrayList<TupleItem> getTuplesInOrder() {
        return getAllTuplesInOrder(new ArrayList<TupleItem>())
    }

    /**
     * getAllTuplesInOrder(), gets all TupleItems in ascending order
     *                        by inOrder Traveral of TupleHolder binary Tree
     *
     * @param tupleList , list of all TupleItems
     * @return tupleList , list of all TupleItems
     */
    ArrayList<TupleItem> getAllTuplesInOrder(ArrayList<TupleItem> tupleList) {
        if (left != null) {
            left.getAllTuplesInOrder(tupleList);
        }
        for (TupleItem tuple : tuples) {
            tupleList.add(tuple)
        }
        if (right!= null) {
            right.getAllTuplesInOrder(tupleList);
        }
        return tupleList
    }

    /**
     * printAllTuples(), prints all TupleItems in ascending
     *
     */
    void printAllTuples() {
        printTuples(this)
    }

    /**
     * printTuples(), prints all TupleItems in ascending order by inOrder Traversal
     *
     * @param tupleHolder , object of TupleHolder class
     */
    private void printTuples(TupleHolder tupleHolder) {
        if (tupleHolder == null) {
            return;
        } else {
            printTuples(tupleHolder.left);
            for (TupleItem tuple : tupleHolder.tuples) {
                println("Tuple Description :" + tuple.item_description)
                println("Tuple weight in Kgs :" + tuple.getWeightInKilograms())
                println("Tuple weight in Lb :" + tuple.getWeightInPounds())
                println("---------------------------")
            }
            println("=============================")
            printTuples(tupleHolder.right)
        }
    }
}
