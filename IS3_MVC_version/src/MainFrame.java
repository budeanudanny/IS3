

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.BorderLayout;

public class MainFrame extends JFrame {
	
	
	
	public MainFrame() {

		//get screen resolution to create main window accordingly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		

		
		//create a new model to process the file and add kids
		//String filename="";
		Model model = new Model("Country_data.csv");
		//System.out.println(model.getData().toString());
		this.setTitle("Data Visualizer");
		this.setPreferredSize(new Dimension(width-50,height-50));

		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		
	
		
		

		//add all the different components to the main frame
		InfoPane info = new InfoPane(model);
		info.setPreferredSize(new Dimension(30,50));
		info.setVisible(true);

		
		CanvasPane canvas = new CanvasPane(model, info);
		getContentPane().add(canvas, BorderLayout.CENTER);
		

		SliderView sliders = new SliderView(model,canvas);
		panel.add(sliders);
		
		ComboBoxPane comboBox = new ComboBoxPane(model, sliders, canvas);
		
		panel.add(comboBox);
		getContentPane().add(panel, BorderLayout.NORTH);
		
		LegendPane leg = new LegendPane(new ImageIcon("Legend.png").getImage());
		leg.setVisible(true);
		
		JPanel textLegend = new JPanel();
		textLegend.setLayout(new BorderLayout());

		textLegend.add(leg, BorderLayout.WEST);
		
		

		
		textLegend.add(info, BorderLayout.CENTER);  
		textLegend.setVisible(true);

		getContentPane().add(textLegend, BorderLayout.SOUTH);
		
		this.pack();
		this.setLocationRelativeTo(null); //this will center the main window on the screen 
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
