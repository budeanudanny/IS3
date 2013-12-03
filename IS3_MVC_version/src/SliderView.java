

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class SliderView extends JPanel {
	
    JSlider jSlider1;
    JSlider  jSlider2;
    Model model;
    
    
    SliderView(Model m){
    	model=m;
    	this.setLayout(new GridLayout(2,1));
    	
    	jSlider1 = new RangeSlider();
    	jSlider1.setMajorTickSpacing(0);
    	jSlider1.setPaintLabels(true);
    	jSlider1.setPaintTicks(true);
        jSlider2 = new RangeSlider();
        jSlider2.setMajorTickSpacing(0);
		jSlider2.setPaintTicks(true);
        jSlider2.setPaintLabels(true);
        
        this.add(jSlider1);
        this.add(jSlider2);
        
        this.setVisible(true);
        
    }
    
    private class Modified implements ActionListener{
    	double min = -1D,max = -1D;
		@Override
		public void actionPerformed(ActionEvent e) {
			JSlider s = (JSlider) e.getSource();
			if (s.getName().equals(jSlider1)){
				min = s.getValue();
			}else if (s.getName().equals(jSlider2)){
				max = s.getValue();
			}
			
		}
    
    
    
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
    		max= 70000;
    		interval = 10;
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
    		System.out.println("here");
    		min = 0;
    		max= 40;
    		interval = 10;
    	}
    	else if (value ==12){
    		System.out.println("here");
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
        	//jSlider2.createStandardLabels(50);
    	}
    }
    
    

}
