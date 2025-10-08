package bci.user;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class User implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    private final int id;
    private final String name;
    private final String email;
    private Status status;
    private Behavior behaviour;
    private final Deque<Boolean> lastReturns;
    private int fine;

    private int consecOnTime = 0;
    private int consecLate = 0;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.email = Objects.requireNonNull(email);
        this.status = new Active();
        this.behaviour = new Normal();
        this.lastReturns = new ArrayDeque<>(5);
        this.fine = 0; 
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public Status getStatus() {
        return this.status;
    }

    public Behavior getBehavior() {
        return this.behaviour;
    }

    public int getFine() {
        return this.fine;
    }
    public void setFine(int fine) {
        this.fine = fine;
    }
    public void addFine(int amount) {
        if (amount > 0) this.fine += amount;
    }
    public void clearFine() {
        this.fine = 0;
    }

    public void setActive() {
        this.status = new Active();
    }
    public void setSuspended() {
        this.status = new Suspended();
    }
    public boolean isSuspended() {
        return this.status instanceof Suspended;
    }

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
