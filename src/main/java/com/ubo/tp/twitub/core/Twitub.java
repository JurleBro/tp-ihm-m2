package com.ubo.tp.twitub.core;

import javax.swing.JFileChooser;
import java.io.File;

import com.ubo.tp.twitub.controller.MainController;
import com.ubo.tp.twitub.datamodel.Database;
import com.ubo.tp.twitub.datamodel.IDatabase;
import com.ubo.tp.twitub.events.file.IWatchableDirectory;
import com.ubo.tp.twitub.events.file.WatchableDirectory;
import com.ubo.tp.twitub.view.TwitubMock;

/**
 * Classe principale l'application.
 *
 * @author S.Lucas
 */
public class Twitub {
  /**
   * Base de données.
   */
  protected IDatabase mDatabase;

  /**
   * Gestionnaire des entités contenu de la base de données.
   */
  protected EntityManager mEntityManager;

  /**
   * Vue principale de l'application.
   */
  protected MainController mainController;

  /**
   * Classe de surveillance de répertoire
   */
  protected IWatchableDirectory mWatchableDirectory;

  /**
   * Répertoire d'échange de l'application.
   */
  protected String mExchangeDirectoryPath;

  /**
   * Idnique si le mode bouchoné est activé.
   */
  protected boolean mIsMockEnabled = false;

  /**
   * Nom de la classe de l'UI.
   */
  protected String mUiClassName;

  /**
   * Constructeur.
   */
  public Twitub() {
    // Init du look and feel de l'application
    this.initLookAndFeel();

    // Initialisation de la base de données
    this.initDatabase();

    // Initialisation du répertoire d'échange
    this.initDirectory();

    if (this.mIsMockEnabled) {
      // Initialisation du bouchon de travail
      this.initMock();
    }

    // Initialisation de l'IHM
    this.initGui();
  }

  /**
   * Initialisation du look and feel de l'application.
   */
  protected void initLookAndFeel() {
  }

  /**
   * Initialisation de l'interface graphique.
   */
  protected void initGui() {
    // Initialisation de la vue principale
    this.mainController = new MainController(this.mEntityManager);
    this.mainController.twits();
  }

  /**
   * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
   * chooser). <br/>
   * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
   * pouvoir utiliser l'application</b>
   */
  protected void initDirectory() {
    JFileChooser choose = new JFileChooser(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources");

    choose.setDialogTitle("Choisissez un repertoire: ");
    choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int res = choose.showSaveDialog(null);
    if (res == JFileChooser.APPROVE_OPTION && choose.getSelectedFile().isDirectory()) {
      this.initDirectory(choose.getSelectedFile().getAbsolutePath());
    } else {
      System.exit(0);
    }
  }

  /**
   * Indique si le fichier donné est valide pour servire de répertoire
   * d'échange
   *
   * @param directory , Répertoire à tester.
   */
  protected boolean isValideExchangeDirectory(File directory) {
    // Valide si répertoire disponible en lecture et écriture
    return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
      && directory.canWrite();
  }

  /**
   * Initialisation du mode bouchoné de l'application
   */
  protected void initMock() {
    TwitubMock mock = new TwitubMock(this.mDatabase, this.mEntityManager);
    mock.showGUI();
  }

  /**
   * Initialisation de la base de données
   */
  protected void initDatabase() {
    mDatabase = new Database();
    mEntityManager = new EntityManager(mDatabase);
  }

  /**
   * Initialisation du répertoire d'échange.
   *
   * @param directoryPath
   */
  public void initDirectory(String directoryPath) {
    mExchangeDirectoryPath = directoryPath;
    mWatchableDirectory = new WatchableDirectory(directoryPath);
    mEntityManager.setExchangeDirectory(directoryPath);

    mWatchableDirectory.initWatching();
    mWatchableDirectory.addObserver(mEntityManager);
  }

  public void show() {
    // ... setVisible?
  }
}
