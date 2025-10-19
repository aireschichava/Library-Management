package bci.user;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import bci.works.Loan;
import java.util.List;

/**
 * Class representing a user of the library system.
 */
public class User implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    /** Unique identifier for the user. */
    private final int id;
    /** Name of the user. */
    private final String name;
    /** Email address of the user. */
    private final String email;
    /** Current status of the user. */
    private Status status;
    /** Current behavior profile of the user. */
    private Behavior behaviour;
    /** Deque tracking the last 5 returns (on time or not). */
    private final Deque<Boolean> lastReturns;
    /** Current fine amount for the user. */
    private int fine;

    /** List of active loans for the user. */
    private List<Loan> loans;

    /** Number of consecutive on-time returns. */
    private int consecOnTime = 0;
    /** Number of consecutive late returns. */
    private int consecLate = 0;

    /**
     * Constructs a new User.
     * @param id unique identifier
     * @param name name of the user
     * @param email email address of the user
     */
    public User(int id, String name, String email) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.email = Objects.requireNonNull(email);
        this.status = new Active();
        this.behaviour = new Normal();
        this.lastReturns = new ArrayDeque<>(5);
        this.fine = 0; 
    }

    /**
     * Returns the user's unique identifier.
     * @return user id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the user's name.
     * @return user name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the user's email address.
     * @return user email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the user's current status.
     * @return user status
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Returns the user's current behavior profile.
     * @return user behavior
     */
    public Behavior getBehavior() {
        return this.behaviour;
    }

    /**
     * Returns the user's current fine amount.
     * @return fine amount
     */
    public int getFine() {
        return this.fine;
    }
    
    
    /** Returns the list of active loans.
	 * @return list of loans
	 */	public List<Loan> getLoans() {
		 
		 return this.loans;
	 }
	 
	 /** Returns the number of active loans.*/
	 public int getNumberOfLoans() {
		 return this.loans.size();
	 }
    
    /**
     * Sets the user's fine amount.
     * @param fine the fine amount to set
     */
    public void setFine(int fine) {
        this.fine = fine;
    }
    /**
     * Adds an amount to the user's fine.
     * @param amount the amount to add
     */
    public void addFine(int amount) {
        if (amount > 0) this.fine += amount;
    }
    /**
     * Clears the user's fine, setting it to zero.
     */
    public void clearFine() {
        this.fine = 0;
    }

    /**
     * Sets the user's status to active.
     */
    public void setActive() {
        this.status = new Active();
    }
    /**
     * Sets the user's status to suspended.
     */
    public void setSuspended() {
        this.status = new Suspended();
    }
    
    /**Sets the requested loans list.
	 * @param loans list of loans
	 */
	public void setLoans(Loan loan) {
		this.loans.add(loan);
	}
	
	/**Returns the list of active loans.
	 */
    
    
    /**
     * Checks if the user is suspended.
     * @return true if suspended, false otherwise
     */
    public boolean isSuspended() {
        return this.status.canBorrow();
    }

    /**
     * Records a return by the user, indicating if it was on time.
     * @param onTime true if the return was on time, false otherwise
     */
    public void recordReturn(boolean onTime) {
        if (lastReturns.size() == 5) {
            lastReturns.removeFirst();
        }
        lastReturns.addLast(onTime);
        if (onTime) {
            consecOnTime++;
            consecLate = 0;
        } else {
            consecLate++;
            consecOnTime = 0;
        }
        updateBehaviour();
    }

    private void updateBehaviour() {
        // 1) FALTOSO: 3 devoluções consecutivas fora de prazo
        if (consecLate >= 3) {
            this.behaviour = new Faulty();
            return;
        }
        // 2) CUMPRIDOR: últimas 5 devoluções todas dentro do prazo
        if (lastReturns.size() == 5 && lastReturns.stream().allMatch(Boolean::booleanValue)) {
            this.behaviour = new Compliant();
            return;
        }
        // 3) FALTOSO → NORMAL: 3 devoluções consecutivas dentro do prazo
        if (behaviour instanceof Faulty && consecOnTime >= 3) {
            this.behaviour = new Normal();
        }
    }
    

    /**
     * Returns a string representation of the user, suitable for display.
     * @return display string
     */
    public String toDisplayString() {
        String base = this.id + " - " + this.name + " - " + this.email + " - " + this.behaviour.getName() + " - " + this.status.getName();
        if (status instanceof Suspended) {
            base += " - EUR " + this.fine;
        }
        return base;
    }

    @Override
    public String toString() {
        return toDisplayString();
    }

    


}