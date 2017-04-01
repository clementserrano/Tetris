package application.controller;

import application.Main;
import application.model.Blokus;
import application.model.Puzzle;
import application.model.Tetris;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Contrôleur du menu
 */
public class MenuController {

    @FXML
    MenuItem libraryItem;

    @FXML
    Menu jeuItem;

    @FXML
    MenuItem newGameItem;

    @FXML
    MenuItem pauseItem;

    @FXML
    MenuItem reprendreItem;

    /**
     * Application JavaFX
     */
    private Main main;

    /**
     * Met à jour le jeu en cours
     */
    public void updateMenu() {
        if (main.getCurrentGame() == null) {
            jeuItem.setVisible(false);
            libraryItem.setVisible(false);
        } else {
            jeuItem.setVisible(true);
            libraryItem.setVisible(true);

            if (!(main.getCurrentGame() instanceof Tetris)) {
                pauseItem.setVisible(false);
                reprendreItem.setVisible(false);
            }

        }
    }

    /**
     * Retourne à la librairie
     */
    @FXML
    public void handleLibrary() {
        if (main.getCurrentGame() instanceof Tetris) {
            Tetris tetris = (Tetris) main.getCurrentGame();
            tetris.getTimeline().stop();
        }
        main.showLirairie();
    }

    /**
     * Quitte l'application
     */
    @FXML
    public void handleQuit() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Lance une nouvelle partie
     */
    @FXML
    public void handleNewGame() {

        if (main.getCurrentGame() instanceof Tetris) {
            Tetris tetris = (Tetris) main.getCurrentGame();
            tetris.getTimeline().stop();
            main.showTetris();
        }

        if (main.getCurrentGame() instanceof Puzzle) {
            main.showPuzzle();
        }

        if (main.getCurrentGame() instanceof Blokus) {
            main.showBlokus();
        }
    }

    /**
     * Met le jeu en pause
     */
    @FXML
    public void handlePause() {
        Tetris tetris = (Tetris) main.getCurrentGame();
        tetris.getTimeline().stop();
    }

    /**
     * Reprend le jeu
     */
    @FXML
    public void handleReprendre() {
        Tetris tetris = (Tetris) main.getCurrentGame();
        tetris.run();
    }

    /**
     * Modifie la référence du Main
     *
     * @param main
     */
    public void setMain(Main main) {
        this.main = main;
    }
}
