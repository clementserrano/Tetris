package application.controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

/**
 * Created by serra on 24/03/2017.
 */
public class MenuController {

    @FXML
    MenuItem quitItem;

    @FXML
    MenuItem libraryItem;

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
     * Modifie la référence du Main
     *
     * @param main
     */
    public void setMain(Main main){
        this.main = main;
    }
}
