package application.model;

import java.util.ArrayList;

public class PieceFactory {

	public static Piece getPiece(String name) {

		ArrayList<int[]> coord = new ArrayList<int[]>();
		Piece piece;
		switch (name) {
		case "S":
			coord.add(new int[]{0,2});
			coord.add(new int[]{0,1});
			coord.add(new int[]{1,1});
			coord.add(new int[]{1,0});
			piece = new Piece(coord);
			piece.setCaseCentrale(coord.get(1));
			return piece;
		case "Z":
			coord.add(new int[]{0,0});
			coord.add(new int[]{0,1});
			coord.add(new int[]{1,1});
			coord.add(new int[]{1,2});
			piece = new Piece(coord);
			piece.setCaseCentrale(coord.get(1));
			return piece;
		case "J":
			coord.add(new int[]{0,1});
			coord.add(new int[]{0,2});
			coord.add(new int[]{0,3});
			coord.add(new int[]{1,3});
			piece = new Piece(coord);
			piece.setCaseCentrale(coord.get(2));
			return piece;
		case "L":
			coord.add(new int[]{0,0});
			coord.add(new int[]{1,0});
			coord.add(new int[]{2,0});
			coord.add(new int[]{2,1});
			piece = new Piece(coord);
			piece.setCaseCentrale(coord.get(2));
			return piece;
		case "O":
			coord.add(new int[]{0,0});
			coord.add(new int[]{0,1});
			coord.add(new int[]{1,0});
			coord.add(new int[]{1,1});
			piece = new Piece(coord);
			piece.setCaseCentrale(coord.get(1));
			return piece;
		case "I":
			coord.add(new int[]{0,0});
			coord.add(new int[]{1,0});
			coord.add(new int[]{2,0});
			coord.add(new int[]{3,0});
			piece = new Piece(coord);
			piece.setCaseCentrale(coord.get(1));
			return piece;
		case "T":
			coord.add(new int[]{0,0});
			coord.add(new int[]{1,0});
			coord.add(new int[]{2,0});
			coord.add(new int[]{1,1});
			piece = new Piece(coord);
			piece.setCaseCentrale(coord.get(3));
			return piece;
		}
		return null;
	}

}
