

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import java.awt.BorderLayout;

public class MainFrame extends JFrame {
	public MainFrame() {
		
		//create a new model to process the file and add kids
		String filename="";
		Model model = new Model(filename);
		
		this.setTitle("Data Visualizer");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		SliderView sliders = new SliderView(model);
		panel.add(sliders);
		
		ComboBoxPane comboBox = new ComboBoxPane(model, sliders);
		panel.add(comboBox);
		getContentPane().add(panel, BorderLayout.NORTH);
		
		
		//create canvas and add to the model
		CanvasPane canvas = new CanvasPane(model);
		getContentPane().add(canvas, BorderLayout.CENTER);
		//model.addChildren(canvas);
		
		JPanel textLegend = new JPanel();
		textLegend.setLayout(new GridLayout(2,1));
		textLegend.add(new LegendPane());
		
		//add infoPane to children
		InfoPane info = new InfoPane(model);
		//model.addChildren(info);
		textLegend.add(info);  
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
