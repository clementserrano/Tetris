package application;

import java.io.IOException;

import application.controller.LibrairieController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Librairie");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			showLirairie();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showLirairie() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/Librairie.fxml"));
			AnchorPane librairie = (AnchorPane) loader.load();

			LibrairieController controller = loader.getController();
			controller.setMain(this);

			rootLayout.setCenter(librairie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showTetris() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/Tetris.fxml"));
			AnchorPane tetris = (AnchorPane) loader.load();
			
			primaryStage.setHeight(tetris.getPrefHeight());
			primaryStage.setWidth(tetris.getPrefWidth());

			rootLayout.setCenter(tetris);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showBlokus() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/Blokus.fxml"));
			AnchorPane blokus = (AnchorPane) loader.load();
			
			primaryStage.setHeight(blokus.getPrefHeight());
			primaryStage.setWidth(blokus.getPrefWidth());

			rootLayout.setCenter(blokus);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showPuzzle() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/Puzzle.fxml"));
			AnchorPane puzzle = (AnchorPane) loader.load();
			
			primaryStage.setHeight(puzzle.getPrefHeight());
			primaryStage.setWidth(puzzle.getPrefWidth());

			rootLayout.setCenter(puzzle);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
