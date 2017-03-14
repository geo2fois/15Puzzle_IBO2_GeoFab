/**
 * Created by geoff on 18/10/2016.
 */
package Gridpack;


import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import java.util.*;



// La class Grid génère le plateau du Puzzle de dimension (x,y)

public class Grid {


        int x = 4, y = 4;   // Gère la taille du plateau
        int nb, k;
        int cpt = 0;
        int Min= 0, Max=((x*y)-1);  // Borne des valeurs des pions
        int xm, ym; // xm et ym servent à stocker la pos de la tile 0

        int temp[] = new int[(x*y)];    // Création du tableau temporaire des valeurs aléatoire
        public int tab[][] = new int[x][y];    // Création du tableau du plateau de jeu

        public boolean ismovable(int x, int y) {
            boolean var = false;
            if(x>=1) {
                if (tab[x - 1][y] == 0) {   // Si la Tile au dessus existe ET est égale à 0
                    xm = x - 1;
                    ym = y;
                    var = true;
                }
            }
            if(x<3) {
                if ( tab[x + 1][y] == 0) {  // Si la Tile au dessous existe ET est égale à 0
                    xm = x + 1;
                    ym = y;
                    var = true;
                }
            }
            if(y>=1) {
                if (tab[x][y - 1] == 0) {   // Si la Tile de gauche existe ET est égale à 0
                    xm = x;
                    ym = y - 1;
                    var = true;
                }
            }
            if(y<3) {
                if (tab[x][y + 1] == 0) {   // Si la Tile de droite existe ET est égale à 0
                    xm = x;
                    ym = y +1;
                    var = true;
                }
            }

            return var;
        }

        public void Swap(int x, int y){ // Effectue le swap entre la tile sélectionnée et la tile 0
            tab[xm][ym] = tab[x][y];
            tab[x][y] = 0;
        }

        public void RefreshGrid(){  // Affiche la Grid actuelle dans la console
            for (int i = 0; i < x; i++) {   // remplissage lignes
                for (int j = 0; j < y; j++) {   // remplissage colonnes
                    System.out.print(" || " + String.format("%4d", tab[i][j]));
                    if (j == (y - 1)) {
                        System.out.print(" || ");
                    }
                }
                System.out.print("\n");
            }
        }

        public Grid() {
            for (k = 0; k < temp.length; k++) {  // Initialisation du tableau temporaire (nécessaire pour la comparaison, lors du remplissage du tableau temporaire)
                temp[k] = (x * y) + 1;
            }

            // Remplissage du tableau temporaire sans doublon

            while (cpt <= (x * y)) {
                boolean Doublon = false;
                nb = Min + (int) (Math.random() * ((Max - Min) + 1));    // Nombre aléatoire

                for (k = 0; k < temp.length; k++) {    // recherche si nb est un doublon
                    if (nb == temp[k]) {
                        Doublon = true;    // si nb est un doublon => recommencer
                    }
                }

                if (!Doublon) {  // si nb n'est pas un doublon => remplissage du tableau temporaire
                    temp[cpt] = nb;
                    cpt = cpt + 1;
                }
                if (cpt == (x * y)) {    // sortie du while lorsque tout les pions ont été générés
                    break;
                }
            }

            // Remplissage du tableau et affichage de la grille de jeu à partir du tableau temporaire

            cpt = 0;

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

        new Grid();
    }


}

