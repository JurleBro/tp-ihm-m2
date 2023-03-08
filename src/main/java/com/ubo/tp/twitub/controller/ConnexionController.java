package com.ubo.tp.twitub.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ubo.tp.twitub.core.EntityManager;
import com.ubo.tp.twitub.datamodel.User;
import com.ubo.tp.twitub.events.file.ConnexionObserver;

public class ConnexionController implements Controller {

  private final EntityManager mEntityManager;

  private List<ConnexionObserver> observers;

  public ConnexionController(EntityManager entityManager) {
    this.mEntityManager = entityManager;
    this.observers = new ArrayList<>();
  }
  public List<Integer> connexion(String login, String password) {
    List<Integer> errorCodes = new ArrayList<>();
    if (login == null || login.isEmpty()) {
      errorCodes.add(-1);
    }
    if (password == null || password.isEmpty()) {
      errorCodes.add(-2);
    }
    if(!errorCodes.isEmpty()) {
      return errorCodes;
    }
    User result = mEntityManager.getUserByName(login);
    if(result == null) {
      return Collections.singletonList(-3);
    }
    if(result.getUserPassword().equals(password)) {
      observers.forEach(observer -> observer.notifyConnexion(result));
      return null;
    }
    return Collections.singletonList(-3);
  }

  public void addObserver(ConnexionObserver observer) {
    observers.add(observer);
  }
}
