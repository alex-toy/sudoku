package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Person;

public class PersonTableModel extends AbstractTableModel {
	
	public ArrayList<Person> db;

	public PersonTableModel() {}
	
	
	
	public void setData(ArrayList<Person> db) {
		this.db = db;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Person person = db.get(row);
		
		switch(col) {
		case 0 : return person.getId();
		case 1 : return person.getName();
		case 2 : return person.getOccupation();
		case 3 : return person.getAgeCategory();
		case 4 : return person.getEmpCat();
		case 5 : return person.isUsCitizen();
		case 6 : return person.getTaxId();
		}
		return null;
	}

}








