import java.util.Arrays;
public class ScaleGenerator{
    
    String chordSwag;
    int rootIndex;
    String[] scaleOutput = new String[7];
    String[] chordOutput = new String [7];
    String[] chords = new String[7];
    
    //All Notes
    String [] notes = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#" };
    
    //Major
    int[] major = {2,2,1,2,2,2,1};
    String[] majorChords = { "maj", "min", "min", "maj","maj", "min", "dim" };
    
    //Minor 
    int[] minor = {2,1,2,2,1,2,2};
    String[] minorChords = { "min", "dim", "maj", "min", "min", "maj","maj" };
    
    
    
    
    public ScaleGenerator(){ //The default constructor should generate a C Major scale
        rootIndex = 0;
        makeScale("C", major);
        makeChords("major");
    }
    
    public ScaleGenerator( String root, String mode){ //makes a scale with key root and mode mode
        rootIndex = 0;
        if (mode.equals("major")){
            makeScale(root, major);
            makeChords(mode);
        }

        if (mode.equals("minor")){
            makeScale(root, minor);
            makeChords(mode);
        }
    }
    
    public void makeChords(String chords){
        
        if (chords.equals("major")){
            for (int i = 0 ; i < majorChords.length; i++){
                chordOutput[i] = scaleOutput[i] + majorChords[i];
            }
        }
        
        if (chords.equals("minor")){
            for (int i = 0 ; i < minorChords.length; i++){
                chordOutput[i] = scaleOutput[i] + minorChords[i];
            }
        }
    }
    
    
    public void makeScale(String root, int[] scale){
        for (int i = 0; i < ((notes.length / 2) - 1); i ++){
            if (notes[i].equals(root)){
                rootIndex = i;
                break;
            }
        }


        scaleOutput[0] = notes[rootIndex];
        
        for (int j = 1; j < scale.length; j++){
            rootIndex += scale[j - 1];
            scaleOutput[j] = notes[rootIndex];
        }
    }
    
    
    public static void main(String[] args){

    }
    
    
}