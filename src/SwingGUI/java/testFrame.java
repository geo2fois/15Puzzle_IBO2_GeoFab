import javax.swing.*;
import java.awt.*;

/**
 * Created by geoff on 19/12/2016.
 */

public class testFrame{

    public static void main(String argv[]) {

        JFrame f = new JFrame("Game plate");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JButton b1 = new JButton("Bouton 1");

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        Box box1 = new Box(BoxLayout.X_AXIS);
        Box box2 = new Box(BoxLayout.X_AXIS);
        Box box3 = new Box(BoxLayout.X_AXIS);
        Box box4 = new Box(BoxLayout.X_AXIS);
        b1.setPreferredSize(new Dimension(500, 500));

        box1.add(b1);
        box1.add(new JButton("Bouton 2"));
        box1.add(new JButton("Bouton 3"));
        box1.add(new JButton("Bouton 4"));

        box2.add(new JButton("Bouton 5"));
        box2.add(new JButton("Bouton 6"));
        box2.add(new JButton("Bouton 7"));
        box2.add(new JButton("Bouton 8"));

        box3.add(new JButton("Bouton 9"));
        box3.add(new JButton("Bouton 10"));
        box3.add(new JButton("Bouton 11"));
        box3.add(new JButton("Bouton 12"));

        box4.add(new JButton("Bouton 13"));
        box4.add(new JButton("Bouton 14"));
        box4.add(new JButton("Bouton 15"));
        box4.add(new JButton("Bouton 0"));




        JMenuBar menuBar = new JMenuBar();
        f.setJMenuBar(menuBar);

        JMenu menu = new JMenu("Fichier");
        // menu.add(menuItem);
        menuBar.add(menu);

        panel.add(box1);
        panel.add(box2);
        panel.add(box3);
        panel.add(box4);
        f.setContentPane(panel);
        f.setMinimumSize(new Dimension(800, 800));
        b1.setMinimumSize(new Dimension(500, 500));
        f.pack();
        f.setVisible(true);

    }
}