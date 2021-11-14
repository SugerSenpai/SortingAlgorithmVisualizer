package de.timseeger.main;

import de.timseeger.algorithms.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class VisualizeArray extends JPanel {
    public static Thread sortingThread;
    public static boolean isRunning;
    public static int current_index, traversing_index;
    private static int LENGTH = 100;
    private int[] randomArray;
    private float[] BAR_HEIGHT = new float[LENGTH];

    public VisualizeArray() {
        setBackground(Color.BLACK);
        randomArray = new int[LENGTH];
        randomizeArray(randomArray);
        getBarHeight();
    }

    public static void startSort(String algorithmName, VisualizeArray visualizeArray, int speed) {
        if (!isRunning) {
            switch (algorithmName) {
                case ("BubbleSort") -> {
                    sortingThread = new Thread(new BubbleSort(visualizeArray, speed));
                    isRunning = true;
                }
                case ("QuickSort") -> {
                    sortingThread = new Thread(new QuickSort(visualizeArray, speed));
                    isRunning = true;
                }
                case ("SelectionSort") -> {
                    sortingThread = new Thread(new SelectionSort(visualizeArray, speed));
                    isRunning = true;
                }
                case ("InsertionSort") -> {
                    sortingThread = new Thread(new InsertionSort(visualizeArray, speed));
                    isRunning = true;
                }
                case("MergeSort") -> {
                    sortingThread = new Thread(new MergeSort(visualizeArray, speed));
                    isRunning = true;
                }
            }
            sortingThread.start();
        }
    }

    public void getBarHeight() {
        float temp = (float) (MainMenu.WINDOW_HEIGHT * 0.85) / LENGTH;
        for (int i = 0; i < LENGTH; i++) {
            BAR_HEIGHT[i] = randomArray[i] * temp;
        }
    }

    public void randomizeArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            Random random = new Random();
            int x = random.nextInt(LENGTH) + 1;
            array[i] = x;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        float BAR_WIDTH = (float) getWidth() / LENGTH;
        Graphics2D graphics2D = (Graphics2D) g;
        super.paintComponent(graphics2D);
        super.paintComponents(graphics2D);
        graphics2D.setColor(Color.GRAY);
        Rectangle2D.Float bar;

        for (int i = 0; i < randomArray.length; i++) {
            bar = new Rectangle2D.Float(i * BAR_WIDTH, 0, BAR_WIDTH, BAR_HEIGHT[i]);
            graphics2D.fill(bar);
        }
        graphics2D.setColor(Color.RED);
        bar = new Rectangle2D.Float(current_index * BAR_WIDTH, 0, BAR_WIDTH, BAR_HEIGHT[current_index]);
        graphics2D.fill(bar);

        graphics2D.setColor(Color.GREEN);
        bar = new Rectangle2D.Float(traversing_index * BAR_WIDTH, 0, BAR_WIDTH, BAR_HEIGHT[traversing_index]);
        graphics2D.fill(bar);
    }

    public void changeArrayLength(int length) {
        if (!isRunning) {
            randomArray = new int[length];
            randomizeArray(randomArray);
            LENGTH = length;
            BAR_HEIGHT = new float[length];
            getBarHeight();
            repaint();
            if(traversing_index > randomArray.length){
                traversing_index = 0;
            }
            if(current_index > randomArray.length){
                current_index = 1;
            }
        }
    }

    public void swap(int firstIndex, int secondIndex) {
        current_index = firstIndex;
        traversing_index = secondIndex;
        int temp = randomArray[firstIndex];
        randomArray[firstIndex] = randomArray[secondIndex];
        randomArray[secondIndex] = temp;
        getBarHeight();
        repaint();
    }

    public void changeArray(int index, int value) {
        current_index = index;
        randomArray[index] = value;
        getBarHeight();
        repaint();
    }

    public int getLength() {
        return randomArray.length;
    }

    public int getValue(int index) {
        return randomArray[index];
    }

}
