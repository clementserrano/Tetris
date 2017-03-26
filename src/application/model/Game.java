package application.model;

import application.controller.GameController;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

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
     * Méthode permettant d'avertir le contrôleur qu'un changement a été effectué sur le modèle
     */
    public void notifyObserver() {
        observer.update();
    }

    /**
     * Vérifie si les nouvelles coordonnées de la Piece ne sont pas sur une autre Piece ou en dehors de la grille
     *
     * @param newCoord une liste de tableaux d'entiers : nouvelles coordonnées de la Piece
     * @return true si les coordonnées sont bonnes, false sinon
     */
    protected boolean checkPosition(ArrayList<int[]> newCoord, Piece piece) {
        for (int[] coord : newCoord) {
            if (coord[0] < 0 || coord[0] >= grid.length) {
                return false;
            }
            if (coord[1] < 0 || coord[1] >= grid[0].length) {
                return false;
            }
            if (grid[coord[0]][coord[1]] instanceof Piece && grid[coord[0]][coord[1]] != piece) {
                return false;
            }
        }
        return true;
    }

    /**
     * Change les coordonnées à la fois dans la matrice et dans le Piece en mouvement
     *
     * @param coords une liste de tableaux d'entiers : nouvelles coordonnées de la Piece
     */
    protected void changeCoord(ArrayList<int[]> coords, Piece piece) {
        for (int[] coord : piece.getCoord()) {
            grid[coord[0]][coord[1]] = null;
        }

        for (int[] coord : coords) {
            grid[coord[0]][coord[1]] = piece;
        }

        piece.setCoord(coords);
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
