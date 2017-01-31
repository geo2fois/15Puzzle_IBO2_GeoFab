/*
 * GridLayoutDemo.java
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GridLayoutDemo extends JFrame {

    GridLayout experimentLayout = new GridLayout(4,4);

    public GridLayoutDemo(String name) {
        super(name);
        setResizable(false);
    }

    public void addComponentsToPane(final Container pane) {
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(4,4));

        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();

        //Add buttons to experiment with Grid Layout
        compsToExperiment.add(new JButton("1"));
        compsToExperiment.add(new JButton("2"));
        compsToExperiment.add(new JButton("3"));
        compsToExperiment.add(new JButton("4"));
        compsToExperiment.add(new JButton("5"));
        compsToExperiment.add(new JButton("6"));
        compsToExperiment.add(new JButton("7"));
        compsToExperiment.add(new JButton("8"));
        compsToExperiment.add(new JButton("9"));
        compsToExperiment.add(new JButton("10"));
        compsToExperiment.add(new JButton("11"));
        compsToExperiment.add(new JButton("12"));
        compsToExperiment.add(new JButton("13"));
        compsToExperiment.add(new JButton("14"));
        compsToExperiment.add(new JButton("15"));
        compsToExperiment.add(new JButton("0"));



        //Add controls to set up horizontal and vertical gaps
        controls.add(new Label("Horizontal gap:"));
        controls.add(new Label("Vertical gap:"));
        controls.add(new Label(" "));


        pane.add(compsToExperiment, BorderLayout.WEST);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.EAST);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        GridLayoutDemo frame = new GridLayoutDemo("GridLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
