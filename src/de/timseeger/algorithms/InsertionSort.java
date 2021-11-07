package de.timseeger.algorithms;

import de.timseeger.main.MainMenu;
import de.timseeger.main.Sound;
import de.timseeger.main.VisualizeArray;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import static de.timseeger.main.Sound.getMidiConvertRate;

public class InsertionSort implements Runnable {
    VisualizeArray visualizeArray;
    int speed;

    public InsertionSort(VisualizeArray visualizeArray, int speed){
        this.visualizeArray = visualizeArray;
        this.speed = speed;
    }

    public void runSort() throws InterruptedException, MidiUnavailableException{
        MainMenu.accesses = 0;
        MainMenu.comparisons = 0;
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        Sound.getChannels(synth);
        double convertMidiRate = getMidiConvertRate(visualizeArray);
        int length = visualizeArray.getLength();

        for(int i = 0;i < length; i++){
            Thread.sleep(speed);
            int key = visualizeArray.getValue(i);
            int j = i-1;
            MainMenu.updateAccesses(2);
            while(j >= 0 && visualizeArray.getValue(j) > key){
                MainMenu.updateComparisons();
                MainMenu.updateAccesses(3);
                Thread.sleep(speed);
                Sound.play((int) (visualizeArray.getValue(j+1) * convertMidiRate), speed);
                System.out.println(i);
                VisualizeArray.traversing_index = i;
                visualizeArray.changeArray(j+1, visualizeArray.getValue(j));
                j--;
            }
            MainMenu.updateAccesses(2);
            Thread.sleep(speed);
            visualizeArray.changeArray(j+1, key);
        }
        VisualizeArray.isRunning = false;
    }


    @Override
    public void run() {
        try{
            runSort();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
