package TradeViewer;

/*
  AxisListPanel.java
*/

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.*;

class AxisListPanel extends JPanel {
	 private ScatterplotPanel parent;
	 private JList xList, yList;

	 public AxisListPanel(ScatterplotPanel p, ArrayList v) {
		  parent = p;
		  setLayout(new GridLayout(2,1));
		  xList = new JList(v.toArray());
		  yList = new JList(v.toArray());

		  ListListener listener = new ListListener();
		  xList.addListSelectionListener(listener);
		  yList.addListSelectionListener(listener);

		  add(xList);
		  add(yList);
  }

	 // inner class definition -- can use the variables of AxisListPanel

	 /* Called whenever the value of a list selection changes */
	 private class ListListener implements ListSelectionListener {
		  public void valueChanged (ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					 JList l =(JList) e.getSource();
					 Object value = l.getSelectedValue();
					 if (value != null) {
						  if (l == xList) {
								System.out.println ("X: " + (String) value);
								parent.updateXAxis ((String) value);
						  }
						  else { // yList
								System.out.println ("Y: " + (String) value);
								parent.updateYAxis ((String) value);
						  }
					 }
				}
		  } // end of ValueChanged
	 } // end of ListListener

}
