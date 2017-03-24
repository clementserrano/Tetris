package application.model;

import application.controller.GameController;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;

/**
 * Classe abstraite contenant les attributs et méthodes communs à tous les jeux
 */
public abstract class Game {

    /**
     * Grille du jeu, il s'agit d'une matrice de Piece. La matrice contiendra
     * la même adresse Piece sur toutes les cases correspondantes à la Piece
     */
    protected Piece[][] grid;

    /**
     * Contrôleur du jeu permettant la mise à jour de la vue par rapport au modèle
     */
    protected GameController observer;

    /**
     * Objet JavaFX permettant la boucle du jeu
     */
    protected Timeline timeline;

    /**
     * Mise en place de la boucle du jeu
     */
    public abstract void run();

    /**
     * Logique du jeu, à chaque itération du jeu cette méthode est appelée
     */
    protected abstract void logic();

    /**
     * Gestion des touches pressées du clavier
     *
     * @param keyCode Code de la touche pressée
     */
    public abstract void handleKeyPressed(KeyCode keyCode);

    /**
     * Méthode permettant d'avertir le contrôleur qu'un changement a été effectué sur le modèle
     */
    public void notifyObserver() {
        observer.update();
    }

    /**
     * Retourne la grille de jeu
     *
     * @return matrice de Piece
     */
    public Piece[][] getGrid() {
        return grid;
    }

    /**
     * Associe le contrôleur au modèle (l'observeur à l'observé
     *
     * @param controller
     */
    public void setObserver(GameController controller) {
        this.observer = controller;
    }

}
