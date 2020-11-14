package gui;

//import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class PersonTableModel  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private List<Person> db;
	private String[] colNames = {"id", "names", "occupation", "age cat", "emp cat", "us citizen", "tax id"};

	public PersonTableModel() {}
		
	
	
	public String getColumnName(int column) {
		return colNames[column];
	}


	
	public int getColumnCount() {
		return 7;
	}

	

}








