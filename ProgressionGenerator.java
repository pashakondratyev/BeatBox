import java.util.Random;
import java.util.Arrays;
public class ProgressionGenerator{

	private int[][] possibleProgressions = {//I added lots for variety
		{1,4,5,4},
		{1,4,5,1},
		{1,6,4,5},
		{1,4,2,5},
		{1,3,4,5},
		{1,5,1,4},
		{1,6,4,7},
		{1,6,3,7},
		{1,7,4,1},
		{1,2,4,5},
		{1,5,4,1},
		{1,4,5,4}
	};

	private String[] chordProgression = new String[4];

	public String[] generator( String[] chords){
		//First lets pick one of possible progressions
		String[] chordOutput = new String[4];
		int[] pickedProgression;
		Random rand = new Random();
		pickedProgression = possibleProgressions[ rand.nextInt(possibleProgressions.length) ];
		for(int i = 0; i < 4; i++){
			chordOutput[i] = chords[ pickedProgression[i] -1 ];
		}
		return chordOutput;
	}

	public ProgressionGenerator(){
	}
	

	public static void main(String[] args){
	}
}