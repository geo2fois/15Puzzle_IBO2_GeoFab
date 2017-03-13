import java.awt.*;
import javax.swing.*;
import Gridpack.Grid;

/**
 * Created by geoff on 19/12/2016.
 */

public class testFrame extends JFrame{

        JFrame GUI = new JFrame("Game plate");
        //GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        GridLayout Gridtable = new GridLayout(4,4);

        testFrame() {
            this.setSize(1000,500);
            int x=this.getWidth();
            int y=this.getHeight();
            panel1.setLayout(Gridtable);
            panel1.setPreferredSize(new Dimension(x/2,y));
            panel1.setBackground(Color.DARK_GRAY);

          //  panel2.setLayout(new GridLayout(4,4)); NO NEED
            panel2.setPreferredSize(new Dimension(x/2,y));
            panel2.setBackground(Color.DARK_GRAY);

//            teststr = String.valueOf(G.tab[1][0]);
//          System.out.println("affichage de grid \"[1][0]\"" + teststr);

            panel1.add(new JButton("1"));
            panel1.add(new JButton("2"));
            panel1.add(new JButton("3"));
            panel1.add(new JButton("4"));
            panel1.add(new JButton("5"));
            panel1.add(new JButton("6"));
            panel1.add(new JButton("7"));
            panel1.add(new JButton("8"));
            panel1.add(new JButton("9"));
            panel1.add(new JButton("10"));
            panel1.add(new JButton("11"));
            panel1.add(new JButton("12"));
            panel1.add(new JButton("13"));
            panel1.add(new JButton("14"));
            panel1.add(new JButton("15"));
            panel1.add(new JButton("0"));

            panel2.add(new Label("MENU HERE"));

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

    public static void main(String[] args) {

        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");


            new testFrame();
        }
        catch (Exception e)
        {
        }

    }
}
