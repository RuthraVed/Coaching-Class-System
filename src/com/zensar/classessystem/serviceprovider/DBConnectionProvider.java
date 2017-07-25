package com.zensar.classessystem.serviceprovider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import oracle.jdbc.OracleDriver;

import com.zensar.classessystem.exceptions.ClassesServicesNotFoundException;

public class DBConnectionProvider {
Connection con;
public static Connection getDBConnecton() throws ClassesServicesNotFoundException{
	
	try {
		Properties p=new Properties();
		p.load(new FileInputStream(".\\resources\\CoachingClassSystem.properties"));
		Class.forName(p.getProperty("driver"));
		System.out.println("Connecton");
		return DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"));
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
		throw new ClassesServicesNotFoundException("Coaching Servie Not Found!!");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new ClassesServicesNotFoundException("Coaching Servie Not Found!!");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new ClassesServicesNotFoundException("Coaching Servie Not Found!!");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new ClassesServicesNotFoundException("Coaching Servie Not Found!!");
	}
	
}
}
