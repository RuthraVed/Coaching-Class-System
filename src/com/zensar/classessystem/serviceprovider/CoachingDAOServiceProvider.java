package com.zensar.classessystem.serviceprovider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import com.zensar.classessystem.daoservice.CoachingClassDaoServices;
import com.zensar.classessystem.daoservice.CoachingClassDaoServicesImpl;
import com.zensar.classessystem.exceptions.ClassesServicesNotFoundException;
import com.zensar.classessystem.services.ClassesServices;
import com.zensar.classessystem.services.ClassesServicesImpl;


public class CoachingDAOServiceProvider {
	Connection con;
	public static CoachingClassDaoServices getDAOService() throws ClassesServicesNotFoundException{
		Properties p=new Properties();
		try {
			p.load(new FileInputStream(".\\resources\\CoachingClassSystem.properties"));
			return (CoachingClassDaoServicesImpl)Class.forName(p.getProperty("coachingclassdaoserviceimpl")).newInstance();
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
