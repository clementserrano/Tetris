package application.model;

import java.util.ArrayList;
import java.util.Random;

public class Tetris {

	private int[][] grid;

	private Piece nextPiece;

	private Piece moveablePiece;

	private ArrayList<Piece> unmoveablePiece;

	private String keyPressed;

	private String[] pieces = { "S", "Z", "L", "J", "T", "O", "I" };

	public Tetris() {
		this.grid = new int[20][10];

		int rnd = new Random().nextInt(pieces.length);
		this.nextPiece = PieceFactory.getPiece(pieces[rnd]);

		rnd = new Random().nextInt(pieces.length);
		this.moveablePiece = PieceFactory.getPiece(pieces[rnd]);

		this.unmoveablePiece = new ArrayList<Piece>();
	}

	public void run() {

		ArrayList<int[]> newCoord = moveablePiece.getCoord();

		switch (keyPressed) {
		case "UP":
			newCoord = moveablePiece.rotate();
			break;
		case "LEFT":
			newCoord = moveablePiece.toLeft();
			break;
		case "RIGHT":
			newCoord = moveablePiece.toRight();
			break;
		}

		if (!keyPressed.equals("") && checkPosition(newCoord)) {
			moveablePiece.setCoord(newCoord);
		}

		keyPressed = "";

		newCoord = moveablePiece.toDown();

		if (checkPosition(newCoord)) {
			moveablePiece.setCoord(newCoord);
		} else {
			unmoveablePiece.add(moveablePiece);
			moveablePiece = nextPiece;

			int rnd = new Random().nextInt(pieces.length);
			nextPiece = PieceFactory.getPiece(pieces[rnd]);
		}

	}

	private boolean checkPosition(ArrayList<int[]> coord) {
		return true;
	}

	public void setKeyPressed(String keyPressed) {
		this.keyPressed = keyPressed;
	}

}
