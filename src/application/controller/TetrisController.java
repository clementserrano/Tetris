package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.Tetris;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TetrisController extends GameController implements Initializable {

    /**
     * Grille affichant la prochaine Piece
     */
	@FXML
	private GridPane gridViewProchain;

    /**
     * Label du score
     */
	@FXML
	private Label lbScore;

    /**
     * Label du niveau courant
     */
	@FXML
	private Label lbNiveau;

    /**
     * Labels de la grille de la prochaine Piece
     */
	private Label[][] labelsProchain;

    /**
     * Modèle du jeu Tetris
     */
	private Tetris game;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert gridView != null : "fx:id=\"gridView\" was not injected: check your FXML file 'Tetris.fxml'.";
		assert gridViewProchain != null : "fx:id=\"gridViewProchain\" was not injected: check your FXML file 'Tetris.fxml'.";
		assert lbScore != null : "fx:id=\"lbScore\" was not injected: check your FXML file 'Tetris.fxml'.";
		assert lbNiveau != null : "fx:id=\"lbNiveau\" was not injected: check your FXML file 'Tetris.fxml'.";
	}

    @Override
	public void init() {

	    // Passe la référence du contrôleur au modèle
	    game.setObserver(this);

		// Configuration de la grille de jeu

		gridView.setStyle("-fx-border-color:grey");

		double height = gridView.getPrefHeight() / game.getGrid().length;
		double width = gridView.getPrefWidth() / game.getGrid()[0].length;

		labels = new Label[game.getGrid().length][game.getGrid()[0].length];

		for (int i = 0; i < game.getGrid().length; i++) {
			for (int j = 0; j < game.getGrid()[0].length; j++) {
				Label label = new Label();
				label.setPrefHeight(height);
				label.setPrefWidth(width);
				label.setAlignment(Pos.CENTER);

				gridView.setRowIndex(label, i);
				gridView.setColumnIndex(label, j);
				gridView.getChildren().add(label);

				labels[i][j] = label;
			}
		}

		// Configuration de l'affichage de la prochaine pièce


		height = gridViewProchain.getPrefHeight() / game.getGridProchain().length;
		width = gridViewProchain.getPrefWidth() / game.getGridProchain()[0].length;

		labelsProchain = new Label[game.getGridProchain().length][game.getGridProchain()[0].length];

		for (int i = 0; i < game.getGridProchain().length; i++) {
			for (int j = 0; j < game.getGridProchain()[0].length; j++) {
				Label label = new Label();
				label.setPrefHeight(height);
				label.setPrefWidth(width);
				label.setAlignment(Pos.CENTER);

				gridViewProchain.setRowIndex(label, i);
				gridViewProchain.setColumnIndex(label, j);
				gridViewProchain.getChildren().add(label);

				labelsProchain[i][j] = label;
			}
		}

		this.update();
	}

    @Override
	public void update() {

		if (game.isGameOver()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Tetris");
			alert.setHeaderText("GAME OVER");
			alert.show();
		} else {
			for (int i = 0; i < game.getGrid().length; i++) {
				for (int j = 0; j < game.getGrid()[0].length; j++) {
					if (game.getGrid()[i][j] != null) {
						labels[i][j].setStyle(
								"-fx-border-color:grey;-fx-background-color:" + game.getGrid()[i][j].getColor() + ";");
					} else {
						labels[i][j].setStyle("");
					}
				}
			}

			for (int i = 0; i < game.getGridProchain().length; i++) {
				for (int j = 0; j < game.getGridProchain()[0].length; j++) {
					if (game.getGridProchain()[i][j] != null) {
						labelsProchain[i][j].setStyle("-fx-border-color:grey;-fx-background-color:"
								+ game.getGridProchain()[i][j].getColor() + ";");
					} else {
						labelsProchain[i][j].setStyle("");
					}
				}
			}

			lbScore.setText(game.getScore());

			lbNiveau.setText(game.getNiveau());
		}
	}

	@Override
	public void handleMouseClicked(int i, int j, boolean inHand) {
		// do nothing
	}

	public void setGame(Tetris game) {
		this.game = game;
	}
}
