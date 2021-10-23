package de.timseeger.algorithms;

import de.timseeger.main.MainMenu;
import de.timseeger.main.Sound;
import de.timseeger.main.VisualizeArray;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;


public class BubbleSort implements Runnable{
    VisualizeArray visualizeArray;
    int speed;

    public BubbleSort(VisualizeArray visualizeArray, int speed){
        this.visualizeArray = visualizeArray;
        this.speed = speed;
    }

    public void runSort()  {
        try {
            MainMenu.accesses = 0;
            MainMenu.comparisons = 0;
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            Sound.getChannels(synth);
            double convertMidiRate = (double) 70/visualizeArray.getLength();
            int length = visualizeArray.getLength();
            for (int i = 0; i < length - 1; i++) {
                for (int j = 0; j < length - i - 1; j++) {
                    MainMenu.accesses++;
                    MainMenu.specificsOfAlgo.setText(MainMenu.accesses + " accesses, " + MainMenu.comparisons + " comparisons, " + MainMenu.THREAD_SLEEP_SPEED + " ms delay");
                    if (visualizeArray.getValue(j) > visualizeArray.getValue(j + 1)) {
                        Sound.play((int) (visualizeArray.getValue(j)*convertMidiRate), MainMenu.THREAD_SLEEP_SPEED);
                        visualizeArray.swap(j, j + 1);
                        MainMenu.comparisons++;
                        MainMenu.specificsOfAlgo.setText(MainMenu.accesses + " accesses, " + MainMenu.comparisons + " comparisons, " + MainMenu.THREAD_SLEEP_SPEED + " ms delay");
                        try {
                            Thread.sleep(speed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        VisualizeArray.isRunning = false;
    }

    @Override
    public void run() {
        runSort();
    }

}
