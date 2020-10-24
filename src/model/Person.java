package model;

import java.io.Serializable;

public class Person implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4643266560861003980L;

	private static int count = 1;
	
	private int id;
	private String name;
	private String occupation;
	private AgeCategory ageCategory;
	private EmploymentCategory empCat;
	private boolean usCitizen;
	private String taxId;
	private Gender gender;
	
	public Person(String name, String occupation, AgeCategory ageCategory, EmploymentCategory empCat, boolean usCitizen,
			String taxId, Gender gender) {
		super();
		
		this.id = count;
		count++;
		
		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCategory;
		this.empCat = empCat;
		this.usCitizen = usCitizen;
		this.taxId = taxId;
		this.gender = gender;
	}
	public int getId() {
		return id;
	}
	public void setEmpCat(EmploymentCategory empCat) {
		this.empCat = empCat;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public EmploymentCategory getEmpCat() {
		return empCat;
	}
	public void setEmpCat1(EmploymentCategory empCat) {
		this.empCat = empCat;
	}
	public boolean isUsCitizen() {
		return usCitizen;
	}
	public void setUsCitizen(boolean usCitizen) {
		this.usCitizen = usCitizen;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public AgeCategory getAgeCategory() {
		return ageCategory;
	}
	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}

}
