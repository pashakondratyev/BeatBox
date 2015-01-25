import java.io.*;
import java.util.ArrayList;

public class Algorithms{
    
    //Bjorklund Algorithm
    
    public static String makeRhythm( int beats, int hits){
        ArrayList<Integer> counts = new ArrayList<Integer>();
        ArrayList<Integer> remainders = new ArrayList<Integer>();
    
        int divisor = beats - hits;
        int level = 0;
    
        if( hits > beats ){
            throw new IllegalArgumentException( "Hits cannot be greater than beats");
        }

        remainders.add(hits);
        while(true){
            counts.add( divisor / remainders.get(level));
            remainders.add( divisor % remainders.get(level));
            divisor = (int)remainders.get(level);
            level++;
            if (remainders.get(level) <= 1){
                break;
            }
        }
        counts.add(divisor);
        final ArrayList<Integer> remainders2 = remainders;
        final ArrayList<Integer> counts2 = counts;
        
        class Builder{ 
            String pattern = "";
            private void build(int j){
                
                if(j == -1){
                    pattern += ".";
                }
                else if( j == -2 ){
                    pattern += "*";
                }
                else{
                    for( int i = 0; i < counts2.get(j); i++){
                        build( j - 1 );
                    }
                    if (remainders2.get(j) != 0){
                        build(j - 2);
                    }
                }
            }
            
            private String buildReturn(int j){
                build(j);
                return pattern;
            } 
            
        }
        
        String pattern;
        pattern = new Builder().buildReturn(level);
        int index = pattern.indexOf("*");
        pattern = pattern.substring(index) + pattern.substring(0, index);
        return(pattern);
    }
    
    public static void main(String[] args){
    }
}
