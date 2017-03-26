package application.controller;

import application.Main;
import application.model.Blokus;
import application.model.Game;
import application.model.Puzzle;
import application.model.Tetris;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Created by clementserrano on 24/03/2017.
 */
public class MenuController {

    @FXML
    MenuItem libraryItem;

    @FXML
    Menu jeuItem;

    /**
     * Application JavaFX
     */
    private Main main;

    /**
     * Met à jour le jeu en cours
     */
    public void updateMenu(){
        if(main.getCurrentGame() == null){
            jeuItem.setVisible(false);
            libraryItem.setVisible(false);
        }else{
            jeuItem.setVisible(true);
            libraryItem.setVisible(true);
        }
    }

    /**
     * Retourne à la librairie
     */
    @FXML
    public void handleLibrary(){
        main.getCurrentGame().getTimeline().stop();
        main.showLirairie();
    }

    /**
     * Quitte l'application
     */
    @FXML
    public void handleQuit(){
        Platform.exit();
        System.exit(0);
    }

    /**
     * Lance une nouvelle partie
     */
    @FXML
    public void handleNewGame(){
        main.getCurrentGame().getTimeline().stop();

        if(main.getCurrentGame() instanceof Tetris){
            main.showTetris();
        }

        if(main.getCurrentGame() instanceof Puzzle){
            main.showPuzzle();
        }

        if(main.getCurrentGame() instanceof Blokus){
            main.showBlokus();
        }
    }

    /**
     * Met le jeu en pause
     */
    @FXML
    public void handlePause(){
        main.getCurrentGame().getTimeline().stop();
    }

    /**
     * Reprend le jeu
     */
    @FXML
    public void handleReprendre(){
        main.getCurrentGame().run();
    }

    /**
     * Modifie la référence du Main
     *
     * @param main
     */
    public void setMain(Main main){
        this.main = main;
    }
}
