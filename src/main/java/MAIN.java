import java.util.Scanner;

/**
 * Created by geoff on 31/01/2017.
 */


public class MAIN {

    public static void main(String[] args) {

        System.out.println("Here is your reference Grid :");
        Gridref Gr = new Gridref();

        System.out.println("Here is your gaming Grid :");
        Grid G = new Grid();

        while (!G.equals(Gr)){
            Scanner sc = new Scanner(System.in);

            System.out.println("Please select the tile you want to move :");

            String str = sc.nextLine();

            System.out.println("you selected the tile : " + str);
        }
    }
}
