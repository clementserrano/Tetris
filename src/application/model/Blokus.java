package application.model;


import java.util.ArrayList;

public class Blokus extends Game {

    private int turn;

    private String[] joueurs = new String[]{"red","green","yellow","blue"};

    private String[] pieces  = new String[]{"B1","B2","B3","B4","O","T","I","L","S"};//,"B5","B6","B7","B8","B9","B10","B11","B12","B13","B14","B15","B16"};

    private ArrayList<ArrayList<Piece>> piecesJoueurs;

    private Piece selectedPiece;

    private Piece[][] gridMain;

    public Blokus(){
        turn = 0;

        grid = new Piece[20][20];

        piecesJoueurs = new ArrayList<ArrayList<Piece>>(4);

        for(int i =0; i< joueurs.length ; i++) {
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

    public void handleMouseClicked(Piece piece, int i, int j, boolean inHand) {
        if(inHand){
            selectedPiece = piece;
        }else{
            int[] vector = new int[]{i-selectedPiece.getCoord().get(0)[0],j-selectedPiece.getCoord().get(0)[1]};

            if(checkPosition(selectedPiece.move(vector),selectedPiece)){
                for (int[] coord : selectedPiece.getCoord()) {
                    gridMain[coord[0]][coord[1]] = null;
                }

                for (int[] coord : selectedPiece.move(vector)) {
                    grid[coord[0]][coord[1]] = selectedPiece;
                }

                selectedPiece.setCoord(selectedPiece.move(vector));

                turn=(turn+1)%joueurs.length;

                changeMainCourante();

                notifyObserver();
            }
        }

    }

    private void changeMainCourante(){
        int[] vector = new int[]{0,0};

        gridMain = new Piece[4][30];

        for(Piece p : piecesJoueurs.get(turn)){
            if(checkPositionMain(p.move(vector),p)) {
                changeCoordMain(p.move(vector), p);
            }
            vector[1]+=p.getCoord().get(p.getCoord().size()-1)[1]+1;
        }
    }

    private boolean checkPositionMain(ArrayList<int[]> newCoord, Piece piece) {
        for (int[] coord : newCoord) {
            if (coord[0] < 0 || coord[0] >= gridMain.length) {
                return false;
            }
            if (coord[1] < 0 || coord[1] >= gridMain[0].length) {
                return false;
            }
            if (gridMain[coord[0]][coord[1]] instanceof Piece && gridMain[coord[0]][coord[1]] != piece) {
                return false;
            }
        }
        return true;
    }

    private void changeCoordMain(ArrayList<int[]> coords, Piece piece) {
        for (int[] coord : piece.getCoord()) {
            gridMain[coord[0]][coord[1]] = null;
        }

        for (int[] coord : coords) {
            gridMain[coord[0]][coord[1]] = piece;
        }

        piece.setCoord(coords);
    }

    public Piece[][] getGridMain() {
        return gridMain;
    }
}
