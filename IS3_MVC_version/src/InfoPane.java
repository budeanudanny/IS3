

import javax.swing.JPanel;
import javax.swing.JTextPane;

public class InfoPane extends JPanel implements ViewController{
	private Model model;
	
	InfoPane(Model m){
		model=m;
		JTextPane info = new JTextPane();
		info.setVisible(true);
	}
	
	
	public void update(){
		
	}
}
