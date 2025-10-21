package bci.works;

import java.io.Serializable;

/**
 * Represents an active loan (request) of a work by a user.
 */
public class Loan implements Serializable {
    private final int id;
    private final int userId;
    private final int workId;
    private final int requestDate;
    private final int dueDate;
    private boolean returned;
    private int returnDate;

    public Loan(int id, int userId, int workId, int requestDate, int dueDate) {
        this.id = id;
        this.userId = userId;
        this.workId = workId;
        this.requestDate = requestDate;
        this.dueDate = dueDate;
        this.returned = false;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getWorkId() { return workId; }
    public int getRequestDate() { return requestDate; }
    public int getDueDate() { return dueDate; }
    public boolean isReturned() { return returned; }
    public int getReturnDate() { return returnDate; }

    public void returnWork(int returnDate) {
        this.returned = true;
        this.returnDate = returnDate;
    }

    public boolean isOverdue(int currentDate) {
        return !returned && currentDate > dueDate;
    }

    public boolean isActive() {
        return !this.returned;
    }

    public boolean isReturnedOnTime() {
        return this.returned && this.returnDate <= this.dueDate;
    }

    public int getDaysLate(int currentDate) {
        int referenceDate = this.returned ? this.returnDate : currentDate;
        return Math.max(0, referenceDate - this.dueDate);
    }

}
