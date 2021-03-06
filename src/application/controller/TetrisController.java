package application.controller;

import application.model.Tetris;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Contrôleur du Tetris.
 */
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
        labels = new Label[game.getGrid().length][game.getGrid()[0].length];
        fillGrid(gridView, game.getGrid(), labels);

        // Configuration de l'affichage de la prochaine pièce
        labelsProchain = new Label[game.getGridProchain().length][game.getGridProchain()[0].length];
        fillGrid(gridViewProchain, game.getGridProchain(), labelsProchain);

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
            updateGrid(game.getGrid(), labels);
            updateGrid(game.getGridProchain(), labelsProchain);
            lbScore.setText(game.getScore());
            lbNiveau.setText(game.getNiveau());
        }
    }

    @Override
    public void handleMouseClicked(int i, int j, boolean inHand) {
        // Ne rien faire
    }

    public void setGame(Tetris game) {
        this.game = game;
    }
}
