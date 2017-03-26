package application.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Created by Lenovo on 26/03/2017.
 */
public class LabelClickedHandler implements EventHandler<MouseEvent> {

    /**
     * Ligne du label
     */
    private int i;

    /**
     * Colonne du label
     */
    private int j;

    /**
     * Contrôleur du Puzzle
     */
    private PuzzleController controller;

    /**
     * Construit l'écouteur avec les coordonnées du label et le contrôleur du Puzzle
     *
     * @param i
     * @param j
     */
    public LabelClickedHandler(int i, int j, PuzzleController controller){
        this.i=i;
        this.j=j;
        this.controller=controller;
    }

    @Override
    public void handle(MouseEvent event) {
        controller.handleMouseClicked(i,j);
    }
}
