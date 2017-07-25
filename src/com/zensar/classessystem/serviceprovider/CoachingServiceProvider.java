package com.zensar.classessystem.serviceprovider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import com.zensar.classessystem.exceptions.ClassesServicesNotFoundException;
import com.zensar.classessystem.services.ClassesServices;
import com.zensar.classessystem.services.ClassesServicesImpl;

public class CoachingServiceProvider {
	Connection con;
	public static ClassesServices getClassService() throws ClassesServicesNotFoundException{
		Properties p=new Properties();
		try {
			p.load(new FileInputStream(".\\resources\\CoachingClassSystem.properties"));
			return (ClassesServicesImpl)Class.forName(p.getProperty("coachingclassserviceimpl")).newInstance();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ClassesServicesNotFoundException("Coaching Servie Not Found!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ClassesServicesNotFoundException("Coaching Servie Not Found!!");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ClassesServicesNotFoundException("Coaching Servie Not Found!!");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ClassesServicesNotFoundException("Coaching Servie Not Found!!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ClassesServicesNotFoundException("Coaching Servie Not Found!!");
		}
		
		
	}

}
