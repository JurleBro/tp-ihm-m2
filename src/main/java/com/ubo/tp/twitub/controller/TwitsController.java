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
  private List<String> userTagsFilter;
  private List<String> tagsFilter;
  private UUID idUserFilter;

  public TwitsController(EntityManager entityManager) {
    this.entityManager = entityManager;
    this.allTwits = new HashSet<>();
    this.userTagsFilter = new ArrayList<>();
    this.tagsFilter = new ArrayList<>();
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
    List<UUID> twits = allTwits
      .stream()
      .filter(twit -> (idUserFilter == null || twit.getTwiter().getUuid().equals(idUserFilter))
        && (containsAll(twit.getTags(), tagsFilter))
        && (userTagsFilter.size() == 1 && twit.getTwiter().getUserTag().toLowerCase().contains(userTagsFilter.get(0).toLowerCase())
            || containsAll(twit.getUserTags(), userTagsFilter)
        )
      )
      .sorted((e1, e2) -> e2.getEmissionDate() < e1.getEmissionDate() ? -1 : 1)
      .map(Twit::getUuid)
      .collect(Collectors.toList());
    return twits;
  }

  public boolean containsAll(Set<String> list1, List<String> list2) {
    return list2.isEmpty() || list2
      .stream()
      .allMatch(tag -> list1
        .stream()
        .anyMatch(twitTag -> twitTag.toLowerCase().contains(tag.toLowerCase()))
      );
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
    List<String> mots = Arrays.asList(text.split(" "));
    userTagsFilter = mots.stream()
      .filter(mot -> mot.startsWith("@"))
      .map(mot -> mot.substring(1))
      .collect(Collectors.toList());
    tagsFilter = mots.stream()
      .filter(mot -> mot.startsWith("#"))
      .map(mot -> mot.substring(1))
      .collect(Collectors.toList());
    twitsPanel.refreshList(getUuidsFiltered());
  }
}
