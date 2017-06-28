package qqc.mosyits.haw.qqc.Networking;

/**
 * ObserverInterface to distribute messages from client to other classes and activities
 */

public interface MessageObserver {
    void updateMessage(String msg);
}
