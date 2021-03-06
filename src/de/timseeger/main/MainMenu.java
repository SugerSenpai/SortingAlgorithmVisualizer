package de.timseeger.main;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public static int WINDOW_WIDTH = 1280;
    public static int WINDOW_HEIGHT = 720;
    public static int THREAD_SLEEP_SPEED = 5;

    public static int accesses, comparisons;
    public static JLabel SizeOfArray, SpeedOfThread, specificsOfAlgo;
    public VisualizeArray visualizeArray;
    public JPanel menuPanel;
    public JComboBox<String> sortSelect;
    public JButton startSort;
    public JSlider arraySlider, speedSlider;

    public MainMenu() {
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

    public static void main(String[] args) {
        new MainMenu();
    }

    public static void updateAccesses(int amount) {
        for (int i = 0; i < amount; i++) {
            accesses++;
        }
        specificsOfAlgo.setText(MainMenu.accesses + " accesses, " + MainMenu.comparisons + " comparisons, " + MainMenu.THREAD_SLEEP_SPEED + " ms delay");
    }

    public static void updateComparisons() {
        comparisons++;
        specificsOfAlgo.setText(MainMenu.accesses + " accesses, " + MainMenu.comparisons + " comparisons, " + MainMenu.THREAD_SLEEP_SPEED + " ms delay");
    }

    private void initializeMenu() {
        SizeOfArray = new JLabel("Array size");

        arraySlider = new JSlider(25, 1000, 100);
        arraySlider.setMajorTickSpacing(100);
        arraySlider.setPaintTicks(true);
        arraySlider.addChangeListener(e -> visualizeArray.changeArrayLength(arraySlider.getValue()));

        SpeedOfThread = new JLabel("Speed of algorithm");

        speedSlider = new JSlider(1, 10, 5);
        speedSlider.setInverted(true);
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.addChangeListener(e -> {
            if (!VisualizeArray.isRunning) THREAD_SLEEP_SPEED = speedSlider.getValue();
        });
        speedSlider.addChangeListener(e -> specificsOfAlgo.setText(accesses + " accesses, " + comparisons + " comparisons, " + THREAD_SLEEP_SPEED + " ms delay"));

        sortSelect = new JComboBox<>();
        sortSelect.addItem("BubbleSort");
        sortSelect.addItem("QuickSort");
        sortSelect.addItem("SelectionSort");
        sortSelect.addItem("InsertionSort");
        sortSelect.addItem("MergeSort");

        startSort = new JButton("Start");
        startSort.addActionListener(e -> VisualizeArray.startSort(String.valueOf(sortSelect.getSelectedItem()), visualizeArray, THREAD_SLEEP_SPEED));

        specificsOfAlgo = new JLabel(accesses + " accesses, " + comparisons + " comparisons, " + THREAD_SLEEP_SPEED + " ms delay");

        menuPanel.add(SizeOfArray);
        menuPanel.add(arraySlider);
        menuPanel.add(SpeedOfThread);
        menuPanel.add(speedSlider);
        menuPanel.add(sortSelect);
        menuPanel.add(startSort);
        menuPanel.add(specificsOfAlgo);
    }

}
