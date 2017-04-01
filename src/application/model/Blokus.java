package application.model;


import java.util.ArrayList;

/**
 * Jeu Blokus
 */
public class Blokus extends Game {

    /**
     * Détermine quel est le joueur qui doit jouer
     */
    private int turn;

    /**
     * Tableau des joueurs
     */
    private String[] joueurs = new String[]{"red", "green", "yellow", "blue"};

    /**
     * Tableau des pièces du jeu.
     * Incomplet car le eu n'est pas fini.
     * Blokus réutilise des pièces du Tetris.
     */
    private String[] pieces = new String[]{"B1", "B2", "B3", "B4", "O", "T", "I", "L", "S"};// ,"B5","B6","B7","B8","B9","B10","B11","B12","B13","B14","B15","B16"};

    /**
     * Mémorise la main de chaque joueur.
     */
    private ArrayList<ArrayList<Piece>> piecesJoueurs;

    /**
     * Pièce qui a été sélectionnée par un joueur et qui va être posée.
     */
    private Piece selectedPiece;

    /**
     * Matrice de la main du joueur dont c'est le tour
     */
    private Piece[][] gridMain;

    /**
     * Constructeur du jeu
     */
    public Blokus() {

        turn = 0;

        grid = new Piece[20][20];

        piecesJoueurs = new ArrayList<ArrayList<Piece>>(4);

        for (int i = 0; i < joueurs.length; i++) {
            piecesJoueurs.add(new ArrayList<Piece>(pieces.length));
            for (String name : pieces) {
                Piece p = PieceFactory.getPiece(name);
                p.setColor(joueurs[i]);
                piecesJoueurs.get(i).add(p);
            }
        }

        gridMain = new Piece[4][30];

        changeMainCourante();

    }

    /**
     * Gère les clics de l'utilisateur.
     * Si aucune pièce n'est sélectionné, sélectionne la pièce que l'utilisateur clique dans sa main.
     * Sinon place la pièce si l'utilisateur clique sur un emplacement valide de la grille.
     *
     * @param piece
     * @param i
     * @param j
     * @param inHand
     */
    public void handleMouseClicked(Piece piece, int i, int j, boolean inHand) {
        if (inHand) {
            selectedPiece = piece;
        } else {
            int[] vector = new int[]{i - selectedPiece.getCoord().get(0)[0], j - selectedPiece.getCoord().get(0)[1]};

            if (checkPosition(selectedPiece.move(vector), selectedPiece, grid)) {
                for (int[] coord : selectedPiece.getCoord()) {
                    gridMain[coord[0]][coord[1]] = null;
                }

                for (int[] coord : selectedPiece.move(vector)) {
                    grid[coord[0]][coord[1]] = selectedPiece;
                }

                selectedPiece.setCoord(selectedPiece.move(vector));

                turn = (turn + 1) % joueurs.length;

                changeMainCourante();

                notifyObserver();
            }
        }

    }

    /**
     * Met à jour la grilleMain avec les pièces de la liste des mains de chaque joueur.
     */
    private void changeMainCourante() {
        int[] vector = new int[]{0, 0};

        gridMain = new Piece[4][30];

        for (Piece p : piecesJoueurs.get(turn)) {
            if (checkPosition(p.move(vector), p, gridMain)) {
                changeCoord(p.move(vector), p, gridMain);
            }
            vector[1] += p.getCoord().get(p.getCoord().size() - 1)[1] + 1;
        }
    }

    public Piece[][] getGridMain() {
        return gridMain;
    }
}
