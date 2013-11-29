package TradeViewer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

class RecordPanel
        extends JPanel
        implements ViewController {

    private ArrayList fieldLabelWidgets, fieldValueWidgets;
    private Model model;

    public RecordPanel(Model m) {
        model = m;

        /* initialise presentation */
        setBackground(Color.lightGray);
        setLayout(new GridLayout(model.record(0).size(), 2));

        /* create the text fields */
        fieldLabelWidgets = new ArrayList();
        fieldValueWidgets = new ArrayList();
        makeWidgets(model.labels());

        /* put the text fields into the panel */
        Iterator fieldLabelWidgetsElements = fieldLabelWidgets.iterator();
        Iterator fieldValueWidgetsElements = fieldValueWidgets.iterator();
        while (fieldLabelWidgetsElements.hasNext()) {
            add((JTextField) fieldLabelWidgetsElements.next());
            add((JTextField) fieldValueWidgetsElements.next());
        }
    }

    private void makeWidgets(ArrayList labels) {
        JTextField tf;

        for (int i = 0; i < labels.size(); i++) {
            tf = new JTextField((String) labels.get(i));
            tf.setEditable(false);
            fieldLabelWidgets.add(tf);
            tf = new JTextField("unselected");
            tf.setEditable(false);
            fieldValueWidgets.add(tf);
        }
    }

    public void update(int row) {
        // System.out.println("RecordPanel.update: " + row);

        ArrayList record = model.record(row);
        for (int i = 0; i < record.size(); i++) {
            ((JTextField) fieldValueWidgets.get(i)).setText((String) record.get(i));
        }
        repaint();
    }
} /* end RecordPanel */
