package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.Statement;



public class Database {
	
	private Connection con;
	//private List<Person> people;
	
	
	public Database() {
		super();
		//this.people = new ArrayList<Person>();
	}
	
	
	public void connect() throws Exception {
		if(con != null) return ;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("driver not found");
		}
		String url = "jdbc:mysql://localhost:3306/swingtest";
		con = DriverManager.getConnection(url, "root", "Colmoschin.80");
		
		System.out.println("connected to : " + con);
	}
	
	
	public void disconnect() {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("cannot close connection");
			}
		}
	}
	
	
	public void save() throws SQLException {
		
		String checkSql = "select count(*) as count from people where id = ?";
		PreparedStatement checkstmt = con.prepareStatement(checkSql);
		
		String insertSql = "insert into people (id, name, age, employment_status, tax_id, us_citizen, gender, occupation) values (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);
		
		String updateSql = "update people set name=?, age=?, employment_status=?, tax_id=?, us_citizen=?, gender=?, occupation=? where id=?";
		PreparedStatement updateStatement = con.prepareStatement(updateSql);
		
	}
	
	
	public void load() throws SQLException {
		//people.clear();
		
		String sql = "select id, name, age, employment_status, tax_id, us_citizen, gender, occupation from people order by name";
		Statement selectStatement = (Statement) con.createStatement();
		
		ResultSet results = selectStatement.executeQuery(sql);
		
		while(results.next()) {
			int id = results.getInt("id");
			String name = results.getString("name");
			String age = results.getString("age");
			String emp = results.getString("employment_status");
			String taxId = results.getString("tax_id");
			boolean isUs = results.getBoolean("us_citizen");
			String gender = results.getString("gender");
			String occ = results.getString("occupation");
			
			//Person person = new Person(id, name, occ, AgeCategory.valueOf(age), EmploymentCategory.valueOf(emp), taxId, isUs, Genderold.valueOf(gender));
			//people.add(person);
		}
		
		results.close();
		selectStatement.close();
	}
	
	
	
	
	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.close();
	}
	
	
	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		ois.close();
	}

}





















