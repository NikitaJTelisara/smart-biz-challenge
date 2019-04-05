package smart.biz.challenge

/**
 * The class representing InventoryManage Singleton
 *
 * @author Nikita Telisara
 *
 */
class InventoryManager {
    private static InventoryManager manager_instance;
    public static TupleHolder firstTupleHolder;

    /**
     * getInstance(), gets the InventoryManager object
     *
     * @return the singleton instance object if exist; otherwise, a new one
     */
    static InventoryManager getInstance() {
        if (manager_instance != null) {
            return manager_instance;
        } else {
            return new InventoryManager()
        }
    }

    /**
     * addTuple(), adds the TupleItem object into the list inside the appropriate
     *             TupleHolder of the TupleHolder binary Tree Structure
     *
     * @param tupleItem , object of TupleItem class
     */
    void addTuple(TupleItem tupleItem) {
        if (firstTupleHolder == null) {
            firstTupleHolder = new TupleHolder(tupleItem)
        } else {
            firstTupleHolder.insertTuple(tupleItem)
        }
    }
}




