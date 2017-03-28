package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.Blokus;
import application.model.Piece;
import application.model.Puzzle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class BlokusController extends GameController implements Initializable{

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

                // Permet la gestion des clics de la souris
                label.addEventHandler(MouseEvent.MOUSE_CLICKED, new LabelClickedHandler(i, j, this,false));

                labels[i][j] = label;
            }
        }

        // Configuration de la main

        gridMain.setStyle("-fx-border-color:grey");

        height = gridMain.getPrefHeight() / game.getGridMain().length;
        width = gridMain.getPrefWidth() / game.getGridMain()[0].length;

        labelsMain = new Label[game.getGridMain().length][game.getGridMain()[0].length];

        for (int i = 0; i < game.getGridMain().length; i++) {
            for (int j = 0; j < game.getGridMain()[0].length; j++) {
                Label label = new Label();
                label.setPrefHeight(height);
                label.setPrefWidth(width);
                label.setAlignment(Pos.CENTER);

                gridMain.setRowIndex(label, i);
                gridMain.setColumnIndex(label, j);
                gridMain.getChildren().add(label);

                // Permet la gestion des clics de la souris
                label.addEventHandler(MouseEvent.MOUSE_CLICKED, new LabelClickedHandler(i, j, this,true));

                labelsMain[i][j] = label;
            }
        }

        this.update();
    }

    @Override
    public void update() {
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

        for (int i = 0; i < game.getGridMain().length; i++) {
            for (int j = 0; j < game.getGridMain()[0].length; j++) {
                if (game.getGridMain()[i][j] != null) {
                    labelsMain[i][j].setStyle(
                            "-fx-border-color:grey;-fx-background-color:" + game.getGridMain()[i][j].getColor() + ";");
                } else {
                    labelsMain[i][j].setStyle("");
                }
            }
        }
    }

    public void setGame(Blokus game) {
        this.game = game;
    }

    public void handleMouseClicked(int i, int j, boolean inHand) {
        Piece p = null;
        if(inHand){
            p = game.getGridMain()[i][j];
        }
        game.handleMouseClicked(p,i,j,inHand);
    }
}
