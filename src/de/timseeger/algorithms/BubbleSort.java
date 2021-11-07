package de.timseeger.algorithms;

import de.timseeger.main.MainMenu;
import de.timseeger.main.Sound;
import de.timseeger.main.VisualizeArray;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import static de.timseeger.main.Sound.getMidiConvertRate;


public class BubbleSort implements Runnable {
    VisualizeArray visualizeArray;
    int speed;

    public BubbleSort(VisualizeArray visualizeArray, int speed) {
        this.visualizeArray = visualizeArray;
        this.speed = speed;
    }

    private void runSort() throws MidiUnavailableException, InterruptedException {
            MainMenu.accesses = 0;
            MainMenu.comparisons = 0;
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            Sound.getChannels(synth);
            double convertMidiRate = getMidiConvertRate(visualizeArray);
            int length = visualizeArray.getLength();

            for (int i = 0; i < length - 1; i++) {
                Thread.sleep(speed);
                MainMenu.updateAccesses(1);
                for (int j = 0; j < length - i - 1; j++) {
                    Thread.sleep(speed);
                    MainMenu.updateAccesses(1);
                    if (visualizeArray.getValue(j) > visualizeArray.getValue(j + 1)) {
                        Thread.sleep(speed);
                        Sound.play((int) (visualizeArray.getValue(j) * convertMidiRate), speed);
                        visualizeArray.swap(j, j + 1);
                        MainMenu.updateAccesses(2);
                        MainMenu.updateComparisons();
                    }
                }
            }
        VisualizeArray.isRunning = false;
    }

    @Override
    public void run() {
        try {
            runSort();
        } catch (MidiUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
