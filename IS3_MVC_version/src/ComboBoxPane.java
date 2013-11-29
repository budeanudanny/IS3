

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ComboBoxPane extends JPanel {
	
	
	ComboBoxPane(){
		
		String[] array = { "Select Category", "Healthy life expectancy (HALE) at birth (years) both sexes", "Gross National Income per capita (PPP international $)",
				"Population (in thousands) total", "Per capita recorded alcohol consumption (litres) among adults (>15 years)",
				"per capita total expenditure on health (PPP int. $)", "Prevalence of current tobacco use among adults (>15y) (%) both sexes",
				"Pop. with sustainable access to improved drinking water sources (%) total", "Net primary school enrolment ratio (%)"};

		//Create the combo box, select item at index 4.
		//Indices start at 0, so 4 specifies the pig.
		//array.setSelectedIndex(4);
		//petList.addActionListener(this);
	
	this.setLayout(new GridLayout(2,1));
	
	JComboBox box1 = new JComboBox(array);
	JComboBox box2 = new JComboBox(array);
	
	this.add(box1);
	this.add(box2);
	
	this.setVisible(true);
	
	}

}
