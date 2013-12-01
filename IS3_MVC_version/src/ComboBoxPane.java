

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ComboBoxPane extends JPanel {
	private Model model;
	private SliderView sliders;
	private JComboBox box1, box2;
	private String[] array= { "Select Category", "Healthy life expectancy (HALE) at birth (years) both sexes", "Gross National Income per capita (PPP international $)",
			"Population (in thousands) total", "Per capita recorded alcohol consumption (litres) among adults (>15 years)",
			"per capita total expenditure on health (PPP int. $)", "Prevalence of current tobacco use among adults (>15y) (%) both sexes",
			"Pop. with sustainable access to improved drinking water sources (%) total", "Net primary school enrolment ratio (%)"};
	
	ComboBoxPane(Model m, SliderView s){
		model=m;
		sliders = s;

		//Create the combo box, select item at index 4.
		//Indices start at 0, so 4 specifies the pig.
		//array.setSelectedIndex(4);
		//petList.addActionListener(this);
	
	this.setLayout(new GridLayout(2,1));
	
	JComboBox box1 = new JComboBox(array);
	box1.addActionListener(new ActionSelect());
	box1.setName("box1");
	JComboBox box2 = new JComboBox(array);
	box2.addActionListener(new ActionSelect());
	box2.setName("box2");
	
	this.add(box1);
	this.add(box2);
	
	this.setVisible(true);
	
	}
	
	
	private class ActionSelect implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			JComboBox b = (JComboBox) e.getSource();

			Object value = b.getSelectedItem();
			System.out.println(e.getSource().toString());
			int v=0;
			for (int i=0; i<array.length; i++){
				if (array[i].compareTo((String) value) == 0)
					v=i;
			}
			
			if (b.getName().compareTo("box1")==0)
				sliders.update("b1",v);
			
			else sliders.update("b2", v);
			
		}
		
	}

}
