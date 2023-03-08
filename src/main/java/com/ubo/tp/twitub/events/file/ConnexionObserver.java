package com.ubo.tp.twitub.events.file;

import com.ubo.tp.twitub.datamodel.User;

public interface ConnexionObserver {
  void notifyConnexion(User connectedUser);
}
