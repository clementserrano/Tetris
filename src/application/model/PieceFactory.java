package application.model;

import java.util.ArrayList;

/**
 * Usine de Piece
 */
public class PieceFactory {

    /**
     * Retourne la Piece correspondante au nom entré
     *
     * @param name un String : Nom de la Piece
     * @return la Piece
     */
    public static Piece getPiece(String name) {

        ArrayList<int[]> coord = new ArrayList<int[]>();
        Piece piece;

        switch (name) {
            case "S":

                // Construit les coordonnées de la Piece
                coord.add(new int[]{0, 2});
                coord.add(new int[]{0, 1});
                coord.add(new int[]{1, 1});
                coord.add(new int[]{1, 0});

                // Construit la Piece
                piece = new Piece(coord);

                // Définit le pivot
                piece.setPivot(coord.get(1));

                // Définit la couleur
                piece.setColor("#1FD925");

                return piece;

            case "Z":
                coord.add(new int[]{0, 0});
                coord.add(new int[]{0, 1});
                coord.add(new int[]{1, 1});
                coord.add(new int[]{1, 2});
                piece = new Piece(coord);
                piece.setPivot(coord.get(1));
                piece.setColor("#FE3301");
                return piece;
            case "J":
                coord.add(new int[]{0, 1});
                coord.add(new int[]{0, 2});
                coord.add(new int[]{0, 3});
                coord.add(new int[]{1, 3});
                piece = new Piece(coord);
                piece.setPivot(coord.get(2));
                piece.setColor("#011AFE");
                return piece;
            case "L":
                coord.add(new int[]{0, 0});
                coord.add(new int[]{1, 0});
                coord.add(new int[]{2, 0});
                coord.add(new int[]{2, 1});
                piece = new Piece(coord);
                piece.setPivot(coord.get(2));
                piece.setColor("#FEAA01");
                return piece;
            case "O":
                coord.add(new int[]{0, 0});
                coord.add(new int[]{0, 1});
                coord.add(new int[]{1, 0});
                coord.add(new int[]{1, 1});
                piece = new Piece(coord);
                piece.setPivot(coord.get(1));
                piece.setColor("#FEF601");
                return piece;
            case "I":
                coord.add(new int[]{0, 0});
                coord.add(new int[]{1, 0});
                coord.add(new int[]{2, 0});
                coord.add(new int[]{3, 0});
                piece = new Piece(coord);
                piece.setPivot(coord.get(1));
                piece.setColor("#01FEED");
                return piece;
            case "T":
                coord.add(new int[]{0, 0});
                coord.add(new int[]{1, 0});
                coord.add(new int[]{2, 0});
                coord.add(new int[]{1, 1});
                piece = new Piece(coord);
                piece.setPivot(coord.get(3));
                piece.setColor("#DC01FE");
                return piece;
        }
        return null;
    }

}
