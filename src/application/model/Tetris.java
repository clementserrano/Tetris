package application.model;

import java.util.ArrayList;
import java.util.Random;

import application.controller.GameController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

/**
 * Modèle du jeu Tetris
 */
public class Tetris extends Game {

    /**
     * Grille contenant la prochaine Piece à afficher
     */
    private Piece[][] gridProchain;

    /**
     * Prochaine Piece
     */
    private Piece nextPiece;

    /**
     * Piece en cours de mouvement
     */
    private Piece moveablePiece;

    /**
     * Tableau des noms des Piece du Tetris
     */
    private String[] pieces = {"S", "Z", "L", "J", "T", "O", "I"};

    /**
     * Score du joueur
     */
    private int score;

    /**
     * Niveau courant du jeu
     */
    private int niveau;

    /**
     * Détermine si le jeu est perdu ou non
     */
    private boolean gameOver;

    /**
     * Construit le Tetris en initialisant ses attributs et en plaçant la première pièce
     */
    public Tetris() {
        this.grid = new Piece[30][16];

        this.gridProchain = new Piece[4][4];

        
        // Sélection aléatoire de la prochaine Piece
        int rnd = new Random().nextInt(pieces.length);
        this.nextPiece = PieceFactory.getPiece(pieces[rnd]);

        // Place la prochaine Piece dans sa grille
        for (int[] coord : nextPiece.getCoord()) {
            gridProchain[coord[0]][coord[1]] = nextPiece;
        }


        // Sélection aléatoire de la Piece en mouvement
        rnd = new Random().nextInt(pieces.length);
        this.moveablePiece = PieceFactory.getPiece(pieces[rnd]);

        // Place la Piece en mouvement sur la grille
        for (int[] coord : moveablePiece.getCoord()) {
            coord[1] += grid[0].length / 2;
            grid[coord[0]][coord[1]] = moveablePiece;
        }

        this.score = 0;

        this.niveau = 1;

        this.gameOver = false;
    }

    @Override
    public void run() {
        // Met en place le timer du jeu en diminuant la fréquence d'intervalle de 100 ms/niveau (départ 1000 ms)
        timeline = new Timeline(new KeyFrame(Duration.millis(1000 - 100 * niveau), ae -> logic()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void logic() {
        // Si le jeu est perdu, on arrête le timer
        if (checkGameOver()) {
            gameOver = true;
            timeline.stop();
        } else {

            // Sinon on descend la Piece
            ArrayList<int[]> newCoord = moveablePiece.toDown();

            // Si la Piece descend sans collision, on valide le changement de coordonnées
            if (checkPosition(newCoord)) {
                changeCoord(newCoord);
            } else {

                // Sinon, on stoppe la Piece et on passe à la suivante
                moveablePiece = nextPiece;

                checkRow();

                boolean stop = false;

                for (int[] coord : moveablePiece.getCoord()) {
                    coord[1] += grid[0].length / 2;
                    if (grid[coord[0]][coord[1]] != null) {
                        stop = true;
                    }

                    if (grid[coord[0]][coord[1]] == null && !stop) {
                        grid[coord[0]][coord[1]] = moveablePiece;
                    }
                }

                // On sélectionne une nouvelle prochaine Piece
                int rnd = new Random().nextInt(pieces.length);
                nextPiece = PieceFactory.getPiece(pieces[rnd]);

                gridProchain = new Piece[4][4];

                for (int[] coord : nextPiece.getCoord()) {
                    gridProchain[coord[0]][coord[1]] = nextPiece;
                }
            }
        }

        // On prévient le contrôleur du changement à chaque itération du jeu
        notifyObserver();
    }

    @Override
    public void handleKeyPressed(KeyCode keyCode) {

        // Si le jeu n'est pas perdu, on autorise les actions du joueur
        if (!isGameOver()) {
            ArrayList<int[]> newCoord = moveablePiece.getCoord();

            switch (keyCode) {
                case UP:
                    newCoord = moveablePiece.rotate();
                    break;
                case LEFT:
                    newCoord = moveablePiece.toLeft();
                    break;
                case RIGHT:
                    newCoord = moveablePiece.toRight();
                    break;
                case DOWN:
                    newCoord = moveablePiece.toDown();
                    break;
                default:
                    return;
            }

            if (checkPosition(newCoord)) {
                changeCoord(newCoord);
            }

            // On prévient le contrôleur du changement à chaque action du joueur
            this.notifyObserver();
        }
    }

    // Méthodes privées nécessaire à la logique du jeu

    /**
     * Vérifie si une ligne est pleine, si elle l'est la supprime
     */
    private void checkRow() {
        boolean lignePleine = false;
        for (int i = grid.length - 1; i >= 0; i--) {
            lignePleine = true;
            for (int j = 0; j < grid[0].length && lignePleine; j++) {
                // Verifie si la Piece n'est pas en mouvement
                if (grid[i][j] == null || grid[i][j] == moveablePiece) {
                    lignePleine = false;
                }
            }
            if (lignePleine) {
                supprimerLigne(i);
                i++;
            }
        }
    }

    /**
     * Supprime une ligne et incrémente le score de 100, et à chaque palier de 1000, augmente le niveau
     *
     * @param indice un int correspondant à l'indice de la ligne à supprimer
     */
    private void supprimerLigne(int indice) {

        // Décale la grille jusqu'à la ligne correspondante à l'indice fournie afin de la supprimer
        for (int i = indice; i >= 0; i--) {
            for (int j = 0; j < grid[0].length && i > 0; j++) {
                grid[i][j] = grid[i - 1][j];
            }
        }

        score += 100;

        // On prévient le contrôleur du changement à chaque augmentation du score
        this.notifyObserver();

        if (score >= niveau * 1000 && niveau < 9) {
            niveau++;

            // On prévient le contrôleur du changement à chaque augmentation du niveau
            this.notifyObserver();
            timeline.stop();
            run();
        }
    }

    /**
     * Vérifie si le jeu est perdu
     *
     * @return true si le jeu est perdu, false sinon
     */
    private boolean checkGameOver() {
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[0][j] instanceof Piece && grid[0][j] != moveablePiece) {
                return true;
            }
        }
        return false;
    }

    /**
     * Change les coordonnées à la fois dans la matrice et dans le Piece en mouvement
     *
     * @param coords une liste de tableaux d'entiers : nouvelles coordonnées de la Piece
     */
    private void changeCoord(ArrayList<int[]> coords) {
        for (int[] coord : moveablePiece.getCoord()) {
            grid[coord[0]][coord[1]] = null;
        }

        for (int[] coord : coords) {
            grid[coord[0]][coord[1]] = moveablePiece;
        }

        moveablePiece.setCoord(coords);
    }

    /**
     * Vérifie si les nouvelles coordonnées de la Piece ne sont pas sur une autre Piece ou en dehors de la grille
     *
     * @param newCoord une liste de tableaux d'entiers : nouvelles coordonnées de la Piece
     * @return true si les coordonnées sont bonnes, false sinon
     */
    private boolean checkPosition(ArrayList<int[]> newCoord) {
        for (int[] coord : newCoord) {
            if (coord[0] < 0 || coord[0] >= grid.length) {
                return false;
            }
            if (coord[1] < 0 || coord[1] >= grid[0].length) {
                return false;
            }
            if (grid[coord[0]][coord[1]] instanceof Piece && grid[coord[0]][coord[1]] != moveablePiece) {
                return false;
            }
        }
        return true;
    }


    // GETTER

    /**
     * Retourne la grille de la prochaine Piece
     *
     * @return une matrice de Piece
     */
    public Piece[][] getGridProchain() {
        return gridProchain;
    }

    /**
     * Retourne le score
     *
     * @return un String
     */
    public String getScore() {
        return String.valueOf(score);
    }

    /**
     * Retourne le niveau
     *
     * @return un String
     */
    public String getNiveau() {
        return String.valueOf(niveau);
    }

    /**
     * Retourne le statut du jeu
     *
     * @return true le jeu est perdu, false sinon
     */
    public boolean isGameOver() {
        return gameOver;
    }
}
