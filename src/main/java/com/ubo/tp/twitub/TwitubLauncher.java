package com.ubo.tp.twitub;

import com.ubo.tp.twitub.core.Twitub;

/**
 * Classe de lancement de l'application.
 * 
 * @author S.Lucas
 */
public class TwitubLauncher {

	/**
	 * Launcher.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Lancement de l'application");
		Twitub twitub = new Twitub();
		twitub.show();
	}

}
