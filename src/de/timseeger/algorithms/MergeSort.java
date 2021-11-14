package de.timseeger.algorithms;

import de.timseeger.main.MainMenu;
import de.timseeger.main.Sound;
import de.timseeger.main.VisualizeArray;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import static de.timseeger.main.MainMenu.updateAccesses;
import static de.timseeger.main.MainMenu.updateComparisons;
import static de.timseeger.main.Sound.getMidiConvertRate;

public class MergeSort implements Runnable {
    VisualizeArray visualizeArray;
    int speed;

    public MergeSort(VisualizeArray visualizeArray, int speed){
        this.visualizeArray = visualizeArray;
        this.speed = speed;
    }

    @Override
    public void run() {
        try {
            runSort();
        } catch (MidiUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void runSort() throws MidiUnavailableException, InterruptedException {
        MainMenu.accesses = 0;
        MainMenu.comparisons = 0;
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        Sound.getChannels(synth);
        
        int left = 0;
        int right = visualizeArray.getLength()-1;
        mergeSort(left, right);
    }

    private void mergeSort(int left, int right) throws InterruptedException {
        if(left < right){
            int mid = (right+left)/2;

            mergeSort(left, mid);
            mergeSort(mid+1, right);
            merge(left, mid, right);
        }
    }

    private void merge(int left, int mid, int right) throws InterruptedException {
        double convertMidiRate = getMidiConvertRate(visualizeArray);
            VisualizeArray.isRunning = true;
            int lSize = mid - left + 1;
            int rSize = right - mid;
            int[] l = new int[lSize];
            int[] r = new int[rSize];
            VisualizeArray.traversing_index = right;

            for(int i = 0; i < lSize; ++i){
                l[i] = visualizeArray.getValue(left+i);
                updateAccesses(1);
            }
            for(int j = 0; j < rSize; ++j){
                r[j] = visualizeArray.getValue(mid+1+j);
                updateAccesses(1);
            }

            int i = 0, j = 0, k = left;
            while (i < lSize && j < rSize) {
                Thread.sleep(speed);
                if (l[i] <= r[j]) {
                    Thread.sleep(speed);
                    Sound.play((int) (visualizeArray.getValue(k) * convertMidiRate), speed);
                    visualizeArray.changeArray(k, l[i]);
                    i++;
                } else {
                    Thread.sleep(speed);
                    Sound.play((int) (visualizeArray.getValue(k) * convertMidiRate), speed);
                    visualizeArray.changeArray(k, r[j]);
                    j++;
                }
                k++;
                updateAccesses(2);
                updateComparisons();
            }
            while (i < lSize) {
                Thread.sleep(speed);
                Sound.play((int) (visualizeArray.getValue(k) * convertMidiRate), speed);
                visualizeArray.changeArray(k, l[i]);
                i++;
                k++;
            }
            updateAccesses(2);
            while (j < rSize) {
                Thread.sleep(speed);
                Sound.play((int) (visualizeArray.getValue(k) * convertMidiRate), speed);
                visualizeArray.changeArray(k, r[j]);
                j++;
                k++;
            }
            updateAccesses(2);
            VisualizeArray.isRunning = false;
    }
}
