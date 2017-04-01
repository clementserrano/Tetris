package application.model;

import java.util.ArrayList;

/**
 * Piece contenant les coordonées d'une pièce et les méthode de transformations de ses coordonnées.
 * Contient également la couleur de la pièce, utilisée ensuite par la vue.
 */
public class Piece {

    /**
     * Liste des coordonnées de la pièce
     */
    private ArrayList<int[]> coord;

    /**
     * Cette case permettra la rotation des pièces
     */
    private int[] pivot;

    /**
     * Couleur de la pièce pour la vue
     */
    private String color;

    /**
     * Sens de la pièce
     */
    private String sens;


    /**
     * Construit une pièce avec les coordonnées fournies
     *
     * @param coord
     */
    public Piece(ArrayList<int[]> coord) {
        this.coord = coord;
    }

    /**
     * Retourne une liste des coordonnées dans le cas où la pièce est décalée vers la gauche.
     * Le pivot est présent une 2ème fois à la fin de la liste.
     *
     * @return une ArrayList de tableaux d'entier
     */
    public ArrayList<int[]> toLeft() {
        ArrayList<int[]> left = new ArrayList<int[]>();

        int[] newPivot = pivot;

        for (int[] cord : coord) {
            int[] newCoord = new int[]{cord[0], cord[1] - 1};

            left.add(newCoord);

            if (pivot == cord) {
                newPivot = newCoord;
            }
        }

        // Rajoute le pivot à la fin
        if (newPivot != null) left.add(newPivot);

        return left;
    }

    /**
     * Retourne une liste des coordonnées dans le cas où la pièce est décalée vers la droite.
     * Le pivot est présent une 2ème fois à la fin de la liste.
     *
     * @return une ArrayList de tableaux d'entier
     */
    public ArrayList<int[]> toRight() {
        ArrayList<int[]> right = new ArrayList<int[]>();

        int[] newPivot = pivot;

        for (int[] cord : coord) {
            int[] newCoord = new int[]{cord[0], cord[1] + 1};

            right.add(newCoord);

            if (pivot == cord) {
                newPivot = newCoord;
            }
        }

        // Rajoute le pivot à la fin
        if (newPivot != null) right.add(newPivot);

        return right;
    }

    /**
     * Retourne une liste des coordonnées dans le cas où la pièce est décalée vers le bas.
     * Le pivot est présent une 2ème fois à la fin de la liste.
     *
     * @return une ArrayList de tableaux d'entier
     */
    public ArrayList<int[]> toDown() {
        ArrayList<int[]> down = new ArrayList<int[]>();
        int[] newPivot = pivot;

        for (int[] cord : coord) {
            int[] newCoord = new int[]{cord[0] + 1, cord[1]};

            down.add(newCoord);

            if (pivot == cord) {
                newPivot = newCoord;
            }
        }

        // Rajoute le pivot à la fin
        if (newPivot != null) down.add(newPivot);

        return down;
    }

    /**
     * Retourne une liste des coordonnées dans le cas où la pièce est décalée vers le haut.
     *
     * @return une ArrayList de tableaux d'entier
     */
    public ArrayList<int[]> toUp() {
        ArrayList<int[]> up = new ArrayList<int[]>();

        for (int[] cord : coord) {
            int[] newCoord = new int[]{cord[0] - 1, cord[1]};

            up.add(newCoord);
        }

        return up;
    }

    /**
     * Retourne une liste des coordonnées dans le cas où la pièce est pivotée.
     * Le pivot est présent une 2ème fois à la fin de la liste.
     *
     * @return une ArrayList de tableaux d'entier
     */
    public ArrayList<int[]> rotate() {
        ArrayList<int[]> rotate = new ArrayList<int[]>();
        int[] newPivot = pivot;

        for (int[] cord : coord) {

            // Construit le vecteur corresdant à la différence entre les coordonnées de la pièce
            // et les coordonnées du pivot (xPiece-xPivot yPiece-yPivot) = (xV1 yV1)
            int[] vector = new int[]{cord[0] - pivot[0], cord[1] - pivot[1]};

            // Pivote ce vecteur à 90° vers la droite (-yV1 xV1) = (xV2 yV2)
            int[] vectorRotate = new int[]{-vector[1], vector[0]};

            // Construit les nouvelles coordonnées du point pivoté en effectuer l'addition des coordonnées
            // du pivot avec le vecteur précédemment calculé (xPivot+xv2 yPivot+yPivot) = (xPiece2 yPiece2)
            int[] newCoord = new int[]{pivot[0] + vectorRotate[0], pivot[1] + vectorRotate[1]};

            rotate.add(newCoord);

            if (pivot == cord) {
                newPivot = newCoord;
            }
        }

        // Rajoute le pivot à la fin
        if (newPivot != null) rotate.add(newPivot);

        return rotate;
    }

    /**
     * Déplace la pièce selon un vecteur
     *
     * @param vector
     * @return une ArrayList de tableaux d'entier
     */
    public ArrayList<int[]> move(int[] vector) {

        ArrayList<int[]> newCoord = new ArrayList<int[]>();

        for (int[] cord : coord) {
            newCoord.add(new int[]{cord[0] + vector[0], cord[1] + vector[1]});
        }

        if (pivot != null) {
            newCoord.add(new int[]{pivot[0] + vector[0], pivot[1] + vector[1]});
        }

        return newCoord;
    }

    /**
     * Retourne la liste des coordonnées de la pièce
     *
     * @return une ArrayList de tableaux d'entier
     */
    public ArrayList<int[]> getCoord() {
        return coord;
    }

    /**
     * Met à jour les coordonées de la pièce avec les coordonées entrées en paramètre.
     * Les dernières coordonnées de la liste entré correspondent aux coordonnées du nouveau pivot.
     *
     * @param coord une ArrayList de tableaux d'entier
     */
    public void setCoord(ArrayList<int[]> coord) {
        this.coord = coord;
        if (pivot != null) pivot = coord.remove(coord.size() - 1);
    }

    /**
     * Modifie le pivot
     *
     * @param pivot un tableau d'entiers
     */
    public void setPivot(int[] pivot) {
        this.pivot = pivot;
    }

    /**
     * Retourne la couleur de la pièce, nécessaire à la vue
     *
     * @return un String
     */
    public String getColor() {
        return color;
    }

    /**
     * Modifie la couleur
     *
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Retourne le sens de la pièce
     *
     * @return un String
     */
    public String getSens() {
        return sens;
    }

    /**
     * Modifie le sens de la pièce
     *
     * @param sens
     */
    public void setSens(String sens) {
        this.sens = sens;
    }
}
