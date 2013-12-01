import java.util.ArrayList;


public class Model {

	private ArrayList children;
	private String file;
	
	public Model(String filename){
		file=filename;
	}
	
	public void addChildren(ViewController v){
		children.add(v);
	}
}
