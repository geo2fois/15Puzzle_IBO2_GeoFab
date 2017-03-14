import java.util.Scanner;
import Gridpack.Grid;

/**
 * Created by geoff on 31/01/2017.
 */


public class MAIN {

    public static void main(String[] args) {

        System.out.println("Here is your reference Grid :");
        Gridref Gr = new Gridref();

        System.out.println("Here is your gaming Grid :");
        Grid G = new Grid();
        SwingGUI SGUI = new SwingGUI(G);

        int x = 4, y = 4;

        while (!Over(G, Gr)) {

            Scanner sc = new Scanner(System.in);
            System.out.println("");
            System.out.println("Please select the Tile you want to move");

            String str = String.valueOf(sc.nextLine());

            System.out.println("You selected the Tile : " + str);

            scanningloop:
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (str.equalsIgnoreCase(String.valueOf(G.tab[i][j]))) {
                        if (G.ismovable(i, j)) {
                            System.out.println("The Tile " + String.valueOf(G.tab[i][j]) + " is movable");
                            G.Swap(i,j);
                            G.RefreshGrid();
                            SGUI.GUIupdating(G);
                            break scanningloop;
                        }
                        if(!G.ismovable(i, j)){
                            System.out.println("The Tile " + String.valueOf(G.tab[i][j]) + " is not movable");
                            System.out.println("    ===> Please select another Tile");
                        }
                    }
                }
            }


        }
        System.out.println("      ======================      ");
        System.out.println("    CONGRATULATIONS!! YOU WON     ");
        System.out.println("      ======================      ");
    }

    public static boolean Over(Grid G, Gridref Gr) { // Effectue le swap entre la tile sélectionnée et la tile 0
        int x = 4, y = 4, cpt = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(G.tab[i][j] == Gr.tab[i][j]){
                    cpt = cpt +1;
                }
            }
        }
        if (cpt == 16){
            return true;
        }
        else return false;
    }

}

