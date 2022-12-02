package no.ntnu.gr30.providers;

/**
 * Responsible for giving each instantiated sensor has a unique id.
 */
public class IdProvider {
    private int id = 0;

    private IdProvider() {}
    private static final IdProvider instance = new IdProvider();
    public static IdProvider getInstance() {
        return instance;
    }

    /**
     * Return an id and increment the value for the next id
     * @return unique id
     */
    public int assignId() {
        int assignedId = this.id;
        this.id += 1;
        return assignedId;
    }
}
