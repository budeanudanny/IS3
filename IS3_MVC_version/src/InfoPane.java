

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.Border;

public class InfoPane extends JPanel{
	private Model model;
	protected JTextArea info ;
	
	InfoPane(Model m){
		model=m;
		info = new JTextArea("");
		
		Border border = BorderFactory.createLineBorder(Color.black);
	    
	    /*
	     * To set JTextField's border use,
	     * void setBorder(Border b)
	     * method.
	     */
	   
	    
		
		info.setForeground(Color.BLACK);
		Font font = new Font("Verdana", Font.BOLD, 12);
		info.setFont(font);
	    info.setBorder(border);

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
