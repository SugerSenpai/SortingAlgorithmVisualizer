package de.timseeger.main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MainMenu extends JFrame{
    public static int WINDOW_WIDTH = 1280;
    public static int WINDOW_HEIGHT = 720;
    public static MainMenu menu;
    public VisualizeArray visualizeArray;
    public JPanel menuPanel;
    public JComboBox<String> sortSelect;
    public JButton startSort;
    public JSlider arraySlider, speedSlider;
    public int SPEED = 10;

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
        arraySlider = new JSlider(25, 1000, 100);
        arraySlider.setMajorTickSpacing(100);
        arraySlider.setPaintTicks(true);
        menuPanel.add(arraySlider);
        arraySlider.addChangeListener(e -> visualizeArray.changeArrayLength(arraySlider.getValue()));

        speedSlider = new JSlider(1, 100, 5);
        speedSlider.setMajorTickSpacing(5);
        speedSlider.setPaintTicks(true);
        menuPanel.add(speedSlider);
        speedSlider.addChangeListener(e -> SPEED = speedSlider.getValue());

        sortSelect = new JComboBox<>();
        sortSelect.addItem("BubbleSort");
        menuPanel.add(sortSelect);

        startSort = new JButton("Start");
        startSort.addActionListener(e -> VisualizeArray.startSort("BubbleSort", visualizeArray, SPEED));
        menuPanel.add(startSort);


    }

    public static void main(String[] args) {
        menu = new MainMenu();
    }

}
