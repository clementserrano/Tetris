package application;

import application.controller.LibrairieController;
import application.controller.MenuController;
import application.controller.TetrisController;
import application.model.Tetris;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * Classe lançant l'application
 * Initialise les modèles et contrôleurs et charge les vues
 */
public class Main extends Application {

    /**
     * Fenêtre de l'application
     */
    private Stage primaryStage;

    /**
     * Pane principale de l'application, lors d'un changement de jeu,
     * seul le Pane au centre du rootLayout sera changé
     */
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        try {

            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("Librairie");


            // Permet l'arrêt du programme lorsque la fenêtre est quitée
            this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    Platform.exit();
                    System.exit(0);
                }
            });

            // Chargement du rootLayout
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Affichage du rootLayout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Affiche le menu des options
            showMenu();

            // Affiche le menu de librairie
            showLirairie();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge et affiche le menu des options de l'application
     */
    public void showMenu() {
        try {

            // Charge la librairie
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Menu.fxml"));
            AnchorPane menu = (AnchorPane) loader.load();

            // Récupère le contrôleur et lui passe la référence du Main
            MenuController controller = loader.getController();
            controller.setMain(this);

            // Affiche la librairie
            rootLayout.setCenter(menu);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge et affiche le menu de librairie proposant à l'utilisateur de choisir le jeu à lancer
     */
    public void showLirairie() {
        try {

            // Charge la librairie
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Librairie.fxml"));
            AnchorPane librairie = (AnchorPane) loader.load();

            // Récupère le contrôleur et lui passe la référence du Main
            LibrairieController controller = loader.getController();
            controller.setMain(this);

            // Affiche la librairie
            rootLayout.setCenter(librairie);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge, affiche et lance le jeu Tetris
     */
    public void showTetris() {
        try {

            // Charge la vue Tetris
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Tetris.fxml"));
            AnchorPane tetris = (AnchorPane) loader.load();

            // Créer le jeu Tetris
            Tetris game = new Tetris();

            // Récupère le contrôleur de la vue et lui passe les référence du Main et du jeu Tetris
            TetrisController controller = loader.getController();
            controller.setMain(this);
            controller.setGame(game);

            // Affiche le Tetris
            rootLayout.setCenter(tetris);

            // Redimensionne la fenêtre afin de correspondre à la taille de la vue Tetris
            this.setSize(tetris);

            // Initialise les champs de la vue en fonction du jeu Tetris
            controller.init();

            // Permet la gestion des touches pressées du clavier
            primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

                // Le jeu Tetris reçoit le code de la touche pressée et gère son comportement
                @Override
                public void handle(KeyEvent event) {
                    game.handleKeyPressed(event.getCode());
                }

            });

            // Lance le jeu Tetris
            game.run();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge, affiche et lance le jeu Blokus
     */
    public void showBlokus() {
        try {

            // Charge la vue Blokus
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Blokus.fxml"));
            AnchorPane blokus = (AnchorPane) loader.load();

            // Redimensionne la fenêtre afin de correspondre à la taille de la vue Blokus
            this.setSize(blokus);

            // Affiche le Blokus
            rootLayout.setCenter(blokus);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge, affiche et lance le jeu Puzzle
     */
    public void showPuzzle() {
        try {

            // Charge la vue Puzzle
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Puzzle.fxml"));
            AnchorPane puzzle = (AnchorPane) loader.load();

            // Redimensionne la fenêtre afin de correspondre à la taille de la vue Puzzle
            this.setSize(puzzle);

            // Affiche le Blokus
            rootLayout.setCenter(puzzle);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Redimensionne la fenêtre afin de correspondre à la taille du Pane entré en paramètre
     *
     * @param pane
     */
    public void setSize(AnchorPane pane) {

        // Redimensionne la fenêtre en rajoutant de la hauteur correspondant à la barre de titre
        primaryStage.setHeight(pane.getPrefHeight() + 50);
        primaryStage.setWidth(pane.getPrefWidth());

        // Repositionne la fenêtre au centre de l'écran
        primaryStage.centerOnScreen();
    }

    /**
     * Retourne la fenêtre
     *
     * @return un Stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Appelle simplement la fonction JavaFX launch() lançant l'application JavaFX
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
