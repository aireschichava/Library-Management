package bci.user;

import bci.exceptions.*;
import java.io.*;

public class User {
    
    private static int nextId = 1;
    private int id;
    private String name;
    private String email;
    private Status status;
    private Behavior behaviour;
    private double fine = 0.0;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.id = nextId++;
        this.status = new Active();
        this.behaviour = new Normal();
        this.fine = 0.0;
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

    public double getFine() {
        return this.fine;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setBehavior(Behavior behavior) {
        this.behaviour = behavior;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public String toDisplayString() {
        String base = this.id + " - " + this.name + " - " + this.email + " - " + this.behaviour.getName() + " - " + this.status.getName();
        if (status instanceof Suspended) {
            base += " - EUR " + (int) this.fine;
        }
        return base;
    }

    @Override
    public String toString() {
        return toDisplayString();
    }
}
