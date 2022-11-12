package no.ntnu.gr30.providers;

public class IdProvider {
    private int id = 0;
    private IdProvider() {}

    private static final IdProvider instance = new IdProvider();

    public static IdProvider getInstance() {
        return instance;
    }

    public int assignId() {
        int assignedId = this.id;
        this.id += 1;
        return assignedId;
    }
}
