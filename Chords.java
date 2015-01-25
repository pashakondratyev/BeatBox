import org.jfugue.*;
import java.util.Random;

public class Chords{
    private Pattern intro, verse, chorus, bridge;
    public Pattern song;
    private String[] chords, bridgeChords;
    
    public Rhythm noteInputer( String[] prog, Rhythm groove ){
        Rhythm returnRhythm = groove;
        returnRhythm.addSubstitution( '*', prog[0] + "s");
        returnRhythm.addSubstitution( '?', prog[1] + "s");
        returnRhythm.addSubstitution( '!', prog[2] + "s");
        returnRhythm.addSubstitution( '@', prog[3] + "s");
        returnRhythm.addSubstitution( '.', "Rs"); 
        return returnRhythm;
    }
    
    public Pattern rhythmMaker( String[] progression ){
        Random random = new Random();
        Rhythm rhythm = new Rhythm();
        int chordRhythm = random.nextInt(8) + 1;
        rhythm.setVoice(2, Algorithms.makeRhythm(16, chordRhythm) + 
            Algorithms.makeRhythm(16, chordRhythm).replace("*", "?") + 
            Algorithms.makeRhythm(16, chordRhythm).replace("*", "!") +
            Algorithms.makeRhythm(16, chordRhythm).replace("*", "@"));
        rhythm.setVoiceDetails(2, "I[ELECTRIC_PIANO_2]" );
        return noteInputer(progression, rhythm).getPattern();
    }
        
    public Pattern setPatterns(){
        Pattern returnChord = new Pattern();
        intro = rhythmMaker( chords );
        verse = rhythmMaker( chords );
        verse.repeat(2);
        chorus = rhythmMaker( chords );
        chorus.repeat(2);
        bridge = rhythmMaker( bridgeChords );
        bridge.repeat(2);
        returnChord.add(intro);
        returnChord.add(verse);
        returnChord.add(chorus);
        returnChord.add(verse);
        returnChord.add(chorus);
        returnChord.add(bridge);
        returnChord.add(verse);
        returnChord.add(chorus);
        return returnChord;
    }
    
    
    public Pattern chordReturn(){
        return song;
    }
    
    public Chords( String[] chordsIn, String[] bridgeChordsIn){
        chords = chordsIn;
        bridgeChords = bridgeChordsIn;
        song = setPatterns();
    }
    
}