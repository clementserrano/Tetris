package application.model;

import java.util.ArrayList;

public class Piece {
	
	private ArrayList<int[]> coord;
	/**
	 * Cette case permettra la rotation des pièces
	 */
	private int[] caseCentrale;
	
	public Piece(ArrayList<int[]> coord){
		this.setCoord(coord);
	}
	
	public ArrayList<int[]> toLeft(){
		return null;
	}
	
	public ArrayList<int[]> toRight(){
		return null;
	}
	
	public ArrayList<int[]> toDown(){
		return null;
	}
	
	public ArrayList<int[]> rotate(){
		return null;
	}

	public ArrayList<int[]> getCoord() {
		return coord;
	}

	public void setCoord(ArrayList<int[]> coord) {
		this.coord = coord;
	}
	
	public void setCaseCentrale(int [] caseCentrale){
		this.caseCentrale = caseCentrale;
	}

}
