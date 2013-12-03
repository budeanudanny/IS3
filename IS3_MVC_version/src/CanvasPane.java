import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

public class CanvasPane extends JPanel implements ViewController {
  
	private Model model;

	private ArrayList<Double> valuesForY;
	private ArrayList<Double> valuesForX;
	private String[] names;
	private ArrayList<Rectangle> vertBars = new ArrayList<Rectangle>();
	
	private String title;
 
	//private String[] colours = {"black","green","yellow","purple","blue","red"};
	
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
		
		valuesForY = new ArrayList<Double>();
		valuesForX = new ArrayList<Double>();
	
		
		for (String key : model.getData().keySet())
			valuesForY.add(model.getData().get(key).get(selectedItem1));
		
		//System.out.println(valuesY);
		
		for (String key : model.getData().keySet())
			valuesForX.add(model.getData().get(key).get(selectedItem2));
		
		//System.out.println(valuesX);
	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    if (valuesForY == null || valuesForY.size() == 0)
	      return;
	    double minValue = 0;
	    double maxValue = 0;
	    for (int i = 0; i < valuesForY.size(); i++) {
	      if (minValue > valuesForY.get(i))
	        minValue = valuesForY.get(i);
	      if (maxValue < valuesForY.get(i))
	        maxValue = valuesForY.get(i);
    }

    Dimension d = getSize();
    int clientWidth = d.width;
    int clientHeight = d.height;
    int barWidth = clientWidth / valuesForY.size();

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
    
    //creation of the bars in the chart
    for (int i = 0; i < valuesForY.size(); i++) {
	    int valueX = i * barWidth + 1;
	    int valueY = top;
	    int height = (int) (valuesForY.get(i) * scale);
	    if (valuesForY.get(i) >= 0)
	    	valueY += (int) ((maxValue - valuesForY.get(i)) * scale);
	    else {
	    	valueY += (int) (maxValue * scale);
	    	height = -height;
	    }
	    //implementing independent colour of each bar
	    if(model.getData().get(names[i]).get(model.getData().get(names[i]).size()-1)==1){
	    	// 1 = Europe = Green
	    	Rectangle r = new Rectangle(valueX, valueY, barWidth -2, height);
			g.setColor(Color.green); //color of the bar
 			g.fillRect(valueX, valueY, barWidth - 2, height);
			g.drawRect(valueX, valueY, barWidth - 2, height);
			g.setColor(Color.black); //color of the text
			int labelWidth = labelFontMetrics.stringWidth(names[i]);
			x = i * barWidth + (barWidth - labelWidth) / 2;
			g.drawString(names[i], x, y);
			vertBars.add(r);
			
	    }else if(model.getData().get(names[i]).get(model.getData().get(names[i]).size()-1)==2){
	    	// 2 = Africa = Black *wink wink*
	    	Rectangle r = new Rectangle(valueX, valueY, barWidth -2, height);
	    	g.setColor(Color.black);
		    g.fillRect(valueX, valueY, barWidth - 2, height);
		    g.drawRect(valueX, valueY, barWidth - 2, height);
		    g.setColor(Color.black);
		    int labelWidth = labelFontMetrics.stringWidth(names[i]);
		    x = i * barWidth + (barWidth - labelWidth) / 2;
		    g.drawString(names[i], x, y);
		    vertBars.add(r);
		    
	    }else if(model.getData().get(names[i]).get(model.getData().get(names[i]).size()-1)==3){
	    	// 3 = Australia = Red
	    	Rectangle r = new Rectangle(valueX, valueY, barWidth -2, height);
			g.setColor(Color.red);
			g.fillRect(valueX, valueY, barWidth - 2, height);
			g.drawRect(valueX, valueY, barWidth - 2, height);
			g.setColor(Color.black);
			int labelWidth = labelFontMetrics.stringWidth(names[i]);
			x = i * barWidth + (barWidth - labelWidth) / 2;
			g.drawString(names[i], x, y);
			vertBars.add(r);
    	
	    }else if(model.getData().get(names[i]).get(model.getData().get(names[i]).size()-1)==4){
	    	// 4 = North America = Blue
	    	Rectangle r = new Rectangle(valueX, valueY, barWidth -2, height);
			g.setColor(Color.blue);
			g.fillRect(valueX, valueY, barWidth - 2, height);
			g.drawRect(valueX, valueY, barWidth - 2, height);
			g.setColor(Color.black);
			int labelWidth = labelFontMetrics.stringWidth(names[i]);
			x = i * barWidth + (barWidth - labelWidth) / 2;
			g.drawString(names[i], x, y);
			vertBars.add(r);
    	
	    }else if(model.getData().get(names[i]).get(model.getData().get(names[i]).size()-1)==5){
	    	// 5 = Asia = Yellow *wink wink*
	    	Rectangle r = new Rectangle(valueX, valueY, barWidth -2, height);
			g.setColor(Color.yellow);
			g.fillRect(valueX, valueY, barWidth - 2, height);
			g.drawRect(valueX, valueY, barWidth - 2, height);
			g.setColor(Color.black);
			int labelWidth = labelFontMetrics.stringWidth(names[i]);
			x = i * barWidth + (barWidth - labelWidth) / 2;
			g.drawString(names[i], x, y);
			vertBars.add(r);
    	
	    }else if(model.getData().get(names[i]).get(model.getData().get(names[i]).size()-1)==6){
	    	// 6 = South America = Magenta
	    	Rectangle r = new Rectangle(valueX, valueY, barWidth -2, height);
			g.setColor(Color.magenta);
			g.fillRect(valueX, valueY, barWidth - 2, height);
			g.drawRect(valueX, valueY, barWidth - 2, height);
			g.setColor(Color.black);
			int labelWidth = labelFontMetrics.stringWidth(names[i]);
			x = i * barWidth + (barWidth - labelWidth) / 2;
			g.drawString(names[i], x, y);
			vertBars.add(r);
    	
	    }
    }
    
    System.out.println(vertBars.toString());
  }
	
	public void update(){
		
	}
	
	
	
	
	
}
