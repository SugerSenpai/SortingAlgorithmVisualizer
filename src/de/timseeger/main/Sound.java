package de.timseeger.main;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.Synthesizer;

public class Sound {
    public static MidiChannel channels;
    public static int VOLUME = 30;


    public static void getChannels(Synthesizer synth) {
        channels = synth.getChannels()[0];
    }

    public static void play(int note, int duration) throws InterruptedException
    {
        channels.noteOn(note, VOLUME );
        Thread.sleep(duration);
        channels.noteOff(note);
    }
}
