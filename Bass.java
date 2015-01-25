import org.jfugue.*;
import java.util.Random;

public class Bass{
    private Pattern intro, verse, chorus, bridge;
    public Pattern song;
    private String[] notes, bridgeNotes;
    
    public String[] getNotes( String[] chords ){
        String[] returnNotes = new String[4];
        for( int i = 0; i < chords.length; i++ ){
            returnNotes[i] = cleanStrings(chords[i]);
        }
        return returnNotes;
    }
    
    public Rhythm noteInputer( String[] prog, Rhythm groove ){
        Rhythm returnRhythm = groove;
        returnRhythm.addSubstitution( '*', prog[0] + "2s");
        returnRhythm.addSubstitution( '?', prog[1] + "2s");
        returnRhythm.addSubstitution( '!', prog[2] + "2s");
        returnRhythm.addSubstitution( '@', prog[3] + "2s");
        returnRhythm.addSubstitution('.', "Rs"); 
        return returnRhythm;
    }
    
    public Pattern rhythmMaker( String[] progression ){
        Random random = new Random();
        Rhythm rhythm = new Rhythm();
        int bassRhythm = random.nextInt(8) + 1;
        rhythm.setVoice(1, Algorithms.makeRhythm(16, bassRhythm) + 
            Algorithms.makeRhythm(16, bassRhythm).replace("*", "?") + 
            Algorithms.makeRhythm(16, bassRhythm).replace("*", "!") +
            Algorithms.makeRhythm(16, bassRhythm).replace("*", "@"));
        rhythm.setVoiceDetails(1, "I[SYNTH_BASS_1]" );
        return noteInputer(progression, rhythm).getPattern();
    }
    
    public Pattern setPatterns(){
        Pattern returnBass = new Pattern();
        intro = rhythmMaker( notes );
        verse = rhythmMaker( notes );
        verse.repeat(2);
        chorus = rhythmMaker( notes );
        chorus.repeat(2);
        bridge = rhythmMaker( bridgeNotes );
        bridge.repeat(2);
        returnBass.add(intro);
        returnBass.add(verse);
        returnBass.add(chorus);
        returnBass.add(verse);
        returnBass.add(chorus);
        returnBass.add(bridge);
        returnBass.add(verse);
        returnBass.add(chorus);
        return returnBass;
    }
    
    public Pattern bassReturn(){
        return song;
    }
    
    private static String cleanStrings(String str) {//Found a similar method on stack exchange and just modified it for my needs
        return str.substring( 0, str.length() - 3); //This removes the chord part of the progression
    }
    
    public Bass( String[] chords, String[] bridgeChords){
        notes = getNotes(chords);
        bridgeNotes = getNotes(bridgeChords);
        song = setPatterns();
    }
    
    public static void main(String[] args){
         
    }
}