package application.model;

import javafx.scene.input.KeyCode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Puzzle extends Game {

    /**
     * Tableau des noms des Piece du Puzzle
     */
    private String[] pieces = {"V2", "V3", "H2", "H3"};

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

        pieceGagante = PieceFactory.getPiece("GG");
        for (int[] coord : pieceGagante.getCoord()) {
            grid[coord[0]][coord[1]] = pieceGagante;
        }

        int lastRnd = -1;
        int rnd = 0;

        // Placement des Piece (11 Piece)
        for(int i=0;i<5;i++) {

            boolean piecePose = false;

            while (!piecePose) {

                // Sélection aléatoire de la Piece
                do {
                    rnd = new Random().nextInt(pieces.length);
                } while (rnd == lastRnd);
                Piece piece = PieceFactory.getPiece(pieces[rnd]);

                // On essaye de poser la pièce à chaque endroit possible
                while (!checkPosition(piece.getCoord(), piece) && piece.getCoord().get(piece.getCoord().size() - 1)[1] <= this.grid.length) {

                    if (piece.getCoord().get(piece.getCoord().size() - 1)[1] >= this.grid[0].length) {
                        piece.setCoord(piece.toDown());
                    }

                    piece.setCoord(piece.toRight());
                }

                if (checkPosition(piece.getCoord(), piece)) {
                    changeCoord(piece.getCoord(), piece);
                    piecePose = true;
                }
            }

            lastRnd = rnd;
            i++;
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
