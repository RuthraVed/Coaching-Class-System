package com.zensar.classessystem.services;

import java.util.ArrayList;

import com.zensar.classessystem.beans.Course;
import com.zensar.classessystem.beans.Student;
import com.zensar.classessystem.beans.StudentAccount;
import com.zensar.classessystem.exceptions.ClassesServicesNotFoundException;
import com.zensar.classessystem.exceptions.CourseDetailsNotFoundException;
import com.zensar.classessystem.exceptions.InvalidEnrollMentNoException;
import com.zensar.classessystem.exceptions.InvalidModeOfPaymentTypeException;
import com.zensar.classessystem.exceptions.InvalidStudentIdException;
import com.zensar.classessystem.exceptions.LessPaymentAmountException;
import com.zensar.classessystem.exceptions.StudentDetailsNotFoundException;
import com.zensar.classessystem.exceptions.StudentEnrollMentDetailsNotFoundException;

public interface ClassesServices {
	ArrayList<Course>  getAllCoursesDetails()throws ClassesServicesNotFoundException, CourseDetailsNotFoundException;

	int acceptStudentDetails(String studentName, String mobileNo, String emailId,String city, String state, String country,int pincode)
			throws ClassesServicesNotFoundException;

	// enrollNo return  amount return
	int enrollStudentForCourse(int studentId,String courseCode,String modeOfPayment )
			throws InvalidStudentIdException,CourseDetailsNotFoundException,
			StudentDetailsNotFoundException,InvalidModeOfPaymentTypeException ,ClassesServicesNotFoundException;

	//return recepitno
	int acceptPayment(int studentId,int enrollmentno,String courseCode,int amount)
			throws InvalidStudentIdException,CourseDetailsNotFoundException,
			StudentDetailsNotFoundException,ClassesServicesNotFoundException, InvalidEnrollMentNoException, StudentEnrollMentDetailsNotFoundException, LessPaymentAmountException;

	int getRemainingFeesAmount(int studentId)
			throws InvalidStudentIdException,StudentDetailsNotFoundException,ClassesServicesNotFoundException;

	StudentAccount getStudentAccountDetails(int studentId, int enrollmentNo)
			throws InvalidStudentIdException,StudentDetailsNotFoundException, InvalidEnrollMentNoException,
			StudentEnrollMentDetailsNotFoundException,ClassesServicesNotFoundException;
	
	ArrayList<StudentAccount>getStudentAllAccountDetails(int studentId)
			throws InvalidStudentIdException,StudentDetailsNotFoundException,ClassesServicesNotFoundException;

	Student getStudentDetails(int studentId )
			throws InvalidStudentIdException,StudentDetailsNotFoundException,ClassesServicesNotFoundException;

	ArrayList<Student> getAllStudentsDetails()
			throws ClassesServicesNotFoundException;

	ArrayList<Student>getCourseWiseAllStudentDetails(String courseCode)
			throws ClassesServicesNotFoundException,CourseDetailsNotFoundException;
}
