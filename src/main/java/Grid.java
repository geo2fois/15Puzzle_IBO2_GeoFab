/**
 * Created by geoff on 18/10/2016.
 */

import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import java.util.Arrays;
// comment test push github
// La class Grid génère le plateau du Puzzle de dimension (x,y)

public class Grid {
    public static void main(String[] args) {
        int x = 4, y = 4;   // Gère la taille du plateau
        int nb, k;
        int cpt = 0;
        int Min= 0, Max=((x*y)-1);  // Borne des valeurs des pions

        int temp[] = new int[(x*y)];    // Création du tableau temporaire des valeurs aléatoire
        int tab[][] = new int[x][y];    // Création du tableau du plateau de jeu

        for(k=0; k<temp.length; k++ ){  // Initialisation du tableau temporaire (nécessaire pour la comparaison, lors du remplissage du tableau temporaire)
            temp[k]=(x*y)+1;
        }

        // Remplissage du tableau temporaire sans doublon

         while(cpt<=(x*y)){
             boolean Doublon = false;
             nb= Min + (int)(Math.random() * ((Max - Min) + 1));    // Nombre aléatoire

             for(k=0; k<temp.length; k++ ) {    // recherche si nb est un doublon
                 if (nb == temp[k]) {
                     Doublon = true;
                 }
             }

             if(Doublon == false){  // si nb n'est pas un doublon => remplissage du tableau temporaire
                 temp[cpt] = nb;
                 cpt = cpt + 1;
             }
             if(cpt==(x*y)){    // sortie du while lorsque tout les pions ont été générés
                 break;
             }
         }

        // Remplissage du tableau et affichage de la grille de jeu à partir du tableau temporaire

        cpt = 0;

        for (int i = 0; i < x; i++) {   // remplissage lignes
            for (int j = 0; j < y; j++) {   // remplissage colonnes
                tab[i][j] = temp[cpt];
                cpt = cpt+1;
                System.out.print(" || " + tab[i][j]);
                if (j == (y - 1)) {
                    System.out.print(" || ");
                }
            }
            System.out.print("\n");
        }
    }
}