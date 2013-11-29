package TradeViewer;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.StringTokenizer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 Model.java

 Basic model for tradeviewer spreadsheet data

 Based on Phil Gray's sourceDomain.java

 * @author matthew
 */
public class Model {

    private ArrayList children;  /* set of subcomponent viewControllers */

    private ArrayList labels, /* attribute names */
            types, /* attribute types (as strings) */
            dataset;            /* attribute values (ArrayList of ArrayLists */

    /* Constructor creates the main data structures and then loads data in 
     * the given file. After this, children should be null but the other 
     * ArrayLists should be filled with the initial data for analysis.
     */

    public Model(String filename) {
        children = new ArrayList();
        labels = new ArrayList();
        types = new ArrayList();
        dataset = new ArrayList();

        load(filename);
    }

    /* 
     * addChild
     * Adds a new view-controller to the set of children of this model
     */
    void addChild(ViewController vc) {
        children.add(vc);
    }


    /* selected
     * Called by any viewcontroller child when a new object has been selected
     * e.g. by mouse click. 
     * Goes through children, to update each one.
     */
    public void selected(int row) {
        if ((row < 0) | (row >= dataSize())) {
            System.err.println("TradeViewerFrame: selection out of range ("
                    + row + ")");
            return;
        }
        // System.out.println("Model.selected: " + row);

        Iterator iter = children.iterator();
        while (iter.hasNext()) {
            ViewController kid = (ViewController) iter.next();
            kid.update(row);
        }
    } // selected

    
    void load(String filename) {
        FileReader fileReader;
        BufferedReader buffer;
        String line;
        try {
            fileReader = new FileReader(new File(filename));
            buffer = new BufferedReader(fileReader);

            // read labels line
            readHeader(buffer.readLine(), labels);
            // read the types line
            readHeader(buffer.readLine(), types);
            while ((line = buffer.readLine()) != null) {
                // now read a line of data
                readALine(line);
            }
            // close the file
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found when loading graph");

        } catch (IOException e) {
            System.out.println("IO exception when loading graph");
        }
    }

    void readHeader(String line, ArrayList headers) {
        // create a tokenizer to parse the line
        StringTokenizer segmentedLine = new StringTokenizer(line, ",");
        // put the elements in the ArrayList Line
        while (segmentedLine.hasMoreTokens()) {
            headers.add(segmentedLine.nextToken());
        }
    }

    void readALine(String line) {
        // create an ArrayList to hold the elements
        ArrayList row = new ArrayList();
        // create a tokenizer to parse the line
        StringTokenizer segmentedLine = new StringTokenizer(line, ",");
        // put the elements in the ArrayList Line
        while (segmentedLine.hasMoreTokens()) {
            row.add(segmentedLine.nextToken());
        }
        // put the row into the dataset
        dataset.add(row);
    }

    /* accessing methods */
    public ArrayList labels() {
        return labels;
    }

    public ArrayList types() {
        return types;
    }

    /* return labels of fields which are reals or integers */
    public ArrayList numericFieldLabels() {
        ArrayList numericFields = new ArrayList();
        String typeElement;
        String labelElement;
        for (int i = 0; i < types.size(); i++) {
            typeElement = types.get(i).toString();

            if (typeElement.equals("REAL")) {
                numericFields.add(labels.get(i).toString());
            } else if (typeElement.equals("INT")) {
                numericFields.add(labels.get(i).toString());
            }
        }
        return numericFields;
    }

    /* return index of field with name 'fieldName' */
    public int indexOfField(String fieldName) {
        boolean found = false;
        int index = 0;
        while ((!found) && (index <= labels.size())) {
            if (labels.get(index).toString().equals(fieldName)) {
                found = true;
            } else {
                index = index + 1;
            }
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
    }

    /*
     public ArrayList data()	{
     return dataset;
     }
     */
    public int dataSize() {
        return dataset.size();
    }

    public ArrayList record(int row) {
        return (ArrayList) dataset.get(row);
    }

    /* return real value of field in field 'fieldNumber' in row 'rowNumber' */
    public double fieldAsReal(int rowNumber, int fieldNumber) {
        ArrayList rowValue = (ArrayList) dataset.get(rowNumber);
        String fieldValue = (String) rowValue.get(fieldNumber);
        Double returnVal = new Double(fieldValue);
        return returnVal.doubleValue();
    }

    /* return real value of field in field 'fieldNumber' of row 'row' */
    public double fieldAsReal(ArrayList row, int fieldNumber) {
        String fieldValue = (String) row.get(fieldNumber);
        Double returnVal = new Double(fieldValue);
        return returnVal.doubleValue();
    }

    /* return ArrayList of all the record keys */
    public ArrayList ids() {
        ArrayList row;
        ArrayList idList = new ArrayList();
        ListIterator iter = dataset.listIterator();

        while (iter.hasNext()) {
            row = (ArrayList) iter.next();
            idList.add((String) row.get(11));
        }
        return idList;
    }

    /* printing methods */
    public void printDataset() {
        ArrayList row;
        ListIterator iter = dataset.listIterator();
        System.out.println("Printout of full dataset");
        System.out.println("++++++++++++++++++++++++");
        while (iter.hasNext()) {
            row = (ArrayList) iter.next();
            printRow(row);
        }
    }

    public void printRow(ArrayList row) {
        Iterator labelIter = labels.iterator();
        Iterator rowIter = row.iterator();

        System.out.println("***************************");
        while (rowIter.hasNext()) {
            System.out.println(labelIter.next().toString() + " = "
                    + rowIter.next().toString());
            System.out.println("***************************");
        }
    }

    public void printRow(int rowNumber) {
        if (rowNumber >= dataset.size()) {
            System.out.println("Error: Model.printRow(): lllegal row number");
        } else {
            printRow((ArrayList) dataset.get(rowNumber - 1));
        }
    }
}
