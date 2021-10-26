package de.timseeger.algorithms;

import de.timseeger.main.MainMenu;
import de.timseeger.main.Sound;
import de.timseeger.main.VisualizeArray;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

import static de.timseeger.main.Sound.getMidiConvertRate;


public class QuickSort implements Runnable {
    VisualizeArray visualizeArray;
    int speed;

    public QuickSort(VisualizeArray visualizeArray, int speed) {
        this.visualizeArray = visualizeArray;
        this.speed = speed;
    }

    private void runSort() throws InterruptedException {
        try {
            MainMenu.accesses = 0;
            MainMenu.comparisons = 0;
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            Sound.getChannels(synth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        quickSort(0, visualizeArray.getLength() - 1);
    }

    private void quickSort(int low, int high) throws InterruptedException {
        if (low < high) {
            int pivot = findPivot(low, high);
            quickSort(low, pivot - 1);
            quickSort(pivot + 1, high);
        }
        VisualizeArray.isRunning = false;
    }

    private int findPivot(int low, int high) throws InterruptedException {
        VisualizeArray.isRunning = true;
        int pivotVal = visualizeArray.getValue(high);
        int i = low - 1;
        double convertMidiRate = getMidiConvertRate(visualizeArray);

        for (int j = low; j <= high - 1; j++) {
            Thread.sleep(speed);
            MainMenu.updateAccesses();
            if (visualizeArray.getValue(j) <= pivotVal) {
                Sound.play((int) (visualizeArray.getValue(j) * convertMidiRate), speed);
                MainMenu.updateComparisons();
                i++;
                visualizeArray.swap(i, j);
                Thread.sleep(speed);
            }
        }
        Thread.sleep(speed);
        visualizeArray.swap(i + 1, high);
        MainMenu.updateAccesses();

        return i + 1;

    }

    @Override
    public void run() {
        try {
            runSort();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
