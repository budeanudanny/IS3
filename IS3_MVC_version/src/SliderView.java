

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;

public class SliderView extends JPanel {
	
    JSlider jSlider1;
    JSlider  jSlider2;
    
    
    SliderView(){
    	
    	this.setLayout(new GridLayout(2,1));
    	
    	jSlider1 = new javax.swing.JSlider();
        jSlider2 = new javax.swing.JSlider();
        
        this.add(jSlider1);
        this.add(jSlider2);
        
        this.setVisible(true);
        
    }

}
