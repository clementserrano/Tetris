package application.controller;

import application.Main;
import application.model.Game;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

/**
 * Classe abstraite contenant les attributs et méthodes communs à tous les contrôleurs de jeux
 */
public abstract class GameController {

	/**
	 * Grille JavaFX représentant la matrice du jeu
	 */
	@FXML
	protected GridPane gridView;

	/**
	 * Modèle du jeu
	 */
	protected Game game;

	/**
	 * Application JavaFX
	 */
	protected Main main;

	/**
	 * Initialise la vue avec les valeurs du modèle
	 */
	public abstract void init();

	/**
	 * Met à jour la vue en fonction du modèle
	 */
	public abstract void update();

	/**
	 * Modifie la référence du Main
	 *
	 * @param main
	 */
	public void setMain(Main main) {
		this.main = main;
	}

	/**
	 * Modifie la référence du jeu
	 *
	 * @param game
	 */
	public void setGame(Game game) {
		this.game = game;
	}
}
