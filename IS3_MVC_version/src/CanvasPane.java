import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextPane;

public class CanvasPane extends JPanel implements MouseListener {

	private Model model;

	private ArrayList<Double> valuesForY;
	private ArrayList<Double> valuesForX;
	private String[] names;
	private ArrayList<VertRect> vertBars = new ArrayList<VertRect>();
	private VertRect clickedVertRect;
	private String title;
	protected int selectedItem1 = -1;
	protected int selectedItem2 = -1;
	private InfoPane info;


	//class in which we store each vertical bar that is created alongside with its properties
	//to make it easier to extract information for the info pane and for the sorting according to the second attribute
	class VertRect{

		protected Rectangle rect;
		protected String country;
		protected String attr1;
		protected String attr2;
		protected double valAttr1;
		protected double valAttr2;

		protected VertRect(Rectangle r, String c, String a1, String a2, double v1, double v2){

			rect = r;
			country = c;
			attr1 = a1;
			attr2 = a2;
			valAttr1 = v1;
			valAttr2 = v2;


		}
		
		//default constructor
		protected VertRect(){
			rect = null;
			country = "none";
			attr1 = "none";
			attr2 = "none";
			valAttr1 = 0;
			valAttr2 = 0;
		}
		
		//automatically generated getters and setters for the VertRect class
		public Rectangle getRect() {
			return rect;
		}

		public void setRect(Rectangle rect) {
			this.rect = rect;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getAttr1() {
			return attr1;
		}

		public void setAttr1(String attr1) {
			this.attr1 = attr1;
		}

		public String getAttr2() {
			return attr2;
		}

		public void setAttr2(String attr2) {
			this.attr2 = attr2;
		}

		public double getValAttr1() {
			return valAttr1;
		}

		public void setValAttr1(double valAttr1) {
			this.valAttr1 = valAttr1;
		}

		public double getValAttr2() {
			return valAttr2;
		}

		public void setValAttr2(double valAttr2) {
			this.valAttr2 = valAttr2;
		}
		
		@Override
		public String toString(){
			String s = "";
			s = s + getRect() + ", " + getCountry() + ", " + getAttr1() + ", " + getValAttr1() 
					+ ", " + getAttr2() + ", " + getValAttr2();
			return s;
		}
	}

	//automatically generated getters and setters
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


	//constructor for the canvas pane
	public CanvasPane(Model m, InfoPane i) {
		model = m;
		title = "";
		names = new String[model.getData().keySet().size()];
		
		addMouseListener(this);

		info = i;
	}
	
	//helper method for creating the country name labels
	public void createNames(int minY, int maxY){
		int k = 0;
		names = new String[1000];
		if (minY == -1 && maxY == -1)
			for(String key : model.OXOrderedCountries)
				names[k++] = key;
		else		
			for(String key : model.OXOrderedCountries)
				if(model.getData().get(key).get(selectedItem1-1)<=maxY && model.getData().get(key).get(selectedItem1-1) >=minY)
					
					names[k++] = key;
	}

	//helper method to help create the restricted hashtable when modifying the range
	public void createValues(int minY, int maxY, int minX, int maxX){
		if (selectedItem1 == -1 || selectedItem2 == -1)
			return;

		valuesForY = new ArrayList<Double>();
		valuesForX = new ArrayList<Double>();

		if (minY == maxY){
			for(String s: model.OXOrderedCountries)
				for (String key : model.getData().keySet())
					if(key.equals(s)){
						valuesForY.add(model.getData().get(key).get(selectedItem1-1));
						break;
					}
		}
		else{
			for(String s: model.OXOrderedCountries)
				for (String key : model.getData().keySet()){
					if(key.equals(s))
						if (model.getData().get(key).get(selectedItem1-1)<=maxY && model.getData().get(key).get(selectedItem1-1) >=minY)
							{valuesForY.add(model.getData().get(key).get(selectedItem1-1));
							System.out.println("added");
							
						}
						else{System.out.println(model.getData().get(key).get(selectedItem1-1));}
					else continue;
				}
		
		}

		//System.out.println(valuesY);
        // TODO asta nu prea face mare lucru pt ca nu e legat sliderul
		// fie legam si aici fie il lasam asa dar tot as vrea o sortare
		// pe axa ox, altfel e degeaba.
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
		//get the size of the component in which we draw the bar chart
		Dimension d = getSize();
		int clientWidth = d.width;
		int clientHeight = d.height;
		int barWidth = clientWidth / valuesForY.size();

		//set fonts for labels, we ended up not using this because it got too overcrowded
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
		vertBars = new ArrayList<VertRect>();
		
		for (int i = 0; i < valuesForY.size(); i++) {
			
			int valueX = i * barWidth + 1;
			int valueY = top;
			int height = (int) (valuesForY.get(i) * scale);
			//System.out.println("scale = " + scale);
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
				/*int labelWidth = labelFontMetrics.stringWidth(names[i]);
			x = i * barWidth + (barWidth - labelWidth) / 2;
			g.drawString(names[i], x, y);*/
				vertBars.add(new VertRect(r, names[i], model.getHeaders().get(selectedItem1), 
						model.getHeaders().get(selectedItem2+1), model.getData().get(names[i]).get(selectedItem1-1),
						model.getData().get(names[i]).get(selectedItem2)));

			}else if(model.getData().get(names[i]).get(model.getData().get(names[i]).size()-1)==2){
				// 2 = Africa = Black *wink wink*
				Rectangle r = new Rectangle(valueX, valueY, barWidth -2, height);
				g.setColor(Color.black);
				g.fillRect(valueX, valueY, barWidth - 2, height);
				g.drawRect(valueX, valueY, barWidth - 2, height);
				g.setColor(Color.black);
				/*int labelWidth = labelFontMetrics.stringWidth(names[i]);
			x = i * barWidth + (barWidth - labelWidth) / 2;
			g.drawString(names[i], x, y);*/
				vertBars.add(new VertRect(r, names[i], model.getHeaders().get(selectedItem1), 
						model.getHeaders().get(selectedItem2+1), model.getData().get(names[i]).get(selectedItem1-1),
						model.getData().get(names[i]).get(selectedItem2)));

			}else if(model.getData().get(names[i]).get(model.getData().get(names[i]).size()-1)==3){
				// 3 = Australia = Red
				Rectangle r = new Rectangle(valueX, valueY, barWidth -2, height);
				g.setColor(Color.red);
				g.fillRect(valueX, valueY, barWidth - 2, height);
				g.drawRect(valueX, valueY, barWidth - 2, height);
				g.setColor(Color.black);
				/*int labelWidth = labelFontMetrics.stringWidth(names[i]);
			x = i * barWidth + (barWidth - labelWidth) / 2;
			g.drawString(names[i], x, y);*/
				vertBars.add(new VertRect(r, names[i], model.getHeaders().get(selectedItem1), 
						model.getHeaders().get(selectedItem2+1), model.getData().get(names[i]).get(selectedItem1-1),
						model.getData().get(names[i]).get(selectedItem2)));

			}else if(model.getData().get(names[i]).get(model.getData().get(names[i]).size()-1)==4){
				// 4 = North America = Blue
				Rectangle r = new Rectangle(valueX, valueY, barWidth -2, height);
				g.setColor(Color.blue);
				g.fillRect(valueX, valueY, barWidth - 2, height);
				g.drawRect(valueX, valueY, barWidth - 2, height);
				g.setColor(Color.black);
				/*int labelWidth = labelFontMetrics.stringWidth(names[i]);
			x = i * barWidth + (barWidth - labelWidth) / 2;
			g.drawString(names[i], x, y);*/
				vertBars.add(new VertRect(r, names[i], model.getHeaders().get(selectedItem1), 
						model.getHeaders().get(selectedItem2+1), model.getData().get(names[i]).get(selectedItem1-1),
						model.getData().get(names[i]).get(selectedItem2)));

			}else if(model.getData().get(names[i]).get(model.getData().get(names[i]).size()-1)==5){
				// 5 = Asia = Yellow *wink wink*
				Rectangle r = new Rectangle(valueX, valueY, barWidth -2, height);
				g.setColor(Color.yellow);
				g.fillRect(valueX, valueY, barWidth - 2, height);
				g.drawRect(valueX, valueY, barWidth - 2, height);
				g.setColor(Color.black);
				/*int labelWidth = labelFontMetrics.stringWidth(names[i]);
			x = i * barWidth + (barWidth - labelWidth) / 2;
			g.drawString(names[i], x, y);*/
				vertBars.add(new VertRect(r, names[i], model.getHeaders().get(selectedItem1), 
						model.getHeaders().get(selectedItem2+1), model.getData().get(names[i]).get(selectedItem1-1),
						model.getData().get(names[i]).get(selectedItem2)));

			}else if(model.getData().get(names[i]).get(model.getData().get(names[i]).size()-1)==6){
				// 6 = South America = Magenta
				Rectangle r = new Rectangle(valueX, valueY, barWidth -2, height);
				g.setColor(Color.magenta);
				g.fillRect(valueX, valueY, barWidth - 2, height);
				g.drawRect(valueX, valueY, barWidth - 2, height);
				g.setColor(Color.black);
				/*int labelWidth = labelFontMetrics.stringWidth(names[i]);
			x = i * barWidth + (barWidth - labelWidth) / 2;
			g.drawString(names[i], x, y);*/
				vertBars.add(new VertRect(r, names[i], model.getHeaders().get(selectedItem1), 
						model.getHeaders().get(selectedItem2+1), model.getData().get(names[i]).get(selectedItem1-1),
						model.getData().get(names[i]).get(selectedItem2)));

			}
		}


		//for(VertRect v : vertBars)
		//System.out.println(v.toString());
	}

	//implement mouse event for clicking on the bar chart
	@Override
	public void mouseClicked(MouseEvent e) {
		int found = 0;
		for(VertRect v : vertBars)
			if(v.getRect().contains(e.getX(), e.getY())){
				clickedVertRect = new VertRect(v.getRect(),v.getCountry(),v.getAttr1(),v.getAttr2(),
						v.getValAttr1(),v.getValAttr2());
				found = 1;
			}
		if(found ==0)
			clickedVertRect = new VertRect();

		String s = clickedVertRect.getCountry() + "\n" +
				clickedVertRect.getAttr1() + ": " + clickedVertRect.getValAttr1() + "\n" +
				clickedVertRect.getAttr2() + ": " + clickedVertRect.getValAttr2();


		info.getInfo().setText(s);
		//System.out.println(	info.getInfo().getText());
		info.remove(info.getInfo());
		info.add(info.getInfo());


		;
		System.out.println("the x is " + e.getX() + " and the y is " + e.getY());
		System.out.println(clickedVertRect);

	}
	
	//methods that needed to be stubbed so that we could implement the mouse listener
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}





}
