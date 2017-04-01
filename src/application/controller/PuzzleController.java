package application.controller;

import application.model.Piece;
import application.model.Puzzle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Contrôleur du Puzzle
 */
public class PuzzleController extends GameController implements Initializable {

    /**
     * Modèle du jeu Puzzle
     */
    private Puzzle game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert gridView != null : "fx:id=\"gridView\" was not injected: check your FXML file 'Puzzle.fxml'.";
    }

    @Override
    public void init() {
        // Passe la référence du contrôleur au modèle
        game.setObserver(this);

        // Configuration de la grille de jeu
        gridView.setStyle("-fx-border-color:grey");
        labels = new Label[game.getGrid().length][game.getGrid()[0].length];
        fillGridEvent(gridView, game.getGrid(), labels, false);

        this.update();
    }

    @Override
    public void update() {
        if (game.isGameWon()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Puzzle");
            alert.setHeaderText("BRAVO !!");
            alert.show();
        } else {
            for (int i = 0; i < game.getGrid().length; i++) {
                for (int j = 0; j < game.getGrid()[0].length; j++) {
                    if (game.getGrid()[i][j] != null) {
                        labels[i][j].setStyle(
                                "-fx-background-color:" + game.getGrid()[i][j].getColor() + ";");
                    } else {
                        labels[i][j].setStyle("");
                    }
                }
            }
        }
    }

    /**
     * Gestion des clics de la souris
     *
     * @param i ligne du label cliqué
     * @param j colonne du label cliqué
     */
    public void handleMouseClicked(int i, int j, boolean inHand) {

        Piece piece = game.getGrid()[i][j];
        if (piece != null) game.handleMouseClicked(piece);

    }

    public void setGame(Puzzle game) {
        this.game = game;
    }

}
