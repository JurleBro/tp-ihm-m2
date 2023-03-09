package com.ubo.tp.twitub.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ubo.tp.twitub.core.EntityManager;
import com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import com.ubo.tp.twitub.datamodel.Twit;
import com.ubo.tp.twitub.datamodel.User;
import com.ubo.tp.twitub.view.TwitsPanel;

public class TwitsController implements Controller, IDatabaseObserver {
  private final EntityManager entityManager;
  private TwitsPanel twitsPanel;
  private Set<Twit> allTwits;
  private String userTagsFilter;
  private String tagsFilter;
  private UUID idUserFilter;

  public TwitsController(EntityManager entityManager) {
    this.entityManager = entityManager;
    this.allTwits = new HashSet<>();
  }

  public void subcribeToDb() {
    entityManager.subscribeToDb(this);
  }

  public void setTwitsPanel(TwitsPanel twitsPanel) {
    this.twitsPanel = twitsPanel;
  }

  public Set<Twit> getTwits() {
    return entityManager.getTwits();
  }

  @Override
  public void notifyTwitAdded(Twit addedTwit) {
    allTwits.add(addedTwit);
    twitsPanel.addTwit(addedTwit);
    List<UUID> twits = getUuidsFiltered();
    twitsPanel.refreshList(twits);
  }

  private List<UUID> getUuidsFiltered() {
    return allTwits
      .stream()
      .filter(twit -> (idUserFilter == null
          || twit.getTwiter().getUuid().equals(idUserFilter))
        && (tagsFilter == null
          || twit.getTags().stream().map(String::toLowerCase).anyMatch(tag -> tag.equals(tagsFilter.toLowerCase())))
        && (userTagsFilter == null
          || twit.getUserTags().stream().map(String::toLowerCase).anyMatch(tag -> tag.equals(userTagsFilter.toLowerCase()))
          || twit.getTwiter().getUserTag().equalsIgnoreCase(userTagsFilter)
        )
      )
      .sorted((e1, e2) -> e2.getEmissionDate() < e1.getEmissionDate() ? -1 : 1)
      .map(Twit::getUuid)
      .collect(Collectors.toList());
  }

  @Override
  public void notifyTwitDeleted(Twit deletedTwit) {

  }

  @Override
  public void notifyTwitModified(Twit modifiedTwit) {

  }

  @Override
  public void notifyUserAdded(User addedUser) {

  }

  @Override
  public void notifyUserDeleted(User deletedUser) {

  }

  @Override
  public void notifyUserModified(User modifiedUser) {

  }

  public void setUserIdFilter(UUID id) {
    idUserFilter = id;
    twitsPanel.refreshList(getUuidsFiltered());
  }

  public void setFilter(String text) {
    if (text != null && !text.isEmpty()) {
      String substring = text.substring(1);
      if(text.startsWith("@")) {
        userTagsFilter = substring;
        tagsFilter = null;
      } else if (text.startsWith("#")) {
        tagsFilter = substring;
        userTagsFilter = null;
      } else {
        tagsFilter = substring;
        userTagsFilter = substring;
      }
    } else {
      tagsFilter = null;
      userTagsFilter = null;
    }
    twitsPanel.refreshList(getUuidsFiltered());
  }
}
