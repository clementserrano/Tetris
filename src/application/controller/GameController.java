package application.controller;

import application.model.Piece;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * Classe abstraite contenant les attributs et méthodes communs à tous les contrôleurs de jeux
 */
public abstract class GameController {

    /**
     * Grille JavaFX représentant la matrice du jeu
     */
    @FXML
    protected GridPane gridView;

    /**
     * Labels de la grille représentant les cases du jeu
     */
    protected Label[][] labels;

    /**
     * Initialise la vue avec les valeurs du modèle
     */
    public abstract void init();

    /**
     * Met à jour la vue en fonction du modèle
     */
    public abstract void update();

    /**
     * Gestion de la souris
     *
     * @param i
     * @param j
     */
    public abstract void handleMouseClicked(int i, int j, boolean inHand);


    /**
     * Remplit le GridPane de labels sur une matrice correspondant aux tailles de la grille,
     * puis remplit une matrice de labels des labels créés.
     * Version sans événement.
     *
     * @param gridView
     * @param grid
     * @param labels
     */
    protected void fillGrid(GridPane gridView, Piece[][] grid, Label[][] labels) {

        double height = gridView.getPrefHeight() / grid.length;
        double width = gridView.getPrefWidth() / grid[0].length;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
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
    }

    /**
     * Remplit le GridPane de labels sur une matrice correspondant aux tailles de la grille,
     * puis remplit une matrice de labels des labels créés
     * Version avec événement (clic sur label)
     *
     * @param gridView
     * @param grid
     * @param labels
     */
    protected void fillGridEvent(GridPane gridView, Piece[][] grid, Label[][] labels, boolean inHand) {

        double height = gridView.getPrefHeight() / grid.length;
        double width = gridView.getPrefWidth() / grid[0].length;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Label label = new Label();
                label.setPrefHeight(height);
                label.setPrefWidth(width);
                label.setAlignment(Pos.CENTER);

                gridView.setRowIndex(label, i);
                gridView.setColumnIndex(label, j);
                gridView.getChildren().add(label);

                // Permet la gestion des clics de la souris
                label.addEventHandler(MouseEvent.MOUSE_CLICKED, new LabelClickedHandler(i, j, this, inHand));

                labels[i][j] = label;
            }
        }
    }

    /**
     * Met à jour la couleur des labels par rapport à la grille
     *
     * @param grid
     * @param labels
     */
    protected void updateGrid(Piece[][] grid, Label[][] labels) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != null) {
                    labels[i][j].setStyle(
                            "-fx-border-color:grey;-fx-background-color:" + grid[i][j].getColor() + ";");
                } else {
                    labels[i][j].setStyle("");
                }
            }
        }
    }
}
