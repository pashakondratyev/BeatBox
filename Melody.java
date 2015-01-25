import org.jfugue.*;
import java.util.Random;

public class Melody{
    
    private Pattern intro, verse, verse2, verse3, chorus, bridge;
    public Pattern song;
    private String[] notes;
    
    
    
    public String rhythmMaker(){
        Random random = new Random();
        Rhythm rhythm = new Rhythm();
        int leadRhythm = random.nextInt(16) + 1;
        rhythm.setVoice(3, Algorithms.makeRhythm(16, leadRhythm));
        rhythm.setVoiceDetails(3, "I[LEAD_SQUARE]" );
        return rhythm.getVoice(3);
    }
    
    public String noteAdder( Pattern noNotes ){
        String toEdit = noNotes.getMusicString();
        Random rand = new Random();
        String returnString ="";
        for( int i = 0; i <  toEdit.length(); i++){
            if( toEdit.charAt(i) == '*' ){
                returnString += notes[rand.nextInt(notes.length)] + "s ";
            }
            else if( toEdit.charAt(i) == '.'){
                returnString += "Rs ";
            }
        }

        return returnString;

    }
    public Pattern setPatterns(){
        Pattern returnSong = new Pattern();
        Rhythm rhythm = new Rhythm();
        Pattern returnPattern = new Pattern("I[LEAD_SQUARE]");
        intro = new Pattern();
        intro.add( rhythmMaker(), rhythmMaker(), rhythmMaker(), rhythmMaker() );
        verse = new Pattern();
        verse.add( rhythmMaker(), rhythmMaker(), rhythmMaker(), rhythmMaker() );
        verse.repeat(2);
        verse2 = new Pattern();
        verse2.add( rhythmMaker(), rhythmMaker(), rhythmMaker(), rhythmMaker() );
        verse2.repeat(2);
        verse3 = new Pattern();
        verse3.add( rhythmMaker(), rhythmMaker(), rhythmMaker(), rhythmMaker() );
        verse3.repeat(2);
        chorus = new Pattern();
        chorus.add( rhythmMaker(), rhythmMaker(), rhythmMaker(), rhythmMaker() );
        chorus.repeat(2);
        bridge = new Pattern();
        bridge.add( rhythmMaker(), rhythmMaker(), rhythmMaker(), rhythmMaker() );
        bridge.repeat(2);
        returnSong.add( intro, verse, chorus, verse2, chorus, bridge, verse3, chorus );

        String noteString = noteAdder(returnSong);
        returnPattern.add( "V4 " + noteString );
        return returnPattern;
    }
    
    public Pattern melodyReturn(){
        return song;
    }
    public Melody( String[] songNotes ){
        notes = songNotes;
        song = setPatterns();
    }
}