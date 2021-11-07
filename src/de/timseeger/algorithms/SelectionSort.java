package de.timseeger.algorithms;

import de.timseeger.main.MainMenu;
import de.timseeger.main.Sound;
import de.timseeger.main.VisualizeArray;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import static de.timseeger.main.Sound.getMidiConvertRate;

public class SelectionSort implements Runnable {
    VisualizeArray visualizeArray;
    int speed;

    public SelectionSort(VisualizeArray visualizeArray, int speed) {
        this.visualizeArray = visualizeArray;
        this.speed = speed;
    }

    private void runSort() throws InterruptedException, MidiUnavailableException {
            MainMenu.accesses = 0;
            MainMenu.comparisons = 0;
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            Sound.getChannels(synth);
            double convertMidiRate = getMidiConvertRate(visualizeArray);
            int length = visualizeArray.getLength();

            for (int i = 0; i < length-1; i++) {
                Thread.sleep(speed);
                int min = i;
                MainMenu.updateAccesses(2);
                for (int j = i + 1; j < length; j++) {
                    Thread.sleep(speed);
                    MainMenu.updateAccesses(1);
                    if (visualizeArray.getValue(j) < visualizeArray.getValue(min)) {
                        Thread.sleep(speed);
                        MainMenu.updateComparisons();
                        MainMenu.updateAccesses(2);
                        min = j;
                    }
                }
                Thread.sleep(speed);
                if (min != i)
                    MainMenu.updateComparisons();
                    MainMenu.updateAccesses(3);
                    visualizeArray.swap(min, i);
                Sound.play((int) (visualizeArray.getValue(min) * convertMidiRate), speed);
            }
        VisualizeArray.isRunning = false;
    }

    @Override
    public void run() {
        try {
            runSort();
        } catch (InterruptedException | MidiUnavailableException e) {
            e.printStackTrace();
        }
    }
}
