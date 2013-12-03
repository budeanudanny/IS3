

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ComboBoxPane extends JPanel {
	private Model model;
	private SliderView sliders;
	private CanvasPane parent;
	private JComboBox box1, box2;
	private String[] array1= { "Select Category", "Healthy life expectancy (HALE) at birth (years) both sexes", "Gross National Income per capita (PPP international $)",
			"Population (in thousands) total", "Per capita recorded alcohol consumption (litres) among adults (>15 years)",
			"Per capita total expenditure on health (PPP int. $)", "Prevalence of current tobacco use among adults (>15y) (%) both sexes",
			"Pop. with sustainable access to improved drinking water sources (%) total", "Net primary school enrolment ratio (%)"};
	private String[] array2 = { "Select Category", "Team Size", "Gold medals", "Silver medals", "Bronze medals", "Total number of medals"};
	public void setArray(String[] array) {
		this.array1 = array;
	}


	ComboBoxPane(Model m, SliderView s, CanvasPane c){
		parent = c;
		model = m;
		sliders = s;

		//Create the combo box, select item at index 4.
		//Indices start at 0, so 4 specifies the pig.
		//array.setSelectedIndex(4);
		//petList.addActionListener(this);
	
	this.setLayout(new GridLayout(2,1));
	
	JComboBox box1 = new JComboBox(array1);
	box1.addActionListener(new ActionSelect());
	box1.setName("box1");
	JComboBox box2 = new JComboBox(array1);
	box2.addActionListener(new ActionSelect());
	box2.setName("box2");
	
	this.add(box1);
	this.add(box2);
	
	this.setVisible(true);
	
	}
	
	
	
	private class ActionSelect implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			JComboBox b = (JComboBox) e.getSource();
			int index = -1;
			//de rezolvat decala
			Object value = b.getSelectedItem();
			if (value.equals("Healthy life expectancy (HALE) at birth (years) both sexes"))
				index = 0;
			else if (value.equals("Gross National Income per capita (PPP international $)"))
				index = 1;
			else if (value.equals("Population (in thousands) total"))
				index = 2;
			else if (value.equals("Per capita recorded alcohol consumption (litres) among adults (>15 years)"))
				index = 3;
			else if (value.equals("Per capita total expenditure on health (PPP int. $)"))
				index = 4;
			else if (value.equals("Prevalence of current tobacco use among adults (>15y) (%) both sexes"))
				index = 5;
			else if (value.equals("Pop. with sustainable access to improved drinking water sources (%) total"))
				index = 6;
			else if (value.equals("Net primary school enrolment ratio (%)"))
				index = 7;
			else if (value.equals("Team Size"))
				index = 8;//de verificat TOATEEEEE... aAAAaaaAAAa
			else if (value.equals("Gold medals"))
				index = 9;
			else if (value.equals("Silver medals"))
				index = 10;
			else if (value.equals("Bronze medals"))
				index = 11;
			else if (value.equals("Total number of medals"))
				index = 12;
			
			{//vedem noi
				}
			
			
			
			int v=0;
			for (int i=0; i<array1.length; i++){
				if (array1[i].compareTo((String) value) == 0)
					v=i;
			}
			
			if (b.getName().compareTo("box1")==0){
				sliders.update("b1",v);
				parent.setSelectedItem1(++index);
			}
			
			else{
				sliders.update("b2", v);
				parent.setSelectedItem2(index);
				}
			parent.createValues();
			
		}
		
	}
	
	
	


	public String[] getArray() {
		return array1;
	}
	

}
