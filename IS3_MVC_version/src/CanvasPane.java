import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class CanvasPane extends JPanel implements ViewController {
  
	private Model model;

	private ArrayList<Double> valuesY;
	private ArrayList<Double> valuesX;
	private String[] names;
	
	private String title;
 
	private String[] colours = {"black","green","yellow","purple","blue","red"};
	
	protected int selectedItem1 = -1;
	protected int selectedItem2 = -1;

	public void setSelectedItem1(int selectedItem1) {
		this.selectedItem1 = selectedItem1;
	}

	public int getSelectedItem1() {
		return selectedItem1;
	}

	public int getSelectedItem2() {
		return selectedItem2;
	}

	public void setSelectedItem2(int selectedItem2) {
		this.selectedItem2 = selectedItem2;
	}
	
	

	public CanvasPane(Model m) {
		model = m;
		title = "";
		names = new String[model.getData().keySet().size()];
		createNames();
	 }

	public void createNames(){
		//aci trebe sa bagam si info din slidere
		int k = 0;
		for(String key : model.getData().keySet())
			names[k++] = key;
	}
	
	public void createValues(){
		if (selectedItem1 == -1 || selectedItem2 == -1)
			return;
		
		valuesY = new ArrayList<Double>();
		valuesX = new ArrayList<Double>();
	
		
		for (String key : model.getData().keySet())
			valuesY.add(model.getData().get(key).get(selectedItem1));
		
		//System.out.println(valuesY);
		
		for (String key : model.getData().keySet())
			valuesX.add(model.getData().get(key).get(selectedItem2));
		
		//System.out.println(valuesX);
	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    if (valuesY == null || valuesY.size() == 0)
	      return;
	    double minValue = 0;
	    double maxValue = 0;
	    for (int i = 0; i < valuesY.size(); i++) {
	      if (minValue > valuesY.get(i))
	        minValue = valuesY.get(i);
	      if (maxValue < valuesY.get(i))
	        maxValue = valuesY.get(i);
    }

    Dimension d = getSize();
    int clientWidth = d.width;
    int clientHeight = d.height;
    int barWidth = clientWidth / valuesY.size();

    Font titleFont = new Font("SansSerif", Font.BOLD, 20);
    FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
    Font labelFont = new Font("SansSerif", Font.PLAIN, 10);
    FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

    int titleWidth = titleFontMetrics.stringWidth(title);
    int y = titleFontMetrics.getAscent();
    int x = (clientWidth - titleWidth) / 2;
    g.setFont(titleFont);
    //g.drawString(title, x, y);

    int top = titleFontMetrics.getHeight();
    int bottom = labelFontMetrics.getHeight();
    if (maxValue == minValue)
      return;
    double scale = (clientHeight - top - bottom) / (maxValue - minValue);
    y = clientHeight - labelFontMetrics.getDescent();
    g.setFont(labelFont);

    for (int i = 0; i < valuesY.size(); i++) {
	    int valueX = i * barWidth + 1;
	    int valueY = top;
	    int height = (int) (valuesY.get(i) * scale);
	    if (valuesY.get(i) >= 0)
	    	valueY += (int) ((maxValue - valuesY.get(i)) * scale);
	    else {
	    	valueY += (int) (maxValue * scale);
	    	height = -height;
	    }
	    //implementing independent colour of each bar
	    if(i%2 ==0){
			g.setColor(Color.blue);
			g.fillRect(valueX, valueY, barWidth - 2, height);
			g.setColor(Color.black);
			g.drawRect(valueX, valueY, barWidth - 2, height);
			int labelWidth = labelFontMetrics.stringWidth(names[i]);
			x = i * barWidth + (barWidth - labelWidth) / 2;
			g.drawString(names[i], x, y);
	    }else if(i%3 == 0){
    	  
	    	  g.setColor(Color.yellow);
		      g.fillRect(valueX, valueY, barWidth - 2, height);
		      g.setColor(Color.black);
		      g.drawRect(valueX, valueY, barWidth - 2, height);
		      int labelWidth = labelFontMetrics.stringWidth(names[i]);
		      x = i * barWidth + (barWidth - labelWidth) / 2;
		      g.drawString(names[i], x, y);
	    }else {
    	  g.setColor(Color.red);
	      g.fillRect(valueX, valueY, barWidth - 2, height);
	      g.setColor(Color.black);
	      g.drawRect(valueX, valueY, barWidth - 2, height);
	      int labelWidth = labelFontMetrics.stringWidth(names[i]);
	      x = i * barWidth + (barWidth - labelWidth) / 2;
	      g.drawString(names[i], x, y);
    	  
      }
    }
  }
	
	public void update(){
		
	}
	
	
	
	
	
}
