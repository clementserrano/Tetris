package application.controller;

import application.Main;
import application.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
	 * Labels de la grille représentant les cases du jeu
	 */
	protected Label[][] labels;

	/**
	 * Initialise la vue avec les valeurs du modèle
	 */
	public abstract void init();

	/**
	 * Met à jour la vue en fonction du modèle
	 */
	public abstract void update();


	/**
	 * Gestion de la souris
	 * @param i
	 * @param j
	 */
    public abstract void handleMouseClicked(int i, int j, boolean inHand);
}
