package application.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import application.controller.TetrisController;
import javafx.scene.input.KeyCode;

public class Tetris {
	/**
	 * Matrice de la grille
	 */
	private Piece[][] grid;

	private Piece nextPiece;

	private Piece moveablePiece;

	private ArrayList<Piece> unmoveablePiece;

	private KeyCode keyPressed;

	private String[] pieces = { "S", "Z", "L", "J", "T", "O", "I" };

	private TetrisController observer;

	public Tetris() {
		this.grid = new Piece[20][10];		

		int rnd = new Random().nextInt(pieces.length);
		this.nextPiece = PieceFactory.getPiece(pieces[rnd]); // Selection aléatoire de la pièce
		
		rnd = new Random().nextInt(pieces.length);
		this.moveablePiece = PieceFactory.getPiece(pieces[rnd]);
		
		for(int[] coord : moveablePiece.getCoord()){
			grid[coord[0]][coord[1]] = moveablePiece;
		}		
		
		this.unmoveablePiece = new ArrayList<Piece>();		
	}

	public void run() {

		Timer timer = new Timer();
		
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				ArrayList<int[]> newCoord = moveablePiece.getCoord();

				/*switch (keyPressed) {
				case UP:
					newCoord = moveablePiece.rotate();
					break;
				case LEFT:
					newCoord = moveablePiece.toLeft();
					break;
				case RIGHT:
					newCoord = moveablePiece.toRight();
					break;
				}

				if (!keyPressed.equals("") && checkPosition(newCoord)) {
					changeCoord(moveablePiece,newCoord);
				}

				keyPressed = null;*/

				newCoord = moveablePiece.toDown();

				if (checkPosition(newCoord)) {
					changeCoord(moveablePiece,newCoord);
				} else {
					unmoveablePiece.add(moveablePiece);
					moveablePiece = nextPiece;

					int rnd = new Random().nextInt(pieces.length);
					nextPiece = PieceFactory.getPiece(pieces[rnd]);
				}
				
				notifyObserver();
			}
			
		},1000,1000);
	}

	private void changeCoord(Piece piece, ArrayList<int[]> coords){
		for(int[] coord : piece.getCoord()){
			grid[coord[0]][coord[1]] = null;
		}
		
		for(int[] coord : coords){
			grid[coord[0]][coord[1]] = piece;
		}
		
		piece.setCoord(coords);
	}
	
	private boolean checkPosition(ArrayList<int[]> coord) {
		return true;
	}

	public void setKeyPressed(KeyCode keyPressed) {
		this.keyPressed = keyPressed;
	}

	public void setObserver(TetrisController tetrisController) {
		this.observer = tetrisController;
	}

	public void notifyObserver() {
		observer.update();
	}
	
	public Piece[][] getGrid(){
		return grid;
	}
}
