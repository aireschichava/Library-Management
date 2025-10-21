package bci;

/**
 * Small data holder for return operation results.
 */
public class ReturnResult {
    private final boolean success;
    private final int fine;
    private final boolean notified;

    public ReturnResult(boolean success, int fine, boolean notified) {
        this.success = success;
        this.fine = fine;
        this.notified = notified;
    }

    public boolean isSuccess() { return success; }
    public int getFine() { return fine; }
    public boolean wasNotified() { return notified; }
}
