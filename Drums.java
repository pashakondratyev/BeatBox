import org.jfugue.*;
import java.util.Random;
import java.io.File;
import java.io.IOException;

public class Drums{
    public Pattern intro;
    public Pattern verse1;
    public Pattern verse2;
    public Pattern verse3;
    public Pattern chorus;
    public Pattern bridge;
    public Pattern song;
    
    
    private Rhythm copyDrums( Rhythm toFill ){ //because the clone method for Rhythm is not possible for us to use we need to make our own
        Rhythm returnRhythm = new Rhythm();
        returnRhythm.setLayer(1, toFill.getLayer(1));//Kick
        returnRhythm.setLayer(2, toFill.getLayer(2));//Snare
        returnRhythm.setLayer(3, toFill.getLayer(3));//Ride
        returnRhythm.setLayer(4, toFill.getLayer(4));//Low Mid Toms
        returnRhythm.setLayer(5, toFill.getLayer(5));//Low Toms
        returnRhythm.setLayer(6, toFill.getLayer(6));//Open Hat
        returnRhythm.setLayer(7, toFill.getLayer(7));//Closed Hat
        returnRhythm.setLayer(8, toFill.getLayer(8));//Crash
        return returnRhythm;
    }
    
    private Rhythm kickSnareGroove(){//Kick Snare grooves are basically universal so we can reuse this function
        Random random = new Random();
        Rhythm rhythm = new Rhythm();
        //Kick Drum
        int kickDrum = random.nextInt(8) + 1;
        rhythm.setLayer(1, Algorithms.makeRhythm(16, kickDrum ));
        //Because of the nature of the snare it can be boiled down to one hit down the middle
        rhythm.setLayer(2, "........o.......");
        //All other instruments
        rhythm.setLayer(3, "................");
        rhythm.setLayer(4, "................");
        rhythm.setLayer(5, "................");
        rhythm.setLayer(6, "................");
        rhythm.setLayer(7, "................");
        rhythm.setLayer(8, "................");
        return rhythm;
    }

    private String hatGroove(){
        Random random = new Random();
        int hat = (random.nextInt(4) +1) * 4;
        String hats = Algorithms.makeRhythm(16, hat).replace("*", "!");
        String returnString = "";
        int hit = 0;
        for( int i = 0; i < hats.length() ; i ++ ){
            if(hats.charAt(i) == '!'){
                if( (hit%4) == 0){
                    returnString += "+";
                    hit ++;
                }
                else{
                    returnString += "!";
                    hit ++;
                }
            }
            else{
                returnString += ".";
            }
        }
        return returnString;
    }

    private Rhythm tomFill1(Rhythm beat){
        Random random = new Random();
        //Toms(for Fill)
        Rhythm rhythm = copyDrums(beat);
        int toms = random.nextInt(3) + 1;
        rhythm.setLayer(4, ".........." + Algorithms.makeRhythm(6, toms).replace("*", "?"));
        rhythm.setLayer(5, "............." + Algorithms.makeRhythm(3, toms).replace("*", "<"));
        return rhythm;
    }


    private Pattern drumsIntro(){
        Random random = new Random();
        Pattern returnPattern = new Pattern();
        Rhythm rhythm = kickSnareGroove();
        //Ride
        int ride = random.nextInt(8) + 1;
        rhythm.setLayer(3, Algorithms.makeRhythm(16, ride).replace("*", "!"));
        //Fill
        Rhythm fill = tomFill1( rhythm );
        rhythm.addSubstitution('*', "[BASS_DRUM]s"); 
        rhythm.addSubstitution('o', "[HAND_CLAP]s");
        rhythm.addSubstitution('!', "[RIDE_CYMBAL_1]s");
        rhythm.addSubstitution('.', "Rs"); 
        fill.addSubstitution('*', "[BASS_DRUM]s"); 
        fill.addSubstitution('o', "[HAND_CLAP]s");
        fill.addSubstitution('!', "[RIDE_CYMBAL_1]s");
        fill.addSubstitution('?', "[LOW_MID_TOM]s");
        fill.addSubstitution('<', "[LOW_TOM]s");
        fill.addSubstitution('.', "Rs"); 

        returnPattern.add(rhythm.getPattern());
        returnPattern.repeat(3);
        returnPattern.add(fill.getPattern());
        return returnPattern;
    }

    private Pattern drumsVerse(){
        Random random = new Random();
        Pattern returnPattern = new Pattern();
        Rhythm rhythm = kickSnareGroove();
        rhythm.setLayer(6, hatGroove());
        //Fill
        Rhythm fill = tomFill1( rhythm );
        rhythm.addSubstitution('*', "[BASS_DRUM]s"); 
        rhythm.addSubstitution('o', "[ELECTRIC_SNARE]s");
        rhythm.addSubstitution('!', "[CLOSED_HI_HAT]s");
        rhythm.addSubstitution('+', "[OPEN_HI_HAT]s");
        rhythm.addSubstitution('.', "Rs"); 
        fill.addSubstitution('*', "[BASS_DRUM]s"); 
        fill.addSubstitution('o', "[ELECTRIC_SNARE]s");
        fill.addSubstitution('!', "[CLOSED_HI_HAT]s");
        fill.addSubstitution('+', "[OPEN_HI_HAT]s");
        fill.addSubstitution('?', "[LOW_MID_TOM]s");
        fill.addSubstitution('<', "[LOW_TOM]s");
        fill.addSubstitution('.', "Rs"); 

        returnPattern.add(rhythm.getPattern());
        returnPattern.repeat(7);
        returnPattern.add(fill.getPattern());
        return returnPattern;
    }

    private Pattern drumsBridge(){
        Random random = new Random();
        Pattern returnPattern = new Pattern();
        Rhythm rhythm = kickSnareGroove();
        rhythm.setLayer(6, hatGroove());
        //Fill
        Rhythm fill = tomFill1( rhythm );
        rhythm.addSubstitution('*', "[COWBELL]s"); 
        rhythm.addSubstitution('o', "[HAND_CLAP]s");
        rhythm.addSubstitution('!', "[CLOSED_HI_HAT]s");
        rhythm.addSubstitution('+', "[OPEN_HI_HAT]s");
        rhythm.addSubstitution('.', "Rs"); 
        fill.addSubstitution('*', "[COWBELL]s"); 
        fill.addSubstitution('o', "[HAND_CLAP]s");
        fill.addSubstitution('!', "[CLOSED_HI_HAT]s");
        fill.addSubstitution('+', "[OPEN_HI_HAT]s");
        fill.addSubstitution('?', "[LOW_MID_TOM]s");
        fill.addSubstitution('<', "[LOW_TOM]s");
        fill.addSubstitution('.', "Rs"); 

        returnPattern.add(rhythm.getPattern());
        returnPattern.repeat(7);
        returnPattern.add(fill.getPattern());
        return returnPattern;
    }

    private Pattern drumsChorus(){
        Random random = new Random();
        Pattern returnPattern = new Pattern();
        Rhythm rhythm = kickSnareGroove();
        //Ride
        int crash = random.nextInt(8) + 1;
        rhythm.setLayer(3, Algorithms.makeRhythm(16, crash).replace("*", "!"));
        //Fill
        Rhythm fill = tomFill1( rhythm );
        rhythm.addSubstitution('*', "[BASS_DRUM]s"); 
        rhythm.addSubstitution('o', "[ELECTRIC_SNARE]s");
        rhythm.addSubstitution('!', "[CRASH_CYMBAL_1]s");
        rhythm.addSubstitution('.', "Rs"); 
        fill.addSubstitution('*', "[BASS_DRUM]s"); 
        fill.addSubstitution('o', "[ELECTRIC_SNARE]s");
        fill.addSubstitution('!', "[CRASH_CYMBAL_1]s");
        fill.addSubstitution('?', "[LOW_MID_TOM]s");
        fill.addSubstitution('<', "[LOW_TOM]s");
        fill.addSubstitution('.', "Rs"); 

        returnPattern.add(rhythm.getPattern());
        returnPattern.repeat(7);
        returnPattern.add(fill.getPattern());
        return returnPattern;
    }

    private Pattern songMake(){
        Pattern returnSong = new Pattern();
        intro = drumsIntro();
        chorus = drumsChorus();
        verse1 = drumsVerse();
        verse2 = drumsVerse();
        verse3 = drumsVerse();
        bridge = drumsBridge();
        returnSong.add(intro);
        returnSong.add(verse1);
        returnSong.add(chorus);
        returnSong.add(verse2);
        returnSong.add(chorus);
        returnSong.add(bridge);
        returnSong.add(verse3);
        returnSong.add(chorus);
        return returnSong;
    }
    
    public Pattern drumReturn(){
        return song;
    }
    
    public Drums(){
        song = songMake();
    }
    
    
    
    
    public static void main(String[] args){
        
    }
}