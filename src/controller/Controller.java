package controller;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;

import gui.FormEvent;
//import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
//import model.Genderold;
//import model.Person;

public class Controller {
	
	Database db = new Database();
	
	
	public void save() throws SQLException {
		db.save();
	}
	
	public void load() throws SQLException {
		db.load();
	}
	
	public void connect() throws Exception {
		db.connect();
	}
	
	public void disconnect() {
		db.disconnect();
	}
	
	
	public void addPerson(FormEvent e) {
		
		String name = e.getName();
		String occupation = e.getOccupation();
		int ageCatId = e.getAgeCategory();
		String empCat = e.getEmpCat();
		String gender = e.getGender();
		boolean isUs = e.isUsCitizen();
		String taxId = e.getTaxId();
		
		
		EmploymentCategory empCategory;
		if(empCat.equals("employed")) { empCategory = EmploymentCategory.employed; }
		else if(empCat.equals("self-employed")) { empCategory = EmploymentCategory.selfEmployed; }
		else if(empCat.equals("unemployed")) { empCategory = EmploymentCategory.unemployed; }
		else { empCategory = EmploymentCategory.other; System.err.println(empCat); }
		
	}
	
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}

}

