package bci.notifications;

/**
 * Observer interface for the Observer design pattern.
 * This interface should be implemented by all observers that wish to be notified of changes in observable objects.
 */

public interface Observer {
		void update(String message);

}
