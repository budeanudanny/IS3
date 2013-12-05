
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
	private String[] array1 = {
			"Select Category",
			"Healthy life expectancy (HALE) at birth (years) both sexes",
			"Gross National Income per capita (PPP international $)",
			"Population (in thousands) total",
			"Per capita recorded alcohol consumption (litres) among adults (>15 years)",
			"Per capita total expenditure on health (PPP int. $)",
			"Prevalence of current tobacco use among adults (>15y) (%) both sexes",
			"Pop. with sustainable access to improved drinking water sources (%) total",
			"Net primary school enrolment ratio (%)" };
	private String[] array2 = { "Select Category", "Team Size", "Gold medals",
			"Silver medals", "Bronze medals", "Total number of medals" };

	public void setArray(String[] array) {
		this.array1 = array;
	}

	/*
	 * constructor to initialise the panel with 2 combo boxes, add listeners to them and add them to the panel
	 */
	ComboBoxPane(Model m, SliderView s, CanvasPane c) {
		parent = c;
		model = m;
		sliders = s;

		this.setLayout(new GridLayout(2, 1));

		JComboBox box1 = new JComboBox(array2);
		box1.addActionListener(new ActionSelect());
		box1.setName("box1");
		JComboBox box2 = new JComboBox(array1);
		box2.addActionListener(new ActionSelect());
		box2.setName("box2");

		this.add(box1);
		this.add(box2);

		this.setVisible(true);

	}

	//class to implement ActionListener, to process user input
	private class ActionSelect implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JComboBox b = (JComboBox) e.getSource();
			int index = -1;

			/*
			 * checks to see what value has been selected from the check box
			 */
			Object value = b.getSelectedItem();
			if (value
					.equals("Healthy life expectancy (HALE) at birth (years) both sexes"))
				index = 0;
			else if (value
					.equals("Gross National Income per capita (PPP international $)"))
				index = 1;
			else if (value.equals("Population (in thousands) total"))
				index = 2;
			else if (value
					.equals("Per capita recorded alcohol consumption (litres) among adults (>15 years)"))
				index = 3;
			else if (value
					.equals("Per capita total expenditure on health (PPP int. $)"))
				index = 4;
			else if (value
					.equals("Prevalence of current tobacco use among adults (>15y) (%) both sexes"))
				index = 5;
			else if (value
					.equals("Pop. with sustainable access to improved drinking water sources (%) total"))
				index = 6;
			else if (value.equals("Net primary school enrolment ratio (%)"))
				index = 7;
			/*
			 * since the following values are part of the first combo box, the if clause also calls the method in the sliderView
			 * class that updates the labels and the range of the slider, according to the min and max in the CSV file for the 
			 * selected categories
			 */
			else if (value.equals("Team Size")) {
				index = 8;
				sliders.createSlider(0, 600, 100);
			} else if (value.equals("Gold medals")) {
				index = 9;
				sliders.createSlider(0, 50, 5);
			} else if (value.equals("Silver medals")) {
				index = 10;
				sliders.createSlider(0, 40, 5);
			} else if (value.equals("Bronze medals")) {
				index = 11;
				sliders.createSlider(0, 40, 5);
			} else if (value.equals("Total number of medals")) {
				index = 12;
				sliders.createSlider(0, 120, 10);
			}
			
			// if the user selects 'select category' in both combo boxes, the following lines of code allow the user
			// to continue visualizing data
			else
			{
				parent.removeAll();
				parent.revalidate();
				parent.repaint();
				return;
			}
			

			/*
			 * the following if decides from which combo box the input comes from
			 * if it comes from the first one, it sends the value to the model and the canvas pane,
			 * if it comes from the second combo box, it sends the value to the canvas pane and call the method
			 * that orders the dataset that will be provided as input
			 */

			if (b.getName().compareTo("box1") == 0) {
				parent.setSelectedItem1(index);
				model.setSelectedYItem(index);
			}

			else {
				parent.setSelectedItem2(index);
				model.createOXOrderedCountries(index); // create my new list
				parent.createNames(-1, -1);
			}

			/*
			 * calls the method that creates the dataset and the repaint methos in the Canvas class
			 */
			
			parent.createValues(-1, -1, -1, -1);
			parent.repaint();

		}

	}

	public String[] getArray() {
		return array1;
	}

}
