package application.model;

import javafx.scene.input.KeyCode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Puzzle extends Game {

    /**
     * Tableau des noms des Piece du Puzzle
     */
    private String[] pieces = {"P1", "P2", "P3", "P4","P5","P6","P9","P10","P11"};
    //private String[] pieces = {"P1", "P2", "P3", "P4","P5","P6","P7","P8","P9","P10","P11"};

    /**
     * Informe si la partie est gagnée ou non
     */
    private boolean gameWon;

    /**
     * Piece sélectionnée
     */
    private Piece selectedPiece;

    /**
     * Piece à amener
     */
    private Piece pieceGagante;

    /**
     * Construit le Puzzle en initialisant ses attributs et en plaçant les pièces
     */
    public Puzzle() {

        this.grid = new Piece[6][6];
        this.gameWon = false;

        pieceGagante = PieceFactory.getPiece("P0");
        for (int[] coord : pieceGagante.getCoord()) {
            grid[coord[0]][coord[1]] = pieceGagante;
        }

        for(String name : pieces){
            Piece piece = PieceFactory.getPiece(name);
            for (int[] coord : piece.getCoord()) {
                grid[coord[0]][coord[1]] = piece;
            }
        }


    }

    /**
     * Gestion des clics de la souris
     *
     * @param piece Piece cliquée
     */
    public void handleMouseClicked(Piece piece) {
        selectedPiece = piece;
    }

    /**
     * Gestion des touches pressées du clavier
     *
     * @param keyCode Code de la touche pressée
     */
    public void handleKeyPressed(KeyCode keyCode) {

        // Si le jeu n'est pas gagné, on autorise les actions du joueur
        if (!isGameWon() && selectedPiece != null) {
            ArrayList<int[]> newCoord = selectedPiece.getCoord();

            switch (keyCode) {
                case UP:
                    if (selectedPiece.getSens().equals("vertical")) newCoord = selectedPiece.toUp();
                    break;
                case LEFT:
                    if (selectedPiece.getSens().equals("horizontal")) newCoord = selectedPiece.toLeft();
                    break;
                case RIGHT:
                    if (selectedPiece.getSens().equals("horizontal")) newCoord = selectedPiece.toRight();
                    break;
                case DOWN:
                    if (selectedPiece.getSens().equals("vertical")) newCoord = selectedPiece.toDown();
                    break;
                default:
                    return;
            }

            if (checkPosition(newCoord, selectedPiece)) {
                changeCoord(newCoord, selectedPiece);
            }

            int[] coord = pieceGagante.getCoord().get(pieceGagante.getCoord().size() - 1);

            // On prévient le contrôleur du changement à chaque action du joueur
            this.notifyObserver();

            if (coord[1] == this.grid[0].length - 1) {
                gameWon = true;
                this.notifyObserver();
            }
        }
    }

    /**
     * Retourne un booléen informant si le jeu est gagné ou non
     *
     * @return true si le jeu est gagné, false sinon
     */
    public boolean isGameWon() {
        return gameWon;
    }


}
