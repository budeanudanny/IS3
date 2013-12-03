

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import java.awt.BorderLayout;

public class MainFrame extends JFrame {
	public MainFrame() {
		
		//create a new model to process the file and add kids
		String filename="";
		Model model = new Model("data.csv");
		//System.out.println(model.getData().toString());
		this.setTitle("Data Visualizer");
		this.setPreferredSize(new Dimension(1800,900));
		//this.setResizable(false);  //in case we want to not make it resizable and not bother with dynamically resizable
		//components even though the bar chart is behaving like that if you resize the window
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		SliderView sliders = new SliderView(model);
		panel.add(sliders);
		
	
		
		
		CanvasPane canvas = new CanvasPane(model);
		getContentPane().add(canvas, BorderLayout.CENTER);
		
		//model.addChildren(canvas);
		
		ComboBoxPane comboBox = new ComboBoxPane(model, sliders, canvas);
		panel.add(comboBox);
		getContentPane().add(panel, BorderLayout.NORTH);
		
		
	
		
	
		
		
		
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
