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
        String teststr;
        int x = 4, y = 4;

        while (!G.equals(Gr)) {
            //System.out.println("Please select the tile you want to move :" + xi);
            Scanner sc = new Scanner(System.in);

            System.out.println("Please select the tile you want to move :");

            String str = String.valueOf(sc.nextLine());

            System.out.println("you selected the tile : " + str);

//            teststr = String.valueOf(G.tab[1][0]);
//            System.out.println("affichage de grid \"[1][0]\"" + teststr);

            scanningloop:
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (str.equalsIgnoreCase(String.valueOf(G.tab[i][j]))) {
                        if (G.ismovable(i, j)) {
                            System.out.println("test ismoveable ok");
                            G.Swap(i,j);
                            G.RefreshGrid();
                            break scanningloop;
                        }

                    }
                }
            }


        }

    }
}

