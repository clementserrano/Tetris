package application.model;

import application.controller.GameController;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;

public abstract class Game {
	
	protected Piece[][] grid;
	
	protected GameController observer;
	
	protected Timeline timeline;
	
	public abstract void run();
	
	protected abstract void logic();
	
	public abstract void handleKeyPressed(KeyCode keyCode);
	
	public void notifyObserver(){
		observer.update();
	}
	
	public Piece[][] getGrid() {
		return grid;
	}
	
	public void setObserver(GameController controller) {
		this.observer = controller;
	}

}
