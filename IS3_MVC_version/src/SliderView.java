

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
    	
    	jSlider1 = new javax.swing.JSlider();
    	jSlider1.setMajorTickSpacing(0);
    	jSlider1.setPaintLabels(true);
    	jSlider1.setPaintTicks(true);
        jSlider2 = new javax.swing.JSlider();
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
    	
    	if (value ==1){
    		min = 15;
    		max= 75;
    		interval = 20;
    	}
    	else if (value == 2){
    		
    	}
    	
    	
    	if (box.compareTo("b1")==0){
    		jSlider1.setMinimum(min);
    		jSlider1.setMaximum(max);
    		jSlider1.setPaintTicks(true);
        	jSlider1.setMajorTickSpacing(interval);
        	//jSlider1.createStandardLabels(50);
    	}
    	else {
    		jSlider2.setMinimum(min);
    		jSlider2.setMaximum(max);

        	jSlider2.setMajorTickSpacing(interval);
        	//jSlider2.createStandardLabels(50);
    	}
    }
    
    

}
