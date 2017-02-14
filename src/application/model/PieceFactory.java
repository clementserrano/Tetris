package application.model;

public class PieceFactory {

	public static Piece getPiece(String name) {
		switch (name) {
		case "S":
			return new Piece(null);
		case "Z":
			return new Piece(null);
		case "J":
			return new Piece(null);
		case "L":
			return new Piece(null);
		case "O":
			return new Piece(null);
		case "I":
			return new Piece(null);
		case "T":
			return new Piece(null);
		}
		return null;
	}

}
