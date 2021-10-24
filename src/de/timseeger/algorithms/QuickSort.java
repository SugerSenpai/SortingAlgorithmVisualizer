package de.timseeger.algorithms;

import de.timseeger.main.MainMenu;
import de.timseeger.main.Sound;
import de.timseeger.main.VisualizeArray;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;


public class QuickSort implements Runnable{
    VisualizeArray visualizeArray;
    int speed;

    public QuickSort(VisualizeArray visualizeArray, int speed){
        this.visualizeArray = visualizeArray;
        this.speed = speed;
    }

    private void runSort() {
        try {
            MainMenu.accesses = 0;
            MainMenu.comparisons = 0;
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            Sound.getChannels(synth);
        }catch(Exception e){
            System.out.println(e);
        }
        quickSort(0, visualizeArray.getLength()-1);
    }

    private void quickSort(int low, int high) {
        if(low < high) {
            int pivot = findPivot(low, high);
            quickSort(low, pivot - 1);
            quickSort(pivot + 1, high);
        }
        VisualizeArray.isRunning = false;
    }

    private int findPivot(int low, int high) {
        VisualizeArray.isRunning = true;
        int pivotVal = visualizeArray.getValue(high);
        int i = low -1;
        double convertMidiRate = (double) 65/visualizeArray.getLength();
        for(int j = low; j <= high-1;j++){
            MainMenu.accesses++;
            MainMenu.specificsOfAlgo.setText(MainMenu.accesses + " accesses, " + MainMenu.comparisons + " comparisons, " + MainMenu.THREAD_SLEEP_SPEED + " ms delay");
            if(visualizeArray.getValue(j) <= pivotVal){
                try {
                    Sound.play((int) (visualizeArray.getValue(j) * convertMidiRate), MainMenu.THREAD_SLEEP_SPEED);
                    MainMenu.comparisons++;
                    MainMenu.specificsOfAlgo.setText(MainMenu.accesses + " accesses, " + MainMenu.comparisons + " comparisons, " + MainMenu.THREAD_SLEEP_SPEED + " ms delay");
                    i++;
                    visualizeArray.swap(i, j);
                    Thread.sleep(speed);
            }catch(Exception e){
                System.out.println(e);
            }
            }
        }
        try{
            Thread.sleep(speed);
        }catch(Exception e){
            System.out.println(e);
        }
        visualizeArray.swap(i+1, high);

        return i+1;

    }

    @Override
    public void run() {
        runSort();
    }

}
