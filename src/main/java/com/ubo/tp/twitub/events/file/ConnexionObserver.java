package main.java.com.ubo.tp.twitub.events.file;

import main.java.com.ubo.tp.twitub.datamodel.User;

public interface ConnexionObserver {
  void notifyConnexion(User connectedUser);
}
