package de.timseeger.algorithms;

import de.timseeger.main.VisualizeArray;


public class BubbleSort implements SortAlgorithm{
    VisualizeArray visualizeArray;
    int speed;

    public BubbleSort(VisualizeArray visualizeArray, int speed){
        this.visualizeArray = visualizeArray;
        this.speed = speed;
    }
    @Override
    public String getName() {
        return "BubbleSort";
    }

    @Override
    public void runSort() {
        int length = visualizeArray.getLength();
        for(int i = 0;i < length-1; i++){
            for(int j = 0; j < length-i-1; j++){
                if(visualizeArray.getValue(j) > visualizeArray.getValue(j+1)) {
                    System.out.println(visualizeArray.getValue(j));
                    visualizeArray.swap(j, j + 1);
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        runSort();
    }
}
