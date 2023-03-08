package com.ubo.tp.twitub.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import com.ubo.tp.twitub.core.EntityManager;
import com.ubo.tp.twitub.datamodel.User;

public class InscriptionController implements Controller {

  private final EntityManager mEntityManager;
  private final MainController mainController;

  public InscriptionController(EntityManager entityManager, MainController mainController) {
    this.mEntityManager = entityManager;
    this.mainController = mainController;
  }
  public List<Integer> inscription(String name, String tag, String password) {
    List<Integer> errorCodes = new ArrayList<>();
    if (name == null || name.isEmpty()) {
      errorCodes.add(-1);
    }
    if (tag == null || tag.isEmpty()) {
      errorCodes.add(-2);
    } else if (mEntityManager.getUserExist(tag)) {
      errorCodes.add(-3);
    }
    if (password == null || password.isEmpty()) {
      errorCodes.add(-4);
    }
    if(!errorCodes.isEmpty()) {
      return errorCodes;
    }
    User newUser = new User(UUID.randomUUID(), tag, password, name, new HashSet<>(), null);
    mEntityManager.sendUser(newUser);
    mainController.connexion();
    return null;
  }

}
