package database;

import java.sql.SQLException;

//import model.AgeCategory;
import model.Database;
//import model.EmploymentCategory;
//import model.Genderold;
//import model.Person;

public class TestDatabase {

	public static void main(String[] args) {
		
		System.out.println("running database test");
		Database db = new Database();
		
		try {
			db.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//db.addPerson(new Person("alex", "MLE", AgeCategory.adult, EmploymentCategory.employed, true, "777", Genderold.male));
		//db.addPerson(new Person("anaele", "boss", AgeCategory.senior, EmploymentCategory.employed, true, "777", Genderold.female));
		
		try {
			db.save();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			db.load();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.disconnect();

	}

}
