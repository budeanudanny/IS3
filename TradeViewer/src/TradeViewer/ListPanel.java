package TradeViewer;

/*
 * Presents a list of labels for the records in the model. 
 * 
 * Stub/draft version
 * No updates based on the model are currently implemented
 * A mouse click on a list item is caught and the index of the selected item
 * is output... but no model selection is yet done.
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

class ListPanel 
extends JPanel 
implements ViewController {
  private JList listWidget;
  private Model model;

  public ListPanel(Model m)	{
    this.model = m;
    /* initialise presentation */
    setBackground(Color.lightGray);
    setLayout(new GridLayout(1,1));

    /* create ArrayList of record labels */
    ArrayList idList = model.ids();

    /* create list widget and its listener */
    listWidget = new JList(idList.toArray());
    ListPanelListener listener = new ListPanelListener();
    listWidget.addMouseListener(listener);
    
    add(listWidget); /* add component */
  }


  /* Stub, to conform with ViewController. */
  public void update (int row) {
    /* to be completed later... */
    // System.out.println("ListPanel.update: " + row);

  }
    
  /*
    This is an example of an inner class. It uses the variable 'listWidget'
    of the outer class.
  */
  private class ListPanelListener extends MouseAdapter {
    public void mouseClicked(MouseEvent e) {
      int row = listWidget.locationToIndex(e.getPoint());
      if (e.getClickCount() == 2) {
	System.out.println("Double clicked on Row " + row);
      }
      // System.out.println("ListPanel.ListPanelListener: " + row);

      model.selected(row);
    }
  }
} /* end ListPanel */
