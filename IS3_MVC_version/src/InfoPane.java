

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class InfoPane extends JPanel implements ViewController{
	private Model model;
	private JTextArea info;
	
	InfoPane(Model m){
		model=m;
		JTextArea info = new JTextArea("Country: \n" + "Attribute1: \n" + "Attribute2: ");
		info.setVisible(true);
		//info.setEditable(false);
	}
	
	
	
	public JTextArea getInfo() {
		return info;
	}



	public void setInfo(JTextArea info) {
		this.info = info;
	}



	public void update(){
		
	}
}
