
public class Gridref{

    int x = 4, y = 4;   // Gère la taille du plateau
    int cpt = 0;

    int temp[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
    int tab[][] = new int[x][y];    // Création du tableau du plateau de reference du jeu

    Gridref() {
        for (int i = 0; i < x; i++) {   // remplissage lignes
            for (int j = 0; j < y; j++) {   // remplissage colonnes
                tab[i][j] = temp[cpt];
                cpt = cpt + 1;
                System.out.print(" || " + String.format("%4d", tab[i][j]));
                if (j == (y - 1)) {
                    System.out.print(" || ");
                }
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {

        new Gridref();
    }
}
