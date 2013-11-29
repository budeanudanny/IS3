

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import java.awt.BorderLayout;

public class MainFrame extends JFrame {
	public MainFrame() {
		
		this.setTitle("Data Visualizer");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		panel.add(new SliderView());
		panel.add(new ComboBoxPane());
		getContentPane().add(panel, BorderLayout.NORTH);
		
		
		
		CanvasPane canvas = new CanvasPane();
		getContentPane().add(canvas, BorderLayout.CENTER);
		
		
		JPanel textLegend = new JPanel();
		textLegend.setLayout(new GridLayout(2,1));
		textLegend.add(new LegendPane());
		textLegend.add(new InfoPane());  
		textLegend.setVisible(true);
		
		getContentPane().add(textLegend, BorderLayout.EAST);

		this.pack();
		this.setVisible(true);
		
	}
	
	
	public static void main(String args[]){
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainFrame mf = new MainFrame();
				mf.setVisible(true);
				mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		
		});
		
	}

}
