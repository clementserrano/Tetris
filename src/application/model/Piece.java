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
            ArrayList<int[]> right = new ArrayList();
            for(int i = 0; i < 4; i++){
                right.add(new int[]{this.getCoord().get(i)[0], this.getCoord().get(i)[1] += 1});
            }
		return right;
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
