package application.controller;

import application.model.Blokus;
import application.model.Piece;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Contrôleur du Blokus
 */
public class BlokusController extends GameController implements Initializable {

    /**
     * Modèle du jeu Blokus
     */
    private Blokus game;

    private Label[][] labelsMain;

    @FXML
    private GridPane gridMain;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert gridView != null : "fx:id=\"gridView\" was not injected: check your FXML file 'Blokus.fxml'.";
        assert gridMain != null : "fx:id=\"gridMain\" was not injected: check your FXML file 'Blokus.fxml'.";
    }

    @Override
    public void init() {
        // Passe la référence du contrôleur au modèle
        game.setObserver(this);

        // Configuration de la grille de jeu
        gridView.setStyle("-fx-border-color:grey");
        labels = new Label[game.getGrid().length][game.getGrid()[0].length];
        fillGridEvent(gridView, game.getGrid(), labels, false);


        // Configuration de la main
        gridMain.setStyle("-fx-border-color:grey");
        labelsMain = new Label[game.getGridMain().length][game.getGridMain()[0].length];
        fillGridEvent(gridMain, game.getGridMain(), labelsMain, true);

        this.update();
    }

    @Override
    public void update() {
        updateGrid(game.getGrid(), labels);
        updateGrid(game.getGridMain(), labelsMain);
    }

    public void setGame(Blokus game) {
        this.game = game;
    }

    public void handleMouseClicked(int i, int j, boolean inHand) {
        Piece p = null;
        if (inHand) {
            p = game.getGridMain()[i][j];
        }
        game.handleMouseClicked(p, i, j, inHand);
    }
}
