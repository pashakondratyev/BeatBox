import org.jfugue.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
public class BeatBox{
    //Driver class
    
    //Main Stuff
    private String root;
    private String[] scale;
    private String[] chords;
    private String[] chordProgression;
    private String[] chordProgressionBridge;
    int tempo;
    
    //Song parts (Instruments)
    public Pattern drums;
    public Pattern bass;
    public Pattern melody;
    public Pattern strummer;
    public Pattern song;

    public void setTempo( int t ){
        tempo = t;
    }

    private void setKey( String key, String mode ){
        root = key;
        ScaleGenerator generateScale = new ScaleGenerator( key, mode);
        scale = generateScale.scaleOutput;
        chords = generateScale.chordOutput;
        ProgressionGenerator prog = new ProgressionGenerator();
        chordProgression = prog.generator( chords );
        chordProgressionBridge = prog.generator( chords );
    }
    
    public void assignPatterns(){
        drums = (new Drums()).drumReturn();
        bass = (new Bass( chordProgression, chordProgressionBridge )).bassReturn(); 
        strummer = (new Chords( chordProgression, chordProgressionBridge)).chordReturn();
        melody = (new Melody( scale )).melodyReturn();

    }

    
    public BeatBox(){
        setKey( "C", "major" );
        assignPatterns();
        song = new Pattern();
        song.add(drums);
        song.add(bass);
        song.add(strummer);
        song.add(melody);
    }

    public BeatBox( String key, String mode, String tempo){
        setKey( key, mode );
        assignPatterns();
        song = new Pattern();
        song.add( "T"+ tempo);
        song.add(drums);
        song.add(bass);
        song.add(strummer);
        song.add(melody);
    }
}