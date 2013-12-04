

import java.awt.Component;
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
    Model model;
    
    
    SliderView(Model m){
    	model=m;
    	this.setLayout(new GridLayout(2,1));
    	
    	jSlider1 = new RangeSlider();
    	jSlider1.setMajorTickSpacing(0);
    	jSlider1.setPaintLabels(true);
    	jSlider1.setPaintTicks(true);
    	jSlider1.addChangeListener(new Modified());
    	jSlider1.setName("slider1");
        jSlider2 = new RangeSlider();
        jSlider2.setMajorTickSpacing(0);
		jSlider2.setPaintTicks(true);
        jSlider2.setPaintLabels(true);
    	jSlider2.addChangeListener(new Modified());
    	jSlider2.setName("slider2");
    	
        this.add(jSlider1);
        this.add(jSlider2);
        
        this.setVisible(true);
        
    }
    
   
    
    
    
    
    public void update(String box, int value){
    	int min=0;
    	int max=0;
    	int interval=0;
    	
    	//set the labels for the sliders according to the comboBox
    	
    	if (value ==0){
    		min = 0;
    		max= 75;
    		interval = 15;
    	}
    	else if (value == 1){
    		min = 0;
    		max= 70;	//70000
    		interval = 10;	//70000
    	}
    	else if (value ==2){
    		min = 0;
    		max= 1500000;
    		interval = 200000;
    	}
    	else if (value == 3){
    		min = 0;
    		max= 18;
    		interval = 3;
    	}
    	else if (value ==4){
    		min = 0;
    		max= 6000;
    		interval = 1000;
    	}
    	else if (value == 5){
    		min = 0;
    		max= 55;
    		interval = 10;
    	}
    	else if (value ==6){
    		min = 0;
    		max= 100;
    		interval = 20;
    	}
    	else if (value == 7){
    		min = 2;
    		max= 580;
    		interval = 100;
    	}
    	else if (value ==8){
    		min = 15;
    		max= 75;
    		interval = 20;
    	}
    	else if (value == 9){
    		min = 0;
    		max= 50;
    		interval = 10;
    	}
    	else if (value ==10){
    		min = 0;
    		max= 40;
    		interval = 10;
    	}
    	else if (value == 11){
 
    		min = 0;
    		max= 40;
    		interval = 10;
    	}
    	else if (value ==12){

    		min = 0;
    		max= 120;
    		interval = 20;
    	}
    	
    	
    	
    	
    	if (box.compareTo("b1")==0){
    		jSlider1.setMinimum(min);
    		jSlider1.setMaximum(max);
        	jSlider1.setMajorTickSpacing(5);
    		jSlider1.setPaintTicks(true);
    		jSlider1.setPaintLabels(true);

        	//jSlider1.createStandardLabels(50);
    	}
    	else {
    		jSlider2.setMinimum(min);
    		jSlider2.setMaximum(max);
    		jSlider2.setMajorTickSpacing(interval);
       		jSlider2.setPaintTicks(true);
    		jSlider2.setPaintLabels(true);

    	}
    }
    
    
    private class Modified implements ChangeListener{
    	double min = -1D,max = -1D;

		public void stateChanged(ChangeEvent e) {

			RangeSlider s = (RangeSlider) e.getSource();

			if (s.getValue() == s.getUpperValue())
			{

			}
			else{
				if (s.getName().equals("slider1")){
					min = s.getValue();
					max = s.getUpperValue();
				}else if (s.getName().equals(jSlider2)){
					min = s.getValue();
					max = s.getUpperValue();
				}
			// TODO Auto-generated method stub
			}
		}
    
    } 

}
