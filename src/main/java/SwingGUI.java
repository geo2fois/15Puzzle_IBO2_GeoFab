import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Gridpack.Grid;




public class SwingGUI extends JFrame{

    JFrame GUI = new JFrame("Game plate");
    //GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    GridLayout Gridtable = new GridLayout(4,4);
    GridLayout Menutable = new GridLayout(4,1);

    public SwingGUI(final Grid g) {
        this.setSize(1000,500);
        int x=this.getWidth();
        int y=this.getHeight();
        panel1.setLayout(Gridtable);
        panel1.setPreferredSize(new Dimension(x/2,y));
        panel1.setBackground(Color.DARK_GRAY);

        //  panel2.setLayout(new GridLayout(4,4)); NO NEED
        panel2.setLayout(Menutable);
        panel2.setPreferredSize(new Dimension(x/2,y));
        panel2.setBackground(Color.DARK_GRAY);


        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                JButton TileGUI = new JButton(String.valueOf(g.tab[i][j]));
                TileGUI.setFont(new Font("Arial", Font.PLAIN, 30));
                panel1.add(TileGUI);
                final int finalI = i;
                final int finalJ = j;
                TileGUI.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("");
                        System.out.println("You clicked on the Tile: " + String.valueOf(g.tab[finalI][finalJ]));
                        if (g.ismovable(finalI, finalJ)) {
                            System.out.println("The Tile " + String.valueOf(g.tab[finalI][finalJ]) + " is movable");
                            g.Swap(finalI,finalJ);
                            g.RefreshGrid();
                            GUIupdating(g);
                        }
                        else if(!g.ismovable(finalI, finalJ)){
                            System.out.println("The Tile " + String.valueOf(g.tab[finalI][finalJ]) + " is not movable");
                            System.out.println("    ===>Please select another Tile");
                        }
                    }
                });


//                GUI.getContentPane();
//                panel1.add(new JButton("1"));
//                panel1.add(new JButton("2"));
//                panel1.add(new JButton("3"));
//                panel1.add(new JButton("4"));
//                panel1.add(new JButton("5"));
//                panel1.add(new JButton("6"));
//                panel1.add(new JButton("7"));
//                panel1.add(new JButton("8"));
//                panel1.add(new JButton("9"));
//                panel1.add(new JButton("10"));
//                panel1.add(new JButton("11"));
//                panel1.add(new JButton("12"));
//                panel1.add(new JButton("13"));
//                panel1.add(new JButton("14"));
//                panel1.add(new JButton("15"));
//                panel1.add(new JButton("0"));
            }

        panel2.add(new Label("MENU HERE"));
        JButton Shuffle = new JButton("Shuffle");
        panel2.add(new JButton("Shuffle"));
        panel2.add(new JButton("Algo 1"));
        panel2.add(new JButton("Algo 2"));

        GUI.add(panel1);
        GUI.add(panel2, BorderLayout.EAST);

        GUI.setMinimumSize(new Dimension(1000, 500));
        GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        GUI.getContentPane();
        //Display the window.
        GUI.pack();
        GUI.setVisible(true);
    }


    public void GUIupdating(final Grid g){
        GUI.setVisible(false);
        panel1.removeAll();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JButton TileGUI = new JButton(String.valueOf(g.tab[i][j]));
                TileGUI.setFont(new Font("Arial", Font.PLAIN, 30));
                panel1.add(TileGUI);
                final int finalI = i;
                final int finalJ = j;
                TileGUI.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("");
                        System.out.println("You clicked on the Tile: " + String.valueOf(g.tab[finalI][finalJ]));
                        if (g.ismovable(finalI, finalJ)) {
                            System.out.println("The Tile " + String.valueOf(g.tab[finalI][finalJ]) + " is movable");
                            g.Swap(finalI,finalJ);
                            g.RefreshGrid();
                            GUIupdating(g);
                        }
                        else if (!g.ismovable(finalI, finalJ)){
                            System.out.println("The Tile " + String.valueOf(g.tab[finalI][finalJ]) + " is not movable");
                            System.out.println("    ===>Please select another Tile");
                        }
                    }
                });
            }
        }
        GUI.getContentPane();
        GUI.setVisible(true);
    }

    public static void main(String[] args) {

        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");


        //    new SwingGUI();
        }
        catch (Exception e)
        {
        }

    }
}
