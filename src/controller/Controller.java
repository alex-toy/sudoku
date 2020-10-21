package controller;


import java.util.ArrayList;

import gui.FormEvent;
import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class Controller {
	
	Database db = new Database();
	
	public ArrayList<Person> getPeople() {
		return db.getPeople();
	}
	
	public void addPerson(FormEvent e) {
		
		String name = e.getName();
		String occupation = e.getOccupation();
		int ageCatId = e.getAgeCategory();
		String empCat = e.getEmpCat();
		String gender = e.getGender();
		boolean isUs = e.isUsCitizen();
		String taxId = e.getTaxId();
		
		AgeCategory ageCategory = null;
		switch(ageCatId) {
			case 0 : ageCategory = AgeCategory.child; break;
			case 1 : ageCategory = AgeCategory.adult; break;
			case 2 : ageCategory = AgeCategory.senior; break;
		}
		
		EmploymentCategory empCategory;
		if(empCat.equals("employed")) { empCategory = EmploymentCategory.employed; }
		else if(empCat.equals("self-employed")) { empCategory = EmploymentCategory.selfEmployed; }
		else if(empCat.equals("unemployed")) { empCategory = EmploymentCategory.unemployed; }
		else { empCategory = EmploymentCategory.other; System.err.println(empCat); }
		
		Gender genderCat = null;
		if(empCat.equals("male")) { genderCat = Gender.male; }
		else if(empCat.equals("female")) { genderCat = Gender.female; }
		
		
		Person person = new Person(name, occupation, ageCategory, empCategory, isUs, taxId, genderCat);
		
		db.addPerson(person);
		
	}

}

