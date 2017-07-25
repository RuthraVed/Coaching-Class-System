package com.zensar.classessystem.daoservice;

import java.sql.SQLException;
import java.util.ArrayList;

import com.zensar.classessystem.beans.Course;
import com.zensar.classessystem.beans.InstallmentDetail;
import com.zensar.classessystem.beans.Student;
import com.zensar.classessystem.beans.StudentAccount;
import com.zensar.classessystem.exceptions.ClassesServicesNotFoundException;
import com.zensar.classessystem.exceptions.CourseDetailsNotFoundException;
import com.zensar.classessystem.exceptions.InvalidEnrollMentNoException;
import com.zensar.classessystem.exceptions.InvalidModeOfPaymentTypeException;
import com.zensar.classessystem.exceptions.InvalidStudentIdException;
import com.zensar.classessystem.exceptions.StudentDetailsNotFoundException;
import com.zensar.classessystem.exceptions.StudentEnrollMentDetailsNotFoundException;

public interface CoachingClassDaoServices {
	ArrayList<Course>  getAllCoursesDetails()throws ClassesServicesNotFoundException, SQLException;

	int insertStudentDetails(Student student)
			throws ClassesServicesNotFoundException, SQLException;

	// enrollNo return  amount return
	int enrollStudentForCourse(int studentId,String courseCode,StudentAccount account )
			throws InvalidStudentIdException,CourseDetailsNotFoundException,
			StudentDetailsNotFoundException,InvalidModeOfPaymentTypeException ,ClassesServicesNotFoundException, SQLException;

	//return recepitno
	int insertPayment(int studentId, int enrollmentno, InstallmentDetail detail)
			throws InvalidStudentIdException, CourseDetailsNotFoundException,
			StudentDetailsNotFoundException, ClassesServicesNotFoundException,
			SQLException;

	int getRemainingFeesAmount(int studentId)
			throws InvalidStudentIdException,StudentDetailsNotFoundException,ClassesServicesNotFoundException, SQLException;

	StudentAccount getStudentAccountDetails(int studentId, int enrollmentNo)
			throws InvalidStudentIdException,StudentDetailsNotFoundException, InvalidEnrollMentNoException,
			StudentEnrollMentDetailsNotFoundException,ClassesServicesNotFoundException, SQLException;
	
	ArrayList<StudentAccount>getStudentAllAccountDetails(int studentId)
			throws InvalidStudentIdException,StudentDetailsNotFoundException,ClassesServicesNotFoundException, SQLException;

	Student getStudentDetails(int studentId )
			throws StudentDetailsNotFoundException,ClassesServicesNotFoundException, InvalidStudentIdException, SQLException;

	ArrayList<Student> getAllStudentsDetails()
			throws ClassesServicesNotFoundException, SQLException, InvalidStudentIdException, StudentDetailsNotFoundException;

	ArrayList<Student>getCourseWiseAllStudentDetails(String courseCode)
			throws ClassesServicesNotFoundException,CourseDetailsNotFoundException, SQLException;
	Course getCourse(String coursecode) throws SQLException;
	public boolean updateStudentAccount(StudentAccount account) throws SQLException;
	


}
