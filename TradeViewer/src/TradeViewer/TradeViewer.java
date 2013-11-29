package TradeViewer;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
/* 
   TradeViewer.java
   
   Really just a wrapper for TradeViewerFrame, given that the 
   filename here is hardwired.

 */

public class TradeViewer {
  public static void main(String args[]) {
   /* 
	JFrame.setDefaultLookAndFeelDecorated(true);
	JDialog.setDefaultLookAndFeelDecorated(true);
	JFrame frame = new JFrame("Open File Frame");
	frame.setLayout(new FlowLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JButton button = new JButton("Select File");
    button.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent ae) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
              File selectedFile = fileChooser.getSelectedFile();
              TradeViewerFrame frame1 = new TradeViewerFrame(selectedFile.getName()); 
            }
          }
        });
        frame.add(button);
        frame.pack();
        frame.setVisible(true);	
    */
    TradeViewerFrame frame1 = new TradeViewerFrame("data500withXandY.csv");

  }
}





