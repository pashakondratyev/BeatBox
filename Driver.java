//Import packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.jfugue.*;
import java.io.File;
import java.io.IOException;
//Main class
public class Driver implements ActionListener{
    //Declare variables
    JFrame frame1;
    JButton Submit, save , Play;
    JLabel aKey, aScale, aTempo, aSave;
    JTextField txtTempo , bSave;
    JComboBox cKey, cScale;
    GridLayout experimental = new GridLayout(5,3);
    BeatBox catchySong = new BeatBox();


    
    public Driver(){
	JFrame frame1 = new JFrame ("BeatBox Mixer");
	frame1.setSize(275,150);

	
	JPanel pane = new JPanel();
	pane.setLayout(experimental);


	
	//Create controls
	Submit = new JButton ("Submit");
	Play = new JButton("Play");
	aKey = new JLabel ("Key:");
	aScale = new JLabel ("Scale:");
	aTempo = new JLabel ("Tempo:");
	txtTempo = new JTextField (10);
	
	String[] bKey = {"A","A#" ,"B","C","D","D#","E","F","F#","G","G#"};
        cKey = new JComboBox(bKey);
	pane.add(cKey);
	
	String[] bScale = {"major","minor"};
        cScale = new JComboBox(bScale);
	pane.add(cScale);

	aSave = new JLabel ("To save as");

	save = new JButton("Save");
	
	bSave = new JTextField(10);
	
	pane.add (aKey);// A = LABEL , C = COMBOBOX
	pane.add(new Label(" "));
	pane.add(cKey);
	
	pane.add (aScale);
	pane.add(new Label(" "));
	pane.add(cScale);
	
	pane.add (aTempo);
	pane.add(new Label(" "));
	pane.add (txtTempo);

	pane.add(new Label(" "));
	pane.add (Submit);
	pane.add(Play);
	
	pane.add(aSave);
	pane.add(bSave);
	pane.add(save);
	
	

	//Set frame visible
	frame1.add(pane);
	frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame1.setVisible (true);
	
        Play.addActionListener(this);
    	Submit.addActionListener(this);
    	save.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e){
    	Player player = new Player();
	
		if (e.getSource() == Submit) {
		    try {
			int f = Integer.parseInt( txtTempo.getText() );
			
		    } catch ( Exception ex ) {
			System.out.println("Invalid tempo, enter a number:0-200");
			return;
		    }
		    if( 0 < Integer.parseInt(txtTempo.getText() ) && Integer.parseInt(txtTempo.getText()) < 200){
			
		    	catchySong = new BeatBox((String)cKey.getSelectedItem(), (String)cScale.getSelectedItem(), txtTempo.getText());
			System.out.print("Song in Key:");
			System.out.println(cKey.getSelectedItem() + " " + cScale.getSelectedItem());
			System.out.print("Song tempo:");
			System.out.println(txtTempo.getText());
			
			
			
				//Connection the multiple files
				//			BeatBox.setTempo(Integer.parseInt(txtTempo.getText()));
				//			ScaleGenerator.makeChords( cScale.getSelectedItem().toLowerCase());
			
			
		    }
		    else{
			System.out.println( txtTempo.getText() + " " + "is not valid. Please try again: 0-200");
		    }
		}
		
		if (e.getSource() == save){
		    if (bSave.getText().length() == 0){
			try {		
			    player.saveMidi(catchySong.song, new File("music-file.mid"));
			    
			} 	
			catch (IOException f){
			    //handle IO Exception
			}
		    }
		    if (bSave.getText().length() != 0){
			try{
			    String name = bSave.getText()+".mid";
			    player.saveMidi(catchySong.song, new File(name));
			}
			catch (IOException f){
			}
		    }
		}
		
		if (e.getSource() == Play){
		    player.play(catchySong.song);
		    player.stop();
		}
		
    }
    
    
    public static void main (String args[]){
	Driver g = new Driver();
    }
}

