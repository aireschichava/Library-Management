package bci.works;
import bci.user.User;


/**
 * Represents an active loan (request) of a work by a user.
 */
public class Loan {
    private final int id;
    private final User user;
    private final Work work;
    private final int requestDate;
    private final int dueDate;
    private boolean returned;
    private int returnDate;

    public Loan(int id, User user, Work work, int requestDate, int dueDate) {
        this.id = id;
        this.user = user;
        this.work = work;
        this.requestDate = requestDate;
        this.dueDate = dueDate;
        this.returned = false;
    }

    public int getId() { return id; }
    public User getUser() { return user; }
    public Work getWork() { return work; }
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
}
