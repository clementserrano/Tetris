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

            // TETRIS

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

            // PUZZLE

            case "P0":
                coord.add(new int[]{2, 0});
                coord.add(new int[]{2, 1});
                piece = new Piece(coord);
                piece.setColor("red");
                piece.setSens("horizontal");
                return piece;
            case "P1":
                coord.add(new int[]{0, 1});
                coord.add(new int[]{0, 2});
                coord.add(new int[]{0, 3});
                piece = new Piece(coord);
                piece.setColor("orange");
                piece.setSens("horizontal");
                return piece;
            case "P2":
                coord.add(new int[]{1, 2});
                coord.add(new int[]{1, 3});
                piece = new Piece(coord);
                piece.setColor("blue");
                piece.setSens("horizontal");
                return piece;
            case "P3":
                coord.add(new int[]{1, 4});
                coord.add(new int[]{2, 4});
                piece = new Piece(coord);
                piece.setColor("green");
                piece.setSens("vertical");
                return piece;
            case "P4":
                coord.add(new int[]{1, 5});
                coord.add(new int[]{2, 5});
                piece = new Piece(coord);
                piece.setColor("yellow");
                piece.setSens("vertical");
                return piece;
            case "P5":
                coord.add(new int[]{2, 2});
                coord.add(new int[]{3, 2});
                piece = new Piece(coord);
                piece.setColor("pink");
                piece.setSens("vertical");
                return piece;
            case "P6":
                coord.add(new int[]{2, 3});
                coord.add(new int[]{3, 3});
                piece = new Piece(coord);
                piece.setColor("purple");
                piece.setSens("vertical");
                return piece;
            case "P7":
                coord.add(new int[]{3, 4});
                coord.add(new int[]{3, 5});
                piece = new Piece(coord);
                piece.setColor("brown");
                piece.setSens("horizontal");
                return piece;
            case "P8":
                coord.add(new int[]{3, 0});
                coord.add(new int[]{4, 0});
                piece = new Piece(coord);
                piece.setColor("black");
                piece.setSens("vertical");
                return piece;
            case "P9":
                coord.add(new int[]{4, 1});
                coord.add(new int[]{4, 2});
                piece = new Piece(coord);
                piece.setColor("lightgrey");
                piece.setSens("horizontal");
                return piece;
            case "P10":
                coord.add(new int[]{4, 3});
                coord.add(new int[]{5, 3});
                piece = new Piece(coord);
                piece.setColor("cyan");
                piece.setSens("vertical");
                return piece;
            case "P11":
                coord.add(new int[]{4, 4});
                coord.add(new int[]{4, 5});
                piece = new Piece(coord);
                piece.setColor("indigo");
                piece.setSens("horizontal");
                return piece;

            // BLOKUS

            case "B1":
                coord.add(new int[]{0, 0});
                piece = new Piece(coord);
                piece.setPivot(coord.get(0));
                return piece;
            case "B2":
                coord.add(new int[]{0, 0});
                coord.add(new int[]{0, 1});
                piece = new Piece(coord);
                piece.setPivot(coord.get(0));
                return piece;
            case "B3":
                coord.add(new int[]{0, 0});
                coord.add(new int[]{0, 1});
                coord.add(new int[]{1, 1});
                piece = new Piece(coord);
                piece.setPivot(coord.get(0));
                return piece;
            case "B4":
                coord.add(new int[]{0, 0});
                coord.add(new int[]{0, 1});
                coord.add(new int[]{0, 2});
                piece = new Piece(coord);
                piece.setPivot(coord.get(0));
                return piece;
            /*case "B5":
                coord.add(new int[]{0, 0});
                piece = new Piece(coord);
                return piece;
            case "B6":
                coord.add(new int[]{0, 0});
                piece = new Piece(coord);
                return piece;
            case "B7":
                coord.add(new int[]{0, 0});
                piece = new Piece(coord);
                return piece;
            case "B8":
                coord.add(new int[]{0, 0});
                piece = new Piece(coord);
                return piece;
            case "B9":
                coord.add(new int[]{0, 0});
                piece = new Piece(coord);
                return piece;
            case "B10":
                coord.add(new int[]{0, 0});
                piece = new Piece(coord);
                return piece;
            case "B11":
                coord.add(new int[]{0, 0});
                piece = new Piece(coord);
                return piece;
            case "B12":
                coord.add(new int[]{0, 0});
                piece = new Piece(coord);
                return piece;
            case "B13":
                coord.add(new int[]{0, 0});
                piece = new Piece(coord);
                return piece;
            case "B14":
                coord.add(new int[]{0, 0});
                piece = new Piece(coord);
                return piece;
            case "B15":
                coord.add(new int[]{0, 0});
                piece = new Piece(coord);
                return piece;
            case "B16":
                coord.add(new int[]{0, 0});
                piece = new Piece(coord);
                return piece;*/
        }
        return null;
    }

}
