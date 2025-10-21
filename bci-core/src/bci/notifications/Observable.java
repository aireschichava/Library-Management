package bci.notifications;

/**
 * Observable interface for the Observer design pattern.
 * This interface allows objects to be observed by multiple observers.
 */
public interface Observable {
    void addObserver(Observer observer);
        void removeObserver(Observer observer);
        void notifyObservers(String message);
        public void notifyLoanObservers(String message);
    }
