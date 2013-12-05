import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Model {

	private int selectedYItem;
	//private ArrayList children;
	private String file;
	protected Hashtable<String, ArrayList<Double>> data = new Hashtable<String, ArrayList<Double>>();
	protected ArrayList<String> headers = new ArrayList<String>();
	protected Hashtable<String, ArrayList<Double>> restrictedData = new Hashtable<String, ArrayList<Double>>();
	protected ArrayList<String> OXOrderedCountries ;
	
	public Model(String filename){
		file=filename;
		loadFile(file);
	}
	
	//sort the vertical bars after the second combo box
	protected void createOXOrderedCountries(int index){
		TreeSet<Double> ts = new TreeSet<Double>();
		OXOrderedCountries = new ArrayList<String>();
		for (String key : data.keySet()){
			ts.add( data.get(key).get(index) );
		}
		Iterator<Double> it = ts.iterator();
		while(it.hasNext()){
			double d = it.next();
			for(String key: data.keySet()){
				if(data.get(key).get(index) == d){
					OXOrderedCountries.add(key);
				}	
			}
		}
		System.out.println(OXOrderedCountries);
	}
	
	/*
	public void addChildren(ViewController v){
		children.add(v);
	}
*/
	//helper method to create the headers arrayList
	protected void readHeader(String line, ArrayList headers) {
	        // create a tokenizer to parse the line
	        StringTokenizer segmentedLine = new StringTokenizer(line, ",");
	        // put the elements in the ArrayList Line
	        while (segmentedLine.hasMoreTokens()) {
	            headers.add(segmentedLine.nextToken());
	        }
	    }
	
	//helper method to assign keys and values to the hashtable
	protected void assignValuesToKey(String line, Hashtable<String, ArrayList<Double>> ht) {
	        // create an ArrayList to hold the elements
	        ArrayList<String> row = new ArrayList<String>();
	        // create a tokenizer to parse the line
	        StringTokenizer segmentedLine = new StringTokenizer(line, ",");
	        // put the elements in the ArrayList Line
	        while (segmentedLine.hasMoreTokens()) {
	            row.add(segmentedLine.nextToken());
	        }
	        String key = row.get(0);
	        //System.out.println(key);
	        row.remove(0); //because the first element will be the country/key
	        //System.out.println(row);
	        ArrayList<Double> values = new ArrayList<Double>();
	        for(String s : row){
	        	Double aux = Double.parseDouble(s); //parse the remaining entries in row to double and 
	        	// add them to values
	        	values.add(aux);
	        }
	        // put the row into the dataset
	        ht.put(key, values);
	}
	
	//load file and populate hashtable
	protected void loadFile(String fileName){
		 
		 FileReader fileReader;
	     BufferedReader buffer;
	     String line;
	     try {
	            fileReader = new FileReader(new File(fileName));
	            buffer = new BufferedReader(fileReader);

	            // read labels line
	            readHeader(buffer.readLine(), headers);
	            
	            while ((line = buffer.readLine()) != null) {
	                // now read a line of data
	                assignValuesToKey(line, data);
	            }
	            buffer.close();
	            // close the file
	            fileReader.close();
	     } catch (FileNotFoundException e) {
	            System.out.println("File not found!");

	     } catch (IOException e) {
	            System.out.println("IO exception!");
	     }
	 }

	//self generated getters and setters
	public Hashtable<String, ArrayList<Double>> getData() {
		return data;
	}
	
	public Hashtable<String, ArrayList<Double>> getRestData() {
		return restrictedData;
	}

	public void setSelectedYItem(int i){
		selectedYItem = i;
	}

	//helper method for updating the restricted hashtable
	public void updateRestData(int min, int max){
		restrictedData = new Hashtable<String, ArrayList<Double>>();
		for (String key : data.keySet()){
			double d = data.get(key).get(selectedYItem+1);
			if (d < max && d > min)
				restrictedData.put(key,data.get(key));
		}
		//System.out.println(restrictedData.toString());
		
		
		
	}
	
	//getters and setters
	public void setData(Hashtable<String, ArrayList<Double>> data) {
		this.data = data;
	}

	public ArrayList<String> getHeaders() {
		return headers;
	}

	public void setHeaders(ArrayList<String> headers) {
		this.headers = headers;
	}	
	
}
