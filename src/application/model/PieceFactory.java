package application.model;

import java.util.ArrayList;

public class PieceFactory {

	public static Piece getPiece(String name) {

		ArrayList<int[]> coord = new ArrayList<int[]>();
		switch (name) {
		case "S":
			coord.add(new int[]{0,2});
			coord.add(new int[]{0,1});
			coord.add(new int[]{1,1});
			coord.add(new int[]{1,0});
			return new Piece(coord);
		case "Z":
			coord.add(new int[]{0,0});
			coord.add(new int[]{0,1});
			coord.add(new int[]{1,1});
			coord.add(new int[]{1,2});
			return new Piece(coord);
		case "J":
			coord.add(new int[]{0,1});
			coord.add(new int[]{0,2});
			coord.add(new int[]{0,3});
			coord.add(new int[]{1,3});
			return new Piece(coord);
		case "L":
			coord.add(new int[]{0,0});
			coord.add(new int[]{1,0});
			coord.add(new int[]{2,0});
			coord.add(new int[]{2,1});
			return new Piece(coord);
		case "O":
			coord.add(new int[]{0,0});
			coord.add(new int[]{0,1});
			coord.add(new int[]{1,0});
			coord.add(new int[]{1,1});
			return new Piece(coord);
		case "I":
			coord.add(new int[]{0,0});
			coord.add(new int[]{1,0});
			coord.add(new int[]{2,0});
			coord.add(new int[]{3,0});
			return new Piece(coord);
		case "T":
			coord.add(new int[]{0,0});
			coord.add(new int[]{1,0});
			coord.add(new int[]{2,0});
			coord.add(new int[]{1,1});
			return new Piece(coord);
		}
		return null;
	}

}
