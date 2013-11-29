package TradeViewer;

/*
 ScatterplotPanel.java
 Instrumented scatterplot for displaying financial data
 */
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;  
import java.awt.event.*;
import java.util.*;

class ScatterplotPanel
        extends JPanel
        implements ViewController {

    private Model model;
    private ScatterplotView scatterplotView;
    private JSlider xScaleSlider, yScaleSlider;
    private JButton leftPan, rightPan, upPan, downPan;
    private int xAxisIndex = 0, yAxisIndex = 1;

    public ScatterplotPanel(Model m) {
        this.model = m;
        final int ZOOM_MIN = 0;
        final int ZOOM_MAX = 100;
        final int ZOOM_INIT = 5;    //initial frames per second
       // double scale = 5.0; 	/* first set an initial scale */

        /* Create and add textfields to edit the scale for X and Y */
        xScaleSlider = new JSlider(JSlider.HORIZONTAL,ZOOM_MIN, ZOOM_MAX, ZOOM_INIT);
        yScaleSlider = new JSlider(JSlider.HORIZONTAL,ZOOM_MIN, ZOOM_MAX, ZOOM_INIT);

        xScaleSlider.setMajorTickSpacing(50);
        xScaleSlider.setMinorTickSpacing(5);
        xScaleSlider.setPaintTicks(true);
        xScaleSlider.setPaintLabels(true);
        yScaleSlider.setMajorTickSpacing(50);
        yScaleSlider.setMinorTickSpacing(5);
        yScaleSlider.setPaintTicks(true);
        yScaleSlider.setPaintLabels(true);
       
        
        /* Set up listeners for when someone changes the zoom */
        xScaleSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	    double scaleVal;
             		int input = (int)xScaleSlider.getValue();             
                    scaleVal = new Double(input).doubleValue();
                    scatterplotView.setXScale(scaleVal);           	
             		}  
            });

        yScaleSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	    double scaleVal;
             		int input = (int)yScaleSlider.getValue();             
                    scaleVal = new Double(input).doubleValue();
                    scatterplotView.setYScale(scaleVal);             	
             		}  
            });

        
        /* Create and add buttons to pan the scatterplot */
        leftPan=new JButton("LEFT");
        rightPan=new JButton("RIGHT");
        upPan=new JButton("UP");
        downPan=new JButton("DOWN");
        
        
        leftPan.addActionListener(new ActionListener() {
       	 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the button");
            }
        });  
        
        
        
        JPanel scaleFieldPanel = new JPanel(/*new BorderLayout()*/);
        scaleFieldPanel.add(xScaleSlider);
        scaleFieldPanel.add(yScaleSlider);
        scaleFieldPanel.add(leftPan);
        scaleFieldPanel.add(rightPan);
        scaleFieldPanel.add(upPan);
        scaleFieldPanel.add(downPan);
        
        

        /* Create the 2d view and load it with data */
        scatterplotView = new ScatterplotView(model, ZOOM_INIT);

        /* Set up a layout manager to handle this panel's subcomponents */
        setLayout(new BorderLayout());

        add("North", scaleFieldPanel);
        add("Center", scatterplotView);
        add("East", new AxisListPanel(this, model.numericFieldLabels()));
    }

    /* 
     Fits with the ViewController interface.
     Updates a single point in the scatterplot based on row-th 
     element of the source domain. 
     */
    public void update(int row) {
        // System.out.println("ScatterplotPanel.update " + row);

        scatterplotView.update(row);  /* pass it down to the scatterplotView */
    }

    // usually called from AxisListPanel 
    public void updateXAxis(String itemLabel) {
        // get index of the field called itemLabel from the model
        xAxisIndex = model.indexOfField(itemLabel);

        // update the scatterplot based on the x and y field indices
        scatterplotView.loadData(xAxisIndex, yAxisIndex);
    }

    public void updateYAxis(String itemLabel) {
        // get index of the field called itemLabel from the source domain
        yAxisIndex = model.indexOfField(itemLabel);

        // update the scatterplot based on the x and y field indices
        scatterplotView.loadData(xAxisIndex, yAxisIndex);
    }
} /* end ScatterplotPanel */
