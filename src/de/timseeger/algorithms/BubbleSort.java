package de.timseeger.algorithms;

import de.timseeger.main.VisualizeArray;

public class BubbleSort implements SortAlgorithm{
    @Override
    public String getName() {
        return "BubbleSort";
    }

    @Override
    public void runSort(VisualizeArray visualizeArray) {
        int length = visualizeArray.getLength();
        for(int i = 0; i < length-1; i++){
            for(int j = 0; j < length - i - 1;j++){
                if(visualizeArray.getValue(j) > visualizeArray.getValue(j+1)){
                    visualizeArray.swap(j, j+1);
                }
            }
        }
    }
}
