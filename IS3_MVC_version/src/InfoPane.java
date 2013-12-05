

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class InfoPane extends JPanel{
	private Model model;
	protected JTextArea info = new JTextArea();
	
	InfoPane(Model m){
		model=m;
		JTextArea info = new JTextArea("");
		this.add(info);
		this.setVisible(true);
		this.info.setEditable(false);
	}
	
	
	
	public JTextArea getInfo() {
		return info;
	}



	public void setInfo(JTextArea info) {
		this.info = info;
	}


}
