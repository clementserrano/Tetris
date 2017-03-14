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

	private String[] pieces = { "S", "Z", "L", "J", "T", "O", "I" };

	private TetrisController observer;

	public Tetris() {
		this.grid = new Piece[20][10];		

		int rnd = new Random().nextInt(pieces.length);
		this.nextPiece = PieceFactory.getPiece(pieces[rnd]); // Selection aleatoire de la piece
		
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
				ArrayList<int[]> newCoord = moveablePiece.toDown();

				if (checkPosition(newCoord, moveablePiece)) {
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
	
	public void handleKeyPressed(KeyCode keyCode){
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
			}

		if (checkPosition(newCoord,moveablePiece)) {
			changeCoord(moveablePiece,newCoord);
		}
		
		this.notifyObserver();
	}
	
	public void checkRow(){
		boolean lignePleine = false;
		for(int i = 0; i < grid.length;i++){
			lignePleine = true;
			for(int j = 0; j < grid[0].length && lignePleine; j++){
				// verif si piece unmoveable
			}
		}
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
	
	private boolean checkPosition(ArrayList<int[]> newCoord, Piece piece) {
		for(int[] coord : newCoord){
			if(coord[0] < 0 || coord[0] >= grid.length){
				return false;
			}
			if(coord[1] < 0 || coord[1] >= grid[0].length){
				return false;
			}
			if(grid[coord[0]][coord[1]] instanceof Piece && grid[coord[0]][coord[1]] != piece){
				return false;
			}
		}
		return true;
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
