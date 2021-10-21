package de.timseeger.main;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class VisualizeArray extends JPanel {
    private int[] randomArray;
    private int LENGTH = 100;
    private float[] BAR_HEIGHT = new float[LENGTH];

    public int getLength(){
        return LENGTH;
    }

    public int getValue(int index){
        return randomArray[index];
    }


    public VisualizeArray(){
        randomArray = new int[LENGTH];
        randomizeArray(randomArray);
        getBarHeight();
        setBackground(Color.BLACK);
    }

    public void getBarHeight(){
        float temp = (float) 650/LENGTH;
        for(int i = 0; i < LENGTH; i++){
            BAR_HEIGHT[i] = randomArray[i]*temp;
        }
    }

    public int[] randomizeArray(int[] array){
        for(int i = 0; i < array.length; i++){
            Random random = new Random();
            int x = random.nextInt(LENGTH);
            array[i] = x;
        }
        return array;
    }

    @Override
    public void paintComponent(Graphics g) {
        float BAR_WIDTH = (float) getWidth()/randomArray.length;
        Graphics2D graphics2D = (Graphics2D) g;
        super.paintComponent(graphics2D);
        super.paintComponents(graphics2D);
        graphics2D.setColor(Color.WHITE);
        Rectangle2D.Float bar;

        for(int i = 0; i< randomArray.length; i++){
            bar = new Rectangle2D.Float(i*BAR_WIDTH, 0, BAR_WIDTH, BAR_HEIGHT[i]);
            graphics2D.fill(bar);
        }
    }


    public void changeArrayLength(int length){
        randomArray = new int[length];
        randomizeArray(randomArray);
        LENGTH = length;
        BAR_HEIGHT = new float[length];
        getBarHeight();
        repaint();
    }

    public void swap(int firstIndex, int secondIndex){
        int temp = randomArray[firstIndex];
        randomArray[firstIndex] = randomArray[secondIndex];
        randomArray[secondIndex] = temp;
        updateArray();
    }

    private void updateArray() {
        repaint();
    }
}
