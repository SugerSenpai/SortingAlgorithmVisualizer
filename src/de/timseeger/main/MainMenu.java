package de.timseeger.main;

import de.timseeger.algorithms.BubbleSort;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame{
    public int WINDOW_WIDTH = 1280;
    public int WINDOW_HEIGHT = 720;
    public static MainMenu menu;
    public VisualizeArray visualizeArray;
    public JPanel menuPanel;
    public JComboBox<String> sortSelect;
    public JButton startSort;

    public MainMenu(){
        super("Sorting Algorithm Visualizer");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        visualizeArray = new VisualizeArray();
        menuPanel = new JPanel();
        initializeMenu();


        add(visualizeArray, BorderLayout.CENTER);
        add(menuPanel, BorderLayout.AFTER_LAST_LINE);
        visualizeArray.repaint();
        setVisible(true);
    }

    private void initializeMenu() {
        JLabel test = new JLabel("test");
        JSlider arraySize = new JSlider(25, 1000, 100);
        arraySize.setMajorTickSpacing(100);
        arraySize.setPaintTicks(true);
        menuPanel.add(arraySize);
        arraySize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                visualizeArray.changeArrayLength(arraySize.getValue());
            }
        });
        sortSelect = new JComboBox<>();
        sortSelect.addItem("BubbleSort");

        menuPanel.add(sortSelect);

        startSort = new JButton("Start");
        startSort.addActionListener(e -> new BubbleSort().runSort(visualizeArray));

        menuPanel.add(startSort);
    }

    public static void main(String[] args) {
        menu = new MainMenu();
    }

}
