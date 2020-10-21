package model;

import java.util.ArrayList;

public class Database {
	
	private ArrayList<Person> people;
	
	public void addPerson(Person person) {
		people.add(person);
	}

	
	public Database() {
		super();
		this.people = new ArrayList<Person>();
	}
	
	public ArrayList<Person> getPeople() {
		return people;
	}


	public void setPeople(ArrayList<Person> people) {
		this.people = people;
	}

}
