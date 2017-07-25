package com.zensar.classessystem.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.zensar.classessystem.beans.Address;
import com.zensar.classessystem.beans.Course;
import com.zensar.classessystem.beans.InstallmentDetail;
import com.zensar.classessystem.beans.Student;
import com.zensar.classessystem.beans.StudentAccount;
import com.zensar.classessystem.daoservice.CoachingClassDaoServices;
import com.zensar.classessystem.exceptions.ClassesServicesNotFoundException;
import com.zensar.classessystem.exceptions.CourseDetailsNotFoundException;
import com.zensar.classessystem.exceptions.InvalidEnrollMentNoException;
import com.zensar.classessystem.exceptions.InvalidModeOfPaymentTypeException;
import com.zensar.classessystem.exceptions.InvalidStudentIdException;
import com.zensar.classessystem.exceptions.LessPaymentAmountException;
import com.zensar.classessystem.exceptions.StudentDetailsNotFoundException;
import com.zensar.classessystem.exceptions.StudentEnrollMentDetailsNotFoundException;
import com.zensar.classessystem.serviceprovider.CoachingDAOServiceProvider;

public class ClassesServicesImpl implements ClassesServices {
	CoachingClassDaoServices daoServices;
	public ClassesServicesImpl() throws ClassesServicesNotFoundException {
		daoServices=CoachingDAOServiceProvider.getDAOService();
	}
	
	@Override
	public ArrayList<Course> getAllCoursesDetails()
			throws ClassesServicesNotFoundException, CourseDetailsNotFoundException {
		try {if(daoServices.getAllCoursesDetails()==null) throw new CourseDetailsNotFoundException("Course Details Not Availble");
			daoServices.getAllCoursesDetails();
		}catch (SQLException e) {throw new ClassesServicesNotFoundException("Classes Services Not Available");	}
		return null;
	}

	@Override
	public int acceptStudentDetails(String studentName, String mobileNo,
			String emailId, String city, String state, String country,int pincode)
					throws ClassesServicesNotFoundException {
		try {
			daoServices.insertStudentDetails(new Student(studentName, mobileNo, emailId, new Address(city, state, country, pincode)));
		} catch (SQLException e) {throw new ClassesServicesNotFoundException("Classes Services Not Available");}
		return 0;
	}

	@Override
	public int enrollStudentForCourse(int studentId, String courseCode,
			String modeOfPayment) throws InvalidStudentIdException,
			CourseDetailsNotFoundException, StudentDetailsNotFoundException,
			InvalidModeOfPaymentTypeException, ClassesServicesNotFoundException {
		try {if(studentId<100)throw new InvalidStudentIdException("Student Id must be Above 100");
		if(this.getStudentDetails(studentId)==null) throw new StudentDetailsNotFoundException("Student Detail Not Found!!");
		if(!modeOfPayment.equalsIgnoreCase("lumsum")|| !modeOfPayment.equalsIgnoreCase("installment")) throw new InvalidModeOfPaymentTypeException("Invalid Mode of Payment");
		if(modeOfPayment.equalsIgnoreCase("lumsum")){
		int feeafterdiscount=(int) ((daoServices.getCourse(courseCode).getFees())*0.10);
		int feeRemain=feeafterdiscount;
		daoServices.enrollStudentForCourse(studentId, courseCode,new StudentAccount(modeOfPayment, feeafterdiscount, feeRemain));
		}
		if(modeOfPayment.equalsIgnoreCase("installment")){
			int feeafterdiscount=daoServices.getCourse(courseCode).getFees();
			int feeRemain=feeafterdiscount;
			daoServices.enrollStudentForCourse(studentId, courseCode,new StudentAccount(modeOfPayment, feeafterdiscount, feeRemain));
			}
		} catch (SQLException e) {throw new ClassesServicesNotFoundException("Classes Services Not Available");}

		return 0;
	}

	@Override
	public int acceptPayment(int enrollmentno,int studentId, String courseCode, int amount)
			throws InvalidStudentIdException, CourseDetailsNotFoundException,
			StudentDetailsNotFoundException, ClassesServicesNotFoundException, InvalidEnrollMentNoException, StudentEnrollMentDetailsNotFoundException, LessPaymentAmountException {
		try {
			if(enrollmentno<1000) throw new InvalidEnrollMentNoException("Invalid Enrollment Number");
			if(this.getStudentAccountDetails(studentId, enrollmentno)==null) throw new StudentEnrollMentDetailsNotFoundException("Student Enrollment Details Not Found");
			if(this.getStudentDetails(studentId)==null)throw new StudentDetailsNotFoundException("Student Details Not Found");
			if(daoServices.getCourse(courseCode)==null) throw new CourseDetailsNotFoundException("Course Detail not found");
			if(this.getStudentAccountDetails(studentId, enrollmentno).getFeesRemaining()<amount) throw new LessPaymentAmountException("Payment less then lumsum amount");
			if(this.getStudentAccountDetails(studentId, enrollmentno).getModeOfPayment().equalsIgnoreCase("lumsum")){
			if(this.getStudentAccountDetails(studentId, enrollmentno).getFeesRemaining()==amount)
				this.getStudentAccountDetails(studentId, enrollmentno).setFeesRemaining(0);
				daoServices.updateStudentAccount(new StudentAccount(enrollmentno, "lumsum", amount, 0));
				return daoServices.insertPayment(enrollmentno,studentId, new InstallmentDetail(amount,new Date()));
			}
			else if(this.getStudentAccountDetails(studentId, enrollmentno).getModeOfPayment().equalsIgnoreCase("installment")){
				int feeafterdiscount=this.getStudentAccountDetails(studentId, enrollmentno).getFeesRemaining();
				int remainfee=this.getStudentAccountDetails(studentId, enrollmentno).getFeesRemaining()-amount;
				daoServices.updateStudentAccount(new StudentAccount(enrollmentno, "insallment", feeafterdiscount, remainfee));
				return daoServices.insertPayment(enrollmentno,studentId, new InstallmentDetail(amount,new Date()));
			}
		} catch (SQLException e) {throw new ClassesServicesNotFoundException("Classes Services Not Available");}
		return 0;
	}

	@Override
	public int getRemainingFeesAmount(int studentId)
			throws InvalidStudentIdException, StudentDetailsNotFoundException,
			ClassesServicesNotFoundException {
		try {this.getStudentDetails(studentId);
			return daoServices.getRemainingFeesAmount(studentId);
		} catch (SQLException e) {throw new ClassesServicesNotFoundException("Classes Services Not Available");}

	}

	@Override
	public StudentAccount getStudentAccountDetails(int studentId,
			int enrollmentNo) throws InvalidStudentIdException,
			StudentDetailsNotFoundException, InvalidEnrollMentNoException,
			StudentEnrollMentDetailsNotFoundException,
			ClassesServicesNotFoundException {
		try {
			this.getStudentDetails(studentId);
			if(enrollmentNo<1000) throw new StudentEnrollMentDetailsNotFoundException("Student Enrollment Details Not Found");
			if(daoServices.getStudentAccountDetails(studentId, enrollmentNo)==null) throw new StudentEnrollMentDetailsNotFoundException("Student Enrollment Details Not Found!!");
			return daoServices.getStudentAccountDetails(studentId, enrollmentNo);
		} catch (SQLException e) {throw new ClassesServicesNotFoundException("Classes Services Not Available");}
	}

	@Override
	public ArrayList<StudentAccount> getStudentAllAccountDetails(int studentId)
			throws InvalidStudentIdException, StudentDetailsNotFoundException,
			ClassesServicesNotFoundException {
		try {
			this.getStudentDetails(studentId);
			return daoServices.getStudentAllAccountDetails(studentId);
		} catch (SQLException e) {throw new ClassesServicesNotFoundException("Classes Services Not Available");}
	}

	@Override
	public Student getStudentDetails(int studentId)
			throws InvalidStudentIdException, StudentDetailsNotFoundException,
			ClassesServicesNotFoundException {
		try {
			if(studentId<100) throw new InvalidStudentIdException("Student Id is Invalid");
			if(daoServices.getStudentDetails(studentId)==null) throw new StudentDetailsNotFoundException("Student Detail Not Found");
			return daoServices.getStudentDetails(studentId);
		} catch (SQLException e) {throw new ClassesServicesNotFoundException("Classes Services Not Available");}
	}

	@Override
	public ArrayList<Student> getAllStudentsDetails()
			throws ClassesServicesNotFoundException {
		try {
			daoServices.getAllStudentsDetails();
		} catch (SQLException e) {
			throw new ClassesServicesNotFoundException("Classes Services Not Available");
		} catch (InvalidStudentIdException e) {
			throw new ClassesServicesNotFoundException("Classes Services Not Available");
		} catch (StudentDetailsNotFoundException e) {
			e.printStackTrace();
			throw new ClassesServicesNotFoundException("Classes Services Not Available");
		}
		return null;
	}

	@Override
	public ArrayList<Student> getCourseWiseAllStudentDetails(String courseCode)
			throws ClassesServicesNotFoundException,
			CourseDetailsNotFoundException {
		try {
			
			daoServices.getCourseWiseAllStudentDetails(courseCode);
		} catch (SQLException e) {throw new ClassesServicesNotFoundException("Classes Services Not Available");}
		return null;

	}
}
