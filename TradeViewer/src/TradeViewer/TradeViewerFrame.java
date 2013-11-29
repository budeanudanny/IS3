package TradeViewer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class TradeViewerFrame extends JFrame {

    private ListPanel listPanel;
    private ScatterplotPanel scatterplotPanel;
    private RecordPanel recordPanel;
    private Model model;
    
    public TradeViewerFrame(String filename) {
        model = new Model(filename);

        addWindowListener(new WindowCloser());

        // add the main Swing components
        listPanel = new ListPanel(model);
        model.addChild(listPanel);
        recordPanel = new RecordPanel(model);
        model.addChild(recordPanel);
        scatterplotPanel = new ScatterplotPanel(model);
        model.addChild(scatterplotPanel);

        // prep component layout
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add("West", listPanel);
        contentPane.add("South", recordPanel);
        contentPane.add("Center", scatterplotPanel);

        final int DEFAULT_FRAME_WIDTH = 900;
        final int DEFAULT_FRAME_HEIGHT = 700;

        setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        setTitle("Trade Viewer");
        setVisible(true);

    }

    private class WindowCloser extends WindowAdapter {

        public void windowClosing(WindowEvent event) {
            System.exit(0); // normally left out
           // I don't want the system to exit when one of the models 
        	// is closed, because the user might just want to open
        	// a new spreadsheet
        }
    }
} /* end TradeViewerFrame */
