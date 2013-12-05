

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderView extends JPanel {
	
    RangeSlider jSlider1;
    RangeSlider  jSlider2;
    JPanel panel1, panel2;
    Model model;
    private int minX,minY,maxX,maxY;
    CanvasPane parent;
    
    
    public int getMinX() {
		return minX;
	}

	public void setMinX(int minX) {
		this.minX = minX;
	}

	public int getMinY() {
		return minY;
	}

	public void setMinY(int minY) {
		this.minY = minY;
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	/*
	 * constructor that adds the sliders to the panel and sets the panel to be visible
	 */
	SliderView(Model m, CanvasPane cp){
    	model=m;
    	parent = cp;
    	this.setLayout(new BorderLayout());

    	jSlider1 = new RangeSlider();
		jSlider1.setPreferredSize(new Dimension(920, 50));
    	panel1= new JPanel();
    	panel1.add(jSlider1);
        jSlider2 = new RangeSlider();
    	panel2 = new JPanel();
    	panel2.add(jSlider2);
    	panel2.setVisible(false);
        this.add(panel1, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.SOUTH);
        
        this.setVisible(true);
        
    }
	
	/*
	 * method to instantiate sliders according to the value that is being selected in the first
	 * combo box.
	 * the values given as parameter, are defined in the combo box class and represent the range of the slider and the
	 * interval at which the labels should be displayed
	 */
	public void createSlider(int min, int max, int interval){
		this.panel1.removeAll();
		this.panel1.revalidate();
		this.repaint();
		
		RangeSlider slider1= new RangeSlider(min, max);
		slider1.setPreferredSize(new Dimension(920, 50));
		slider1.setMajorTickSpacing(interval);
		slider1.setPaintLabels(true);
		slider1.setPaintTicks(true);
		slider1.addChangeListener(new Modified());
		slider1.setName("slider1");
		
		this.panel1.add(slider1);
		this.panel1.revalidate();
		this.panel1.repaint();
		
		this.revalidate();
		this.repaint();
	}


	
	/*
	 * class that responds to user input by retrieving the values of the sliders' ticks and updates what is 
	 * displayed in the canvasPane
	 */

    private class Modified implements ChangeListener{
    	

		public void stateChanged(ChangeEvent e) {

			RangeSlider s = (RangeSlider) e.getSource();

			if (s.getValue() == s.getUpperValue())
			{

			}
			else{
				if (s.getName().equals("slider1")){
					minY = s.getValue();
					maxY = s.getUpperValue();
					parent.createValues(minY, maxY, -1, -1);
					model.updateRestData(minY, maxY);
					parent.createNames(minY, maxY);
					
					parent.repaint();
					}else if (s.getName().equals("slider2")){
					minX = s.getValue();
					maxY = s.getUpperValue();
					parent.createValues(-1, -1, minX, maxX);
					model.updateRestData(minX, maxX);
					parent.repaint();
					
				}
			}
		}
    
    } 

}
