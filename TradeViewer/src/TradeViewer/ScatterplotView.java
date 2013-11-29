package TradeViewer;

/*
 ScatterplotView.java

 Scatterplot for displaying financial data
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

class ScatterplotView extends JPanel {

    private Point axisOrigin;
    private int width, height;
    private double xScale, yScale;
    private double cacheX[], cacheY[];
    private int selected;
    private Model model;

    public ScatterplotView(Model model, double scale) {
        setSize(new Dimension(100, 100));
        // initialise origin to midpoint of panel
        axisOrigin = new Point();
        // initialise scales
        xScale = scale;
        yScale = scale;
        // initialise data
        this.model = model;
        int dataSize = model.dataSize();
        cacheX = new double[dataSize];
        cacheY = new double[dataSize];
        loadData(0, 1);
        selected = -1;
        addMouseListener(new MouseButtonHandler());
    }

    class MouseButtonHandler extends MouseAdapter {

        public void mouseReleased(MouseEvent me) {
            int row = dotSelected(new Point(me.getX(), me.getY()));
            // System.out.println("ScatterplotView.MouseButtonHandler: " + row);
            if (row >= 0) {
                model.selected(row);
            }
        }
    }

    int dotSelected(Point p) {
        int found = -1;
        int max = model.dataSize();
        int i = 0;
        while ((i < max) && (found == -1)) {
            if ((Math.abs(p.getX() - (axisOrigin.getX()
                    + (int) (cacheX[i] * xScale))) <= 2)
                    & (Math.abs(p.getY() - (axisOrigin.getY()
                    - (int) (cacheY[i] * yScale))) <= 2)) {
                found = i;
            } else {
                i++;
            }
        }
        return found;
    } // dotSelected

    public void paint(Graphics g) {
        double x, y;
        // get panel dimensions
        height = getSize().height;
        width = getSize().width;
        // fix nasty redraw bug
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, width, height);
        // set axis to center
        axisOrigin.setX(width / 2);
        axisOrigin.setY(height / 2);
        // draw the axes
        g.setColor(Color.blue);
        g.drawLine(0, axisOrigin.getY(), width, axisOrigin.getY());
        g.drawLine(axisOrigin.getX(), 0, axisOrigin.getX(), height);
        // draw all the elements except the selected one
        for (int i = 0; i < model.dataSize(); i++) {
            if (i != selected) {
                g.setColor(Color.red);
                g.fillOval(axisOrigin.getX() + (int) (cacheX[i] * xScale) - 2,
                        axisOrigin.getY() - (int) (cacheY[i] * yScale) - 2,
                        4, 4);
            }
        }
        // do selected item last so it isn't obscured
        if (selected >= 0) {
            g.setColor(Color.green);
            g.fillOval(axisOrigin.getX() + (int) (cacheX[selected] * xScale) - 2,
                    axisOrigin.getY() - (int) (cacheY[selected] * yScale) - 2,
                    4, 4);
        }
    }

    void loadData(int xAxisField, int yAxisField) {
        // construct a scatterplot mapping data in field with index xAxisField
        // to the x axis and data in field yAxisField to the y axis
        for (int i = 0; i < model.dataSize(); i++) {
            cacheX[i] = model.fieldAsReal(i, xAxisField);
            cacheY[i] = model.fieldAsReal(i, yAxisField);
        }
        repaint();
    }

    // updating methods
    public void itemSelected(int pos) {
        selected = pos;
        repaint();
    }

    public void update(int pos) {
        // System.out.println("ScatterPlotView.update: " + pos);
        selected = pos;
        repaint();
    }

    public void setXScale(double val) {
        xScale = val;
        repaint();
    }

    public void setYScale(double val) {
        yScale = val;
        repaint();
    }
} // end ScatterplotPanel
